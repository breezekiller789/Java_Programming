//  建立汽車的class，我的汽車碳足跡計算方法是，汽車的油耗公升數* 0.785
//  然後油耗公升數的算法是，汽車公里數除以14.5，這是一般自小客車的油耗指數，
//  我參考的是台灣2019的法規規定，這算完就會是油耗公升數，參考資料在下面。
//  https://wiki.mbalib.com/zh-tw/%E7%A2%B3%E8%B6%B3%E8%BF%B9
//  https://am.u-car.com.tw/49257.html

public class Car implements CarbonFootprint{
    private final double factor = 0.785;
    private int distance;
    private final double distance_to_fuel_factor = 1/14.5;    //  1L of gas can averagely run 15.2 kilometer

    // default constructor
    public Car(int distance){
        if(distance >= 0){
            this.distance = distance;
        }
        else
            throw new IllegalArgumentException("輸入資料皆須為正");
    }
    // 取值
    public int getDistance(){
        return this.distance;
    }
    //  計算碳足跡。
    @Override
    public int getCarbonFootprint(){
        return (int)(distance * distance_to_fuel_factor) * 1000;    //  公斤轉公克要乘以一千
    }
    @Override
    public String toString(){
        return String.format("This class is %s, its CarbonFootprint is %d grams total",
                this.getClass().getName(), getCarbonFootprint());
    }
}
