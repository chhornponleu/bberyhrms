package com.wing.app.services;

import com.wing.app.entities.user.User;

public interface UserService {
	User getByUsername(String username);
}
