//Custom exception for handling book-related errors
public class BookNotFoundException extends Exception {
    //constructor that accepts an error message
    public BookNotFoundException(String message) {
        super(message);
    }
}
