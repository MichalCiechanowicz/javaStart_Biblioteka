package Library.io;

import Library.model.Book;
import Library.model.LibraryUser;
import Library.model.Magazine;
import Library.model.Publication;

import java.util.Collection;

public class ConsolPrinter {

    public void printBooks(Collection<Publication> publications) {
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
            }
        }
    }

    public void printMagazine(Collection<Publication> publications) {
        for (Publication publication : publications) {
            if (publication instanceof Magazine) {
                printLine(publication.toString());
            }
        }
    }

    public void printUsers(Collection<LibraryUser> users) {
        for (LibraryUser user : users) {
            printLine(user.toString());
        }
    }

    public void printLine(String text) {
        System.out.println(text);
    }
}
