public class Main {
    public static void main(String[] args) {
        // Create an instance of LibraryManager
        LibraryManager manager = new LibraryManager();

        // Create sample books
        Book book1 = new Book("Java Programming", "John Doe", 45.99, 10);
        Book book2 = new Book("Data Structures", "Jane Smith", 39.99, 5);

        // List all books (should be empty initially)
        System.out.println("Listing all books:");
        manager.listBooks();

        try {
            // Add books
            System.out.println("\nAdding books...");
            manager.addBook(book1);
            manager.addBook(book2);
            System.out.println("Books added successfully!");
        } catch (Exception e) {
            System.out.println("Error while adding books: " + e.getMessage());
        }

        // Search for a book (should throw BookNotFoundException for 'Advanced Java')
        System.out.println("\nSearching for 'Advanced Java':");
        try {
            Book foundBook = manager.searchBook("Advanced Java");
            System.out.println("Book found: " + foundBook);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // List all books again to confirm added books
        System.out.println("\nListing all books after adding:");
        manager.listBooks();

        // Remove a book
        System.out.println("\nRemoving 'Java programming':");
        try {
            manager.removeBook("Java Programming"); //Removing an existing book
        } catch (Exception e) {
            System.out.println("Error while removing book: " + e.getMessage());
        }

        //Try to remove a non-existent book
        System.out.println("\nAttempting to remove 'Advanced Java'");
        try {
            manager.removeBook("Advanced Java");  //Removing an existing book
        } catch (Exception e) {
            System.out.println("Error while removing book: " + e.getMessage());
        }

        //List books again to confirm removal
        System.out.println("\nListing all books after removal: ");
        manager.listBooks();
    }
}
