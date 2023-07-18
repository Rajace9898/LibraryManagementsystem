package Repositories;

import JdbcConn.*;
import Main.Main;
import Resources.Book;
import Resources.ReserveAndBurrow;
import Resources.Transaction;
import Resources.User;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static JdbcConn.JdbcConn.openConn;


public class ReserveAndBurrowRepo {
    public static List<Transaction> getTransactions(String name, String phone) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();


        openConn();
        String sql ="SELECT name, title, issue_date, return_date FROM fonebook_management.reserveandburrow" +
                " inner join book b on reserveandburrow.book_id = b.id " +
                " inner join  user u on reserveandburrow.user_id = u.id" +
                " where return_date is not null and name=? and phone=?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setString(1,name);
        statement.setString(2,phone);
        ResultSet result =  statement.executeQuery();
        while (result.next()){
            Transaction transaction = new Transaction();
            transaction.setName(result.getString("name"));
            transaction.setTitle(result.getString("title"));
            transaction.setIssueDate(String.valueOf(result.getDate("issue_date")));
            transaction.setReturnDate(String.valueOf(result.getDate("return_date")));
            transactionList.add(transaction);

        }
        JdbcConn.connection.close();
        return transactionList;
    }

    public ReserveAndBurrow getTransactionById(Integer id) throws Exception{
        ReserveAndBurrow reserveAndBurrow = new ReserveAndBurrow();

            openConn();
            String sql ="SELECT * FROM fonebook_management.reserveandburrow where id=?";
            PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result =  statement.executeQuery();
            while (result.next()){
               reserveAndBurrow.setId(result.getLong("id"));
               reserveAndBurrow.setUserId(result.getLong("user_id"));
               reserveAndBurrow.setBookId(result.getLong("book_id"));
               reserveAndBurrow.setReserved(result.getBoolean("reserved"));
               reserveAndBurrow.setIsIssued(result.getBoolean("is_burrowed"));
               reserveAndBurrow.setIssueDate(result.getDate("issue_date").toLocalDate());
               reserveAndBurrow.setReturnDate(result.getDate("return_date").toLocalDate());
            }
        JdbcConn.connection.close();

        return reserveAndBurrow;

    }
    public ReserveAndBurrow getActiveTransactionByUserAndBookID(Long userId, Long bookId) throws Exception{
            ReserveAndBurrow reserveAndBurrow = new ReserveAndBurrow();
            openConn();
            String sql ="SELECT * FROM fonebook_management.reserveandburrow where user_id=? and book_id=? and is_burrowed=true";
            PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.setLong(2, bookId);
            ResultSet result =  statement.executeQuery();
            while (result.next()){
                reserveAndBurrow.setId(result.getLong("id"));
                reserveAndBurrow.setUserId(result.getLong("user_id"));
                reserveAndBurrow.setBookId(result.getLong("book_id"));
                reserveAndBurrow.setReserved(result.getBoolean("reserved"));
                reserveAndBurrow.setIsIssued(result.getBoolean("is_burrowed"));
                reserveAndBurrow.setIssueDate(result.getDate("issue_date").toLocalDate());
                reserveAndBurrow.setReturnDate(result.getDate("return_date").toLocalDate());
            }
        JdbcConn.connection.close();


        return reserveAndBurrow;

    }
    public ReserveAndBurrow getTransactionByUserAndBookID(Long userId, Long bookId) throws Exception{
        ReserveAndBurrow reserveAndBurrow = new ReserveAndBurrow();



        openConn();
        String sql ="SELECT * FROM fonebook_management.reserveandburrow where user_id=? and book_id=? and return_date is not null ";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setLong(1, userId);
        statement.setLong(2, bookId);
        ResultSet result =  statement.executeQuery();
        while (result.next()){
            reserveAndBurrow.setId(result.getLong("id"));
            reserveAndBurrow.setUserId(result.getLong("user_id"));
            reserveAndBurrow.setBookId(result.getLong("book_id"));
            reserveAndBurrow.setReserved(result.getBoolean("reserved"));
            reserveAndBurrow.setIsIssued(result.getBoolean("is_burrowed"));
            reserveAndBurrow.setIssueDate(result.getDate("issue_date").toLocalDate());
            reserveAndBurrow.setReturnDate(result.getDate("return_date").toLocalDate());
        }
        JdbcConn.connection.close();


        return reserveAndBurrow;

    }
    public ReserveAndBurrow getReservedTransactionByUserAndBookID(Long userId, Long bookId) throws Exception{

        openConn();
        String sql ="SELECT * FROM fonebook_management.reserveandburrow where user_id=? and book_id=? and reserved=? and is_burrowed=?";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setLong(1, userId);
        statement.setLong(2, bookId);
        statement.setBoolean(3,true);
        statement.setBoolean(4,false);
        ResultSet result =  statement.executeQuery();
        ReserveAndBurrow reserveAndBurrow = new ReserveAndBurrow();
        while (result.next()){

            reserveAndBurrow.setId(result.getLong("id"));
            reserveAndBurrow.setUserId(result.getLong("user_id"));
            reserveAndBurrow.setBookId(result.getLong("book_id"));
            reserveAndBurrow.setReserved(result.getBoolean("reserved"));
            reserveAndBurrow.setIsIssued(result.getBoolean("is_burrowed"));
          //  reserveAndBurrow.setIssueDate(result.getDate("issue_date").toLocalDate());
          //  reserveAndBurrow.setReturnDate(result.getDate("return_date").toLocalDate());

        }
        JdbcConn.connection.close();


        return reserveAndBurrow;

    }
    public void reserveBook(Integer isbn) throws Exception {

            BookRepo bookRepo = new BookRepo();
            UserRepo userRepo = new UserRepo();
            User user = userRepo.getUserByName(Main.user);
            Book book = bookRepo.findBookByISBN(isbn);
            openConn();

            String sql =" INSERT Into   fonebook_management.reserveandburrow " +
                    "(user_id , book_id, reserved, is_burrowed)" +
                    "VALUES (?,?,?,?)";
            PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
            statement.setLong(1,user.getId());
            statement.setLong(2,book.getId());
            statement.setBoolean(3,true);
            statement.setBoolean(4,false);
            statement.execute();
        JdbcConn.connection.close();

    }

    public void issueBook(Integer isbn) throws Exception {

            BookRepo bookRepo = new BookRepo();
            UserRepo userRepo = new UserRepo();
            User user = userRepo.getUserByName(Main.user);
            Book book = bookRepo.findBookByISBN(isbn);
            ReserveAndBurrow reserveAndBurrow = new ReserveAndBurrow();
            ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
            try {
               reserveAndBurrow = reserveAndBurrowRepo.getActiveTransactionByUserAndBookID(user.getId(), book.getId());
              if(reserveAndBurrow.getIsIssued()==null){
                  if (book.getCopies() > 0) {
                      openConn();
                      String sql = " INSERT into reserveandburrow ( user_id, book_id, reserved, is_burrowed, issue_date)" +
                              "values (?,?,?,?,?)";
                      PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
                      statement.setLong(1, user.getId());
                      statement.setLong(2, book.getId());
                      statement.setBoolean(3, true);
                      statement.setBoolean(4, true);
                      statement.setDate(5, Date.valueOf(LocalDate.now()));
                      statement.execute();
                      book.setCopies(book.getCopies() - 1);
                      bookRepo.updateBook(book);
                      JdbcConn.connection.close();
                      System.out.println("book issued");
                  } else {
                      System.out.println("Unable to Issue book. No copies of the book available");

                  }

              }else if(reserveAndBurrow.getIsIssued()){
                  throw new Exception();
              } else if (!reserveAndBurrow.getIsIssued() && reserveAndBurrow.getReserved()) {
                  if (book.getCopies() > 0) {
                      openConn();
                      book.setCopies(book.getCopies() - 1);
                      bookRepo.updateBook(book);

                      String sql = " UPDATE reserveandburrow set  reserved=?, is_burrowed=?, issue_date=? " +
                              "where user_id=? and  book_id=?";
                      PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);

                      statement.setBoolean(1, true);
                      statement.setBoolean(2, true);
                      statement.setDate(3, Date.valueOf(LocalDate.now()));
                      statement.setLong(4, user.getId());
                      statement.setLong(5, book.getId());
                      statement.execute();
                      JdbcConn.connection.close();
                      System.out.println("book issued");
                  } else {
                      System.out.println("Unable to Issue book. No copies of the book available");

                  }
                  
              }
            } catch (Exception e) {
                System.out.println("Unable to Issue book. Book doesnt exist");

            }





    }
    public void returnBook(Integer isbn) throws Exception{


            BookRepo bookRepo = new BookRepo();
            UserRepo userRepo = new UserRepo();
            User  user = userRepo.getUserByName(Main.user);
            Book book = bookRepo.findBookByISBN(isbn);




                    openConn();
                    book.setCopies(book.getCopies()+1);
                    bookRepo.updateBook(book);

                    String sql =" UPDATE  fonebook_management.reserveandburrow " +
                            "SET reserved=?, is_burrowed=? ,return_date=?" +
                            " WHERE user_id=? and book_id=? ";
                    PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);

                    statement.setBoolean(1,false);
                    statement.setBoolean(2,false);
                    statement.setDate(3, Date.valueOf(LocalDate.now()));
                    statement.setLong(4,user.getId());
                    statement.setLong(5,book.getId());
                    statement.execute();
                    JdbcConn.connection.close();
                    FineRepo fineRepo = new FineRepo();
                    fineRepo.setFine(user.getId(), book.getId());


    }

    public List<Book> viewBooksBurrowedByUser(String name) throws Exception{

            List<Book> bookList = new ArrayList<>();

            UserRepo userRepo = new UserRepo();
            BookRepo bookRepo = new BookRepo();

            User user=userRepo.getUserByName(name);


            openConn();
            String sql = "SELECT book_id FROM fonebook_management.reserveandburrow where user_id=? and is_burrowed=true";
            PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
            statement.setLong(1,user.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Book book = bookRepo.findBookById(result.getLong("book_id"));
                bookList.add(book);

            }
        JdbcConn.connection.close();

        return bookList;

    }


    public void cancelReservationByIsbn(Integer isbn) throws Exception{


            BookRepo bookRepo = new BookRepo();
            UserRepo userRepo = new UserRepo();
            User user = userRepo.getUserByName(Main.user);
            Book book = bookRepo.findBookByISBN(isbn);
            openConn();
            ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();
            ReserveAndBurrow reserveAndBurrow = new ReserveAndBurrow();
            try {
                reserveAndBurrow= reserveAndBurrowRepo.getReservedTransactionByUserAndBookID(user.getId(), book.getId());
                String sql =" DELETE From fonebook_management.reserveandburrow where user_id=? and book_id=? and issue_date is null and return_date is null ";
                PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
                statement.setLong(1,user.getId());
                statement.setLong(2,book.getId());
                statement.execute();

            } catch (Exception e) {
                System.out.println("Unable to cancel reservation by ISBN: " + isbn);

            }

        JdbcConn.connection.close();

    }
    public  List<Book> getBurrowedBooks() throws Exception{
        openConn();
        List<Book> bookList = new ArrayList<>();
        String sql= "select max(reserveandburrow.id) , isbn, title, author " +
                "from reserveandburrow " +
                "inner join book " +
                "on reserveandburrow.book_id = book.id " +
                "inner join user " +
                "on user.id = reserveandburrow.user_id  " +
                "where is_burrowed = true" +
                " group by book_id";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Book book = new Book();
            book.setISBN(resultSet.getInt("isbn"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            bookList.add(book);
        }
        JdbcConn.connection.close();
        return bookList;

    }
    public  List<Book> getReservedBooks() throws Exception{
        openConn();
        List<Book> bookList = new ArrayList<>();
        String sql= "select max(reserveandburrow.id) , isbn, title, author " +
                "from reserveandburrow " +
                "inner join book " +
                "on reserveandburrow.book_id = book.id " +
                "inner join user " +
                "on user.id = reserveandburrow.user_id  " +
                "where reserved = true and is_burrowed=false " +
                " group by book_id";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Book book = new Book();
            book.setISBN(resultSet.getInt("isbn"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            bookList.add(book);
        }
        JdbcConn.connection.close();
        return bookList;

    }
}
