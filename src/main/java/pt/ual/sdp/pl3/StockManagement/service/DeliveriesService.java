package pt.ual.sdp.pl3.StockManagement.service;

import pt.ual.sdp.pl3.StockManagement.dao.DeliveriesDao;
import pt.ual.sdp.pl3.StockManagement.models.Delivery;

import java.sql.SQLException;
import java.util.List;

public class DeliveriesService {
        DeliveriesDao deliveryDao = new DeliveriesDao();
/*
        public List<Delivery> getAllDeliveries() throws Exception {
            return deliveryDao.getAll();
        }

        public Delivery getDelivery(int id) throws SQLException {
            return deliveryDao.getDeposit(id);
        }
*/
        public boolean createDelivery(Delivery delivery) throws Exception {
            return deliveryDao.newDelivery(delivery);
        }

}
