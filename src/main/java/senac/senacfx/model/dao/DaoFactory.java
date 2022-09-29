package senac.senacfx.model.dao;

import senac.senacfx.db.DB;
import senac.senacfx.model.dao.impl.FactoryDaoJDBC;
import senac.senacfx.model.dao.impl.DistributorDaoJDBC;

public class DaoFactory {

    public static DistributorDao createSellerDao(){
        return new DistributorDaoJDBC(DB.getConnection());
    }

    public static FactoryDao createDepartmentDao(){
        return new FactoryDaoJDBC(DB.getConnection());
    }

}
