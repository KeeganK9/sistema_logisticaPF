package senac.senacfx.model.dao;

import senac.senacfx.model.entities.Factory;

import java.util.List;

public interface FactoryDao {

    void insert(Factory obj);
    void update(Factory obj);
    void deleteById(Integer id);
    Factory findById(Integer id);
    List<Factory> findAll();

}
