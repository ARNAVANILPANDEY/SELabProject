import java.awt.print.Book;
import java.util.Vector;

public class inventory {
    Vector<book> Books=new Vector<book>();
    public void addBook(book Book)
    {
        if(findBook(Book)>-1)
        {
            (Books.get(findBook(Book)).quantity)++;
        }
        else
            Books.addElement(Book);
    }
    public int findBook(book Book)
    {
        return Books.indexOf(Book);
    }

    public void genStats(book Book)
    {
        System.out.println("Book Name: "+ Book.name + "     Publisher: "+Book.publication +
                "    ISBN: "+Book.isbn+"    Copies Sold:"+ Book.soldCopies+
                "   Sales Revenue:"+(Book.price * Book.soldCopies));
    }



}
