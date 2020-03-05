package Library.model;

import java.time.MonthDay;
import java.util.Objects;

public class Magazine extends Publication {

    public static final String TYPE = "Magazyn";
    private MonthDay monthDay;
    private String language;

    public Magazine(String tytul, int dataWydania, String wydawca, int month, int day, String language) {
        super(tytul, dataWydania, wydawca);
        this.monthDay = MonthDay.of(month, day);
        this.language = language;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public MonthDay getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(MonthDay monthDay) {
        this.monthDay = monthDay;
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
                monthDay.getDayOfMonth() + "; " +
                monthDay.getMonthValue() + "; " +
                language;
    }

    @Override
    public String toString() {
        return "Magazine{" + super.toString() +
                ", month=" + monthDay.getMonth() +
                ", day=" + monthDay.getDayOfMonth() +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine)) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(monthDay, magazine.monthDay) &&
                Objects.equals(language, magazine.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), monthDay, language);
    }
}
