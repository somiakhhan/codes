public class Book implements Comparable<Book> {
    private String title, author;
    private int publicationYear;
    private double price;

    public Book(String title, String author, int publicationYear, double price) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
    }
    public int compareTo(Book other) {
        int publicationYearComparison = Integer.compare(this.publicationYear, other.publicationYear);
        if(publicationYearComparison != 0) {
            return publicationYearComparison;
        }
        int priceComparison = Double.compare(this.price, other.price);
        if(priceComparison != 0) {
            return priceComparison;
    }
        int authorComparison = this.author.compareTo(other.author);
        if(authorComparison != 0) {
            return authorComparison;
        }
       int titleComparison = this.title.compareTo(other.title);
            return titleComparison;
    }
}
   
