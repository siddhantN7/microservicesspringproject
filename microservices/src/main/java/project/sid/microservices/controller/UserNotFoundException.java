package project.sid.microservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//inorder to send the response status
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message)
	{
		super(message); //passing message to superclass that is runtimeexception
	}
}
