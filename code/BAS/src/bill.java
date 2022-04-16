import java.util.Date;
import java.util.Vector;

public class bill {
    String issuerID;
    Date date=new Date();
    String name;
    Vector<Item> items=new Vector<Item>();
    Integer discount;
    Float amtPay;
     public void addBook(Item item)
     {
        items.addElement(item);
     }

     public void remBook(Item item)
     {
        items.remove(item);
     }

}
