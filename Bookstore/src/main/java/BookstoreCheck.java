import java.util.List;

public class BookstoreCheck {

    public static boolean isUnique(List<Book> bookList, Book book) {
        return !bookList.contains(book);
    }

    public static Book bigger(Book book1, Book book2) {
        return book1.getPageCount() >= book2.getPageCount() ? book1 : book2;
    }
}
