package noemicoppotelli.appviaggiaziendali_backend.exceptions;

public class BadRequestExeception extends RuntimeException {
    public BadRequestExeception(String message) {
        super(message);
    }
}
