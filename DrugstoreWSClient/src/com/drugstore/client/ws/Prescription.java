
package com.drugstore.client.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for prescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prescription">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.server.drugstore.com/}entity">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="doctor" type="{http://ws.server.drugstore.com/}doctor" minOccurs="0"/>
 *         &lt;element name="lineItems" type="{http://ws.server.drugstore.com/}prescriptionLineItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="patient" type="{http://ws.server.drugstore.com/}patient" minOccurs="0"/>
 *         &lt;element name="prescriptionType" type="{http://ws.server.drugstore.com/}prescriptionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prescription", propOrder = {
    "date",
    "doctor",
    "lineItems",
    "patient",
    "prescriptionType"
})
public class Prescription
    extends Entity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected Doctor doctor;
    protected List<PrescriptionLineItem> lineItems;
    protected Patient patient;
    protected PrescriptionType prescriptionType;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the doctor property.
     * 
     * @return
     *     possible object is
     *     {@link Doctor }
     *     
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the value of the doctor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Doctor }
     *     
     */
    public void setDoctor(Doctor value) {
        this.doctor = value;
    }

    /**
     * Gets the value of the lineItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrescriptionLineItem }
     * 
     * 
     */
    public List<PrescriptionLineItem> getLineItems() {
        if (lineItems == null) {
            lineItems = new ArrayList<PrescriptionLineItem>();
        }
        return this.lineItems;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    public void setPatient(Patient value) {
        this.patient = value;
    }

    /**
     * Gets the value of the prescriptionType property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriptionType }
     *     
     */
    public PrescriptionType getPrescriptionType() {
        return prescriptionType;
    }

    /**
     * Sets the value of the prescriptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriptionType }
     *     
     */
    public void setPrescriptionType(PrescriptionType value) {
        this.prescriptionType = value;
    }

}
