package Library.model;

import java.util.Objects;

public class Book extends Publication {

    public static final String TYPE = "Ksiazka";
    private String autor;
    private int iloscStron;
    private String isbn;

    public Book(String tytul, String autor, String wydawca, int dataWydania, int iloscStron, String isbn) {
        super(tytul, dataWydania, wydawca);
        this.autor = autor;
        this.iloscStron = iloscStron;
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIloscStron() {
        return iloscStron;
    }

    public void setIloscStron(int iloscStron) {
        this.iloscStron = iloscStron;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toCsv() {
        return TYPE +"; "+
                getTytul() + "; " +
                autor + "; " +
                getWydawca() + "; " +
                getDataWydania()+"; " +
                iloscStron+"; " +
                isbn;
    }

    @Override
    public String toString() {
        return "Ksiazka - " +
                "Tytul: " + getTytul() +
                ", Autor: " + autor +
                ", Wydawca: " + getWydawca() +
                ", Data Wydania: " + getDataWydania() +
                ", ilosc Stron: " + iloscStron +
                ", isbn: " + isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return iloscStron == book.iloscStron &&
                Objects.equals(autor, book.autor) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), autor, iloscStron, isbn);
    }
}
