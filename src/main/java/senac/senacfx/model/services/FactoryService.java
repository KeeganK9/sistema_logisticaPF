package senac.senacfx.model.services;

import senac.senacfx.model.dao.DaoFactory;
import senac.senacfx.model.dao.FactoryDao;
import senac.senacfx.model.entities.Factory;

import java.util.List;

public class FactoryService {

    //dependencia injetada usando padrao factory
    private FactoryDao dao = DaoFactory.createDepartmentDao();

    public List<Factory> findAll() {
        return dao.findAll();

        //Dados MOCK (fake) so para testar, sem puxar do banco por hora
//        List<Department> list = new ArrayList<>();
//        list.add(new Department(1,"Computadores"));
//        list.add(new Department(2,"Alimentação"));
//        list.add(new Department(3,"Financeiro"));
//        return list;

    }
    public void saveOrUpdate(Factory obj){
        if (obj.getId() == null){
            dao.insert(obj);
        } else {
            dao.update(obj);
            }
        }

        public void remove(Factory obj){
            dao.deleteById(obj.getId());
        }
    }

