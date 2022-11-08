package com.bl.user.service;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bl.user.configuration.RabbitMQConfig;
import com.bl.user.dto.LoginDTO;
import com.bl.user.dto.ResponseDTO;
import com.bl.user.dto.UserDto;
import com.bl.user.exception.CustomerExceptions;
import com.bl.user.model.User;
import com.bl.user.repository.UserRepository;
import com.bl.user.util.EmailSenderService;
import com.bl.user.util.TokenManger;


@Service
public class UserService implements IUserService {
	@Autowired
	UserRepository userRepo;
	@Autowired
	private EmailSenderService mailService;
	@Autowired
	TokenManger tokenGen;
	@Autowired
	private ModelMapper modelMapper;
//	@Autowired
//	private BookRepository bookRepo;
	
	@Autowired
	private AmqpTemplate template;
	
	
	@Override
	public void send(String token) {
		String email = tokenGen.decodeToken(token);
		User user = userRepo.findByEmail(email).orElseThrow(() -> new CustomerExceptions("User Not Found"));
		template.convertAndSend(RabbitMQConfig.exchange, RabbitMQConfig.routingkey, email);
//		System.out.println("Send msg = " + user.getCartBook());

	}
	// Ability to serve controller's insert user record api call
	public User registerUser(UserDto userdto) {
		User user = convertCusDtoToEntity(userdto);
		Optional<User> newUser = userRepo.findByEmail(user.getEmail());
		if (newUser.isPresent()) {
			throw new CustomerExceptions("Customer is already present");
		}
		
//			mailService.sendEmail(user.getEmail());
		return userRepo.save(user);
	}

	// Ability to serve controller's user login api call
	@Override
	public ResponseDTO userLogin(LoginDTO logindto)  {
		Optional<User> user = userRepo.findByEmail(logindto.getEmail());
		if (logindto.getEmail().equals(user.get().getEmail())
				&& logindto.getPassword().equals(user.get().getPassword())) {
			String token = tokenGen.createToken(user.get().getEmail());
			
			mailService.sendEmail(user.get().getEmail());
			return new ResponseDTO(token, user.get().getEmail());
		} else {

			throw new CustomerExceptions("User doesn't exists");

		}
	}

	@Override
	public UserDto convertEntityToDto(@Valid User user) {
		UserDto userDto = new UserDto();
		userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

//	@Override
//	public ResponseDTO addBookToCart(int bookID, String token) {
//		String email = tokenGen.decodeToken(token);
//		User user = userRepo.findByEmail(email).orElseThrow(() -> new CustomerExceptions("User Not Found"));
//		Book book = bookRepo.findById(bookID).get();
//		user.getCartBook().add(book);
//		user = userRepo.save(user);
//		return new ResponseDTO("Book Added in the cart", user);
//	}
//public OrderDto placeOrder(String token) {
//		
//	String email = tokenGen.decodeToken(token);
//	User user = userRepo.findByEmail(email).orElseThrow(() -> new CustomerExceptions("User Not Found"));
//		double totalCost = user.getCartBook().stream().
//				map((order) -> order.getPrice()).reduce(0.0,Double::sum);
//		OrderDto orderDto = new OrderDto();
//		orderDto.setTotalCost(totalCost);
//		orderDto.setCartBook(user.getCartBook());
//		userRepo.save(user);
//		
//		mailService.sendEmail(user.getEmail());
//		return orderDto;
//	}
	public User convertCusDtoToEntity(UserDto userdto) {
		User user = new User();
		user = modelMapper.map(userdto, User.class);
		return user;
	}
	

}
