public class OrderManager {
    //Method to place an order
    public void placeOrder(BookStore store, String title, int quantity){
        try {
            //Attempt to find the book
            Book book = store.searchBook(title);

            //Check if enough stock is available
            if (book.getQuantity() < quantity) {
                throw new IllegalArgumentException("Insufficient stock for the book: " + title);
            }
            //Update the book's stock and display a success message
            book.setQuantity(book.getQuantity() - quantity);
            System.out.println("Order placed successfully for " + quantity + " copies of " + title);
        }catch (BookNotFoundException e){
            //handle the case where the book is not found
            System.err.println("Error : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Handle invalid order quantities
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Catch-all for any other unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
