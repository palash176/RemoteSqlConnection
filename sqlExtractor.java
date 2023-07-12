import java.sql.*;

/**
 * Class is responsible for connecting to the dedicated SQL server (hosted on PostGres)
 * and running selection queries to extract data with the required parameters to
 * train the Dynamic Tasking ML model.
 */
public class sqlExtractor {

    /**
     * Main method containing connection, routing and selection query to extract data
     * @param args unused
     * @throws SQLException for connection errors
     */
    public static void main(String args[]) throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        String SELECT_QUERY = "" +
                /*
                Enter all the required Fields Here.
                */
                "";
        String INSERT_QUERY = "....." ;

        // Establishing connection to the dragon fire database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to First database...");
            /*
            Ensure that you configure your connection to the database
             */
            conn = DriverManager.getConnection("IP",
                    "user", "root" +"\n");
            statement =  conn.createStatement();
            rs = statement.executeQuery(SELECT_QUERY);
            // Executing selection query
            while (rs.next()){
                System.out.println(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    statement.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //connecting second DB- change connection detail
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to First database...");
            /*
            Connection to remote database for storing the data
             */
            conn = DriverManager.getConnection("IP", "user", "root");
            conn.setAutoCommit(false);
            statement =  conn.createStatement();
            rs = statement.executeQuery(INSERT_QUERY);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    statement.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}