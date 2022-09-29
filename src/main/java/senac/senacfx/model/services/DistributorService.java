package senac.senacfx.model.services;

import senac.senacfx.model.dao.DaoFactory;
import senac.senacfx.model.dao.DistributorDao;

import java.util.List;

public class DistributorService {

    //dependencia injetada usando padrao factory
    private DistributorDao dao = DaoFactory.createSellerDao();

    public List<senac.senacfx.model.entities.Distributor> findAll() {
        return dao.findAll();

        //Dados MOCK (fake) so para testar, sem puxar do banco por hora
//        List<Seller> list = new ArrayList<>();
//        list.add(new Seller(1,"Computadores"));
//        list.add(new Seller(2,"Alimentação"));
//        list.add(new Seller(3,"Financeiro"));
//        return list;

    }
    public void saveOrUpdate(senac.senacfx.model.entities.Distributor obj){
        if (obj.getId() == null){
            dao.insert(obj);
        } else {
            dao.update(obj);
            }
        }

        public void remove(senac.senacfx.model.entities.Distributor obj){
            dao.deleteById(obj.getId());
        }
    }

