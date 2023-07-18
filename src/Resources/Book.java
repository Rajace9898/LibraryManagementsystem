package Resources;

public class Book {
    private Long id;
    private String title;
    private Integer ISBN;
    private String author;
    private Integer copies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Book() {
    }

    public Book(Long id, String title, Integer ISBN, String author, Integer copies) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Book{" +
            //    "Id=" + id +
                " ISBN=" + ISBN +
                ", Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Copies=" + copies +
                '}';
    }
    public String toStringWOCopies() {
        return "Book{" +
                " ISBN=" + ISBN +
                ", Title='" + title + '\'' +
                ", Author='" + author + '\'' +
            //    ", Copies=" + copies +
                '}';
    }
}
