package project.sid.microservices.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import project.sid.microservices.controller.UserNotFoundException;

//extending the parent class to override its method
@ControllerAdvice   // so that this class is picked up by the controllers
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	//OVERRIDING THE METHOD WHICH DEFINES THE STRUCTURE
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
	{
		
		//MAKUNG USE OF SAME EX VARIBALE WHICH HAS THE ERROR DATA
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//if we want to have specific code sent. define individually. In get method in controller ypu are throwing this exception. It gets directed here nad not to the defaultv one
	@ExceptionHandler(UserNotFoundException.class)
	//method name doesnt matter!!! its the @exception annotation that matters
	public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception ex, WebRequest request)
	{
		
		//MAKUNG USE OF SAME EX VARIBALE WHICH HAS THE ERROR DATA
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String allErrors = "";
		//BY DOING THIS WE ARE SENDING OUR OWN FORMAT LIKE BEFORE TO THE CONSUMER WITH PROPER MESSAGE AND CODE. IF WE DONT METION IT, WE WILL GET THE DEFAULT RESONSE BY SPRING
		//here instead of sending the entire IT code, u can send ur message (which we defined in entity class), for this make use of ex.getFieldError().getDefaultMessage()
		//if there are multiple errors then we need to loop around ex.getFieldError().getDefaultMessage() to show all messages
		//explore ex.methods for more things like count of errors etc
		
		//ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		for(int i = 0;i<ex.getFieldErrors().size();i++)
		{
			allErrors+=ex.getFieldErrors().get(i).getDefaultMessage();
		}
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), allErrors, request.getDescription(false));
		
		
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
}
