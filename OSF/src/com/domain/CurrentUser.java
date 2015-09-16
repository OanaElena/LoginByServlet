package com.domain;

public class CurrentUser {
     private static User user;
     
     public CurrentUser(){	 
     }
     
     public CurrentUser(User user){
    	 CurrentUser.user=user;
     }

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		CurrentUser.user = user;
	}
     
}
