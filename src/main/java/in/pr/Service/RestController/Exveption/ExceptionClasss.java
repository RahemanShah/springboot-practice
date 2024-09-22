package in.pr.Service.RestController.Exveption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionClasss {

	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<String> handleExp(UnAuthorizedException exp){
		
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.NOT_FOUND);
	}
}
