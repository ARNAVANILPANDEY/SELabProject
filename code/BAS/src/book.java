import java.util.Scanner;

public class book
{
    String isbn;
    String author;
    String publication;
    String name;
    Float price;
    Integer quantity;
    Float inventoryLevel;
    Integer soldCopies;

    public void putDetails(String isbn,String author,String publication, String name,Float price, Integer quantity)
    {
        this.author=author;
        this.isbn=isbn;
        this.publication=publication;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    public void showDetails()
    {
        System.out.println("ISBN: "+isbn+" Name: "+name+" Price: "+ price+ " Author: "+author+
                " Publication: "+publication+ " Quantity: "+quantity);
    }

    public void updatePrice(Float price)
    {
        this.price=price;
    }

    public void updateQuan(Integer quantity)
    {
        this.quantity=quantity;
    }


}


