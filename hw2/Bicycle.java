//  定義Bicycle的class
//  腳踏車的碳足跡計算公式是，距離乘上每公里16g的二氧化碳，參考資料在底下
//  https://www.ourstreetsmpls.org/does_bike_commuting_affect_your_carbon_footprint_and_how_much

public class Bicycle implements CarbonFootprint{

    private int distance;
    private final int grams_per_kilometer = 16;

    //  default constructor
    public Bicycle(int distance){
        setDistance(distance);
    }
    //  設定腳踏車行走的距離。
    public void setDistance(int distance){
        if(distance >= 0)
            this.distance = distance;
        else
            throw new IllegalArgumentException("距離沒有負的");
    }
    //  回傳距離，如果需要看的話。
    public int getDistance(){
        return this.distance;
    }
    //  計算腳踏車的碳足跡
    @Override
    public int getCarbonFootprint(){
        return distance * grams_per_kilometer;
    }
    @Override
    public String toString(){
        return String.format("This class is %s, its CarbonFootprint is %d grams per kilometer",
                this.getClass().getName(), getCarbonFootprint(), distance);
    }
}
