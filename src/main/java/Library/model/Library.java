package Library.model;

import Library.exeption.PublicationAllReadyExistExeption;
import Library.exeption.UserAlreadyExistExeption;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublication(Comparator<Publication> comparator) {
        ArrayList<Publication> list = new ArrayList<>(this.publications.values());
        list.sort(comparator);
        return list;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator) {
        ArrayList<LibraryUser> list = new ArrayList<>(this.users.values());
        list.sort(comparator);
        return list;
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTytul())) {
            throw new PublicationAllReadyExistExeption("Taka publikacja jest juz w bilbiotece");
        }
        publications.put(publication.getTytul(), publication);
    }

    public void addUser(LibraryUser user) {
        if (users.containsKey(user.getPesel())) {
            throw new UserAlreadyExistExeption("Taki uzytkownik juz jest zarejestrowany");
        }
        users.put(user.getPesel(), user);
    }

    public boolean removePublication(Publication publ) {
        if (publications.containsValue(publ)) {
            publications.get(publ.getTytul());
        }
        return false;
    }
}
