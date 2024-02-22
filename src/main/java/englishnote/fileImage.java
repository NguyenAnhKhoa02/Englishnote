package englishnote;

import java.util.ArrayList;

public class fileImage extends fileOptionCommon {

    fileImage() {
        super(path_File);
        // TODO Auto-generated constructor stub
    }

    // set Array from list to write file
    protected void setArrDirFile(ArrayList<String> arrayList) {
        writeArrayListToFille(arrayList);
    }

    // return the arrlist after read file
    public ArrayList<String> getArrList_DirFileImage() {
        return getArrListFile();
    }

    private static String path_File = "..\\englishnote\\src\\main\\java\\Setting\\fileImage.txt";
}
