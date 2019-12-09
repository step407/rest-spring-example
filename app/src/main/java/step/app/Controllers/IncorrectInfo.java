package step.app.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Error variable TO_UpperCase requires a boolean value")
public class IncorrectInfo extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
