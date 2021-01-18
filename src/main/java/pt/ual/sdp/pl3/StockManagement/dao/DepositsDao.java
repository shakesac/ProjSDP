package pt.ual.sdp.pl3.StockManagement.dao;

import pt.ual.sdp.pl3.StockManagement.models.Deposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepositsDao {

    public List<Deposit> getAll() throws Exception {
        List<Deposit> depositsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("SELECT * FROM stck_mngmnt.deposits ORDER BY depo_id");
            rs = ps.executeQuery();
            while (rs.next()) {
                Deposit deposit = new Deposit();
                deposit.setId(rs.getInt(1));
                deposit.setItem(rs.getInt(2));
                deposit.setQuantity(rs.getInt(3));
                depositsList.add(deposit);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            c.close();
        }
        return depositsList;
    }

    public Deposit getDeposit(int id) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Deposit deposit = new Deposit();
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("SELECT * FROM stck_mngmnt.deposits WHERE depo_id=?");
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                deposit.setId(rs.getInt(1));
                deposit.setItem(rs.getInt(2));
                deposit.setQuantity(rs.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            c.close();
        }
        return deposit;
    }

    public boolean newDeposit(Deposit deposit) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        boolean confirm = true;
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("INSERT INTO stck_mngmnt.items (item_name, item_description) VALUES (?,?)");
            ps.setInt(1, deposit.getItem());
            ps.setInt(2, deposit.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            confirm = false;
        } finally {
            ps.close();
            c.close();
        }
        return confirm;
    }

}
