package com.bl.user.service;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.bl.user.dto.LoginDTO;
import com.bl.user.dto.ResponseDTO;
import com.bl.user.dto.UserDto;
import com.bl.user.model.User;

public interface IUserService {

//	UserDto convertEntityToDto(@Valid LoginDTO userRegistration);

	public ResponseDTO userLogin(LoginDTO logindto) ;

	UserDto convertEntityToDto(@Valid User user);

	public User registerUser(@Valid UserDto userDto);

//	public ResponseDTO addBookToCart(int bookID, String token);

//	public Object placeOrder(String token);

	public void send(String token);

}
