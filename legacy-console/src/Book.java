public class Book {
    // Attributes of the Book class
    private String title;
    private String author;
    private double price;
    private int quantity;

    //Constructor to initialize Book attributes
    public Book(String title,String author,double price, int quantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    //Getter for the book's title
    public String getTitle() {
        return title;
    }

    //setter for the book's title
    public void setTitle(String title) {
        this.title = title;
    }

    //Getter for the book's author
    public String getAuthor() {
        return author;
    }

    //Setter for the book's author
    public void setAuthor(String author) {
        this.author = author;
    }

    //Getter for the book's price
    public double getPrice() {
        return price;
    }

    //Setter for the book's price
    public void setPrice(double price){
        this.price = price;
    }

    //Getter for the quantity
    public int getQuantity(){
        return quantity;
    }

    //Setter for the quantity
    public void setQuantity(int quantity){
       this.quantity = quantity;
    }

    //Override toString() method for displaying book details
    @Override
    public String toString(){
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
