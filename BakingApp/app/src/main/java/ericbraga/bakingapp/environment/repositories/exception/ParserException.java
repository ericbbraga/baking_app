package ericbraga.bakingapp.environment.repositories.exception;

public class ParserException extends Exception {

    public ParserException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("An issue occurred when tried apply the parser. \n %s", super.getMessage());
    }
}
