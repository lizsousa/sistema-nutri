package br.edu.ifg.sistemanutri.util.exception;

public class NegocioException extends Exception{

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
