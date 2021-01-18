package pt.ual.sdp.pl3.StockManagement.dao;

import pt.ual.sdp.pl3.StockManagement.models.Delivery;
import pt.ual.sdp.pl3.StockManagement.models.Deposit;
import pt.ual.sdp.pl3.StockManagement.models.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveriesDao {

    public boolean newDelivery(Delivery delivery) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        boolean confirm = true;
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("INSERT INTO stck_mngmnt.deliveries (deli_address) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, delivery.getAddress());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            delivery.setId(id);
            for (Item i : delivery.getItems().keySet()) {
                PreparedStatement psi = c.prepareStatement("INSERT INTO stck_mngmnt.deliveries_items (deli_id, item_id, qty) VALUES (?,?,?)");
                System.out.println(delivery.getId());
                psi.setInt(1, delivery.getId());
                psi.setInt(2, i.getId());
                psi.setInt(3, delivery.getItems().get(i));
                psi.executeUpdate();
            }
        } catch (Exception e) {
            confirm = false;
        } finally {
            ps.close();
            c.close();
        }
        return confirm;
    }

}
