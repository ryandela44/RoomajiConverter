import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    //Creates file
    public static void open() throws IOException {
        File myObj = new File("/home/ryan/IdeaProjects/RoomajiConverter/src/file.html");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else
            System.out.println("File already exists.");
    }
    //Writes in the html file
    public static void write(ArrayList<ArrayList<String>> hirakatanga) throws IOException {
        FileWriter myWriter = new FileWriter("/home/ryan/IdeaProjects/RoomajiConverter/file.html");
        String htmlheader = "<!DOCTYPE html>";
        htmlheader += "<html><head><meta charset=\"UTF-8\"><title>TP 2</title></head>";
        String htmlbody = "<body><hr><table>";
        String htmlfooter ="</table><hr></body></html>";

        myWriter.write(htmlheader);
        myWriter.write(htmlbody);
        myWriter.write(transpose(hirakatanga));
        myWriter.write(htmlfooter);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
    //transposes the roomaji format into the Hiragana/Katagana on the html file
    public static String transpose(ArrayList<ArrayList<String>> hirakatanga) {
        ArrayList<String> temp;
        String buffer = "";
        for (int i = hirakatanga.size()-1; i>=0; i--) {
            temp = hirakatanga.get(i);
            buffer += "<tr>";
            for(int j = 0; j<temp.size(); j++) {
                buffer += "<td>"+"&#"+temp.get(j)+";"+"</td>";
            }
            buffer += "</tr>";
        }
        return buffer;
    }
}
