import java.util.ArrayList;

/**
 * Library holds a collection of books and registered users.
 * It supports registering users, adding books to library, borrowing/returning books.
 */
public class Library {
    // Add the missing implementation to this class
    String address;
    ArrayList<Book> bookCollection = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    /**
     * @param libAddress the address of the library
     */
    public Library(String libAddress) {
        // TODO: Implement the method
        this.address = libAddress;
    }

    /**
     * @param newBook the book to add to the library collection
     */
    public void addBook(Book newBook){
        // TODO: Implement the method
        bookCollection.add(newBook);
    }

    /**
     * @param user the user to register with the library
     */
    public void registerUser(User user) {
        // TODO: Implement the method
        users.add(user);
    }

    /**
     * print the opening hours of the library
     * */
    public static void printOpeningHours(){
        // TODO: Implement the method
        System.out.println("Libraries are open daily from 9am to 5pm.");
    }

    /**
     * print the address of the library
     */
    public void printAddress(){
        // TODO: Implement the method
        System.out.printf("%s%n", this.address);
    }

    /**
     * @param title the title of the book to borrow
     * @param user  the user who wants to borrow the book
     */
    // Borrow a book on behalf of a user. Each borrow operation lets the user take one book.
    // Enforces that a user may have at most User.MAX_BORROW concurrent borrows.
    public void borrowBook(String title, User user) {
        // TODO: Implement the method
        if(user.getBorrowedCount() >= User.MAX_BORROW){
            System.out.printf("User %s has already borrowed the maximum number of books (%d).%n", user.getName(), User.MAX_BORROW);
            return;
        }
        int find_idx = -1;
        for(int i = 0; i < bookCollection.size(); i++){
            Book book = bookCollection.get(i);
            if(book.getTitle().equals(title)){
                find_idx = i;
                break;
            }
        }
        Book book = bookCollection.get(find_idx);
        if (find_idx != -1 && book.isBorrowed()){
            System.out.println("Sorry, this book is already borrowed.");
            return;
        }
        //book.borrowed(user.getName());
        user.addBorrowedBook(book);
        System.out.printf("User %s successfully borrowed %s%n", user.getName(), title);
    }

    /**
     * print books that are available to borrow
     * */
    public void printAvailableBooks(){
        // TODO: Implement the method
        for(Book book : bookCollection){
            if(!book.isBorrowed()){     // do not print books that are borrowed by users
                System.out.println(book.getTitle());
            }
        }
    }

    /**
     * @param title the title of the book to return
     * @param user  the user returning the book
     */
    // Return a book on behalf of a user, and remove it from the borrower's list.
    public void returnBook(String title, User user){
        // TODO: Implement the method
        int find_idx = -1;
        for(int i = 0; i < bookCollection.size(); i++){
            Book book = bookCollection.get(i);
            if(book.getTitle().equals(title)){
                find_idx = i;
                break;
            }
        }
        Book book = bookCollection.get(find_idx);
        user.removeBorrowedBook(book);  // remove the borrowed book from booklist
        book.returned();    // return the book
        System.out.printf("%s successfully returned %s%n", user.getName(), book.getTitle());
    }

    /**
     * test case
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Create the library
        Library library = new Library("10 Main St.");

        // Add books to the library
        library.addBook(new Book("The Da Vinci Code", 10.0));
        library.addBook(new Book("Le Petit Prince", 8.0));
        library.addBook(new Book("A Tale of Two Cities", 12.0));
        library.addBook(new Book("The Lord of the Rings", 15.0));

        // Print opening hours and the addresses
        System.out.println("Library hours:");
        printOpeningHours();
        System.out.println();

        System.out.println("Library address:");
        library.printAddress();
        System.out.println();

        // Create users and register them
        User alice = new User("U1", "Alice");
        User bob = new User("U2", "Bob");
        library.registerUser(alice);
        library.registerUser(bob);

        // Try to borrow The Lord of the Rings
        System.out.println("Borrowing The Lord of the Rings:");
        library.borrowBook("The Lord of the Rings", alice); // should succeed
        library.borrowBook("The Lord of the Rings", bob);   // should say already borrowed
        System.out.println();

        // Print the titles of all available books from both libraries
        System.out.println("Books available in the library:");
        library.printAvailableBooks();
        System.out.println();;
        System.out.println();

        // Return The Lord of the Rings to the library
        System.out.println("Returning The Lord of the Rings:");
        library.returnBook("The Lord of the Rings", alice);
        System.out.println();

        // Now demonstrate the per-user max-borrow (3 books)
        System.out.println("\nDemonstrate max 3 concurrent borrows for a user:");
        library.borrowBook("The Da Vinci Code", alice);
        library.borrowBook("Le Petit Prince", alice);
        library.borrowBook("A Tale of Two Cities", alice);
        // This fourth borrow should be rejected
        library.borrowBook("The Lord of the Rings", alice);
        /*
        System.out.println(alice.getBorrowedCount());
        System.out.println(bob.getBorrowedCount());
        */
        // Show borrowed count for Alice
        System.out.println(alice.getName() + " has borrowed " + alice.getBorrowedCount() + " books.");

        // Final available books in library
        System.out.println("\nBooks available in the library:");
        library.printAvailableBooks();
    }
}
