package Library.app;

public class LibrabryApp {

    final static private String APP_NAME = "Biblioteka v1.8";

    public static void main(String[] args) {

        System.out.println(APP_NAME);
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlLoop();

    }
}
