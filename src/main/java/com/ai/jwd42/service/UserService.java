package com.ai.jwd42.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.jwd42.dto.User;
import com.ai.jwd42.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean checkEmailandPassword(String email, String passowrd) {

		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			return false;
		} else {
			if (passowrd.equals(user.getPassword())) {
				return true;
			}
		}

		return false;
	}

	public List<User> findAllUser() {

		return userRepository.findAllUser();
	}

	public boolean checkPhoneNumberAlreadyExist(String phone_number) {

		User user = userRepository.findUserByPhoneNumber(phone_number);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkPhoneNumberAlreadyExistAtUpdate(String phone_number, int id) {

		User user = userRepository.findUserByPhoneNumber(phone_number);
		if (user == null) {
			return false;
		} else if (user.getId() == id) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkEmailAlreadyExist(String email) {

		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkEmailAlreadyExistAtUpdate(String email, int id) {

		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			return false;
		} else {
			if (user.getId() == id) {
				return false;
			}
			return true;
		}
	}

	public int insertUser(User user) {

		return userRepository.insertUser(user);
	}

	public void updateUser(User user) {

		userRepository.updateUser(user);
	}

	public void deleteUser(int id) {

		userRepository.deleteUser(id);
	}

	public boolean checkPassword(String passowrd, int id) {

		User user = userRepository.findUserById(id);

		if (passowrd.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public void changePassword(String passowrd, int id) {

		userRepository.changePassword(id, passowrd);

	}

}
