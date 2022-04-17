import java.util.Date;
import java.util.Vector;


public class bill {
    String issuerID;
    String date;
    String name;
    Vector<Item> items=new Vector<Item>();
    Integer discount;
    Float amtPay;
    public bill(String issuerID, String date, String name, Vector<Item>v,Integer discount,Float amtPay)
    {
        this.issuerID=issuerID;
        this.date=date;
        this.name=name;
        this.items=v;
        this.discount=discount;
        this.amtPay=amtPay;
    }
     public void addBook(Item item)
     {
        items.addElement(item);
     }

     public void remBook(Item item)
     {
        items.remove(item);
     }

}
