package br.com.marcio.api.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException{
    public DataIntegratyViolationException(String message)
    {
        super(message);
    }
}
