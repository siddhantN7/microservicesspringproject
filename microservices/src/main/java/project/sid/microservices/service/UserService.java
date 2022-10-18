package project.sid.microservices.service;

import java.util.List;

import project.sid.microservices.entity.Users;

public interface UserService {

public List<Users> getallUsers();
	
	public Users getuserbyId(int id);
	
	public Users addUser(Users user);
	
	public Users updateUser(Users user);
	
	public void updatepartofUser(int id);
	
	public void deleteUser(int id);
	
}
