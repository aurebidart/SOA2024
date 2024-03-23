package ar.edu.iua.iw3.backend.util;

import org.springframework.http.HttpStatus;

public interface IStandardResponseBusiness {
	public StandardResponse build(HttpStatus httpStatus, Throwable ex, String message);
}
