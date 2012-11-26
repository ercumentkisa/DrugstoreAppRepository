/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugstore.client.domain;

import com.drugstore.client.ws.Drug;
import com.drugstore.client.ws.PrescriptionLineItem;

/**
 *
 * @author ekisa
 */
public class AlternativeDrug {
    
    private PrescriptionLineItem pli;
    private Drug drug;

    public AlternativeDrug() {
    }

    public AlternativeDrug(PrescriptionLineItem pli, Drug drug) {
        this.pli = pli;
        this.drug = drug;
    }

    
    
    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public PrescriptionLineItem getPli() {
        return pli;
    }

    public void setPli(PrescriptionLineItem pli) {
        this.pli = pli;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlternativeDrug other = (AlternativeDrug) obj;
        if (this.pli != other.pli && (this.pli == null || !this.pli.equals(other.pli))) {
            return false;
        }
        if (this.drug != other.drug && (this.drug == null || !this.drug.equals(other.drug))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.pli != null ? this.pli.hashCode() : 0);
        hash = 97 * hash + (this.drug != null ? this.drug.hashCode() : 0);
        return hash;
    }
    
    
}
