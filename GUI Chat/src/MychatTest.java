
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Date;

public class MychatTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new MyChat();
    }

}

class MyChat {
    private Frame frame;

    private TextArea display, dataInput;

    private TextField ipInput;

    private Button sendBtn;
    
    private boolean isClose = false;

    public MyChat() {
        frame = new Frame("�ٵ�QQ");
        frame.setSize(400, 500);
        frame.setLocation(100, 150);
        frame.setResizable(false);
        display = new TextArea(23, 30);
        display.setEditable(false);
        dataInput = new TextArea(10, 30);
        ipInput = new TextField(20);
        sendBtn = new Button("���� (Ctrl+Enter)");

        frame.add(display, "North");
        frame.add(dataInput, "Center");
        Panel p = new Panel(new BorderLayout());
        p.add(ipInput, "West");
        p.add(sendBtn, "East");
        frame.add(p, "South");

        frame.setVisible(true);
        handlerEvent();
        new Thread(new ReceiveInMyChat()).start();
    }

    private void handlerEvent() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                isClose = true;
                ipInput.setText("127.0.0.1");
                sendMsg();
                // System.exit(0);
            }
        });

        // ����
        sendBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sendMsg();
            }
        });

        dataInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == '\n') {
                    sendMsg();
                }
            }
        });
    }

    protected void sendMsg() {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            byte buf[]= dataInput.getText().getBytes();
            String host = ipInput.getText();
            if (host == null || host.equals("") || host.length() == 0) {
                host = "255.255.255.255";
            }
            DatagramPacket dp = new DatagramPacket(buf, buf.length,
                    InetAddress.getByName(host), 6788);
            ds.send(dp);
            String data = "����                     ������  "
                    + DateFormat.getInstance().format(new Date()) + "\r\n"
                    + dataInput.getText() + "\r\n";
            display.append(data);
            // ���������¼
            saveMessage(data);
            dataInput.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }


    // ������Ϣ
    class ReceiveInMyChat implements Runnable {
        public void run() {
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(6788);
                DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
                while (true) {
                    ds.receive(dp);
                    // �ж��Ƿ�ر��߳�
                    if (isClose) {
                        break;
                    }
                    if (ipInput.getText().length() == 0) {
                        continue;
                    }
                    String nowDate = DateFormat.getInstance()
                            .format(new Date());
                    String msg = dp.getAddress().getHostName() + " ������  "
                            + nowDate;
                    String data = msg + "\r\n"
                            + new String(dp.getData(), 0, dp.getLength())
                            + "\r\n";
                    display.append(data);
                    saveMessage(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (ds != null) {
                    ds.close();
                }
            }
        }
    }

    /**
     * ���������¼
     * @param data
     */
    private void saveMessage(String data) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/chat.txt", true);
            fw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}