package Library.io.file;

import Library.exeption.DataExportExeption;
import Library.exeption.DataImportExeption;
import Library.exeption.InvalidDataExeption;
import Library.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Library.csv";
    private static final String USER_FILE = "User.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublication(library);
        importLibraryUser(library);
        return library;
    }

    private void importLibraryUser(Library library) {
        try (Scanner fileReader = new Scanner(new File(USER_FILE))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                LibraryUser libraryUser = createUserFromString(line);
                library.addUser(libraryUser);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportExeption("Brak pliku " + USER_FILE);
        }
    }

    private void importPublication(Library library) {
        try (Scanner fileReader = new Scanner(new File(FILE_NAME))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Publication publication = createObjectFromString(line);
                library.addPublication(publication);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportExeption("Brak pliku " + FILE_NAME);
        }
    }


    private Publication createObjectFromString(String line) {
        String[] splited = line.split("; ");
        String type = splited[0];
        if (Book.TYPE.equals(type)) {
            return createBook(splited);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazie(splited);
        }
        throw new InvalidDataExeption("Nieznany typ obiektu " + type);
    }

    private LibraryUser createUserFromString(String line) {
        String[] splitted = line.split("; ");
        String type = splitted[0];
        if (LibraryUser.TYPE.equals(type)) {
            String neme = splitted[1];
            String suranme = splitted[2];
            String pesel = splitted[3];
            return new LibraryUser(neme, suranme, pesel);
        }
        throw new InvalidDataExeption("Nieznany typ obiekty " + type);

    }

    private Magazine createMagazie(String[] data) {

        String title = data[1];
        int dataWydania = Integer.parseInt(data[2]);
        String wydawca = data[3];
        int day = Integer.parseInt(data[4]);
        String month = data[5];
        String languange = data[6];

        return new Magazine(title, dataWydania, wydawca, month, day, languange);

    }

    private Book createBook(String[] data) {
        String title = data[1];
        String author = data[2];
        String wydawca = data[3];
        int dataWydania = Integer.parseInt(data[4]);
        int iloscStron = Integer.parseInt(data[5]);
        String isbn = data[6];

        return new Book(title, author, wydawca, dataWydania, iloscStron, isbn);
    }


    @Override
    public void saveData(Library library) {
        exportPublication(library);
        exportUsers(library);
    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> publications = library.getUsers().values();
        exportToCsv(publications, USER_FILE);
    }


    private void exportPublication(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications, FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection<T> collection, String fileName) {
        try (
                FileWriter writer = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
        ) {
            for (T element : collection) {
                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            throw new DataExportExeption("Blad zapisu danych do pliku " + fileName);
        }
    }
}
