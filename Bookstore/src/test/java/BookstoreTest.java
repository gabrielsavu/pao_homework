import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookstoreTest {

    @Test
    public void create() {
        Bookstore bookstar = new Bookstore();

        Book book = new Book();
        book.setPageCount(4L);
        book.setPublisher("Editura 1");
        book.setTitle("Titlu 1");
        book.setAuthor("Autor 1");

        Book book1 = new Book();
        book1.setPageCount(7L);
        book1.setPublisher("Editura 2");
        book1.setTitle("Titlu 2");
        book1.setAuthor("Autor 2");

        Book book2 = new Book();
        book2.setPageCount(9L);
        book2.setPublisher("Editura 3");
        book2.setTitle("Titlu 3");
        book2.setAuthor("Autor 3");

        bookstar.add(book);
        bookstar.add(book1);
        bookstar.add(book2);
        //addBooks();
    }

    @Test
    public void read() {

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }


    /*private List<Book> addBooks() {
        Book book = new Book();
        book.setTitle("Prima carte");
        book.setAuthor("Primul autor");
        book.setPublisher("Prima editura");
        book.setPageCount(4L);
        books.add(book);
        Book book2 = new Book();
        book2.setTitle("A doua carte");
        book2.setAuthor("Al doilea autor");
        book2.setPublisher("A doua editura");
        book2.setPageCount(2L);
        books.add(book2);
        Book book3 = new Book();
        book3.setTitle("A treia carte");
        book3.setAuthor("Al treilea autor");
        book3.setPublisher("A treia editura");
        book3.setPageCount(0L);
        books.add(book3);
        Book book4 = new Book();
        book4.setTitle("A treia carte");
        book4.setAuthor("Al treilea autor");
        book4.setPublisher("A treia editura");
        book4.setPageCount(5L);
        books.add(book4);
        return books;
    }*/

}
