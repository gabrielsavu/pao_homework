import java.text.MessageFormat;

public class Book {

    private String title;

    private String author;

    private String publisher;

    private Long pageCount;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return MessageFormat.format("BOOK_TITLE: {0}\nBOOK_AUTHOR: {1}\nBOOK_PUBLISHER: {2}", this.title.toUpperCase(), this.author.toLowerCase(), this.publisher);
    }
}
