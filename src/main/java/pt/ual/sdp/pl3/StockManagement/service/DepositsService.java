package pt.ual.sdp.pl3.StockManagement.service;

import pt.ual.sdp.pl3.StockManagement.dao.DepositsDao;
import pt.ual.sdp.pl3.StockManagement.models.Deposit;

import java.sql.SQLException;
import java.util.List;

public class DepositsService {
        DepositsDao depositsDao = new DepositsDao();

        public List<Deposit> getAllDeposits() throws Exception {
            return depositsDao.getAll();
        }

        public Deposit getDeposit(int id) throws SQLException {
            return depositsDao.getDeposit(id);
        }

        public boolean createDeposit(Deposit deposit) throws Exception {
            return depositsDao.newDeposit(deposit);
        }

}
