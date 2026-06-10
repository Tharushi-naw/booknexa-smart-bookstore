public class Order {
    private int orderId;
    private String bookTitle;
    private int quantity;
    private String customerName;

    //Constructor to initialize order attributes
    public Order(int orderId, String bookTitle, int quantity, String customerName){
        this.orderId = orderId;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.customerName = customerName;
    }
    //Getter for orderId
    public int getOrderId(){
        return orderId;
    }

    //Setter for orderId
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    //Getter for book title
    public String getBookTitle(){
        return bookTitle;
    }

    //Setter for book title
    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }

    //Getter for quantity
    public int getQuantity(){
        return quantity;
    }

    //Setter for quantity
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    //Getter for customer name
    public String getCustomerName(){
        return customerName;
    }

    //Setter for customer name
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    //override toString() method to display order details
    @Override
    public String toString() {
        return "order{" +
                "orderId=" + orderId +
                ",bookTitle='" + bookTitle + '\'' +
                ",quantity=" + quantity +
                ",customerName='" + customerName + '\'' +
                '}';
    }
}
