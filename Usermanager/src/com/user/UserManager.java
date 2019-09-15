package com.user;

public interface UserManager {
      int userId = 0;
      String userName ="";
	public static void addUser(String userId, String userName) {
		System.out.printf("用户添加成功");
	}
	void delUser(String userId);
	String findUser(String userId);
	void modifyUser(String userId, String userName);
	
      
}
