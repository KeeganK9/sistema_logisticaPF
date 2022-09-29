package senac.senacfx.model.dao.impl;

import senac.senacfx.db.DB;
import senac.senacfx.db.DbException;
import senac.senacfx.model.dao.FactoryDao;
import senac.senacfx.model.entities.Factory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactoryDaoJDBC implements FactoryDao {
    private Connection conn;

    public FactoryDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Factory obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("insert into department " +
                    "(Name) " +
                    "values (?) ",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Error! No rows affected!");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Factory obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("update department " +
                            "set Name = ? " +
                            "where Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0){
                throw new DbException("Error! No rows affected!");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("delete from department where Id = ?");

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0){
                throw new DbException("Departamento inexistente!");
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Factory findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("" +
                    "select * from department " +
                    "where Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
                Factory dep = instantiateDepartment(rs);
                return dep;

            }
            return null;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    private Factory instantiateDepartment(ResultSet rs) throws SQLException {
        Factory dep = new Factory();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Factory> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("" +
                    "select * from department "+
                    "order by Name");

            rs = st.executeQuery();

            List<Factory> list = new ArrayList<>();
            Map<Integer, Factory> map = new HashMap<>();

            while (rs.next()){

                Factory dep = map.get(rs.getInt("Id"));

                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("Id"), dep);
                }

                list.add(dep);

            }
            return list;

        } catch (SQLException e){
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }


    }
}
