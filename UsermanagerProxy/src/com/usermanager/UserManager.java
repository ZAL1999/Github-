package com.usermanager;

public interface UserManager {

	 public static  void addUser(String userId, String userName) {
		System.out.printf("success----adduser");
	};

	void delUser(String userId);

	String findUser(String userId);

	void modifyUser(String userId, String userName);

	

}
