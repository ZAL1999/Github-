package drawing;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonListener implements ActionListener,MouseListener,MouseMotionListener {
		
		private int index=0;
		private Shape ShArr[];
		private JPanel jp;
		private java.awt.Graphics gr;
		private String zhiling="";
		int x0=0,y0=0,x1=0,y1=0,x2=0,y2=0,x3=0,x4=0,y3=0,y4=0,start_x=0,start_y=0;
		public void set_jp(JPanel jp) {
			this.jp=jp;
			}
		public void set_gr(java.awt.Graphics G) {
			this.gr = G;
		}
		public void set_ShArr(Shape ShArr[]) {
			this.ShArr = ShArr;
		}
		public int get_len() {
			return index;
		}
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			x1 = 200; y1 = 200;
			if (e.getActionCommand()=="") {
				JButton j = (JButton)e.getSource();
				gr.setColor(j.getBackground());
			}
			else {
				zhiling = e.getActionCommand();
				if("清除".equals(zhiling)) {
					//System.out.println("这里是buttonlistener的清除");
					index = 0;
				//	p.set_len(len);
					jp.repaint();
					x4 = 0;
					y4 = 0;
				}
			}
		}
		//鼠标拖动时间，用来绘画曲线
		public void mouseDragged(MouseEvent e) {
			//System.out.println("Drag");
			if("曲线".equals(zhiling)) {
				x1 = x0; y1 = y0; x0 = e.getX(); y0 = e.getY();
				gr.drawLine(x1,y1,x0,y0);
				ShArr[index++] = new Shape(x1,y1,x0,y0,zhiling);
				
			}
		}

		// Override鼠标监听//点击鼠标点击时间，用来绘画多边形
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if("多边形".equals(zhiling)) {
				
				if(x4==0&&y4==0){
					
					x4 = e.getX(); y4 = e.getY();
					start_x = x4; start_y = y4;
					//gr.drawLine(x3, y3, x4, y4);
				}
				else {
					//System.out.println("Here is duobianxing ");
					x3 = x4; y3 = y4; x4 = e.getX(); y4 = e.getY();
					gr.drawLine(x3, y3, x4, y4);
					ShArr[index++] = new Shape(x3,y3,x4,y4,zhiling);
				}
				if(e.getClickCount()==2){
				//System.out.println("双击");
				//System.out.println("双击");
				x4 =0; y4=0;
				gr.drawLine(start_x, start_y, e.getX(), e.getY());
				ShArr[index++] = new Shape(start_x,start_y,e.getX(),e.getY(),zhiling);
			}	
			}
			
		}

		//当鼠标按下的时候，获取起点的坐标
		@Override // 按下
		public void mousePressed(MouseEvent e) {
		//	System.out.println("按下");
			
			if("曲线".equals(zhiling)){
				x0 = e.getX();
				y0 = e.getY();
			}
			// TODO Auto-generated method stub
			x1 = e.getX();
			y1 = e.getY();
			
		}
		//当鼠标松开的时候，获取终点的坐标
		@Override // 松开
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			x2 = e.getX();
			y2 = e.getY();
		//	System.out.println("松开");
			if("直线".equals(zhiling)){
				gr.drawLine(x1, y1, x2, y2);
				ShArr[index++] = new Shape(x1,y1,x2,y2,zhiling);
				//System.out.println("长度是 "+len);
			}
			else if("矩形".equals(zhiling)) {
				gr.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
				ShArr[index++] = new Shape(x1,y1,x2,y2,zhiling);
			}
			else if("圆形".equals(zhiling)) {
				gr.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
				ShArr[index++] = new Shape(x1,y1,x2,y2,zhiling);
			}
		}
		//剩下的方法，可以暂时不用管
		@Override // 进入
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override // 移出
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseMoved(MouseEvent e) {
			
		}
	}

