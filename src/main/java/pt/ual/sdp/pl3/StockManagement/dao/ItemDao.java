package pt.ual.sdp.pl3.StockManagement.dao;

import pt.ual.sdp.pl3.StockManagement.models.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    public List<Item> getAll() throws Exception {
        List<Item> itemList = new ArrayList<>();
        java.sql.Connection c = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("select * from stck_mngmnt.items order by item_id");
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                item.setDescription(rs.getString(3));
                itemList.add(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            c.close();
        }
        return itemList;
    }

    public Item getItem(int id) throws SQLException {
        java.sql.Connection c = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;
        Item item = new Item();
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("SELECT * FROM stck_mngmnt.items WHERE item_id=?");
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                item.setId(rs.getInt(1));
                item.setName(rs.getString(2));
                item.setDescription(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            c.close();
        }
        return item;
    }

   public boolean newItem(Item item) throws Exception {
       java.sql.Connection c = null;
       java.sql.PreparedStatement ps = null;
       boolean confirm = true;
       try {
           c = DbConn.getConnection();
           ps = c.prepareStatement("INSERT INTO stck_mngmnt.items (item_name, item_description) VALUES (?,?)");
           ps.setString(1, item.getName());
           ps.setString(2, item.getDescription());
           ps.executeUpdate();
       } catch (Exception e) {
           confirm = false;
       } finally {
           ps.close();
           c.close();
       }
       return confirm;
   }

   public int updateItem(Item item) throws SQLException {
       java.sql.Connection c = null;
       java.sql.PreparedStatement ps = null;
       int rs = 0;
       try {
           c = DbConn.getConnection();
           ps = c.prepareStatement("UPDATE stck_mngmnt.items SET item_name = ?, item_description = ? WHERE item_id = ?");
           ps.setString(1, item.getName());
           ps.setString(2, item.getDescription());
           ps.setInt(3, item.getId());
           rs = ps.executeUpdate();
           System.out.println(rs);
       } catch (Exception e) {
           rs = 2;
       } finally {
           ps.close();
           c.close();
       }
       return rs;
   }

    public int deleteItem(int id) throws SQLException {
        java.sql.Connection c = null;
        java.sql.PreparedStatement ps = null;
        int rs = 0;
        try {
            c = DbConn.getConnection();
            ps = c.prepareStatement("DELETE FROM stck_mngmnt.items WHERE item_id = ?");
            ps.setInt(1, id);
            rs = ps.executeUpdate();
        } catch (Exception e) {
            rs = 2;
        } finally {
            ps.close();
            c.close();
        }
        return rs;
    }

}
