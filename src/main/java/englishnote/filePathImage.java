package englishnote;

import java.util.ArrayList;

public class filePathImage extends fileOptionCommon {

    filePathImage() {
        super(path_File);
        // TODO Auto-generated constructor stub
    }

    protected void setPathImage(ArrayList<String> arrayList) {
        writeArrayListToFille(arrayList);
    }

    protected ArrayList<String> getPathImage() {
        return getArrListFile();
    }

    private static String path_File = "..\\englishnote\\src\\main\\java\\Setting\\pathImage.txt";
}
