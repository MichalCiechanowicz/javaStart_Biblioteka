package Library.model;

import java.io.Serializable;
import java.util.Objects;

abstract public class User implements Serializable, CsvConvertible {

    private String name;
    private String surName;
    private String pesel;

    public User(String name, String surName, String pesel) {
        this.name = name;
        this.surName = surName;
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return name + " " + surName + " " + pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surName, user.surName) &&
                Objects.equals(pesel, user.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surName, pesel);
    }
}
