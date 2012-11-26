/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.model;

/**
 *
 * @author ekisa
 */
import com.drugstore.client.util.DrugstoreClientUtil;
import com.drugstore.client.ws.Doctor;
import com.drugstore.client.ws.Patient;
import com.drugstore.client.ws.Prescription;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class PrescriptionTableModel extends AbstractTableModel implements Serializable {

    public static final int DATE_INDEX = 0;
    public static final int PATIENT_INDEX = 1;
    public static final int DOCTOR_INDEX = 2;
    
    protected String[] columnNames;
    protected List<Prescription> data = new ArrayList<Prescription>();

    public PrescriptionTableModel() {
        columnNames = new String[]{
            "Date", "Patient", "Doctor"
        };
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class getColumnClass(int column) {
        switch (column) {
            case DATE_INDEX:
                return String.class;
            case PATIENT_INDEX:
                return Patient.class;
            case DOCTOR_INDEX:
                return Doctor.class;
            default:
                return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        Prescription record = (Prescription) data.get(row);
        switch (column) {
            case DATE_INDEX:
                return DrugstoreClientUtil.dateXMLToString(record.getDate());
            case PATIENT_INDEX:
                return record.getPatient().getName() + " " + record.getPatient().getSurname();
            case DOCTOR_INDEX:
                return record.getDoctor().getName() + " " + record.getDoctor().getSurname();
            default:
                return new Object();
        }
    }

    

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public List<Prescription> getData() {
        if(data == null){
            data = new ArrayList<Prescription>();
        }
        return data;
    }

    public void setData(List<Prescription> data) {
        this.data = data;
        this.fireTableDataChanged();
    }
    
    public Prescription getRowData(int row){
        return data.get(row);
    }

    public void removeData(int index) {
        data.remove(index);
        this.fireTableDataChanged();
    }
    
    
}
