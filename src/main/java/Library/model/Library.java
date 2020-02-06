package Library.model;

import java.io.Serializable;

public class Library implements Serializable {

    private static final int MAX_AMOUNT_OF_PUBLICATIONS = 2000;
    private Publication[] publications = new Book[MAX_AMOUNT_OF_PUBLICATIONS];
    private int publicationNumber = 0;

    public Publication[] getPublications() {
        Publication[] result = new Publication[publicationNumber];
        for (int i = 0; i < result.length; i++) {
            result[i] = publications[i];
        }
        return result;
    }

    public void addBook(Book book) {
        addPublication(book);
    }

    public void addMagazine(Magazine magazine) {
        addPublication(magazine);
    }

    private void addPublication(Publication publication) {
        if (publicationNumber >= MAX_AMOUNT_OF_PUBLICATIONS) {
            throw new ArrayIndexOutOfBoundsException("Max publications exedeed" + MAX_AMOUNT_OF_PUBLICATIONS);
        } else {
            publications[publicationNumber] = publication;
            publicationNumber++;
        }
    }


}
