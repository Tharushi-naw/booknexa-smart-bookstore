public class Customer {
    //Attributes
    private String name;
    private String username;
    private String password;

    //Constructor to initialize customer attributes
    public Customer(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
    }

    //Getter for customer's name
    public String getName(){
        return name;
    }

    //Setter for customer's name
    public void setName(String name){
        this.name = name;
    }

    //Getter for username
    public String getUsername(){
        return username;
    }

    //Setter for username
    public void setUsername(String username){
        this.username = username;
    }

    //Getter for password
    public String getPassword() {
        return password;
    }

    //Setter for password
    public void setPassword(String password){
        this.password = password;
    }

    //override toString() method to display basic customer details
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
