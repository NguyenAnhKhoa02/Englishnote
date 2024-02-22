package englishnote;

import java.util.ArrayList;

public class fileWord extends fileOptionCommon {
    fileWord() {
        super(file_Path);
    }

    // set Array from list to write file
    protected void setArrWordFromList(ArrayList<String> arrayList) {
        writeArrayListToFille(arrayList);
    }

    // return the arrlist after read file
    public ArrayList<String> getArrList_Word() {
        return getArrListFile();
    }

    private static String file_Path = "..\\englishnote\\src\\main\\java\\fileEnglishNote\\word.txt";
}
