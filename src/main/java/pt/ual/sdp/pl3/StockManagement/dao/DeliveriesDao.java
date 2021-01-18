package pt.ual.sdp.pl3.StockManagement.dao;

import pt.ual.sdp.pl3.StockManagement.models.Delivery;
import pt.ual.sdp.pl3.StockManagement.models.Item;
import pt.ual.sdp.pl3.StockManagement.service.ItemsService;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class DeliveriesDao {

    public List<Delivery> getAllDeliveries() throws Exception {
        List<Delivery> deliveriesList = new ArrayList<>();
        ItemsService itemService = new ItemsService();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("SELECT * FROM stck_mngmnt.deliveries ORDER BY deli_id");
            rs = ps.executeQuery();
            while (rs.next()) {
                int deliId = rs.getInt(1);
                String deliAddr = rs.getString(2);
                PreparedStatement psi = c.prepareStatement("SELECT * FROM stck_mngmnt.deliveries_items WHERE deli_id=?");
                psi.setInt(1, deliId);
                ResultSet rsi = psi.executeQuery();
                HashMap<Item, Integer> itemsMap = new HashMap<>();
                while (rsi.next()) {
                    Item item = itemService.getItem(rsi.getInt(2));
                    int qty = rsi.getInt(3);
                    itemsMap.put(item, qty);
                }
                Delivery delivery = new Delivery(deliAddr, itemsMap);
                delivery.setId(deliId);
                deliveriesList.add(delivery);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            c.close();
        }
        return deliveriesList;
    }

    public Delivery getDelivery(int id) throws SQLException {
        java.sql.Connection c = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        ItemsService itemService = new ItemsService();
        HashMap<Item, Integer> itemsMap = new HashMap<>();
        Delivery delivery = new Delivery("", itemsMap);
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("SELECT * FROM deliveries WHERE deli_id=?");
            ps.setObject(1, id);
            rs = ps.executeQuery();
            int deliId =0;
            String deliAddr = null;
            while (rs.next()) {
                deliId = rs.getInt(1);
                deliAddr = rs.getString(2);
                PreparedStatement psi = c.prepareStatement("SELECT * FROM stck_mngmnt.deliveries_items WHERE deli_id=?");
                psi.setInt(1, deliId);
                ResultSet rsi = psi.executeQuery();
                itemsMap = new HashMap<>();
                while (rsi.next()) {
                    Item item = itemService.getItem(rsi.getInt(2));
                    int qty = rsi.getInt(3);
                    itemsMap.put(item, qty);
                }
                delivery.setId(deliId);
                delivery.setAddress(deliAddr);
                delivery.setItems(itemsMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            c.close();
        }
        return delivery;
    }

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
