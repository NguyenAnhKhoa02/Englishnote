package englishnote;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class manageFileImage extends fileImage {
    manageFileImage() {
        str_dir = getArrList_DirFileImage();
    }

    public void setToFile() {
        setArrDirFile(str_dir);
    }

    public ArrayList<String> getDirFile() {
        return str_dir;
    }

    private ArrayList<String> str_dir;
}
