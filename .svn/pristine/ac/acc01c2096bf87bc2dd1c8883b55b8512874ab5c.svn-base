package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.PrescriptionLineItem;
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
public class PrescriptionLineItemRepositoryJDBCImpl implements PrescriptionLineItemRepository{

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static PrescriptionLineItemRepositoryJDBCImpl INSTANCE;

    private PrescriptionLineItemRepositoryJDBCImpl() {
    }

    public static synchronized PrescriptionLineItemRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrescriptionLineItemRepositoryJDBCImpl();
        }
        
        return INSTANCE;
    }

    @Override
    public PrescriptionLineItem find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        PrescriptionLineItem prescriptionLineItem = null;
        try {
            ps = connection.prepareStatement("select * from PrescriptionLineItem pli where pli.id = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                prescriptionLineItem = PrescriptionLineItemRepositoryJDBCImpl.buildObjectFromResultSet(result, "pli");
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

        return prescriptionLineItem;
    }

    @Override
    public void delete(PrescriptionLineItem object) {
        
    }

    @Override
    public PrescriptionLineItem persist(PrescriptionLineItem object) {
        if (object.getId() == null) {
            return this.create(object);
        }else {
            return this.update(object);
        }
    }

    private PrescriptionLineItem update(PrescriptionLineItem object) {
        return null;
    }

    private PrescriptionLineItem create(PrescriptionLineItem object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("insert into PrescriptionLineItem values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setLong(2, object.getQuantity());
            ps.setLong(3, object.getUsage());
            ps.setLong(4, object.getPrescription().getId());
            ps.setLong(5, object.getDrug().getId());

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
    public List<PrescriptionLineItem> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<PrescriptionLineItem> resultList = new ArrayList<PrescriptionLineItem>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from PrescriptionLineItem pli ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by pli.id asc");
            }else{
                sqlBuffer.append(orderClause);
            }
            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
				PrescriptionLineItem prescriptionLineItem = PrescriptionLineItemRepositoryJDBCImpl.buildObjectFromResultSet(result, "pli");

                resultList.add(prescriptionLineItem);
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

    public static PrescriptionLineItem buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        PrescriptionLineItem prescriptionLineItem = new PrescriptionLineItem();
        prescriptionLineItem.setId(result.getLong(pointer + ".ID"));
        prescriptionLineItem.setQuantity(result.getLong(pointer + ".QUANTITIY"));
        prescriptionLineItem.setUsage(result.getLong(pointer + ".USAGE"));
        prescriptionLineItem.setPrescription(PrescriptionRepositoryJDBCImpl.getInstance().find(result.getLong(pointer + ".PRESCRIPTIONID")));
        prescriptionLineItem.setDrug(DrugRepositoryJDBCImpl.getInstance().find(result.getLong(pointer + ".DRUGID")));

        return prescriptionLineItem;
    }

    
}
