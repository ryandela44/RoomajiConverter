import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws IOException {
        ArrayList<String> roomaji = new ArrayList<>();
        ArrayList<ArrayList<String>> hiraganaKatagana = new ArrayList<>();
        HashMap<String, String> hiraganaTable = new HashMap<>();
        HashMap<String, String> kataganaTable = new HashMap<>();
        //scan input file
        Reader.scan(roomaji);
        //parse through the tables and store content in hasmaps
        Reader.parseHiragana(hiraganaTable);
        Reader.parseKatagana(kataganaTable);
        //converts from roomajo to hiragana/Katagana
        Convert.convert(roomaji, kataganaTable, hiraganaTable, hiraganaKatagana);
        //create and write resul in file
        Writer.open();
        Writer.write(hiraganaKatagana);
    }

}