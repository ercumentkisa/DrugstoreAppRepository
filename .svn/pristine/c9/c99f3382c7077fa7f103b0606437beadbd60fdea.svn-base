package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.*;
import com.drugstore.shared.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 20.11.2011
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class ContainerRepositoryJDBCImpl implements ContainerRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static ContainerRepositoryJDBCImpl INSTANCE;

    private ContainerRepositoryJDBCImpl() {
    }

    public static synchronized ContainerRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContainerRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public Container find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        Container container = null;
        try {
            ps = connection.prepareStatement("select * from Container c where c.ID = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                container = ContainerRepositoryJDBCImpl.buildObjectFromResultSet(result, "c");

                this.addSubContainersTo(container, connection);

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

        return container;
    }

    @Override
    public void delete(Container object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from Container where ID = ?");
            ps.setLong(1, object.getId());

            ps.executeUpdate();
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
    }

    @Override
    public Container persist(Container object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private Container update(Container object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);

            ps = connection.prepareStatement("update Container c set c.NAME = ? " +
                    " where c.ID = ?");

            ps.setString(1, object.getName());

            ps.executeUpdate();

            PreparedStatement removeOldSubContainersStatement = connection.prepareStatement("delete from Container where parentId = ?");
            removeOldSubContainersStatement.setLong(1, object.getId());
            removeOldSubContainersStatement.executeUpdate();
            removeOldSubContainersStatement.close();

            for (Iterator<Container> iterator = object.getSubContainers().iterator(); iterator.hasNext(); ) {
                Container  subContainer = iterator.next();

                PreparedStatement addSubContainerStatement = connection.prepareStatement("update Container c set c.parentId = ?");
                addSubContainerStatement.setLong(1, object.getId());

                addSubContainerStatement.executeUpdate();
                addSubContainerStatement.close();
            }

            connection.commit();
            
            return this.find(object.getId());

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

    private Container create(Container object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {

            connection.setAutoCommit(false);

            ps = connection.prepareStatement("insert into Container values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setString(2, object.getName());
            ps.setLong(3, object.getContainerType().getId());
            ps.setLong(4, object.getParent().getId());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            long key = 0;
            while (generatedKeys.next()) {
                key = generatedKeys.getLong(1);
            }


            for (Iterator<Container> iterator = object.getSubContainers().iterator(); iterator.hasNext(); ) {
                Container subContainer = iterator.next();

                PreparedStatement pliStatement = connection.prepareStatement("update Container c set c.parentId = ?");
                pliStatement.setLong(1, key);
 
                pliStatement.execute();
                pliStatement.close();
            }

            connection.commit();

            object.setId(Long.valueOf(key));
            return object;
            //return this.find(Long.valueOf(key));

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
    public List<Container> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Container> resultList = new ArrayList<Container>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Container c ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append(" order by c.NAME asc");
            } else {
                sqlBuffer.append(orderClause);
            }

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Container container = ContainerRepositoryJDBCImpl.buildObjectFromResultSet(result, "c");
                
                resultList.add(container);
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

    

    public static Container buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        Container container = new Container();
        container.setId(result.getLong(pointer + ".ID"));
        container.setName(result.getString(pointer + ".NAME"));

        return container;
    }

    private void addSubContainersTo(Container container, Connection connection) {
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Container c where c.parentId = ?");
            ps = connection.prepareStatement(sqlBuffer.toString());
            ps.setLong(1, container.getId());
            result = ps.executeQuery();
            while (result.next()) {
                Container subContainer = buildObjectFromResultSet(result, "c");
                container.addSubContainer(subContainer);
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
    }
}
