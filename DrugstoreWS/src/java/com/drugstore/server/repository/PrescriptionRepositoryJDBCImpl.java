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
public class PrescriptionRepositoryJDBCImpl implements PrescriptionRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static PrescriptionRepositoryJDBCImpl INSTANCE;

    private PrescriptionRepositoryJDBCImpl() {
    }

    public static synchronized PrescriptionRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrescriptionRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public Prescription find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        Prescription prescription = null;
        try {
            ps = connection.prepareStatement("select * from Prescription p, Patient pa, Doctor d, PrescriptionType pt " +
                    " where p.PRESCRIPTIONTYPEID = pt.ID AND p.DOCTORID = d.ID AND pa.ID = p.PATIENTID" +
                    " AND  p.id = ?");

            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                prescription = PrescriptionRepositoryJDBCImpl.buildObjectFromResultSet(result, "p");

                Patient patient = PatientRepositoryJDBCImpl.buildObjectFromResultSet(result, "pa");
                prescription.setPatient(patient);

                Doctor doctor = DoctorRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");
                prescription.setDoctor(doctor);

                PrescriptionType prescriptionType = PrescriptionTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "pt");
                prescription.setPrescriptionType(prescriptionType);

                this.addPrescriptionLineItemsTo(prescription, connection);

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

        return prescription;
    }

    @Override
    public void delete(Prescription object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from Prescription where ID = ?");
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
    public Prescription persist(Prescription object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private Prescription update(Prescription object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);

            ps = connection.prepareStatement("update Prescription p set p.DATE = ?, p.PRESCRIPTIONTYPEID= ?, p.DOCTORID = ?, p.PATIENTID = ? " +
                    " where p.ID = ?");

            ps.setDate(1, new java.sql.Date(object.getDate().getTime()));
            ps.setLong(2, object.getPrescriptionType().getId());
            ps.setLong(3, object.getDoctor().getId());
            ps.setLong(4, object.getPatient().getId());
            ps.setLong(5, object.getId());

            ps.executeUpdate();

            PreparedStatement removeOldLineItemsStatement = connection.prepareStatement("delete from PrescriptionLineItem where prescriptionId = ?");
            removeOldLineItemsStatement.setLong(1, object.getId());
            removeOldLineItemsStatement.executeUpdate();
            removeOldLineItemsStatement.close();

            for (Iterator<PrescriptionLineItem> iterator = object.getLineItems().iterator(); iterator.hasNext(); ) {
                PrescriptionLineItem prescriptionLineItem = iterator.next();

                PreparedStatement addLineItemStatement = connection.prepareStatement("insert into PrescriptionLineItem values (?, ?, ?, ?, ?)");
                addLineItemStatement.setNull(1, Types.LONGVARCHAR);
                addLineItemStatement.setLong(2, prescriptionLineItem.getQuantity());
                addLineItemStatement.setLong(3, prescriptionLineItem.getUsage());
                addLineItemStatement.setLong(4, object.getId());
                addLineItemStatement.setLong(5, prescriptionLineItem.getDrug().getId());

                addLineItemStatement.executeUpdate();
                addLineItemStatement.close();
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

    private Prescription create(Prescription object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {

            connection.setAutoCommit(false);

            ps = connection.prepareStatement("insert into Prescription values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setDate(2, new Date(object.getDate().getTime()));
            ps.setLong(3, object.getPrescriptionType().getId());
            ps.setLong(4, object.getDoctor().getId());
            ps.setLong(5, object.getPatient().getId());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            long key = 0;
            while (generatedKeys.next()) {
                key = generatedKeys.getLong(1);
            }


            for (Iterator<PrescriptionLineItem> iterator = object.getLineItems().iterator(); iterator.hasNext(); ) {
                PrescriptionLineItem prescriptionLineItem = iterator.next();

                PreparedStatement pliStatement = connection.prepareStatement("insert into PrescriptionLineItem values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                pliStatement.setNull(1, Types.LONGVARCHAR); // auto increment
                pliStatement.setLong(2, prescriptionLineItem.getQuantity());
                pliStatement.setLong(3, prescriptionLineItem.getUsage());
                pliStatement.setLong(4, key);
                pliStatement.setLong(5, prescriptionLineItem.getDrug().getId());

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
    public List<Prescription> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Prescription> resultList = new ArrayList<Prescription>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Prescription p, Doctor d, Patient pa, PrescriptionType pt where ");
            sqlBuffer.append("p.DOCTORID = d.ID AND p.PRESCRIPTIONTYPEID = pt.ID AND p.PATIENTID = pa.ID ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by p.DATE desc");
            } else {
                sqlBuffer.append(orderClause);
            }

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Prescription prescription = PrescriptionRepositoryJDBCImpl.buildObjectFromResultSet(result, "p");

                Doctor doctor = DoctorRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");
                prescription.setDoctor(doctor);

                PrescriptionType prescriptionType = PrescriptionTypeRepositoryJDBCImpl.buildObjectFromResultSet(result, "pt");
                prescription.setPrescriptionType(prescriptionType);

                Patient patient = PatientRepositoryJDBCImpl.buildObjectFromResultSet(result, "pa");
                prescription.setPatient(patient);

                resultList.add(prescription);
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

    public List<Drug> listAllDrugsWithThisPrescriptionType(PrescriptionType prescriptionType) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Drug> resultList = new ArrayList<Drug>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Drug d, Manufacturer m, PrescriptionType pt where ");
            sqlBuffer.append(" d.PRESCRIPTIONTYPEID = ").append(prescriptionType.getId().toString());
            sqlBuffer.append(" AND d.MANUFACTURERID = m.ID AND d.PRESCRIPTIONTYPEID = pt.ID ");
            sqlBuffer.append("order by d.name asc");

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Drug drug = DrugRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");

                Manufacturer manufacturer = ManufacturerRepositoryJDBCImpl.buildObjectFromResultSet(result, "m");
                drug.setManufacturer(manufacturer);
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

    public static Prescription buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setId(result.getLong(pointer + ".ID"));
        prescription.setDate(new Date(result.getDate(pointer + ".DATE").getTime()));

        return prescription;
    }

    private void addPrescriptionLineItemsTo(Prescription prescription, Connection connection) {
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from PrescriptionLineItem where prescriptionId = ?");
            ps = connection.prepareStatement(sqlBuffer.toString());
            ps.setLong(1, prescription.getId());
            result = ps.executeQuery();
            while (result.next()) {
                PrescriptionLineItem prescriptionLineItem = new PrescriptionLineItem();
                prescriptionLineItem.setId(result.getLong("ID"));
                prescriptionLineItem.setQuantity(result.getLong("QUANTITIY"));
                prescriptionLineItem.setUsage(result.getLong("USAGE"));
                prescriptionLineItem.setDrug(DrugRepositoryJDBCImpl.getInstance().find(result.getLong("DRUGID")));
                prescriptionLineItem.setPrescription(prescription);
                
                prescription.addLineItem(prescriptionLineItem);
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
