package com.drugstore.server.domain;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 30.10.2011
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class Prescription extends Entity{

    @NotNull
    private PrescriptionType prescriptionType;
    @NotNull
    private Doctor doctor;
    @NotNull
    private Patient patient;
    @NotNull
    private Date date;

    List<PrescriptionLineItem> lineItems = new ArrayList<PrescriptionLineItem>();

    // RequestFactory icin no-arg constructor gerekli
    public Prescription() {
    }

    public void addLineItem(PrescriptionLineItem prescriptionLineItem) {
        lineItems.add(prescriptionLineItem);
    }

    public void removeLineItem(PrescriptionLineItem prescriptionLineItem) {
        lineItems.remove(prescriptionLineItem);
    }

    @XmlElement(name = "lineItems")
    public List<PrescriptionLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<PrescriptionLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }
}
