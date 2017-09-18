import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class MDXTest {
  public static void main (String args[]) {
    System.out.println("MDX Test");
    if( args.length != 3 ) {
      System.out.println("Expects: <ConnectionString> <User> <Password>");
      System.out.println("ex: 'jdbc:xmla:Server=http://atscale:10502/xmla/default/dev;USEJWT=true' 'TOKEN' 'the JWT token'" );
      return;
    }
    String connectionString = args[0];
    String user = args[1];
    String password = args[2];
    queryAtScale( connectionString, user, password );
  }

  private static void queryAtScale( String connectionString, String user, String password){
    try {
      System.out.println("Load Class");
      Class.forName("org.olap4j.driver.xmla.XmlaOlap4jDriver");
      System.out.println("Connect");
      Connection connection = DriverManager.getConnection(connectionString, user, password);
      System.out.println("Unwrap Conn");
      OlapConnection mdxConnection = connection.unwrap(OlapConnection.class);
      System.out.println("Create Statement");
      OlapStatement statement = mdxConnection.createStatement();
      System.out.println("Set Catalog");
      mdxConnection.setCatalog("Kiran_Sales_Insights");
      System.out.println("Starting the query");
      CellSet cellSet = statement.executeOlapQuery("SELECT\n" +
      " NON EMPTY Hierarchize(\n" +
      " { DrilldownLevel(\n" +
      " { [Gender Dimension].[Gender Hierarchy].[All] },,,\n" +
      " INCLUDE_CALC_MEMBERS\n" +
      " ) }\n" +
      " ) DIMENSION PROPERTIES PARENT_UNIQUE_NAME,\n" +
      " HIERARCHY_UNIQUE_NAME ON COLUMNS\n" +
      "FROM\n" +
      " [KiranInternet Sales Cube] CELL PROPERTIES VALUE,\n" +
      " FORMAT_STRING,\n" +
      " LANGUAGE,\n" +
      " BACK_COLOR,\n" +
      " FORE_COLOR,\n" +
      " FONT_FLAGS");
      System.out.println("Executed MDX Query");
      statement.close();
      mdxConnection.close();
      System.out.println("Closed MDX Connection");
    } catch (Exception e) {
      System.out.println("Failed");
      e.printStackTrace();
    }
  }
}
