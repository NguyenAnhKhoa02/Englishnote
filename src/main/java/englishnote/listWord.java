package englishnote;

import java.util.ArrayList;

public class listWord extends fileWord {
    listWord() {
        arr_ListWord = getArrList_Word();
    }

    public void setArrList() {
        setArrWordFromList(arr_ListWord);
    }

    public ArrayList<String> getArrList() {
        return arr_ListWord;
    }

    public ArrayList<String> getArrayListWord() {
        ArrayList<String> wordList = new ArrayList<>();

        int i = 0;
        while (i < arr_ListWord.size()) {
            wordList.add(arr_ListWord.get(i).split("@")[0]);
            i++;
        }

        return wordList;
    }

    public ArrayList<String> getArrayListMeaning() {
        ArrayList<String> wordList = new ArrayList<>();

        int i = 0;
        while (i < arr_ListWord.size()) {
            wordList.add(arr_ListWord.get(i).split("@")[1]);
            i++;
        }

        return wordList;
    }

    private ArrayList<String> arr_ListWord;
}
