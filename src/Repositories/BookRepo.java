package Repositories;

import JdbcConn.JdbcConn;
import Resources.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static JdbcConn.JdbcConn.connection;
import static JdbcConn.JdbcConn.openConn;


public class BookRepo {

    public void addBook(Book book) throws SQLException {  //works
        openConn();
        String sql = "INSERT INTO fonebook_management.book (isbn, title, author, copies) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setInt(1, book.getISBN());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getAuthor());
        statement.setInt(4, book.getCopies());
        statement.executeUpdate();
        statement.close();


        JdbcConn.connection.close();


    }

    public void removeBookByISBN(Integer isbn) throws SQLException { //works
        openConn();
        String sql = "DELETE FROM fonebook_management.book WHERE isbn = ?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setInt(1, isbn);
        statement.executeUpdate();

        JdbcConn.connection.close();


    }

    public void updateBook(Book book) throws SQLException {

        openConn();
        String sql = "UPDATE fonebook_management.book SET author =? , title=?, copies=? WHERE isbn=?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setInt(3, book.getCopies());
        statement.setInt(4, book.getISBN());
        statement.executeUpdate();

        JdbcConn.connection.close();


    }


    public Book findBookByTitle(String title) throws SQLException {  //works
        Book book = new Book();
        openConn();
        String sql = "SELECT id, isbn, author, title ,copies FROM fonebook_management.book WHERE title = ?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setString(1, title);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            book.setId(result.getLong("id"));
            book.setISBN(result.getInt("isbn"));
            book.setAuthor(result.getString("author"));
            book.setTitle(result.getString("title"));
            book.setCopies(result.getInt("copies"));
        }
        JdbcConn.connection.close();

        return book;
    }

    public Book findBookByAuthor(String author) throws SQLException { //works

        Book book = new Book();
        openConn();
        String sql = "SELECT id, isbn, title, author, copies FROM fonebook_management.book WHERE author = ?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setString(1, author);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            book.setId(result.getLong("id"));
            book.setISBN(result.getInt("isbn"));
            book.setTitle(result.getString("title"));
            book.setAuthor(result.getString("author"));
            book.setCopies(result.getInt("copies"));
        }
        JdbcConn.connection.close();

        return book;


    }

    public Book findBookByISBN(Integer isbn) throws SQLException {  //works

        Book book = new Book();
        openConn();
        String sql = "SELECT id, isbn, title, author, copies FROM fonebook_management.book WHERE isbn = ?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setInt(1, isbn);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            book.setId(result.getLong("id"));
            book.setISBN(result.getInt("isbn"));
            book.setTitle(result.getString("title"));
            book.setAuthor(result.getString("author"));
            book.setCopies(result.getInt("copies"));
        }
        JdbcConn.connection.close();
        return book;
    }

    public List<Book> getBooks() throws SQLException {

        ArrayList<Book> bookList = new ArrayList<>();
        openConn();
        String sql = "SELECT * FROM fonebook_management.book ";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Book book = new Book();
            book.setId(result.getLong("id"));
            book.setISBN(result.getInt("isbn"));
            book.setTitle(result.getString("title"));
            book.setAuthor(result.getString("author"));
            book.setCopies(result.getInt("copies"));
            bookList.add(book);
        }
        JdbcConn.connection.close();
        return bookList;

    }


    public Book findBookById(Long bookId) throws Exception {
        Book book = new Book();
        openConn();
        String sql = "SELECT id, isbn, title, author, copies FROM fonebook_management.book WHERE id = ?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setLong(1, bookId);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            book.setId(result.getLong("id"));
            book.setISBN(result.getInt("isbn"));
            book.setTitle(result.getString("title"));
            book.setAuthor(result.getString("author"));
            book.setCopies(result.getInt("copies"));
        }
        JdbcConn.connection.close();


        return book;

    }

    public int getTotalNumberOfBooks() throws Exception {
        int number = 0;
        String sql = "Select copies from book";
        openConn();
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            number = number + resultSet.getInt("copies");
        }
        connection.close();


        return number;
    }

    public List<Book> getListOfAvailableBooks() throws Exception {

        List<Book> bookList = new ArrayList<>();
        openConn();
        String sql = " select * from book where copies>?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setInt(1, 0);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getLong("id"));
            book.setISBN(resultSet.getInt("isbn"));
            book.setAuthor(resultSet.getString("author"));
            book.setTitle(resultSet.getString("title"));
            book.setCopies(resultSet.getInt("copies"));
            bookList.add(book);
        }
        connection.close();
        return bookList;

    }
}
