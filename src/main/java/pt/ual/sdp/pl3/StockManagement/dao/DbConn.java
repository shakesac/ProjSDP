package pt.ual.sdp.pl3.StockManagement.dao;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DbConn {

    public DbConn() {
    }

    public static Connection getConnection() throws Exception {
        Context c = new InitialContext();
        try {
            DataSource d = (DataSource) c.lookup("java:/PostgresDS");
            return d.getConnection();
        } catch (Exception ex) {
            throw new Exception("Database connection error");
        }
    }
}
