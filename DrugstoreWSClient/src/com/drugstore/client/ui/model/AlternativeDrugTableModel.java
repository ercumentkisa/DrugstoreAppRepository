/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.ui.model;

/**
 *
 * @author ekisa
 */
import com.drugstore.client.domain.AlternativeDrug;
import com.drugstore.client.util.DrugstoreClientUtil;
import com.drugstore.client.ws.Doctor;
import com.drugstore.client.ws.Drug;
import com.drugstore.client.ws.Patient;
import com.drugstore.client.ws.Prescription;
import com.drugstore.client.ws.PrescriptionLineItem;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import javax.swing.table.AbstractTableModel;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class AlternativeDrugTableModel extends AbstractTableModel implements Serializable {

    public static final int DRUG_NAME_INDEX = 0;
    public static final int DRUG_PPP_INDEX = 1;
    public static final int DRUG_SELLING_COST_INDEX = 2;
    public static final int ALTERNATIVE_DRUG_NAME_INDEX = 3;
    public static final int ALTERNATIVE_DRUG_PPP_INDEX = 4;
    public static final int ALTERNATIVE_DRUG_SELLING_COST_INDEX = 5;
    
    protected String[] columnNames;
    protected List<AlternativeDrug> data = new ArrayList<AlternativeDrug>();

    public AlternativeDrugTableModel() {
        columnNames = new String[]{
            "Drug", "Price Per Pill", "Selling Cost", "Suggested Alternative", "Price Per Pill", "Selling Cost"
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
            case DRUG_NAME_INDEX:
                return String.class;
            case DRUG_PPP_INDEX:
                return Double.class;
            case DRUG_SELLING_COST_INDEX:
                return Long.class;
            case ALTERNATIVE_DRUG_NAME_INDEX:
                return String.class;
            case ALTERNATIVE_DRUG_PPP_INDEX:
                return Double.class;
            case ALTERNATIVE_DRUG_SELLING_COST_INDEX:
                return Long.class;
            default:
                return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
        AlternativeDrug  alternativeDrug = data.get(row);
        
        switch (column) {
            case DRUG_NAME_INDEX:
                return alternativeDrug.getPli().getDrug().getName();
            case DRUG_PPP_INDEX:
                return alternativeDrug.getPli().getDrug().getSellingCost().doubleValue() / alternativeDrug.getPli().getDrug().getNumberOfPills().doubleValue();
            case DRUG_SELLING_COST_INDEX:
                return alternativeDrug.getPli().getDrug().getSellingCost();
            case ALTERNATIVE_DRUG_NAME_INDEX:
                return alternativeDrug.getDrug().getName();
            case ALTERNATIVE_DRUG_PPP_INDEX:
                return alternativeDrug.getDrug().getSellingCost().doubleValue() / alternativeDrug.getDrug().getNumberOfPills().doubleValue();
            case ALTERNATIVE_DRUG_SELLING_COST_INDEX:
                return alternativeDrug.getDrug().getSellingCost();
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

    public List<AlternativeDrug> getData() {
        if(data == null){
            data = new ArrayList<AlternativeDrug>();
        }
        return data;
    }

    public void setData(List<AlternativeDrug> data) {
        this.data = data;
        this.fireTableDataChanged();
    }
    
    public AlternativeDrug getRowData(int row){
        return data.get(row);
    }
}
