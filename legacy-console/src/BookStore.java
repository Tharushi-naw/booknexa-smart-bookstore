import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookStore {
    //List to store books
    private List<Book> books;

    //Constructor to initialize the book list
    public BookStore(){
        this.books = new ArrayList<>();
    }

    //method to add a new book to the store
    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    //method to search for a book by title
    public Book searchBook(String title) throws BookNotFoundException{
        for (Book book : books){
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        //Throw custom exception if the book is not found
        throw new BookNotFoundException ("Book with title '" + title + "' not found");
    }

    //method to save books to a file
    public void saveBooksToFile(String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Book book : books) {
                //Write book details in a CSV format: title,author,price,quantity
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getPrice() + "," + book.getQuantity() + "\n");
            }
            System.out.println("Books saved successfully to " + filename);
        } catch (IOException e){
            System.err.println("Error saving books to file: " + e.getMessage());
        }
    }

    //method to load books from a file
    public void loadBooksFromFiles(String filename) {
        books.clear(); //Clear existing books before loading new ones
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    //Parse book details and add to the list
                    String title = parts[0];
                    String author = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    books.add(new Book(title, author, price, quantity));
                }
            }
            System.out.println("Books loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading books from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing book data: " + e.getMessage());
        }
    }
}
