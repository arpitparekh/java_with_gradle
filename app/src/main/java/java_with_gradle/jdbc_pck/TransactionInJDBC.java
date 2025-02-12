package java_with_gradle.jdbc_pck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionInJDBC{

  public static void main(String[] args) {

    try {

      Scanner sc = new Scanner(System.in);

      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bascom", "root", "Walden0042$$");

      // by default connection is in auto commit mode
      // to disable auto commit mode
      connection.setAutoCommit(false);

      String query = "insert into college(c_name,location) values ('LD','Ahmedabad')";
      Statement s = connection.createStatement();
      s.executeUpdate(query);


      String query2 = "insert into stock(date_added) values ('2022-05-25')";
      s.executeUpdate(query2);

      Savepoint sp = connection.setSavepoint("savepoint1");

      String query1 = "insert into college(c_name,location) values ('Silver Oak','Ahmedabad')";
      s.executeUpdate(query1);

      // connection.commit();
      connection.rollback(sp);
      connection.commit();


    } catch (SQLException e) {
      System.out.println(e);
    }

  }

}
