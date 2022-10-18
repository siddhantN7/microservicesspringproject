package project.sid.microservices.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.sid.microservices.entity.Users;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	EntityManager entityManager;
	@Override
	public List<Users> getallUsers() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		Query<Users> theQuery = currentSession.createQuery("from Users", Users.class);
		
		
		List<Users> users = theQuery.getResultList();
		
		
		return users;
	}

	@Override
	public Users getuserbyId(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Users user = currentSession.get(Users.class, id);
		
		return user;
	}

	@Override
	public Users addUser(Users user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.save(user); 
		
		
		return user;

	}

	@Override
	public Users updateUser(Users user) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.merge(user); 
		
		return user;

	}

	@Override
	public void updatepartofUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.delete(currentSession.get(Users.class, id));

	}

}
