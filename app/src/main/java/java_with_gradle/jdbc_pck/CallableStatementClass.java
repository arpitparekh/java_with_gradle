package java_with_gradle.jdbc_pck;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatementClass {
  public static void main(String[] args) {

    try {

      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bascom", "root", "Walden0042$$");

      CallableStatement st = connection.prepareCall("call emp_location()");
      ResultSet result = st.executeQuery();

      PreparedStatement pst = connection.prepareStatement(
        "select st_name,age,c_name,location from student inner join college on student.cid = college.id");
      ResultSet result1 = pst.executeQuery();

      while (result.next()) {
        System.out.println(result.getString("emp_name") + " " + result.getString("location"));
      }

      while (result1.next()) {
        System.out.println(result1.getString("st_name") + " " + result1.getInt("age") + " " + result1.getString("c_name")
            + " " + result1.getString("location"));
      }
      st.close();
      pst.close();

    } catch (SQLException e) {
      System.out.println(e);
    }

  }
}
