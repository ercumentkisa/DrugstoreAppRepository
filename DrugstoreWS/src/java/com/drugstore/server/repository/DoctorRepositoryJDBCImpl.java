package com.drugstore.server.repository;

import com.drugstore.server.exception.DrugStoreRuntimeException;
import com.drugstore.server.db.ConnectionManager;
import com.drugstore.server.domain.Doctor;
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
public class DoctorRepositoryJDBCImpl implements DoctorRepository {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static DoctorRepositoryJDBCImpl INSTANCE;

    private DoctorRepositoryJDBCImpl() {
    }

    public static synchronized DoctorRepositoryJDBCImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoctorRepositoryJDBCImpl();
        }

        return INSTANCE;
    }

    @Override
    public Doctor find(Long key) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        Doctor doctor = null;
        try {
            ps = connection.prepareStatement("select * from Doctor d where d.id = ?");
            ps.setLong(1, key);
            result = ps.executeQuery();
            while (result.next()) {
                doctor = DoctorRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");

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

        return doctor;
    }

    @Override
    public void delete(Doctor object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("delete from Doctor where ID = ?");
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
    public Doctor persist(Doctor object) {
        if (object.getId() == null) {
            return this.create(object);
        } else {
            return this.update(object);
        }
    }

    private Doctor update(Doctor object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("update DRUG d set d.NAME = ?, d.SURNAME = ?, d.EXPERTISE = ? " +
                    " where d.ID = ?");

            ps.setString(1, object.getName());
            ps.setString(2, object.getSurname());
            ps.setString(3, object.getExpertise());
            ps.setLong(4, object.getId());

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

    private Doctor create(Doctor object) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into Doctor values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setNull(1, Types.LONGVARCHAR); // auto increment
            ps.setString(2, object.getName());
            ps.setString(3, object.getSurname());
            ps.setString(4, object.getExpertise());

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
    public List<Doctor> listAll(String orderClause) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        List<Doctor> resultList = new ArrayList<Doctor>();
        try {
            StringBuffer sqlBuffer = new StringBuffer("select * from Doctor d ");
            if (StringUtil.isEmpty(orderClause)) {
                sqlBuffer.append("order by d.name asc");
            } else {
                sqlBuffer.append(orderClause);
            }

            ps = connection.prepareStatement(sqlBuffer.toString());
            result = ps.executeQuery();
            while (result.next()) {
                Doctor doctor = DoctorRepositoryJDBCImpl.buildObjectFromResultSet(result, "d");

                resultList.add(doctor);
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

    public static Doctor buildObjectFromResultSet(ResultSet result, String pointer) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(result.getLong(pointer + ".ID"));
        doctor.setName(result.getString(pointer + ".NAME"));
        doctor.setSurname(result.getString(pointer + ".SURNAME"));
        doctor.setExpertise(result.getString(pointer + ".EXPERTISE"));

        return doctor;
    }

}
