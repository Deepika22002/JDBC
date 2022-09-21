package invoice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBfunction {
    public Connection connect_to_db(String dbname,String user,String pass){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(conn!=null){
                System.out.println("connection established");
            }else{
                System.out.println("connection failed");
            }
        }catch(Exception e){
            System.out.println(e);

        }
        return conn;

    }
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query="create table "+table_name+"(item_no SERIAL, item_name text,quantity int,cost int,primary key (item_no));";
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void rowinsert(Connection conn,String table_name,String item_name,Integer quantity, Integer cost){
        Statement statement;
        try{
            String query=String.format("insert into %s(item_name,quantity,cost) values('%s','%d','%d');",table_name,item_name,quantity,cost);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void read_data(Connection conn, String table_name){
        Statement statement;
        ResultSet rs;
        try{
            String query=String.format("select * from %s",table_name);
            statement=conn.createStatement();
            rs= statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("item_no")+" ");
                System.out.print(rs.getString("item_name")+" ");
                System.out.print(rs.getString("quantity")+" ");
                System.out.println(rs.getString("cost")+" ");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
