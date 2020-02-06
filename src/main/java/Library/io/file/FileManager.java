package Library.io.file;

import Library.model.Library;

public interface FileManager {

    Library importData();

    void saveData(Library library);

}
