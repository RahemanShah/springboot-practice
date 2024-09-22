package in.pr.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CallException {
	
	@ExceptionHandler(ExceptionUser.class)
	public ResponseEntity<String> getException(ExceptionUser userexp){
		
		return new ResponseEntity<String>(userexp.getMessage(),HttpStatus.NOT_FOUND);
	}

}
