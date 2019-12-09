package step.app.Controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.Welcome;
import models.commonRequest;

@RestController
@RequestMapping("api/v1/problems")
public class AppController {
	
	@GetMapping("/")
	public ResponseEntity<Welcome> getControl() {
		Welcome message =  new Welcome("Welcome to Radar Web services");		
		return new ResponseEntity<Welcome> (message,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> postControl(@RequestHeader Boolean TO_UpperCase, @RequestBody commonRequest cred){
		if(TO_UpperCase) {
			commonRequest res = new commonRequest(cred.getfName().toUpperCase(),cred.getTitle().toUpperCase());
			return new ResponseEntity<commonRequest>(res,HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<commonRequest>(cred,HttpStatus.OK);
		}
		
	}
	
	@PutMapping("/")
	public ResponseEntity<?> putControl(@RequestBody commonRequest cred){
		LocalDateTime ts = LocalDateTime.now();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("X_TIME_OF_EVENT", ts.toString());
		return ResponseEntity.ok().headers(responseHeaders).body(cred);
	}
	
	@DeleteMapping("/{randomNum}")
	public ResponseEntity<?> deleteControl(@PathVariable Number randomNum){
		Welcome del = new Welcome(randomNum + " deleted successfully");
		return new ResponseEntity<Welcome>(del,HttpStatus.OK);
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public @ResponseBody
	ResponseEntity<?> mismatchHandler(Exception exception, HttpServletRequest request){
		if(request.getMethod().equals("POST"))
			{Welcome err = new Welcome("ERROR _ MESSAGE here");
			return new ResponseEntity<Welcome>(err,HttpStatus.BAD_REQUEST);
			}//Post method incorrect header value type
		
		else {
			Welcome err = new Welcome("Value is not numeric");
			return new ResponseEntity<Welcome>(err, HttpStatus.BAD_REQUEST);
		}//Delete method incorrect path variable type
	
	
	} //Display custom error message for parameters of the wrong type
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public @ResponseBody
	ResponseEntity<?> missingHandler(Exception exception, HttpServletRequest request){
			Welcome err = new Welcome("ERROR _ MESSAGE here");
			return new ResponseEntity<Welcome>(err,HttpStatus.BAD_REQUEST);
	} //Display custom error message for missing header parameters.
	


}
