package pt.ual.sdp.pl3.StockManagement.service;

import pt.ual.sdp.pl3.StockManagement.dao.ItemDao;
import pt.ual.sdp.pl3.StockManagement.models.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemsService {
    ItemDao itemDao = new ItemDao();

    public List<Item> getAllItems() throws Exception {
        return itemDao.getAll();
    }

    public Item getItem(int id) throws SQLException {
        return itemDao.getItem(id);
    }

    public boolean createItem(Item item) throws Exception {
        return itemDao.newItem(item);
    }

    public int updateItem(Item item) throws SQLException {
       return itemDao.updateItem(item);
    }

    public int deleteItem(int id) throws SQLException {
        return itemDao.deleteItem(id);
    }

}
