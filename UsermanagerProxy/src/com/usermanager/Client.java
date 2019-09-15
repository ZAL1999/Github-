package com.usermanager;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogHandler logHandler=new LogHandler();  
        UserManager userManager=(UserManager)logHandler.newProxyInstance(new UserManagerImpl());  
        //UserManager userManager=new UserManagerImpl();  
        UserManager.addUser("1111", "ÕÅÈı");  
	}

}
