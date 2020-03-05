package Library.io;

import Library.model.Book;
import Library.model.LibraryUser;
import Library.model.Magazine;

import java.util.Scanner;

public class DataReader {

    private Scanner scanner = new Scanner(System.in);
    private ConsolPrinter printer;

    public DataReader(ConsolPrinter printer) {
        this.printer = printer;
    }

    public Book readAndCreateBook() {
        printer.printLine("Podaj Tytul");
        String tytul = scanner.nextLine();
        printer.printLine("Podaj Autora");
        String autor = scanner.nextLine();
        printer.printLine("Podaj Wydawce");
        String wydawca = scanner.nextLine();
        printer.printLine("Podaj Date Wydania");
        int dataWydania = scanner.nextInt();
        scanner.nextLine();
        printer.printLine("Podaj Ilos Stron");
        int iloscStron = scanner.nextInt();
        scanner.nextLine();
        printer.printLine("Podaj ISBN");
        String isbn = scanner.nextLine();
        return new Book(tytul, autor,  wydawca,dataWydania, iloscStron, isbn);

    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Podaj Tytul");
        String tytul = scanner.nextLine();
        printer.printLine("Podaj Date Wydania");
        int dataWydania = scanner.nextInt();
        scanner.nextLine();
        printer.printLine("Podaj Wydawce");
        String wydawca = scanner.nextLine();
        printer.printLine("Podaj miesiac");
        int month = scanner.nextInt();
        scanner.nextLine();
        printer.printLine("Podaj dzien");
        int day = scanner.nextInt();
        scanner.nextLine();
        printer.printLine("Podaj jezyk");
        String language = scanner.nextLine();

        return new Magazine(tytul, dataWydania, wydawca, month, day, language);

    }

    public LibraryUser readAndCreateLibraryUser(){
        printer.printLine("Podaj Imie");
        String name = scanner.nextLine();
        printer.printLine("Podaj nazwisko");
        String surname = scanner.nextLine();
        printer.printLine("Podaj Pesel");
        String pesel = scanner.nextLine();

        return  new LibraryUser(name,surname,pesel);
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public String getString() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

}
