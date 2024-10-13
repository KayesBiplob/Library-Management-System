import javax.swing.JOptionPane;
import java.util.ArrayList;

// Define a LibraryItem interface
interface LibraryItem {
    void checkOut();
    void returnItem();
    boolean isAvailable();
}

// Create a Book class implementing the LibraryItem interface
class Book implements LibraryItem {
    private String bookName;
    private String author;
    private boolean checkedOut;

    public Book(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
        this.checkedOut = false;
    }

    @Override
    public void checkOut() {
        checkedOut = true;
    }

    @Override
    public void returnItem() {
        checkedOut = false;
    }

    @Override
    public boolean isAvailable() {
        return !checkedOut;
    }

    @Override
    public String toString() {
        return "Book Name: " + bookName + " | Author: " + author;
    }
}

public class LibraryManagementSystem {
    private static ArrayList<LibraryItem> library = new ArrayList<>();

    public static void addBook() {
        String bookName = JOptionPane.showInputDialog("Enter the Name of the book:");
        String author = JOptionPane.showInputDialog("Enter the author of the book:");
        LibraryItem book = new Book(bookName, author);
        library.add(book);
        JOptionPane.showMessageDialog(null, "Book Added to the Library:\n" + book.toString());
    }

    public static void displayBooks() {
        StringBuilder libraryInfo = new StringBuilder();
        for (LibraryItem item : library) {
            if (item instanceof Book) {
                libraryInfo.append(item.toString()).append(" | Available: ").append(item.isAvailable()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, "Books in the Library:\n" + libraryInfo.toString());
    }

    public static void checkOutBook() {
        String input = JOptionPane.showInputDialog("Enter the name of the book you want to check out:");
        for (LibraryItem item : library) {
            if (item instanceof Book && item.isAvailable() && ((Book) item).bookName.equalsIgnoreCase(input)) {
                item.checkOut();
                JOptionPane.showMessageDialog(null, "Book checked out:\n" + item.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Book not found or already checked out.");
    }

    public static void returnBook() {
        String input = JOptionPane.showInputDialog("Enter the name of the book you want to return:");
        for (LibraryItem item : library) {
            if (item instanceof Book && !item.isAvailable() && ((Book) item).bookName.equalsIgnoreCase(input)) {
                item.returnItem();
                JOptionPane.showMessageDialog(null, "Book returned:\n" + item.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Book not found or already available.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        while (true) {
            String[] options = {"Add Book", "Display Books", "Check Out Book", "Return Book", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option", "Library Management System",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            try {
                if (choice == 0) {
                    JOptionPane.showMessageDialog(null, "You choose Add Book!");
                    addBook();
                }
                if (choice == 1) {
                    JOptionPane.showMessageDialog(null, "You choose Display Books!");
                    displayBooks();
                }
                if (choice == 2) {
                    JOptionPane.showMessageDialog(null, "You choose Check Out Book!");
                    checkOutBook();
                }
                if (choice == 3) {
                    JOptionPane.showMessageDialog(null, "You choose Return Book!");
                    returnBook();
                }
                if (choice == 4) {
                    JOptionPane.showMessageDialog(null, "You choose Exit!");
                    System.exit(0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
            }
        }
    }
}
