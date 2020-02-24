package Library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibraryUser extends User {

    public static final String TYPE = "User";
    private List<Publication> historyRent = new ArrayList<>();
    private List<Publication> actualRent = new ArrayList<>();

    public LibraryUser(String name, String surName, String pesel) {
        super(name, surName, pesel);
    }

    @Override
    public String toCsv() {
        return TYPE + "; " + getName() + "; " + getSurName() + "; " + getPesel() + "; ";
    }

    public List<Publication> getHistoryRent() {
        return historyRent;
    }

    public List<Publication> getActualRent() {
        return actualRent;
    }

    public void addToHistoryRent(Publication publication) {
        historyRent.add(publication);
    }

    public void addActualRent(Publication publication) {
        actualRent.add(publication);
    }

    public boolean returnPublication(Publication publication) {
        boolean result = false;
        if (actualRent.contains(publication)) {
            actualRent.remove(publication);
            addToHistoryRent(publication);
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryUser)) return false;
        if (!super.equals(o)) return false;
        LibraryUser that = (LibraryUser) o;
        return Objects.equals(historyRent, that.historyRent) &&
                Objects.equals(actualRent, that.actualRent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), historyRent, actualRent);
    }
}
