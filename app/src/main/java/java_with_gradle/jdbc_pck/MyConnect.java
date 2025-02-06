package java_with_gradle.jdbc_pck;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class MyConnect {

  public static void main(String[] args) {

    // database connection

    try {

      // Class.forName("com.mysql.cj.jdbc.Driver");

      Connection con = DatabaseUtil.connectDatabase("fromjdbc");

      DatabaseUtil.createTable();
      // insert data
      // DatabaseUtil.insertData(con, "Kiran");
      // DatabaseUtil.insertData(con, "Sumit");
      // DatabaseUtil.insertData(con, "Pradip");



      // DatabaseUtil.updateData(1, "arjun");

      // DatabaseUtil.deleteData(1);
      // String[] names= {"demo1","demo2","demo3"};
      // DatabaseUtil.insertDataPrepareStatement(names);

      // DatabaseUtil.updateDataPrepareStatement(5, "Samachar");

      HashMap<Integer,String> data = new HashMap<>();
      data.put(6, "india");
      data.put(7, "bali");

      DatabaseUtil.updateDataPrepareStatement(data);

      DatabaseUtil.readData();

      DatabaseUtil.closeStatement();
      DatabaseUtil.closePrepareStatement();
      DatabaseUtil.closeConnection();

    } catch (Exception e) {
      System.out.println(e);
    }

  }

}
