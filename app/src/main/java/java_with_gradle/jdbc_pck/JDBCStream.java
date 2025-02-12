package java_with_gradle.jdbc_pck;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.tika.Tika;

public class JDBCStream {

  public static void main(String[] args) {


    Connection connection;
    try {

      Tika tika = new Tika();
      connection = DriverManager.getConnection("jdbc:mysql://localhost/bascom", "root", "Walden0042$$");

      PreparedStatement pst = connection.prepareStatement("insert into photo(file) values (?)");

      String path = "/home/arpit-parekh/Downloads/NisargArunbhaiTrivediResume.pdf";

      FileInputStream fis = new FileInputStream(path);

      pst.setBinaryStream(1, fis);
      pst.executeUpdate();

      String query = "select * from photo";
      PreparedStatement pst1 = connection.prepareStatement(query);
      ResultSet result = pst1.executeQuery();

      String writePath = "/home/arpit-parekh/files/bascom/";

      int counter = 1;
      while (result.next()) {

        InputStream fis1 = result.getBinaryStream("file");

        String mimeType = tika.detect(fis1);
        System.out.println("MIME Type: " + mimeType);
        String extension = mimeType.split("/")[1]; // Example: application/pdf -> pdf

        String fileName = "file" + counter + "." + extension;

        FileOutputStream fos = new FileOutputStream("/home/arpit-parekh/files/bascom/" + fileName);

        fos.write(fis1.readAllBytes());
        
        fos.close();
        counter++;
        System.out.println("File written successfully");


      }

      pst.close();
      connection.close();

    } catch (Exception e) {
      System.out.println(e);
    }




  }

}
