package Library.app;

import Library.exeption.DataExportExeption;
import Library.exeption.DataImportExeption;
import Library.exeption.UserAlreadyExistExeption;
import Library.io.ConsolPrinter;
import Library.io.DataReader;
import Library.io.file.FileManager;
import Library.io.file.FileManagerBuilder;
import Library.model.*;
import Library.model.comparator.AlphabeticTitleComparator;

import java.util.Comparator;
import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolPrinter printer = new ConsolPrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Dane z pliku wczytane prawidlowo");
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
                    printBooks();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZINE:
                    deleteMagazie();
                    break;
                case ADD_USER:
                    addLibraryUser();
                    break;
                case PRINT_USERS:
                    printUsers();
                    break;
                case FIND_BOOK:
                    findBookByTitle();
                    break;
                default:
                    printer.printLine("Podales zla wartosc");
            }
        } while (option != Option.EXIT);
    }

    private void findBookByTitle() {
        printer.printLine("Podaj tytul publikacji");
        String title = dataReader.getString();
        String titleNotFound = "Brak Publikacji o danym tytule";
        library.getPublicationByTitle(title)
                .ifPresentOrElse(System.out::println,()-> System.out.println(titleNotFound));
    }

    private void printUsers() {
        printer.printUsers(library.getSortedUsers(
//                (p1, p2) -> p1.getSurName().compareToIgnoreCase(p2.getSurName()
                Comparator.comparing(User::getSurName,String.CASE_INSENSITIVE_ORDER)
                ));

    }

    private void addLibraryUser() {
        LibraryUser libraryUser = dataReader.readAndCreateLibraryUser();
        try {
            library.addUser(libraryUser);
        } catch (UserAlreadyExistExeption e) {
            printer.printLine(e.getMessage());
        }

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

//uzywamy tutaj wyrazen lambda do sortowania
    private void printMagazines() {
        printer.printMagazine(library.getSortedPublication(
//                (p1,p2)-> p1.getTytul().compareToIgnoreCase(p2.getTytul()
//                wwyrazenie lambda mozna zastapic tym
                Comparator.comparing(Publication::getTytul,String.CASE_INSENSITIVE_ORDER)));
    }
//uzywamy tutaj stworzonej przez nas klasy komparatora zamiast wyrazenia lambda. Wyrazenie lambda
    private void printBooks() {
        printer.printBooks(library.getSortedPublication(new AlphabeticTitleComparator()));

    }

    private void addMagazine() {
//mozna to zrobic tez tak jak w metodzie addBook, czyli nie tworzyc referencji magazine,
// tylko odrazu do metody addPublication przekazac dataReader.readAndCreateMagazine jako parametr.
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (
                InputMismatchException e) {
            printer.printLine("Podales zwla wartosc, powinna byc liczbowa");
        } catch (
                ArrayIndexOutOfBoundsException e) {
            printer.printLine("Bilioteka jest juz pelna");
        }

    }

    private void deleteMagazie() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            if (library.removePublication(magazine)) {
                printer.printLine("Magazyn zostal usuniety");
            } else {
                printer.printLine("Nie ma takiego magazynu");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Podales nieprawidlowe dane");
        }
    }

    private void addBook() {
        try {
            library.addPublication(dataReader.readAndCreateBook());
        } catch (InputMismatchException e) {
            printer.printLine("Podales zwla wartosc, powinna byc liczbowa");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Bilioteka jest juz pelna");
        }
    }

    private void deleteBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book)) {
                printer.printLine("Ksiazka zostala usunieta");
            } else {
                printer.printLine("Nie ma takiej ksiazki");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Podales nieprawidlowe dane");
        }
    }

//    private Publication[] getSortedPublication() {
//        Publication[] publications = library.getPublications();
//        Arrays.sort(publications, new AlphabeticComparator());
//        return publications;
//    }

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

    private enum Option {

        EXIT(0, " Wyjscie"),
        ADD_BOOK(1, " Dodaj ksiazke"),
        ADD_MAGAZINE(2, " Dodaj magazyn"),
        PRINT_BOOKS(3, " Wyswietl ksiazki"),
        PRINT_MAGAZINES(4, " Wyswietl magazyny"),
        DELETE_BOOK(5, " Usun Ksiazke"),
        DELETE_MAGAZINE(6, " Usun Magazyn"),
        ADD_USER(7, " Dodaj Czytelnika"),
        PRINT_USERS(8, " Wyswietl Czytelnikow"),
        FIND_BOOK(9,"Wyszukaj ksiazke po tytule");

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