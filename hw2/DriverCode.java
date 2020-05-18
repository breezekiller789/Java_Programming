import java.util.ArrayList;

//  Driver code
public class DriverCode{
    public static void main(String[] args){
        ArrayList<CarbonFootprint> myList = new ArrayList<CarbonFootprint>();

        myList.add(new Building(7, 20, 10));
        myList.add(new Building(12, 19, 15));
        myList.add(new Building(3, 5, 12));
        myList.add(new Bicycle(30));
        myList.add(new Bicycle(45));
        myList.add(new Bicycle(19));
        myList.add(new Car(103131));
        myList.add(new Car(7777));
        myList.add(new Car(80000));

        for(CarbonFootprint footprint : myList){
            System.out.println(footprint.toString());
        }
    }
}
