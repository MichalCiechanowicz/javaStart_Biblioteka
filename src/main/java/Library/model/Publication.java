package Library.model;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public abstract class Publication implements Serializable, Comparable<Publication>, CsvConvertible {

    private String tytul;
    private Year dataWydania;
    private String wydawca;

    public Publication(String tytul, int dataWydania, String wydawca) {
        this.tytul = tytul;
        this.dataWydania = Year.of(dataWydania);
        this.wydawca = wydawca;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Year getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(Year dataWydania) {
        this.dataWydania = dataWydania;
    }

    public String getWydawca() {
        return wydawca;
    }

    public void setWydawca(String wydawca) {
        this.wydawca = wydawca;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "tytul='" + tytul + '\'' +
                ", dataWydania=" + dataWydania +
                ", wydawca='" + wydawca + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;
        Publication that = (Publication) o;
        return Objects.equals(tytul, that.tytul) &&
                Objects.equals(dataWydania, that.dataWydania) &&
                Objects.equals(wydawca, that.wydawca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tytul, dataWydania, wydawca);
    }

    @Override
    public int compareTo(Publication p) {
        return tytul.compareToIgnoreCase(p.tytul);
    }

}
