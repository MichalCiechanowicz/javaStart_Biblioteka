package Library.io;

import Library.model.Book;
import Library.model.Publication;

public class ConsolPrinter {

    public void printBooks(Publication[] publications) {
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
            }
        }
    }

    public void printMagazine(Publication[] publications) {
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
            }
        }
    }

    public void printLine(String text) {
        System.out.println(text.toUpperCase());
    }
}
