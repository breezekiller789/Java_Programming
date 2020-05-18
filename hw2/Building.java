//  定義Building的class
//  因為建築物的碳排放量實在太多變數了，網路上的公開資料也相對比較缺乏，所以這
//  個部份我就自己去假設，建築物的碳排放量我就假設是(每層樓有多少戶人家 * 樓層數
//  * 建築物年齡），我這是自己隨便亂設的，沒有實際依據。

public class Building implements CarbonFootprint{
    private int year; //  這棟建築從蓋好到現在，過了多久。
    private int household;  //  這棟建築現在有多少戶人家。
    private int layer;  //  有幾層樓。
    private final int grams_per_year = 102;  //  公共設施，例如電梯，公共廁所這些
    private final int grams_per_household = 432;    //  因為人的碳排放量比較高，所以設比較高。
    private final int grams_per_layer = 93; //  每一樓層都會有電燈、飲水機、洗衣機、烘衣機等等。

    // default constructor
    public Building (int year, int household, int layer){
        if(year >= 0 && layer >= 0 && household >= 0){
            this.year = year;
            this.household = household;
            this.layer = layer;
        }
        else
            throw new IllegalArgumentException("輸入資料必須皆大於零");
    }
    //  回傳參數
    public int getYear(){
        return this.year;
    }
    public int getHousehold(){
        return this.household;
    }
    public int getLayer(){
        return this.layer;
    }

    //  回傳建築物的碳排放量。
    @Override
    public int getCarbonFootprint(){
        return (grams_per_layer * layer) + (year * grams_per_year) + (grams_per_household * household);
    }
    @Override
    public String toString(){
        return String.format("This class is %s, its CarbonFootprint is %d grams per year",
                this.getClass().getName(), getCarbonFootprint());
    }
}
