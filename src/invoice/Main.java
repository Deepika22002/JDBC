package invoice;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        DBfunction db=new DBfunction();
        Connection conn= db.connect_to_db("Invoice","postgres","deepika");
       db.createTable(conn,"Items");
       db.rowinsert(conn,"Items","xyz",7,100);
       db.read_data(conn,"Items");
    }
}
