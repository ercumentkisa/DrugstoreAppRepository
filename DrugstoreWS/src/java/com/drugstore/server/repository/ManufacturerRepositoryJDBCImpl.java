package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.Manufacturer;
import com.drugstore.shared.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 20.11.2011
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class ManufacturerRepositoryJDBCImpl implements ManufacturerRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static ManufacturerRepositoryJDBCImpl INSTANCE;

    private ManufacturerRepositoryJDBCImpl() {
    }

    public static synchronized ManufacturerRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ManufacturerRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public Manufacturer find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        Manufacturer manufacturer = null;
        try {
            ps = connection.prepareStatement("select * from Manufacturer m where m.id = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                manufacturer = ManufacturerRepositoryJDBCImpl.buildObjectFromResultSet(result, "m");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                    ps.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DrugStoreRuntimeException(e);
                }
            }

        }

        return manufacturer;
    }

    @Override
    public void delete(Manufacturer object) {

    }

    @Override
    public Manufacturer persist(Manufacturer object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private Manufacturer update(Manufacturer object) {
        return null;
    }

    private Manufacturer create(Manufacturer object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into Manufacturer values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setString(2, object.getName());
            ps.setString(3, object.getCountry());
            ps.setString(4, object.getContact());
            ps.setString(5, object.getContactPhone());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            long key = 0;
            while (generatedKeys.next()) {
                key = generatedKeys.getLong(1);
            }

            return this.find(Long.valueOf(key));

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DrugStoreRuntimeException(e);
                }
            }

        }

        return null;
    }

    @Override
    public List<Manufacturer> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Manufacturer> resultList = new ArrayList<Manufacturer>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Manufacturer m ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by m.name asc");
            } else {
                sqlBuffer.append(orderClause);
            }
            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Manufacturer manufacturer = ManufacturerRepositoryJDBCImpl.buildObjectFromResultSet(result, "m");

                resultList.add(manufacturer);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                    ps.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DrugStoreRuntimeException(e);
                }
            }

        }

        return resultList;
    }

    public static Manufacturer buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(result.getLong(pointer + ".ID"));
        manufacturer.setName(result.getString(pointer + ".NAME"));
        manufacturer.setContact(result.getString(pointer + ".CONTACT"));
        manufacturer.setContactPhone(result.getString(pointer + ".CONTACTPHONE"));
        manufacturer.setCountry(result.getString(pointer + ".COUNTRY"));

        return manufacturer;
    }
}
