import java.util.ArrayList;

public class LibraryManager {
    //Collection to store books
    private ArrayList<Book> bookList = new ArrayList<>();

    //Method to add a book to the collection
    public void addBook(Book book) throws Exception {
        for (Book b : bookList) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
                throw new Exception("Book already exists!");
            }
        }
        bookList.add(book);
        System.out.println("Book added successfully!");
    }

    //Method to list all books
    public void listBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books available");
        } else {
            System.out.println("Listing all books");
            for (Book book : bookList) {
                System.out.println(book);
            }
        }
    }

    //Method to search for a book by title
    public Book searchBook(String title) throws BookNotFoundException {
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with title '" + title + "'  not found. ");
    }

    //Method to remove a book by title
    public void removeBook(String title) {
        try {
            Book book = searchBook(title);
            bookList.remove(book);
            System.out.println("Book removed successfully");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
