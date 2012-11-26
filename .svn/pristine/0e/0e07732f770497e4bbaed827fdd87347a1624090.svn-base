package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.ContainerType;
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
public class ContainerTypeRepositoryJDBCImpl implements ContainerTypeRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static ContainerTypeRepositoryJDBCImpl INSTANCE;

    private ContainerTypeRepositoryJDBCImpl() {
    }

    public static synchronized ContainerTypeRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContainerTypeRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public ContainerType find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        ContainerType containerType = null;
        try {
            ps = connection.prepareStatement("select * from ContainerType ct where ct.id = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                containerType = ContainerTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "ct");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DrugStoreRuntimeException(e);
                }
            }

        }

        return containerType;
    }

    @Override
    public void delete(ContainerType object) {

    }

    @Override
    public ContainerType persist(ContainerType object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private ContainerType update(ContainerType object) {
        return null;
    }

    private ContainerType create(ContainerType object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into ContainerType values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setString(2, object.getName());

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
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DrugStoreRuntimeException(e);
                }
            }

        }

        return null;
    }

    @Override
    public List<ContainerType> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<ContainerType> resultList = new ArrayList<ContainerType>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from ContainerType ct ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by ct.NAME asc");
            } else {
                sqlBuffer.append(orderClause);
            }
            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                ContainerType containerType = ContainerTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "ct");
                resultList.add(containerType);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DrugStoreRuntimeException(e);
                }
            }

        }

        return resultList;
    }

    public static ContainerType buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        ContainerType containerType = new ContainerType();
        containerType.setId(result.getLong(pointer + ".ID"));
        containerType.setName(result.getString(pointer + ".NAME"));
        return containerType;
    }
}
