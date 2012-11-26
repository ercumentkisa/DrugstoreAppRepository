package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.Patient;
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
public class PatientRepositoryJDBCImpl implements PatientRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static PatientRepositoryJDBCImpl INSTANCE;

    private PatientRepositoryJDBCImpl() {
    }

    public static synchronized PatientRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PatientRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public Patient find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        Patient patient = null;
        try {
            ps = connection.prepareStatement("select * from Patient p, SocialSecurityProvider ssp " +
                    " where p.SOCIALSECURITYPROVIDERID = ssp.ID " +
                    " AND  p.id = ?");
            
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                patient = PatientRepositoryJDBCImpl.buildObjectFromResultSet(result, "p");

                SocialSecurityProvider socialSecurityProvider = SocialSecurityProviderRepositoryJDBCImpl.buildObjectFromResultSet(result, "ssp");
                patient.setSocialSecurityProvider(socialSecurityProvider);

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

        return patient;
    }

    @Override
    public void delete(Patient object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from Patient where ID = ?");
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
    public Patient persist(Patient object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private Patient update(Patient object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update Patient p set p.NAME = ?, p.SURNAME = ?, p.SSN = ?, p.ADDRESS = ?, p.HOMEPHONE = ?, p.GSM = ?, p.EMAIL = ?, p.SOCIALSECURITYPROVIDERID = ? " +
                    " where p.ID = ?");

            ps.setString(1, object.getName());
            ps.setString(2, object.getSurname());
            ps.setString(3, object.getSsn());
            ps.setString(4, object.getAddress());
            ps.setString(5, object.getHomePhone());
            ps.setString(6, object.getGsm());
            ps.setString(7, object.getEmail());
            ps.setLong(8, object.getSocialSecurityProvider().getId());
            ps.setLong(9, object.getId());

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

    private Patient create(Patient object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into Patient values (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setString(2, object.getName());
            ps.setString(3, object.getSurname());
            ps.setString(4, object.getSsn());
            ps.setString(5, object.getAddress());
            ps.setString(6, object.getHomePhone());
            ps.setString(7, object.getGsm());
            ps.setString(8, object.getEmail());
            ps.setLong(9, object.getSocialSecurityProvider().getId());

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
    public List<Patient> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Patient> resultList = new ArrayList<Patient>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Patient p, SocialSecurityProvider ssp where ");
            sqlBuffer.append("p.SOCIALSECURITYPROVIDERID = ssp.ID ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by p.NAME, p.SURNAME desc");
            } else {
                sqlBuffer.append(orderClause);
            }

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Patient patient = PatientRepositoryJDBCImpl.buildObjectFromResultSet(result, "p");

                SocialSecurityProvider socialSecurityProvider = SocialSecurityProviderRepositoryJDBCImpl.buildObjectFromResultSet(result, "ssp");
                patient.setSocialSecurityProvider(socialSecurityProvider);

                resultList.add(patient);
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

    public static Patient buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        Patient patient = new Patient();
        patient.setId(result.getLong(pointer + ".ID"));
        patient.setName(result.getString(pointer + ".NAME"));
        patient.setSurname(result.getString(pointer + ".SURNAME"));
        patient.setSsn(result.getString(pointer + ".SSN"));
        patient.setAddress(result.getString(pointer + ".ADDRESS"));
        patient.setHomePhone(result.getString(pointer + ".HOMEPHONE"));
        patient.setGsm(result.getString(pointer + ".GSM"));
        patient.setEmail(result.getString(pointer + ".EMAIL"));

        return patient;
    }

}
