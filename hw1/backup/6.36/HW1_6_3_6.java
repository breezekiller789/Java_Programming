//  405410100   資工系  黃晉威

import java.util.*;

public class HW1_6_3_6{

    public static void main(String[] args){
        //  新增一個scanner物件
        Scanner sc = new Scanner(System.in);
        //  先把要印的訊息都先列好
        String[] Correct_Strings = {"Very good!", "Excellent!", "Nice work!",
            "Keep up the good work!"};
        String[] Wrong_Strings = {"No. Please try again.", "Wrong. Try once more."
            , "Don't give up!", "No. Keep trying."};
        int x=0, y=0, result=0;
        try{
            while(true){
                //  呼叫getRandomNumberInRange()來拿一個區間內的隨機值
                x = getRandomNumberInRange(1, 10);
                y = getRandomNumberInRange(1, 10);
                System.out.println("How much is "+x+" times "+y+" ?");
                result = sc.nextInt();
                while(true){
                    //  一拿到值就進來，看是不是對的
                    if(result != x*y){
                        //  只要一錯，就進去迴圈，因為可以允許他重複錯多次。
                        while(true){
                            //  在印之前，先隨機拿一個0~3之間的值，再以這個值
                            //  為index，去陣列裡面取出訊息並印出
                            int wrong_i = getRandomNumberInRange(0, 3);
                            System.out.println(Wrong_Strings[wrong_i]);
                            result = sc.nextInt();
                            if(result != x*y){
                                continue;
                            }
                            else
                                break;
                        }
                    }
                    //  是對的就印完後出去
                    else{
                        //  在印之前，先隨機拿一個0~3之間的值，再以這個值
                        //  為index，去陣列裡面取出訊息並印出
                        int correct_j = getRandomNumberInRange(0, 3);
                        System.out.println(Correct_Strings[correct_j]);
                        break;
                    }
                }
            }
        }
        //  萬一使用者輸入不是數字，就印出這個錯誤訊息
        catch(InputMismatchException e){
            System.out.println("Input must be integer!");
            return;
        }
    }

    //  回傳一個區間內的隨機值
    private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
