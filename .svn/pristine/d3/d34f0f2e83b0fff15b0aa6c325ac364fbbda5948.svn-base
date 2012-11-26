package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.PrescriptionType;
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
public class PrescriptionTypeRepositoryJDBCImpl implements PrescriptionTypeRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static PrescriptionTypeRepositoryJDBCImpl INSTANCE;

    private PrescriptionTypeRepositoryJDBCImpl() {
    }

    public static synchronized PrescriptionTypeRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrescriptionTypeRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public PrescriptionType find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        PrescriptionType prescriptionType = null;
        try {
            ps = connection.prepareStatement("select * from PrescriptionType pt where pt.id = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                prescriptionType = PrescriptionTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "pt");
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

        return prescriptionType;
    }

    @Override
    public void delete(PrescriptionType object) {

    }

    @Override
    public PrescriptionType persist(PrescriptionType object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private PrescriptionType update(PrescriptionType object) {
        return null;
    }

    private PrescriptionType create(PrescriptionType object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into PrescriptionType values (?, ?)", Statement.RETURN_GENERATED_KEYS);
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
    public List<PrescriptionType> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<PrescriptionType> resultList = new ArrayList<PrescriptionType>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from PrescriptionType pt ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by pt.id asc");
            } else {
                sqlBuffer.append(orderClause);
            }
            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                PrescriptionType prescriptionType = PrescriptionTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "pt");
                resultList.add(prescriptionType);
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

    public static PrescriptionType buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        PrescriptionType prescriptionType = new PrescriptionType();
        prescriptionType.setId(result.getLong(pointer + ".ID"));
        prescriptionType.setName(result.getString(pointer + ".NAME"));
        return prescriptionType;
    }
}
