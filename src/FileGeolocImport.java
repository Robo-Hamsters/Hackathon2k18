import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileGeolocImport {
    private String fileName;
    private FileInputStream fileInput;

    public FileGeolocImport()
    {
        this.fileInput = null;
        this.fileName = null;
    }
    public FileGeolocImport(String fileName)
    {
        this.fileName = fileName;
        try {
            this.fileInput = new FileInputStream(this.fileName);
            importData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void importData()
    {

    }
}
