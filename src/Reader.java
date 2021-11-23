import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Reader {
    //Reads the input roomaji file
    public static void scan(ArrayList<String> arrayList) throws FileNotFoundException {
        File myfile = new File("roomaji.txt");
        Scanner sc = new Scanner(myfile);
        String data;
        while (sc.hasNextLine()) {
            data = sc.nextLine();
            arrayList.add(data);
        }
        sc.close();
    }

    //Goes through the Hiragana table and puts the content in a Hashmap to be accessed when converting
    public static void parseHiragana(HashMap<String, String> map) throws IOException {
        File myfile = new File("Hiragana.txt");

        BufferedReader br = null;
        br = new BufferedReader(new FileReader(myfile));

        String line = null;

        // read file line by line
        while ((line = br.readLine()) != null) {

            // split the line by :
            String[] parts = line.split(":");

            // first part is roomaji, second is Katagana
            String name = parts[0].trim();
            String number = parts[1].trim();

            // put name, number in HashMap if they are
            // not empty
            if (!name.equals("") && !number.equals(""))
                map.put(name, number);
        }
    }

    //Goes through the Katagana table and puts the content in a Hashmap to be accessed when converting
    public static void parseKatagana(HashMap<String, String> map) throws IOException {
        File myfile = new File("Katagana.txt");

        BufferedReader br = null;
        br = new BufferedReader(new FileReader(myfile));

        String line = null;

        // read file line by line
        while ((line = br.readLine()) != null) {

            // split the line by :
            String[] parts = line.split(":");

            // first part is roomaji, second is Katagana
            String name = parts[0].trim();
            String number = parts[1].trim();

            // put name, number in HashMap if they are
            // not empty
            if (!name.equals("") && !number.equals(""))
                map.put(name, number);
        }
    }
}
