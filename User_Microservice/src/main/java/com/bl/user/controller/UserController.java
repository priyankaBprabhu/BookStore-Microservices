package com.bl.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bl.user.dto.LoginDTO;
import com.bl.user.dto.ResponseDTO;
import com.bl.user.dto.UserDto;
import com.bl.user.model.User;
import com.bl.user.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userregistration")
public class UserController {
	@Autowired
	IUserService userService;
	
	@GetMapping("/hello") 
	public String  helloword(){
		return "hello word";
	}

	@PostMapping("/register")
	@ApiOperation("Register the new user")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto user) {
		User newUser = userService.registerUser(user);
		UserDto Dto = userService.convertEntityToDto(newUser);
		return ResponseEntity.status(200).body("employee registered sucessfully");
	}

	// Ability to call api to login user
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> userLogin(@Valid @RequestBody LoginDTO logindto) {
		ResponseDTO user = userService.userLogin(logindto);
		ResponseDTO dto = new ResponseDTO("User logged in successfully !");
		return new ResponseEntity<ResponseDTO>(user, HttpStatus.OK);
	}

//	@PutMapping("/book/cart/{bookID}")
//	public ResponseEntity<ResponseDTO> addBookToCart(@PathVariable int bookID, @RequestHeader String token) {
//		return new ResponseEntity<ResponseDTO>(userService.addBookToCart(bookID, token), HttpStatus.OK);
//	}

//	@PutMapping("/book/order")
//	public ResponseEntity<OrderDto> placeOrder(@RequestHeader String token) {
//		return new ResponseEntity(userService.placeOrder(token), HttpStatus.OK);
//	}

	@GetMapping("/book/ordermessage")
	public String producer(@RequestHeader String token) {
		userService.send(token);
		return "JMS Message sent to the RabbitMQ Successfully";
	}
}
