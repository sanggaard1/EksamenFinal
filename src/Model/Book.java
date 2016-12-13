package Model;

/**
 * Created by Sanggaard on 24/11/2016.
 */
public class Book {

    private int bookID;
    private long ISBN;
    private String publisher;
    private String title;
    private String author;
    private double priceAB;
    private double priceSAXO;
    private double priceCDON;
    private int version;

    //Constructor
    public Book(int bookID, long ISBN, String publisher, String title, String author, double priceAB, double priceSAXO, double priceCDON, int version) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.priceAB = priceAB;
        this.priceSAXO = priceSAXO;
        this.priceCDON = priceCDON;
        this.version = version;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPriceAB() {
        return priceAB;
    }

    public void setPriceAB(double priceAB) {
        this.priceAB = priceAB;
    }

    public double getPriceSAXO() {
        return priceSAXO;
    }

    public void setPriceSAXO(double priceSAXO) {
        this.priceSAXO = priceSAXO;
    }

    public double getPriceCDON() {
        return priceCDON;
    }

    public void setPriceCDON(double priceCDON) {
        this.priceCDON = priceCDON;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
