//STEP 1. Import required packages
import java.sql.*;

    public class one {
        // JDBC driver name and database URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String hihi_URL = "jdbc:mysql://localhost/hihi";

        //  Database credentials
        static final String USER = "root";
        static final String PASS = "Raketa4";

        private Connection connect() {
            Connection conn = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(hihi_URL, USER, PASS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        }


        private void doSelect(Connection conn){
            Statement stmt = null;
            try {
                //STEP 4: Execute a query
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT goroda.idgoroda, goroda.gorodaname, ludi.ludiname FROM hihi.ludi JOIN hihi.goroda ON ludi.idludi = goroda.idgoroda";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("idgoroda");
                    String title = rs.getString("gorodaname");
                    String name = rs.getString("ludiname");

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", Title of Goroda: " + title);
                    System.out.println(", Name: " + name);
                }
                rs.close();
                stmt.close();

            } catch(SQLException se2){
                // nothing we can do
                se2.printStackTrace();
                close(conn);
            }
        }


        private void close(Connection conn){
            try {
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        private void doInsert(Connection conn){
            Statement stmt = null;
            try {
                System.out.println("Inserting records into the table...");
                stmt = conn.createStatement();

                String sql = "INSERT INTO hihi.ludi(idludi, ludiname, idgoroda) " +
                        "VALUES ( 5, 'Evgenia', 3)";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO hihi.ludi(idludi, ludiname, idgoroda) " +
                        "VALUES ( 6, 'Sasha', 3)";
                stmt.executeUpdate(sql);
                System.out.println("Inserted records into the table...");

                stmt.close();
            } catch(SQLException se2){
                // nothing we can do
                se2.printStackTrace();
                close(conn);
            }
        }

        private void doUpdate(Connection conn){
            Statement stmt = null;
            try {
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql = "UPDATE ludi " +
                        "SET ludiname = 'Dima' WHERE idludi = 5";
                stmt.executeUpdate(sql);

                // Now you can extract all the records
                // to see the updated records
                sql = "SELECT idludi, ludiname, idgoroda FROM ludi";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("idludi");
                    String Name = rs.getString("ludiname");
                    int idC  = rs.getInt("idgoroda");

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", Name: " + Name);
                    System.out.println(", idgoroda: " + idC);
                }
                rs.close();
            }catch(SQLException se2){
                // nothing we can do
                se2.printStackTrace();
                close(conn);
            }
        }

        private void doDelete(Connection conn){
            Statement stmt = null;
            try {
                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql = "DELETE FROM ludi " +
                        "WHERE idludi = 5";
                stmt.executeUpdate(sql);

                // Now you can extract all the records
                // to see the remaining records
                sql = "SELECT idludi, ludiname, idgoroda FROM ludi";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    //Retrieve by column name
                    int id = rs.getInt("idludi");
                    String Name = rs.getString("ludiname");
                    int idC  = rs.getInt("idgoroda");

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", Name: " + Name);
                    System.out.println(", idgoroda: " + id);
                }
                rs.close();
            }catch(SQLException se2){
                // nothing we can do
                se2.printStackTrace();
                close(conn);
            }
        }

        private void CreateTable(Connection conn){
            Statement stmt = null;
            try {
                System.out.println("Creating table in given database...");
                stmt = conn.createStatement();

                String sql = "CREATE TABLE PATIENTS " +
                        "(id INTEGER not NULL, " +
                        " first VARCHAR(255), " +
                        " last VARCHAR(255), " +
                        " age INTEGER, " +
                        " PRIMARY KEY ( id ))";

                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");
            } catch(SQLException se2){
                // nothing we can do
                se2.printStackTrace();
                close(conn);
            }
        }

        private void AlterTable(Connection conn){
            Statement stmt = null;
            try {
                System.out.println("Creating table in given database...");
                stmt = conn.createStatement();

                String sql = "ALTER TABLE PATIENTS ADD Goroda VARCHAR(255)" ;

                stmt.executeUpdate(sql);
                System.out.println("Alter table in given database...");
            } catch(SQLException se2){
                // nothing we can do
                se2.printStackTrace();
                close(conn);
            }
        }

        public static void main(String[] args) {
            one mc = new one();
            Connection c = mc.connect();
            mc.connect();
            //mc.doInsert(c);
            //mc.doUpdate(c);
            //mc.doDelete(c);
            //mc.doSelect(c);
            // mc.CreateTable(c);
            mc.AlterTable(c);
            mc.close(c);
            System.out.println("Goodbye!");
        }//end main
    }//end FirstExample

