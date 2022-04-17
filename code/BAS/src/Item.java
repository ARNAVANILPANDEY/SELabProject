public class Item {
    String bookName;
    String isbn;
    Integer quantity;
    Float rate;
    Float amount;

    public Item(String bookName, String isbn, Integer quantity, Float rate, Float amount)
    {
        this.bookName=bookName;
        this.isbn=isbn;
        this.quantity=quantity;
        this.rate=rate;
        this.amount=amount;
    }
}
