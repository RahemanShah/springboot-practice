package in.pr.Service.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.pr.Entity.User;
import in.pr.Entity.Dto.UserDto;
import in.pr.Service.UserService;
import in.pr.Service.IMPL.UserDtoImpl;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserDtoImpl service;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user){

		User createUser = service.createUser(user);
		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
	}

	
	
	@GetMapping("/getUser/{id}")
	public  ResponseEntity<User> getOneUser( @PathVariable Integer id) {
		
		User oneUser = service.getOneUser(id);
		return new ResponseEntity<User>(oneUser, HttpStatus.OK);
	}

	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Integer> deleteUser( @PathVariable Integer id) {
		Integer deleteUser = service.deleteUser(id);
		return new ResponseEntity<Integer>(deleteUser, HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/getall")
	public ResponseEntity <List<User>> GetAllUser(){
		List<User> getAllUser = service.GetAllUser();
		return new ResponseEntity<List<User>>(getAllUser, HttpStatus.OK);
	}
	
	
	
    @PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") User user) {
    	
    	User updateUser = service.updateUser(user);
		return new ResponseEntity<User>(updateUser, HttpStatus.OK);
	}

    @GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> isUserExists( @PathVariable Integer id) {
		Boolean userExists = service.isUserExists(id);
		return new ResponseEntity<Boolean>(userExists, HttpStatus.OK);
	}

}
