/**
 * This is a class named "Book" where it stores informations about books
 * Information may include a book's title, its price, the status of being borrowed,
 * and the name of the borrower.
 * The Book class also has a function that tells the rental price of the book.
 */
public class Book {

    // TODO: Define the class members.
    private String bookTitle;
    private double bookPrice;
    private boolean isBorrowed;
    private String borrower;

    /**
     * @param bookTitle the title of the book
     * @param bookPrice the price of the book
     */
    public Book(String bookTitle, double bookPrice) {
        // TODO: Implement the method
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.isBorrowed = false;
        this.borrower = null;
    }

    /**
     * @param userName the name of the user borrowing the book
     */
    // Marks the book as borrowed by a specific user
    public void borrowed(String userName) {
        // TODO: Implement the method
        this.borrower = userName;
        this.isBorrowed = true;
    }

    /**
     * return the book and clear borrower info
     * */
    // Marks the book as returned and clears borrower information
    public void returned() {
        // TODO: Implement the method
        this.isBorrowed = false;
        this.borrower = null;
    }

    /**
     * @return true if the book is currently borrowed; false otherwise
     */
    // Checks if the book is currently borrowed
    public boolean isBorrowed() {
        // TODO: Implement the method
        return this.borrower != null;
    }

    /**
     * @return the current borrower's name, or null if not borrowed
     */
    // Gets the name of the current borrower
    public String getBorrowerName() {
        // TODO: Implement the method
        return this.borrower;
    }

    /**
     * @return the title of the book
     */
    // Gets the title of the book
    public String getTitle() {
        // TODO: Implement the method
        return this.bookTitle;
    }

    /**
     * @return the price of the book
     */
    // Gets the price of the book
    public double getPrice() {
        // TODO: Implement the method
        return this.bookPrice;
    }

    /**
     * @param days the number of rental days
     * @return the rental fee (capped at the book price)
     */
    // Calculates payment for a rental based on duration
    public double calculatePayment(int days) {
        // TODO: Implement the method
        if (days <= 30) { return 0;}
        double fee = ((double) days - 30) * 0.5;
        if (fee > this.bookPrice){
            return this.bookPrice;
        } else {
            return fee;
        }
    }

    /**
     * @param arguments command-line arguments
     */
    public static void main(String[] arguments) {
        // Small test of the Book class
        Book example = new Book("The Da Vinci Code", 10);

        // Test basic properties
        System.out.println("Title (should be The Da Vinci Code): " + example.getTitle());
        System.out.println("Price (should be 10.0): " + example.getPrice());
        System.out.println("Borrowed? (should be false): " + example.isBorrowed());

        // Test borrowing functionality
        example.borrowed("user1");
        System.out.println("Borrowed? (should be true): " + example.isBorrowed());
        System.out.println("Borrower Name (should be user1): " + example.getBorrowerName());

        // Test payment calculation
        System.out.println("Estimated Payment for 30 days: (should be 0.0): " + example.calculatePayment(30));
        System.out.println("Estimated Payment for 40 days: (should be 5.0): " + example.calculatePayment(40));
        System.out.println("Estimated Payment for 60 days: (should be 10.0): " + example.calculatePayment(60));

        // Test return functionality
        example.returned();
        System.out.println("Borrowed? (should be false): " + example.isBorrowed());
    }
}
