import java.io.*;
import java.util.*;
public class Open_File{

    public static void main(String[] args){

        File dir = new File("./");//建立目錄物件
		String contents[]=dir.list();//取得目錄中的目錄與檔案陣列

		if(!dir.isDirectory()){//如果他"不"是目錄就回傳true，並說不是目錄，其餘false
			System.out.println("Not a directory");
		}else if (contents.length==0){//contents.length==0代表沒有任何檔案或是目錄，秀出為空
			System.out.println("目錄"+dir.getName()+"內無檔案");
		}else{
			for(int i = 0;i<contents.length; i++){//目錄和檔案名稱一個一個列出來
				System.out.println(contents[i]);
			}
		}
    }

}
