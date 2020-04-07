import java.util.*;

public class ID{

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String id = scanner.next();
        String msg=null;
        try{
            String gender=id.substring(1,2);
            int k=Integer.parseInt(gender);

            if(k==1){
                msg="男";
            }else if(k==2){
                msg="女";
            }else{
                msg="輸入錯誤";
            }
        }
        catch(Exception e){
            msg="輸入資料長度應足夠且身份證第二碼應是數字";
        }
        finally{
            System.out.println(msg);
        }
    }
    
}
