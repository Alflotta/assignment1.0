import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    public void run() {
        boolean running = true;
        System.out.println("Welcome to Library App!");
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer
            switch (choice) {
                case 1 -> printAllBooks();
                case 2 -> addNewBook();
                case 3 -> searchBooksByTitle();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> deleteBookById();
                case 7 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    private void printMenu() {
        System.out.println("\n1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
    }
    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }
    private void addNewBook() {
        try {
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Author: ");
            String author = sc.nextLine();
            System.out.print("Year: ");
            int year = sc.nextInt();
            sc.nextLine();
            Book book = new Book(title, author, year);
            books.add(book);
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void searchBooksByTitle() {
        System.out.print("Enter part of title: ");
        String search = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(search)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found");
        }
    }
    private void borrowBook() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (book.isAvailable()) {
            book.markAsBorrowed();
            System.out.println("Book borrowed successfully");
        } else {
            System.out.println("Book is already borrowed");
        }
    }
    private void returnBook() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (!book.isAvailable()) {
            book.markAsReturned();
            System.out.println("Book returned successfully");
        } else {
            System.out.println("Book was not borrowed");
        }
    }
    private void deleteBookById() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        books.remove(book);
        System.out.println("Book deleted successfully");
    }
    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}

