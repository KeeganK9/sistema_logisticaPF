package senac.senacfx.model.dao;

import senac.senacfx.model.entities.Factory;

import java.util.List;

public interface DistributorDao {

    void insert(senac.senacfx.model.entities.Distributor obj);
    void update(senac.senacfx.model.entities.Distributor obj);


    void deleteById(Integer id);
    senac.senacfx.model.entities.Distributor findById(Integer id);
    List<senac.senacfx.model.entities.Distributor> findAll();
    List<senac.senacfx.model.entities.Distributor> findByDepartment(Factory factory);

}
