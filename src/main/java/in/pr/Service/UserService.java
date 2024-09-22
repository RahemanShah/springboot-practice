package in.pr.Service;

import java.util.List;

import in.pr.Entity.User;

public interface UserService {

	
	public User createUser(User user);
	
	public User getOneUser(Integer id);
	
	public Integer deleteUser(Integer id);
	
	public List<User> GetAllUser();
	
	public User updateUser(User user);
	
	public Boolean isUserExists(Integer id);
	
	
}
