package Library.model.comparator;

import Library.model.Publication;

import java.util.Comparator;

public class AlphabeticTitleComparator implements Comparator<Publication> {
    @Override
    public int compare(Publication p1, Publication p2) {
        if(p1==null&&p2==null){
            return 0;
        }else if(p1==null){
            return 1;
        }else if (p2==null){
            return -1;
        }return p1.getTytul().compareTo(p2.getTytul());
    }
}
