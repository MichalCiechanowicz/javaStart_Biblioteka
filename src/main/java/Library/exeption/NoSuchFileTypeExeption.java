package Library.exeption;

public class NoSuchFileTypeExeption extends RuntimeException {
    public NoSuchFileTypeExeption(String message) {
        System.err.println(message);
    }
}
