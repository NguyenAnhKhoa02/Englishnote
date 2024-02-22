package englishnote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class fileOptionCommon {

    // read file
    fileOptionCommon(String path_File) {
        arr_ReaderFile = new ArrayList<>();
        this.path_File = path_File;

        try {
            file = new FileReader(path_File, StandardCharsets.UTF_8);
            file_Buffer = new BufferedReader(file);

            while ((str_Read = file_Buffer.readLine()) != null) {
                arr_ReaderFile.add(str_Read);
            }
        } catch (Exception e) {
            System.out.println("File is not opened");
        } finally {
            try {
                file_Buffer.close();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // write file
    protected void writeArrayListToFille(ArrayList<String> arrayList) {
        try {
            file_Writer = new FileWriter(path_File, StandardCharsets.UTF_8);
            file_BufferWriter = new BufferedWriter(file_Writer);

            for (String line : arrayList) {
                file_BufferWriter.append(line);
                file_BufferWriter.newLine();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                file_BufferWriter.close();
                file_Writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // return arr_ReaderFile after read file
    protected ArrayList<String> getArrListFile() {
        return arr_ReaderFile;
    }

    private FileReader file;
    private BufferedReader file_Buffer;
    private FileWriter file_Writer;
    private BufferedWriter file_BufferWriter;

    private String str_Read;
    private ArrayList<String> arr_ReaderFile;

    private String path_File;
}
