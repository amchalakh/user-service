package com.edusol.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edusol.user.model.User;
import com.edusol.user.repository.UserRepository;

@Service
public class UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		
		userRepository.save(user);
		logger.info(user.toString());
		return user;
		
	}

	public List<User> getUsers() {
		
		List<User> users =  userRepository.findAll();
		logger.info(users.toString());
		return users;
		
	}

	public String updateUser(User user) {
		logger.info(user.toString());
		 userRepository.save(user);
		 logger.info("record updated successfully");
		 return "record updated successfully";
	}

	public ResponseEntity<String>  deleteUser(int id) {
		logger.info("Deleteing user by:"+id);
		String message = "";
		try{
		User user = userRepository.getOne(id);
		userRepository.deleteById(id);
		message = "record deleted successfully"+id;
		logger.info(message);
		return new ResponseEntity<String> (message, HttpStatus.OK);
		
		}catch(Exception e){
			message = "record not  found"+id;
			logger.error(message);
			return new ResponseEntity<String> (message, HttpStatus.NOT_FOUND);
			
		}
		
	}

	public List<User> getUsersByCity(String city) {
		logger.info("Getting user details by:"+city);
		List<User> users = userRepository.findByCity(city);
		
		return users;
	}

}
