// https://www.w3schools.com/java/java_files_read.asp
import java.io.*;
import java.util.*;

public class File_Exist{

    public static void main(String[] args){
        try {
            int n = 100;
            ArrayList<Integer> list = new ArrayList<Integer>(n);
            File myObj = new File("input1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextInt()) {
                int data = myReader.nextInt();
                list.add(data);
                // System.out.println(data);
            }
            for(int i=0; i<list.size(); i++){
                System.out.println(list.get(i));
            }
            myReader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File Open Failure");
            e.printStackTrace();
        }
    }

}
