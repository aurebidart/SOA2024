package ar.edu.iua.iw3.backend.business.Exceptions;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadStateException extends Exception{

    private static final long serialVersionUID = 1L;

    @Builder
    public BadStateException(String message, Throwable ex) {
        super(message, ex);
    }

    @Builder
    public BadStateException(String message) {
        super(message);
    }

    @Builder
    public BadStateException(Throwable ex) {
        super(ex.getMessage(), ex);
    }

    
}
