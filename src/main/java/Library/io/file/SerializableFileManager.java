package Library.io.file;

import Library.exeption.DataExportExeption;
import Library.exeption.DataImportExeption;
import Library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {

    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {

        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);

        ) {
            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportExeption("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportExeption("Blad odczytu pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportExeption("Niezgodnosc formatu danych " + FILE_NAME);
        }
    }

    @Override
    public void saveData(Library library) {
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportExeption("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportExeption("Blad zapisu danych do pliku " + FILE_NAME);
        }
    }
}
