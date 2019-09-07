package drawing;

import java.awt.Graphics;

import javax.swing.JPanel;
//定义一个图案的类，方便对多种图案进行实现和管理
public class Shape{
	//Shape类有name和两点坐标的属性
	private int x1,x2,y1,y2;
	private String name;
	public Shape(int x1,int y1,int x2,int y2,String name){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.name = name;
	}
	//根据不同的图案名字，开展不同的重绘机制
	public void repaint(Graphics g) {
		switch(name) {
		case "直线":
			g.drawLine(x1, y1, x2, y2);
			break;
		case "矩形":
			g.drawRect(Math.min(x1, x2), Math.min(y1 ,y2), Math.abs(x1-x2), Math.abs(y1-y2));
			break;
		case "圆形":
			g.drawOval(Math.min(x1, x2), Math.min(y1 ,y2), Math.abs(x1-x2), Math.abs(y1-y2));
			break;
		case "曲线":
			g.drawLine(x1, y1, x2, y2);
			break;
		case "多边形":
			g.drawLine(x1, y1, x2, y2);
			break;
		}
	}
}
