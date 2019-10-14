package Serialize1;

import java.io.Serializable;

public class Teacher extends Person implements Serializable{
	private static final long serialVersionUID=1L;
	private String position="нч";
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
