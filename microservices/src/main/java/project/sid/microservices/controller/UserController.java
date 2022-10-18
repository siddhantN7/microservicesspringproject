package project.sid.microservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import project.sid.microservices.entity.Users;
import project.sid.microservices.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	//here based on version the api is called. the url will end with somethig like ?version=1.
	@GetMapping(path = "/api/users", params ="version=1")
	public List<Users> getAllUsers()
	{
		return userService.getallUsers();
		
	}
	
	//if no version is specified then it csddomes here
	@GetMapping(path = "/api/users")
	public List<Users> getAllUsers2()
	{
		return userService.getallUsers();
		
	}
	
	//header way
	@GetMapping(path = "/api/users", headers = "X-API-VERSION=1")
	public List<Users> getAllUsersHeader()
	{
		return userService.getallUsers();
		
	}
	
	@GetMapping("/api/users/{userId}")
	public Users getUserById(@PathVariable int userId)
	{
		Users userGet =  userService.getuserbyId(userId);
		if(userGet == null)
		{
			throw new UserNotFoundException("id:" + userId);
		}
		return userGet;
		
	}
	
	
	//when we use valid , when binding happens, the validation defined in our entity class is autommatically invoked
	@PostMapping("/api/users")
	public ResponseEntity<Users>  addUser(@Valid @RequestBody Users user)
	{
		
		//sending response status of 201 and return the uri of the created user
		Users userC = userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{userId}")     //setting same path as /api/users
							.buildAndExpand(userC.getUserId()) //seeting the id now
							.toUri();   //converting to uri
		return  ResponseEntity.created(location).build();
		
		
	}
	
	@PutMapping("/api/users")
	public Users updateUser(@RequestBody Users user)
	{
		return userService.updateUser(user);
		
	}
	
	@DeleteMapping("/api/users/{userId}")
	public void deleteUser(@PathVariable int userId)
	{
		userService.deleteUser(userId);
		
	}
}
