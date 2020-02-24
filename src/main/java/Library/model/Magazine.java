package Library.model;

import java.util.Objects;

public class Magazine extends Publication {

    public static final String TYPE = "Magazyn";
    private String month;
    private int day;
    private String language;

    public Magazine(String tytul, int dataWydania, String wydawca, String month, int day, String language) {
        super(tytul, dataWydania, wydawca);
        this.month = month;
        this.day = day;
        this.language = language;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toCsv() {
        return TYPE + "; " +
                getTytul() + "; " +
                getDataWydania() + "; " +
                getWydawca() + "; " +
                day + "; " +
                month + "; " +
                language;
    }

    @Override
    public String toString() {
        return "Magazine{" + super.toString() +
                ", month=" + month +
                ", day=" + day +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine)) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return month == magazine.month &&
                day == magazine.day &&
                Objects.equals(language, magazine.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), month, day, language);
    }
}
