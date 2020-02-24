package Library.io.file;

import Library.exeption.NoSuchFileTypeExeption;
import Library.io.ConsolPrinter;
import Library.io.DataReader;

public class FileManagerBuilder {

    private ConsolPrinter printer;
    private DataReader dataReader;

    public FileManagerBuilder(ConsolPrinter printer, DataReader dataReader) {
        this.printer = printer;
        this.dataReader = dataReader;
    }

    public FileManager build() {
        printer.printLine("Wybierz format pliku");
        FileType fileType = getFile();
        switch (fileType) {
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeExeption("Nie ma takiego rozszrzenia");
        }
    }

    private FileType getFile() {

        boolean typeOk = false;
        FileType result = null;

        do {
            printType();
            String type = dataReader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Nieprawidlowy rodzaj pliku, sprobuj jeszcze raz");
            }
        } while (!typeOk);
        return result;
    }

    private void printType() {
        FileType[] values = FileType.values();
        for (FileType value : values) {
            printer.printLine(value.name());
        }
    }
}
