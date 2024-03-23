package ar.edu.iua.iw3.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StandardResponseBusiness implements IStandardResponseBusiness {

	@Value("${dev.info.enabled:false}")
	private boolean devInfoEnabled;
	
	@Override
	public StandardResponse build(HttpStatus httpStatus, Throwable ex, String message) {
		StandardResponse sr=new StandardResponse(message, ex, httpStatus, devInfoEnabled);
		return sr;
	}

}
