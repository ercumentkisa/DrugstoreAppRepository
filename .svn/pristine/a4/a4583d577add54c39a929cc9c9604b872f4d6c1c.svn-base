package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.SocialSecurityProvider;
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
public class SocialSecurityProviderRepositoryJDBCImpl implements SocialSecurityProviderRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static SocialSecurityProviderRepositoryJDBCImpl INSTANCE;

    private SocialSecurityProviderRepositoryJDBCImpl() {
    }

    public static synchronized SocialSecurityProviderRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SocialSecurityProviderRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public SocialSecurityProvider find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        SocialSecurityProvider socialSecurityProvider = null;
        try {
            ps = connection.prepareStatement("select * from SocialSecurityProvider ssp " +
                    " where  p.id = ?");
            
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                socialSecurityProvider = SocialSecurityProviderRepositoryJDBCImpl.buildObjectFromResultSet(result, "ssp");

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

        return socialSecurityProvider;
    }

    @Override
    public void delete(SocialSecurityProvider object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from SocialSecurityProvider where ID = ?");
            ps.setLong(1, object.getId());

            ps.executeUpdate();
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
    }

    @Override
    public SocialSecurityProvider persist(SocialSecurityProvider object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private SocialSecurityProvider update(SocialSecurityProvider object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update SocialSecurityProvider ssp set ssp.NAME= ? " +
                    " where ssp.ID = ?");

            ps.setString(1, object.getName());
            ps.setLong(2, object.getId());

            ps.executeUpdate();

            return this.find(object.getId());

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

    private SocialSecurityProvider create(SocialSecurityProvider object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into SocialSecurityProvider values (?, ?)", Statement.RETURN_GENERATED_KEYS);

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
    public List<SocialSecurityProvider> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<SocialSecurityProvider> resultList = new ArrayList<SocialSecurityProvider>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from SocialSecurityProvider ssp ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by ssp.NAME asc");
            } else {
                sqlBuffer.append(orderClause);
            }

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                SocialSecurityProvider socialSecurityProvider = SocialSecurityProviderRepositoryJDBCImpl.buildObjectFromResultSet(result, "ssp");

                resultList.add(socialSecurityProvider);
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

    public static SocialSecurityProvider buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        SocialSecurityProvider socialSecurityProvider = new SocialSecurityProvider();
        socialSecurityProvider.setId(result.getLong(pointer + ".ID"));
        socialSecurityProvider.setName(result.getString(pointer + ".NAME"));

        return socialSecurityProvider;
    }

}
