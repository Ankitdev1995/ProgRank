package com.becoderr.service;

import com.becoderr.entity.User;

public interface UserService {

	public User saveUser(User user, String url);
	
	public void removeSessionMessage();
	
	public void sendEmail(User user, String path);
	
	public boolean verifyAccount(String verificationCode);
}
