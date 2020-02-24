package Library.exeption;

public class InvalidDataExeption extends RuntimeException {
    public InvalidDataExeption(String message) {
        System.err.println(message);
    }
}
