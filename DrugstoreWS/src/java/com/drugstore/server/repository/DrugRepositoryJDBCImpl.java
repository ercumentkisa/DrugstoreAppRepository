package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.Drug;
import com.drugstore.server.domain.Manufacturer;
import com.drugstore.server.domain.PrescriptionType;
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
public class DrugRepositoryJDBCImpl implements DrugRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static DrugRepositoryJDBCImpl INSTANCE;

    private DrugRepositoryJDBCImpl() {
    }

    public static synchronized DrugRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DrugRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public Drug find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        Drug drug = null;
        try {
            ps = connection.prepareStatement("select * from Drug d, Manufacturer m, PrescriptionType pt where " +
                    " d.MANUFACTURERID = m.ID AND d.PRESCRIPTIONTYPEID = pt.ID " +
                    " AND  d.id = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                drug = DrugRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");

                Manufacturer manufacturer = ManufacturerRepositoryJDBCImpl.buildObjectFromResultSet(result, "m");
                drug.setManufacturer(manufacturer);

                PrescriptionType prescriptionType = PrescriptionTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "pt");
                drug.setPrescriptionType(prescriptionType);

                this.addAlternativeDrugsTo(drug, connection);
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

        return drug;
    }

    @Override
    public void delete(Drug object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from Drug where ID = ?");
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
    public Drug persist(Drug object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private Drug update(Drug object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            
            ps = connection.prepareStatement("update Drug d set d.NAME = ?, d.CODE = ?, d.NUMBEROFPILLS = ?, d.MANUFACTURERID = ?, d.PRESCRIPTIONTYPEID = ?, d.OWNINGCOST = ?, d.SELLINGCOST = ? " +
                    " where d.ID = ?");

            ps.setString(1, object.getName());
            ps.setString(2, object.getCode());
            ps.setLong(3, object.getNumberOfPills());
            ps.setLong(4, object.getManufacturer().getId());
            ps.setLong(5, object.getPrescriptionType().getId());
            ps.setLong(6, object.getOwningCost());
            ps.setLong(7, object.getSellingCost());
            ps.setLong(8, object.getId());

            ps.executeUpdate();
            
            
            PreparedStatement removeOldAlternativeDrugsStatement = connection.prepareStatement("delete from AlternativeDrug where drug1Id = ? or drug2Id = ?");
            removeOldAlternativeDrugsStatement.setLong(1, object.getId());
            removeOldAlternativeDrugsStatement.setLong(2, object.getId());
            removeOldAlternativeDrugsStatement.executeUpdate();
            removeOldAlternativeDrugsStatement.close();

            for (Iterator<Drug> iterator = object.getAlternatives().iterator(); iterator.hasNext();) {
                Drug alternativeDrug = iterator.next();

                PreparedStatement addAlternativeDrugStatement = connection.prepareStatement("insert into AlternativeDrug values (?, ?)");
                addAlternativeDrugStatement.setLong(1, object.getId());
                addAlternativeDrugStatement.setLong(2, alternativeDrug.getId());

                addAlternativeDrugStatement.executeUpdate();
                addAlternativeDrugStatement.close();
                
                PreparedStatement addAlternativeDrugStatementInverse = connection.prepareStatement("insert into AlternativeDrug values (?, ?)");
                addAlternativeDrugStatementInverse.setLong(1, alternativeDrug.getId());
                addAlternativeDrugStatementInverse.setLong(2, object.getId());

                addAlternativeDrugStatementInverse.executeUpdate();
                addAlternativeDrugStatementInverse.close();
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

    private Drug create(Drug object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            
            ps = connection.prepareStatement("insert into Drug values (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setString(2, object.getCode());
            ps.setString(3, object.getName());
            ps.setLong(4, object.getNumberOfPills());
            ps.setLong(5, object.getOwningCost());
            ps.setLong(6, object.getSellingCost());
            ps.setLong(7, object.getManufacturer().getId());
            ps.setLong(8, object.getPrescriptionType().getId());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            long key = 0;
            while (generatedKeys.next()) {
                key = generatedKeys.getLong(1);
            }
            object.setId(key);
            
            for (Iterator<Drug> iterator = object.getAlternatives().iterator(); iterator.hasNext();) {
                Drug alternativeDrug = iterator.next();

                PreparedStatement addAlternativeDrugStatement = connection.prepareStatement("insert into AlternativeDrug values (?, ?)");
                addAlternativeDrugStatement.setLong(1, object.getId());
                addAlternativeDrugStatement.setLong(2, alternativeDrug.getId());

                addAlternativeDrugStatement.executeUpdate();
                addAlternativeDrugStatement.close();

                PreparedStatement addAlternativeDrugStatementInverse = connection.prepareStatement("insert into AlternativeDrug values (?, ?)");
                addAlternativeDrugStatementInverse.setLong(1, alternativeDrug.getId());
                addAlternativeDrugStatementInverse.setLong(2, object.getId());

                addAlternativeDrugStatementInverse.executeUpdate();
                addAlternativeDrugStatementInverse.close();
            }

            connection.commit();
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
    public List<Drug> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Drug> resultList = new ArrayList<Drug>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Drug d, Manufacturer m, PrescriptionType pt where ");
            sqlBuffer.append("d.MANUFACTURERID = m.ID AND d.PRESCRIPTIONTYPEID = pt.ID ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by d.name asc");
            } else {
                sqlBuffer.append(orderClause);
            }

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Drug drug = DrugRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");

                Manufacturer manufacturer = ManufacturerRepositoryJDBCImpl.buildObjectFromResultSet(result, "m");
                drug.setManufacturer(manufacturer);

                PrescriptionType prescriptionType = PrescriptionTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "pt");
                drug.setPrescriptionType(prescriptionType);

                resultList.add(drug);
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

    public static Drug buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        Drug drug = new Drug();
        drug.setId(result.getLong(pointer + ".ID"));
        drug.setName(result.getString(pointer + ".NAME"));
        drug.setCode(result.getString(pointer + ".CODE"));
        drug.setOwningCost(result.getLong(pointer + ".OWNINGCOST"));
        drug.setSellingCost(result.getLong(pointer + ".SELLINGCOST"));
        drug.setNumberOfPills(result.getLong(pointer + ".NUMBEROFPILLS"));

        return drug;
    }

    private Drug addAlternativeDrugsTo(Drug drug, Connection connection) {
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Drug d where d.ID in(")
                        .append(" select ad.DRUG2ID from Drug d, Alternativedrug ad")
                        .append(" where d.ID = ad.DRUG1ID")
                        .append(" AND d.ID = ").append(drug.getId().toString())
                    .append(")");

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Drug alternative = DrugRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");
                drug.addAlternative(alternative);
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

        return drug;
    }

    public List<Drug> findCheaperDrugAlternatives(Drug drug) {
        String sql = "select d2.* from drugstore.drug d, drugstore.drug d2, drugstore.alternativedrug ad "
                + " where d.ID = ad.drug1Id AND d2.ID = ad.drug2Id "
                + " AND(d.SellingCost / d.NumberOfPills) > (d2.SellingCost / d2.NumberOfPills)"
                + " AND d.ID = ?";
        
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Drug> resultList = new ArrayList<Drug>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, drug.getId());
            
            result = ps.executeQuery();
            
            while (result.next()) {
                Long alternativeDrugId = result.getLong("d2.ID");
                final Drug alternativeDrug = this.find(alternativeDrugId);
                resultList.add(alternativeDrug);
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
}
