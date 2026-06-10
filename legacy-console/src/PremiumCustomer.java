public class PremiumCustomer extends Customer {
    //Attribute
    private double discountRate;

    //Constructor to initialize PremiumCustomer attributes
    public PremiumCustomer(String name, String username, String password, double discountRate){
        super(name, username, password);    //call parent constructor
        this.discountRate= discountRate;
    }

    //Getter for discount rate
    public double getDiscountRate(){
        return discountRate;
    }

    //Setter for discount rate
    public void setDiscountRate (double discountRate){
        this.discountRate = discountRate;
    }

    //override toString() method to include premium specific details
    @Override
    public String toString() {
        return "PremiumCustomer{" +
                "name='" + getName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", discountRate=" + discountRate +
                '}';
    }
}
