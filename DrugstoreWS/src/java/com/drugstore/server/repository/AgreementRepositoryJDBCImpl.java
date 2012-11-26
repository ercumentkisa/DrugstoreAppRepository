package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.*;
import com.drugstore.shared.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 20.11.2011
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class AgreementRepositoryJDBCImpl implements AgreementRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static AgreementRepositoryJDBCImpl INSTANCE;

    private AgreementRepositoryJDBCImpl() {
    }

    public static synchronized AgreementRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AgreementRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public Agreement find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        Agreement agreement = null;
        try {
            ps = connection.prepareStatement("select * from Agreement a, SocialSecurityProvider ssp where a.SocialSecurityProviderId=ssp.ID and a.ID = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                agreement = AgreementRepositoryJDBCImpl.buildObjectFromResultSet(result, "a");

                SocialSecurityProvider ssp = SocialSecurityProviderRepositoryJDBCImpl.buildObjectFromResultSet(result, "ssp");
                agreement.setSsp(ssp);

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

        return agreement;
    }

    @Override
    public void delete(Agreement object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from Agreement where ID = ?");
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
    public Agreement persist(Agreement object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private Agreement update(Agreement object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("update Agreement a set a.STARTINGDATE = ?, a.EXPIREDATE = ?, a.SOCIALSECURITYPROVIDERID = ?  " +
                    " where a.ID = ?");

            ps.setDate(1, new java.sql.Date(object.getStartingDate().getTime()));
            ps.setDate(2, new java.sql.Date(object.getEndingDate().getTime()));
            ps.setLong(3, object.getSsp().getId());
            
            ps.executeUpdate();

            
            
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

    private Agreement create(Agreement object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("insert into Agreement values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setDate(2, new java.sql.Date(object.getStartingDate().getTime()));
            ps.setDate(3, new java.sql.Date(object.getEndingDate().getTime()));
            ps.setLong(4, object.getSsp().getId());

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
    public List<Agreement> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Agreement> resultList = new ArrayList<Agreement>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Agreement a ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append(" order by a.EXPIREDATE asc");
            } else {
                sqlBuffer.append(orderClause);
            }

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Agreement agreement = AgreementRepositoryJDBCImpl.buildObjectFromResultSet(result, "a");
                
                resultList.add(agreement);
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

    

    public static Agreement buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        Agreement agreement = new Agreement();
        agreement.setId(result.getLong(pointer + ".ID"));
        agreement.setStartingDate(new Date(result.getDate(pointer + ".STARTINGDATE").getTime()));
        agreement.setEndingDate(new Date(result.getDate(pointer + ".EXPIREDATE").getTime()));
        return agreement;
    }

    
}
