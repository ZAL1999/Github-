package com.user;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager userManager=new UserManagerImplProxy(new UserManangerImpl() {
		});  
       UserManager.addUser("1111", "ÕÅÈı");  
	}

}
