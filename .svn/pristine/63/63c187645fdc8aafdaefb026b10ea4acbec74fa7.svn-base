package com.drugstore.server.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 30.10.2011
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
public class PrescriptionLineItem extends Entity{
    @NotNull
    private Long quantity;
    @NotNull
    private Long usage;
    @NotNull
    private Prescription prescription;
    @NotNull
    private Drug drug;

    public void afterUnmarshal(Unmarshaller u, Object parent) {
        this.prescription = (Prescription) parent;
    }
    
    // RequestFactory icin no-arg constructor gerekli
    public PrescriptionLineItem() {
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getUsage() {
        return usage;
    }

    public void setUsage(Long usage) {
        this.usage = usage;
    }

    @XmlTransient
    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
