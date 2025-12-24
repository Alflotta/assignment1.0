import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books = new ArrayList<>();
    private Scanner s = new Scanner(System.in);
    public void enter() {
        boolean tof = true;
        System.out.println("Welcome to Library App!");
        while (tof) {
            System.out.println("1.Print all books");
            System.out.println("2.Add new book");
            System.out.println("3.Search books by title");
            System.out.println("4.Borrow a book");
            System.out.println("5.Return a book");
            System.out.println("6.Delete a book by id");
            System.out.println("7.Quit");
            System.out.print("Choose an option: ");
            int choice = s.nextInt();
            s.nextLine(); // clear buffer
            switch (choice) {
                case 1 -> printAllBooks();
                case 2 -> addNewBook();
                case 3 -> searchBooksByTitle();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> deleteBookById();
                case 7 -> {
                    tof = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
    }
    public void addNewBook() {
        System.out.print("Title: ");
        String title = s.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        System.out.print("Author: ");
        String author = s.nextLine();
        if (author.isEmpty()) {
            System.out.println("Author cannot be empty!");
            return;
        }
        int year = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Year: ");
            if (s.hasNextInt()) {
                year = s.nextInt();
                s.nextLine();
                if (year > 0) {
                    valid = true;
                } else {
                    System.out.println("Year must be positive!");
                }
            } else {
                System.out.println("Please enter a valid number!");
                s.nextLine();
            }
        }
        Book book = new Book(title, author, year);
        books.add(book);
        System.out.println("Book added!");
    }
    private void searchBooksByTitle() {
        System.out.print("Enter title");
        String search = s.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().toLowerCase().contains(search)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("not found");
        }
    }
    private void borrowBook() {
        System.out.print("Enter book id");
        int id = s.nextInt();
        s.nextLine();
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("not found");
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
        System.out.print("Enter book id");
        int id = s.nextInt();
        s.nextLine();
        Book rbook = findBookById(id);
        if (rbook == null) {
            System.out.println("Not found");
            return;
        }
        if (!rbook.isAvailable()) {
            rbook.markAsReturned();
            System.out.println("Book returned");
        } else {
            System.out.println("Book was not borrowed");
        }
    }
    private void deleteBookById() {
        System.out.print("Enter book id");
        int id = s.nextInt();
        s.nextLine();
        Book dbook = findBookById(id);
        if (dbook == null) {
            System.out.println("Not found");
            return;
        }
        books.remove(dbook);
        System.out.println("Book deleted");
    }
    private Book findBookById(int id) {
        for (Book fbook : books) {
            if (fbook.getId() == id) {
                return fbook;
            }
        }
        return null;
    }
}
