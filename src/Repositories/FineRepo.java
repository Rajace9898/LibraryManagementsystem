package Repositories;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import JdbcConn.*;
import Resources.FineView;
import Resources.ReserveAndBurrow;
import Resources.User;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import static JdbcConn.JdbcConn.openConn;

public class FineRepo {


   public void setFine(Long userId, Long bookId)throws Exception {

      ReserveAndBurrowRepo reserveAndBurrowRepo = new ReserveAndBurrowRepo();

      ReserveAndBurrow reserveAndBurrow = reserveAndBurrowRepo.getTransactionByUserAndBookID(userId, bookId);
      Period period = Period.between(reserveAndBurrow.getIssueDate(), reserveAndBurrow.getReturnDate());
      int difference = period.getDays();

      int finePerDay = 1;

      if (difference > 90) {

         openConn();
         String sql = "INSERT INTO fonebook_management.fine ( user_id, book_id, over_due, total_amt, fine_per_day, is_paid) " +
                 "values (?,?,?,?,?,?)";
         PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
         statement.setLong(1, reserveAndBurrow.getUserId());
         statement.setLong(2, reserveAndBurrow.getBookId());
         statement.setInt(3,  difference % 90);
         statement.setInt(4, (difference % 90) * finePerDay);
         statement.setInt(5, finePerDay);
         statement.setBoolean(6, false);

      }
      JdbcConn.connection.close();
   }

   public List<FineView> viewFineByName(String name) throws SQLException {
      UserRepo userRepo =new UserRepo();
      User user = userRepo.getUserByName(name);
      List<FineView> fineViewList = new ArrayList<>();
      openConn();
      String sql ="select title, author, over_due, fine_per_day, total_amt  " +
              " from fine  " +
              "inner join user" +
              " on user.id = fine.user_id " +
              "inner join book " +
              " on book.id =fine.book_id " +
              "where is_paid=? and user_id=?";
      PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
      statement.setBoolean(1,false);
      statement.setLong(2,user.getId());
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()){
         FineView fineView = new FineView();
         fineView.setTitle(resultSet.getString("title"));
         fineView.setAuthor(resultSet.getString("author"));
         fineView.setOverDue(resultSet.getInt("over_due"));
         fineView.setFinePerDay(resultSet.getInt("fine_per_day"));
         fineView.setTotalAmount(resultSet.getInt("total_amt"));
         fineViewList.add(fineView);
      }
      JdbcConn.connection.close();

      return fineViewList;
   }
   public List<FineView> viewFinesOwedByUsers() throws Exception{
      List<FineView> fineViewList = new ArrayList<>();
      openConn();
      String sql ="select name, title, author, over_due, fine_per_day, total_amt  " +
              " from fine  " +
              "inner join user" +
              " on user.id = fine.user_id " +
              "inner join book " +
              " on book.id =fine.book_id " +
              "where is_paid=?";
      PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
      statement.setBoolean(1,false);

      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()){
         FineView fineView = new FineView();
         fineView.setName(resultSet.getString("name"));
         fineView.setTitle(resultSet.getString("title"));
         fineView.setAuthor(resultSet.getString("author"));
         fineView.setOverDue(resultSet.getInt("over_due"));
         fineView.setFinePerDay(resultSet.getInt("fine_per_day"));
         fineView.setTotalAmount(resultSet.getInt("total_amt"));

         fineViewList.add(fineView);


      }
      JdbcConn.connection.close();
      return fineViewList;
   }

   public void payFines(String name) throws Exception {

      openConn();
      UserRepo userRepo = new UserRepo();
      User user = userRepo.getUserByName(name);
      String  sql = "Update fine set is_paid=? where user_id=?";
      PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
      statement.setBoolean(1,true);
      statement.setLong(2,user.getId());
      statement.execute();
      JdbcConn.connection.close();
   }
}
