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
import com.drugstore.client.ws.Manufacturer;
import com.drugstore.client.ws.Patient;
import com.drugstore.client.ws.Drug;
import com.drugstore.client.ws.PrescriptionType;
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

public class PatientTableModel extends AbstractTableModel implements Serializable {

    public static final int NAME_INDEX = 0;
    public static final int SURNAME_INDEX = 1;
    public static final int SSN_INDEX = 2;
    
    protected String[] columnNames;
    protected List<Patient> data = new ArrayList<Patient>();

    public PatientTableModel() {
        columnNames = new String[]{
            "Name", "Surname", "SSN"
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
            case NAME_INDEX:
                return String.class;
            case SURNAME_INDEX:
                return String.class;
            case SSN_INDEX:
                return String.class;
            default:
                return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        Patient record = (Patient) data.get(row);
        switch (column) {
            case NAME_INDEX:
                return record.getName();
            case SURNAME_INDEX:
                return record.getSurname();
            case SSN_INDEX:
                return record.getSsn();
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

    public List<Patient> getData() {
        if(data == null){
            data = new ArrayList<Patient>();
        }
        return data;
    }

    public void setData(List<Patient> data) {
        this.data = data;
        this.fireTableDataChanged();
    }
    
    public Patient getRowData(int row){
        return data.get(row);
    }
}
