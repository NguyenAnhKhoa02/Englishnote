package englishnote;

import java.util.ArrayList;

public class managePathFile extends filePathImage {
    managePathFile() {
        path_Image = getPathImage();
    }

    public void setPathFile() {
        setPathImage(path_Image);
    }

    public ArrayList<String> getPathFile() {
        return path_Image;
    }

    private ArrayList<String> path_Image;
}
