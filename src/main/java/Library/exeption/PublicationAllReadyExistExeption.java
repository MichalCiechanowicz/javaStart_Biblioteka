package Library.exeption;

public class PublicationAllReadyExistExeption extends RuntimeException {
    public PublicationAllReadyExistExeption(String message) {
        super(message);
    }
}
