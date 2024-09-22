package in.pr.Service.IMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pr.Entity.User;
import in.pr.Repository.UserRepository;
import in.pr.Service.UserService;
import in.pr.Service.RestController.Exveption.UnAuthorizedException;

@Service
public class UserDtoImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	
	@Override
	public User createUser(User user) {
		
		return userRepo.save(user);
	}

	@Override
	public User getOneUser(Integer id) {
	    // Use orElseThrow to handle the case when the user is not found
	    return userRepo.findById(id)
	            .orElseThrow(() -> new UnAuthorizedException("User ID not found: " + id));
	}

	@Override
	public Integer deleteUser(Integer id) {
//	      boolean existsById = userRepo.existsById(id);
//	      
//	      if(existsById) {
//	    	  userRepo.deleteById(id);
//	      }
//		return null;
		
		User userDel = userRepo.findById(id).orElseThrow( 
				()-> new UnAuthorizedException("user id not found: "+id));
		if(userDel!=null) {
			userRepo.deleteById(id);
		}
		return null;
	}

	@Override
	public List<User> GetAllUser() {
		return userRepo.findAll();
	}

	@Override
	public User updateUser(User user) {
		
		     User us = userRepo.findById(user.getId()).orElseThrow( 
		    		  ()-> new UnAuthorizedException("user id not found: "+user.getId()));
		
		if(us!=null) {
			us.setName(user.getName());
			us.setPassword(user.getPassword());
			us.setAbout(user.getAbout());
			us.setEmail(user.getEmail());
			
			User updateUser = userRepo.save(us);
			return updateUser;
		}
		return null;
	}

	@Override
	public Boolean isUserExists(Integer id) {
		boolean existsById = userRepo.existsById(id);
		return existsById;
	}

	
	
	
	
}
