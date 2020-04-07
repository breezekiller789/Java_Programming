//  405410100   資工系  黃晉威

import java.util.*;

public class HW1_6_3_8{

    public static void main(String[] args){
        //  新增一個scanner物件
        Scanner sc = new Scanner(System.in);
        //  先把要印的訊息都先列好
        String[] Correct_Strings = {"Very good!", "Excellent!", "Nice work!",
            "Keep up the good work!"};
        String[] Wrong_Strings = {"No. Please try again.", "Wrong. Try once more."
            , "Don't give up!", "No. Keep trying."};
        int x=0, y=0, result=0, Cnt=0;
        float Correct_Cnt=0;
        try{
            while(true){
                System.out.println("Please enter difficulty level");
                int level = sc.nextInt();
                if(level == 0){
                    System.out.println("Level must be at least 1!");
                    System.exit(1);
                }
                Correct_Cnt=0;
                Cnt=0;
                //  回合計數，輸入十次就結束
                while(Cnt<10){
                    //  先去拿到位數值之後，再以這些區間去拿隨機值
                    //  如果level是3，那就是10^2 ~ 10^3-1
                    x = getRandomNumberInRange(levelDigit(level-1), levelDigit(level));
                    y = getRandomNumberInRange(levelDigit(level-1), levelDigit(level));
                    System.out.println("How much is "+x+" times "+y+" ?");
                    result = sc.nextInt();
                    Cnt++;  //  輸入一次Cnt就加一
                    //  一拿到值就進來，看是不是對的
                    if(result != x*y){
                        //  在印之前，先隨機拿一個0~3之間的值，再以這個值
                        //  為index，去陣列裡面取出訊息並印出
                        int wrong_i = getRandomNumberInRange(0, 3);
                        System.out.println(Wrong_Strings[wrong_i]);
                        //  如果一錯再錯就會進這個回圈
                        while(Cnt<10){
                            int result1 = sc.nextInt();
                            Cnt++;  //  輸入一次Cnt就加一
                            if(result1 != x*y){
                                int idx = getRandomNumberInRange(0, 3);
                                System.out.println(Wrong_Strings[idx]);
                                continue;
                            }
                            else{
                                int idx = getRandomNumberInRange(0, 3);
                                System.out.println(Correct_Strings[idx]);
                                //  答對一題就加一
                                Correct_Cnt++;
                                break;
                            }
                        }
                    }
                    else{
                        //  在印之前，先隨機拿一個0~3之間的值，再以這個值
                        //  為index，去陣列裡面取出訊息並印出
                        int correct_j = getRandomNumberInRange(0, 3);
                        System.out.println(Correct_Strings[correct_j]);
                        //  答對一題就加一
                        Correct_Cnt++;
                    }
                }
                //  會來這裡代表十次都輸入完畢了，再依照要求印出訊息
                if(Correct_Cnt < 7.5){
                    System.out.println("======================================");
                    System.out.println("Please ask your teacher for extra help.");
                }
                else{
                    System.out.println("=======================================================");
                    System.out.println("Congratulations, you are ready to go to the next level!");
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

    //  回傳難度的位數，1位數就是0~9，也就是10^0~10^1-1
    private static int levelDigit(int level){
        int ret = 1;
        //  0次方就直接回傳0
        if(level == 0){
            return 0;
        }
        for(int i=0; i<level; i++){
            ret = ret * 10;
        }
        return ret-1;
    }

}
