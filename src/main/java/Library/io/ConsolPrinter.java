package Library.io;

import Library.model.*;

import java.util.Collection;

public class ConsolPrinter {

    public void printBooks(Collection<Publication> publications) {
        long count = publications.stream()
                .filter(publication -> publication instanceof Book)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (count == 0) {
            printLine("Brak Ksiazek w bibliotece");
        }
    }

    public void printMagazine(Collection<Publication> publications) {
        long count = publications.stream()
                .filter(publication -> publication instanceof Magazine)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (count == 0) {
            printLine("Brak magazynow w bibliotece");
        }
    }

    public void printUsers(Collection<LibraryUser> users) {
//        for (LibraryUser user : users) {
//            printLine(user.toString());
//        } mozna to zrobic rowniez za pomoca strumieni obie wersje sa prawidlowe
        users.stream()
                .map(User::toString)
                .forEach(this::printLine);
    }

    public void printLine(String text) {
        System.out.println(text);
    }
}
