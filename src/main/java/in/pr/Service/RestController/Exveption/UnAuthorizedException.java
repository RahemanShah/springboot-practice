package in.pr.Service.RestController.Exveption;

public class UnAuthorizedException extends RuntimeException {

	public UnAuthorizedException() {
		
	}
	
	public UnAuthorizedException(String msg) {
		super(msg);
	}
}
