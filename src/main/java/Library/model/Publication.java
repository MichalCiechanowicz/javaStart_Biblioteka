package Library.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication implements Serializable {

    private String tytul;
    private int dataWydania;
    private String wydawca;

    public Publication(String tytul, int dataWydania, String wydawca) {
        this.tytul = tytul;
        this.dataWydania = dataWydania;
        this.wydawca = wydawca;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(int dataWydania) {
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
        return "tytul='" + tytul + '\'' +
                ", dataWydania=" + dataWydania +
                ", wydawca='" + wydawca + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;
        Publication that = (Publication) o;
        return dataWydania == that.dataWydania &&
                Objects.equals(tytul, that.tytul) &&
                Objects.equals(wydawca, that.wydawca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tytul, dataWydania, wydawca);
    }
}
