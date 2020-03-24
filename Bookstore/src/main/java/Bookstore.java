import java.util.List;

public class Bookstore {
    private List<Book> bookList;

    public void add(Book book) {
        if (BookstoreCheck.isUnique(bookList, book)) {
            bookList.add(book);
        }
    }

    public void remove(Book book) {
        bookList.remove(book);
    }


}
