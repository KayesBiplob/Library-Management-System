package Final;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class LibraryManagementSystem implements Library, StudentActions, AdminActions {

	private static ArrayList<Book> library = new ArrayList<>();
	private static HashMap<String, String> credentials = new HashMap<>();
	private static boolean isAdminLoggedIn = false;
	private static boolean isStudentLoggedIn = false;

	static {
		// Initializing the credentials for both student and admin roles
		credentials = new HashMap<>();
		credentials.put("Student", "student123");
		credentials.put("Admin", "admin123");
	}

	@Override
	public void addBook() {
		String Name = JOptionPane.showInputDialog("Enter the Name of the book:");
		String author = JOptionPane.showInputDialog("Enter the Author of the book:");
		Book book = new Book(Name, author);
		library.add(book);
		JOptionPane.showMessageDialog(null, "Book added to the library: \n" + book.toString());
	}

	@Override
	public void viewLibrary() {
		StringBuilder libraryInfo = new StringBuilder("Library Contents:\n");
		for (Book book : library) {
			libraryInfo.append(book.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, libraryInfo.toString());
	}

	@Override
	public void searchBook() {
		// Prompts the user to enter the name of the book to be searched
		String searchQuery = JOptionPane.showInputDialog("Enter the Name of the book you want to search for:");
		// Initializes an ArrayList to store the search results
		ArrayList<Book> searchResults = new ArrayList<>();
		// Iterates through each Book in the library
		for (Book book : library) {
			// Checks if the Book's name (case-insensitive) contains the search query
			if (book.getBookName().toLowerCase().contains(searchQuery.toLowerCase())) {
				// Adds the Book to the searchResults if the name contains the search query
				searchResults.add(book);
			}
		}
		// If no results were found, display a message indicating that no books were
		// found
		if (searchResults.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No books found with the given Name.");
		} else {
			// If results were found, build a string containing the search results and
			// display them using JOptionPane
			StringBuilder searchResultString = new StringBuilder("Search Results:\n");
			for (Book book : searchResults) {
				// Appends each book's details to the searchResultString
				searchResultString.append(book.toString()).append("\n");
			}
			// Displays the search results using JOptionPane
			JOptionPane.showMessageDialog(null, searchResultString.toString());
		}
	}

	@Override
	public void removeBook() {
		// Prompts the user to input the name of the book to be removed
		String removeQuery = JOptionPane.showInputDialog("Enter the Name of the book you want to remove:");
		// Initializes a boolean variable to track whether the book was removed
		boolean removed = false;
		// Initializes an ArrayList to store the remove results
		ArrayList<Book> booksToRemove = new ArrayList<>();
		// Iterates through each Book in the library
		for (Book book : library) {
			// Checks if the Book's name matches the removeQuery (ignoring case)
			if (book.getBookName().equalsIgnoreCase(removeQuery)) {
				// If a match is found, remove the book from the library
				booksToRemove.add(book);
				removed = true;
				// Display a message indicating that the book has been removed
				JOptionPane.showMessageDialog(null, "Book removed from the library: \n" + book.toString());
				// Exit the loop since the book has been removed
				break;
			}
		}
		// If no book was removed, throw an exception with an appropriate message
		if (removed) {
			library.removeAll(booksToRemove);
		} else {
			throw new IllegalArgumentException("No books found with the given Name. No book removed.");
		}
	}

	@Override
	public void generateReport() {
		// Generate the report based on the library data
		StringBuilder report = new StringBuilder("Library Report:\n");
		for (Book book : library) {
			report.append(book.toString()).append("\n");
		}
		// Instead of displaying it with JOptionPane, consider writing the report to a
		// file or printing it to the console.
		System.out.println(report.toString());
	}

	@Override
	public void borrowBook() {
		// Prompts the user to enter the name of the book to borrow
		String borrowQuery = JOptionPane.showInputDialog("Enter the Name of the book you want to borrow:");
		boolean bookFound = false;

		// Iterate through each Book in the library
		for (Book book : library) {
			// Check if the Book's name matches the borrowQuery (ignoring case) and if it is
			// not already borrowed
			if (book.getBookName().equalsIgnoreCase(borrowQuery) && !book.isBorrowed()) {
				// If a match is found and the book is not already borrowed, indicate that the
				// book has been borrowed
				book.setBorrowed(true);
				book.setBorrowedDate(LocalDate.now(), book); // Set the borrowed date to the current date
				JOptionPane.showMessageDialog(null, "You have successfully borrowed the book: \n" + book.toString());
				bookFound = true;
				break;
			} else if (book.getBookName().equalsIgnoreCase(borrowQuery) && book.isBorrowed()) {
				// If the book is already borrowed, inform the user
				JOptionPane.showMessageDialog(null, "The book is already borrowed.");
				bookFound = true;
				break;
			}
		}

		// If no book was found, display a message indicating that no books were found
		if (!bookFound) {
			JOptionPane.showMessageDialog(null, "No books found with the given Name.");
		}
	}

	@Override
	public void returnBook() {
		// Prompts the user to enter the name of the book to return
		String returnQuery = JOptionPane.showInputDialog("Enter the Name of the book you want to return:");
		boolean bookFound = false;

		// Iterate through each Book in the library
		for (Book book : library) {
			// Check if the Book's name matches the returnQuery (ignoring case) and if it is
			// currently borrowed
			if (book.getBookName().equalsIgnoreCase(returnQuery) && book.isBorrowed()) {
				// If a match is found and the book is currently borrowed, indicate that the
				// book has been returned
				book.setBorrowed(false);
				JOptionPane.showMessageDialog(null, "You have successfully returned the book: \n" + book.toString());
				bookFound = true;
				break;
			} else if (book.getBookName().equalsIgnoreCase(returnQuery) && !book.isBorrowed()) {
				// If a match is found but the book is not currently borrowed, inform the user
				JOptionPane.showMessageDialog(null, "The book is not currently borrowed.");
				bookFound = true;
				break;
			}
		}

		// If no book was found, display a message indicating that no books were found
		if (!bookFound) {
			JOptionPane.showMessageDialog(null, "No books found with the given Name.");
		}
	}

	public void showLibraryBooks() {
		if (!library.isEmpty()) {
			StringBuilder libraryInfo = new StringBuilder("Library Contents:\n");
			for (Book book : library) {
				libraryInfo.append(book.toString()).append("\n");
			}
			JOptionPane.showMessageDialog(null, libraryInfo.toString());
		} else {
			JOptionPane.showMessageDialog(null, "The library is currently empty.");
		}
	}

	public static void main(String[] args) {
		LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();

		while (true) {
			String[] roles = { "Student", "Admin", "Exit" };
			int roleChoice = JOptionPane.showOptionDialog(null, "Select your role:", "Role Selection", 0,
					JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);

			if (roleChoice == 2) {
				// Exit the program if the user chooses to exit
				JOptionPane.showMessageDialog(null, "You Choose Exit!");
				System.exit(0);
			}

			String username = JOptionPane.showInputDialog("Enter username:");
			String password = JOptionPane.showInputDialog("Enter password");

			// Check credentials based on the selected role
			if (roles[roleChoice].equals("Admin") && credentials.containsKey("Admin")
					&& credentials.get("Admin").equals(password)) {
				isAdminLoggedIn = true;
			} else if (roles[roleChoice].equals("Student") && credentials.containsKey("Student")
					&& credentials.get("Student").equals(password)) {
				isStudentLoggedIn = true;
			} else {
				isAdminLoggedIn = false;
				isStudentLoggedIn = false;
				JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
				continue;
			}

			// Once logged in, provide options based on the role
			while (isAdminLoggedIn || isStudentLoggedIn) {
				if (isAdminLoggedIn) {
					String[] adminOptions = { "Add Book", "Remove Book", "Generate Report", "Search Book",
							"View Library", "Log Out" };
					int adminChoice = JOptionPane.showOptionDialog(null, "Admin Menu", "Main Menu", 0,
							JOptionPane.QUESTION_MESSAGE, null, adminOptions, adminOptions[0]);

					try {
						if (adminChoice == 0) {
							JOptionPane.showMessageDialog(null, "You choose Add Book!");
							libraryManagementSystem.addBook();
						}
						if (adminChoice == 1) {
							JOptionPane.showMessageDialog(null, "You choose Remove Book!");
							libraryManagementSystem.removeBook();
						}
						if (adminChoice == 2) {
							JOptionPane.showMessageDialog(null, "You choose Generate Report!");
							libraryManagementSystem.generateReport();
						}
						if (adminChoice == 3) {
							JOptionPane.showMessageDialog(null, "You choose Search Book!");
							libraryManagementSystem.searchBook();
						}
						if (adminChoice == 4) {
							JOptionPane.showMessageDialog(null, "You choose View Library!");
							libraryManagementSystem.showLibraryBooks(); // Display the library books
						}
						if (adminChoice == 5) {
							isAdminLoggedIn = false; // Log out the admin
							break;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
					}
				}

				if (isStudentLoggedIn) {
					String[] studentOptions = { "Borrow Book", "Return Book", "Search Book", "View Library",
							"Log Out" };
					int studentChoice = JOptionPane.showOptionDialog(null, "Student Menu", "Main Menu", 0,
							JOptionPane.QUESTION_MESSAGE, null, studentOptions, studentOptions[0]);

					try {
						if (studentChoice == 0) {
							JOptionPane.showMessageDialog(null, "You choose Borrow Book!");
							libraryManagementSystem.borrowBook();
						}
						if (studentChoice == 1) {
							JOptionPane.showMessageDialog(null, "You choose Return Book!");
							libraryManagementSystem.returnBook();
						}
						if (studentChoice == 2) {
							JOptionPane.showMessageDialog(null, "You choose Search Book!");
							libraryManagementSystem.searchBook();
						}
						if (studentChoice == 3) {
							JOptionPane.showMessageDialog(null, "You choose View Library!");
							libraryManagementSystem.showLibraryBooks(); // Display the library books
						}
						if (studentChoice == 4) {
							isStudentLoggedIn = false; // Log out the student
							break;
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
					}
				}
			}
		}
	}
}
