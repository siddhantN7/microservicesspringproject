package project.sid.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.sid.microservices.dao.UserDao;
import project.sid.microservices.entity.Users;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userdao;
	@Override
	public List<Users> getallUsers() {
		
		return userdao.getallUsers();
	}

	@Override
	public Users getuserbyId(int id) {
		
		return userdao.getuserbyId(id);
	}

	@Override
	public Users addUser(Users user) {
		
		 return userdao.addUser(user);

	}

	@Override
	public Users updateUser(Users user) {
		return userdao.updateUser(user);

	}

	@Override
	public void updatepartofUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(int id) {
		userdao.deleteUser(id);

	}

}
