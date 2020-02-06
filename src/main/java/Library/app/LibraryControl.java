package Library.app;

import Library.exeption.DataExportExeption;
import Library.exeption.DataImportExeption;
import Library.io.ConsolPrinter;
import Library.io.DataReader;
import Library.io.file.FileManager;
import Library.io.file.FileManagerBuilder;
import Library.model.Library;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolPrinter printer = new ConsolPrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        printer.printLine("Dane z pliku wczytane prawidlowo");
        try {
            library = fileManager.importData();
        } catch (DataImportExeption e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjonowano nowa baze");
            library = new Library();
        }
    }


    void controlLoop() {
        Option option;

        do {
            printOutOption();

            option = getOption();
            switch (option) {
                case EXIT:
                    exit();
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_BOOKS:
                    printer.printBooks(library.getPublications());
                    break;
                case PRINT_MAGAZINES:
                    printer.printMagazine(library.getPublications());
                    break;
                default:
                    printer.printLine("Podales zla wartosc");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchMethodException e) {
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzona wartosc nie jest liczba, wprowadz ponownie");
            }
        }
        return option;
    }

//    private void printMagazines() {
//        Publication[] publications = library.getPublications();
//        printer.printMagazine(publications);
//    }
//
//    private void printBooks() {
//        Publication[] publications = library.getPublications();
//        printer.printBooks(publications);
//    }

    private void addMagazine() {
        try {
            library.addMagazine(dataReader.readAndCreateMagazine());
        } catch (
                InputMismatchException e) {
            printer.printLine("Podales zwla wartosc, powinna byc liczbowa");
        } catch (
                ArrayIndexOutOfBoundsException e) {
            printer.printLine("Bilioteka jest juz pelna");
        }

    }

    private void addBook() {
        try {
            library.addBook(dataReader.readAndCreateBook());
        } catch (InputMismatchException e) {
            printer.printLine("Podales zwla wartosc, powinna byc liczbowa");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Bilioteka jest juz pelna");
        }
    }

    private void exit() {
        try {
            fileManager.saveData(library);
            printer.printLine("Dane zostaly zapisane poprawnie");
        } catch (DataExportExeption e) {
            printer.printLine(e.getMessage());
        }

        printer.printLine("Dziekujemy za korzystanie z naszej biblioteki");
        dataReader.close();
    }

    private void printOutOption() {
        printer.printLine("Wybierz jedna z opcji");
        for (Option options : Option.values()) {
            printer.printLine(options.toString());
        }
    }

    public enum Option {

        EXIT(0, " wyjscie"),
        ADD_BOOK(1, " dodaj ksiazke"),
        ADD_MAGAZINE(2, " dodaj magazyn"),
        PRINT_BOOKS(3, " wyswietl ksiazki"),
        PRINT_MAGAZINES(4, " wyswietl magazyny");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchMethodException {
            try {
                return values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchMethodException("Brak opcji o id: " + option);
            }
        }
    }
}