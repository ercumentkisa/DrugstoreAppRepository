package com.drugstore.server.domain;

import javax.validation.constraints.Digits;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 30.10.2011
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
//@Entity
public class Drug extends Entity{
//    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String code;
    @NotNull
    private Long numberOfPills;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private Long owningCost;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private Long sellingCost;

    @NotNull
    private Manufacturer manufacturer;

    @NotNull
    private PrescriptionType prescriptionType;

    private List<Drug> alternatives = new ArrayList<Drug>();


//    private Integer version;

    // RequestFactory icin no-arg constructor gerekli
    public Drug() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public void addAlternative(Drug alternative) {
        alternatives.add(alternative);
    }

    public void removeAlternative(Drug alternative) {
        alternatives.remove(alternative);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfPills() {
        return numberOfPills;
    }

    public void setNumberOfPills(Long numberOfPills) {
        this.numberOfPills = numberOfPills;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getOwningCost() {
        return owningCost;
    }

    public void setOwningCost(Long owningCost) {
        this.owningCost = owningCost;
    }

    public Long getSellingCost() {
        return sellingCost;
    }

    public void setSellingCost(Long sellingCost) {
        this.sellingCost = sellingCost;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(PrescriptionType prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public List<Drug> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Drug> alternatives) {
        this.alternatives = alternatives;
    }
}
