import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Convert {

    public static  void convert(ArrayList<String> roomaji, HashMap<String,String> katmap,HashMap<String,String> hiramap, ArrayList<ArrayList<String>> hiraganaKatagana) {
        String data;
        String buffer = "";
        ArrayList<String> line = new ArrayList<>();

//verfie si
        for (int i = 0; i < roomaji.size(); i++) {
            //puts the content from the roomaji into a string
            data = roomaji.get(i);
            //data = data.replaceAll("\\s","");

            for (int j = 0; j < data.length(); j++) {
                //checks if the array index is empty to avoid exception
                    if (data.charAt(j) != ' ') {
                        //adds each character into a buffer
                        buffer += data.charAt(j);
                        //checks if it is a valid vowel
                        if(checkvowels(buffer, katmap,hiramap, line)) {
                            buffer = "";
                        }
                        //checks if it is a n'y or a special case
                        else if(checkn(buffer, katmap,hiramap, line)) {
                            buffer = "";
                        }
                        //for all the other valid options for the Hiragana and Katagana tables
                        else if(check(buffer, katmap,hiramap, line))
                           buffer ="";
                    }
            }
            //Add the converted line in an arraylist
            ArrayList<String> temp = new ArrayList<>(line);
            hiraganaKatagana.add(temp);
            line.clear();
        }
    }
    //checks if character correponds to the Hiragana and Katagana table
    public static boolean check(String buffer,HashMap<String,String> katmap,HashMap<String,String>hiramap,ArrayList<String> line) {
        //boolean to indicate is a character is valid or not
        boolean ischeck = false;
        String temp = String.valueOf(buffer.charAt(0));
        //for the Katagana we check if the first letter is uppercase and and the length of the buffer is bigger than 1
        if(Character.isUpperCase(buffer.charAt(0)) && buffer.length()>1) {
            //we convert the rest to lower case
            for (int i = 1; i < buffer.length(); i++)
                temp += Character.toLowerCase(buffer.charAt(i));
            //We check if the content of the buffer is the same as a key in the Katagana table
            if (katmap.containsKey(temp)) {
                //we add the Katagana equivalent to the current line
                line.add(katmap.get(temp));
                //we change ischeck to true to comfirm that the item was found in the map
                ischeck = true;
            }
        }
        //we check if the first letter is lower case and we repeat the same procedure for as for the uppercase but we search in the Hiragana table
        else if(Character.isLowerCase(buffer.charAt(0)) && buffer.length()>1) {
            for (int i = 1; i < buffer.length(); i++)
                temp += Character.toLowerCase(buffer.charAt(i));
            if (hiramap.containsKey(temp)) {
                line.add(hiramap.get(temp));
                ischeck = true;
            }
        }
        temp = "";
    return  ischeck;
    }
    //Checks if the roomaji correscomps to a valid vowel
    public static boolean checkvowels(String buffer,HashMap<String,String> katmap,HashMap<String,String>hiramap,ArrayList<String> line) {
        boolean ischeck = false;
        String temp = String.valueOf(buffer.charAt(0));
        //Same as before if the first character is uppercase we search in the Katagana table and if it is lowercase search in the Hiragana table.
        if(Character.isUpperCase(buffer.charAt(0)) && (buffer.charAt(0) == 'A' || buffer.charAt(0) == 'E' || buffer.charAt(0) == 'I' || buffer.charAt(0) == 'O' || buffer.charAt(0) == 'U')) {
            if (katmap.containsKey(temp)) {
                line.add(katmap.get(temp));
                ischeck = true;
            }
        }
        else if(Character.isLowerCase(buffer.charAt(0)) && (buffer.charAt(0) == 'a' || buffer.charAt(0) == 'e' || buffer.charAt(0) == 'i' || buffer.charAt(0) == 'o' || buffer.charAt(0) == 'u')) {
            if (hiramap.containsKey(temp)) {
                line.add(hiramap.get(temp));
                ischeck = true;
            }
        }
        temp = "";

        return ischeck;
    }
    //Checks if it is a pecial case of ya,yo or yu aswell as if the roomanij is n'
    //since they are special case we don't check in thwe tables but directly add the correposnding Hiragana and Katagana to the line arraylist.
    public static boolean checkn(String buffer,HashMap<String,String> katmap,HashMap<String,String>hiramap,ArrayList<String> line) {
        boolean ischeck = false;
        String temp = String.valueOf(buffer.charAt(0));
        if(buffer.length()>1 && buffer.charAt(0) == 'N' && buffer.charAt(1) == '\'' ) {
                line.add("12531");
                ischeck = true;
        }
        else if(buffer.length()>1 && buffer.charAt(0) == 'n' && buffer.charAt(1) == '\'') {
                line.add("12435");
                ischeck = true;
        }
        else if (buffer.length()>1 ) {
            boolean b = buffer.charAt(1) == 'a' || buffer.charAt(1) == 'u' || buffer.charAt(1) == 'o' || buffer.charAt(1) == 'A' || buffer.charAt(1) == 'U' || buffer.charAt(1) == 'O';
            if(buffer.charAt(0) == 'Y' && b) {
                checkExceptions(buffer,temp,line);
                ischeck = true;
            }

            else if(buffer.charAt(0) == 'y' && b) {
                checkExceptions(buffer,temp,line);
                ischeck = true;
            }
        }
        temp = "";
        return  ischeck;
    }
//checks for exceptions just a switch statement with the corresponding Hiragana and Katagana values`
    public static void checkExceptions(String buffer,String temp,ArrayList<String> line) {
        for (int i = 1; i < buffer.length(); i++)
            temp += Character.toLowerCase(buffer.charAt(i));
        switch (temp) {
            case "ya" -> line.add("12419");
            case "Ya" -> line.add("12515");
            case "yu" -> line.add("12421");
            case "Yu" -> line.add("12517");
            case "yo" -> line.add("12423");
            case "YO" -> line.add("12519");
        }
    }
    }
