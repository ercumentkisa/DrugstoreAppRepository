
package com.drugstore.client.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drug complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drug">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.server.drugstore.com/}entity">
 *       &lt;sequence>
 *         &lt;element name="alternatives" type="{http://ws.server.drugstore.com/}drug" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manufacturer" type="{http://ws.server.drugstore.com/}manufacturer" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfPills" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="owningCost" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="prescriptionType" type="{http://ws.server.drugstore.com/}prescriptionType" minOccurs="0"/>
 *         &lt;element name="sellingCost" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drug", propOrder = {
    "alternatives",
    "code",
    "manufacturer",
    "name",
    "numberOfPills",
    "owningCost",
    "prescriptionType",
    "sellingCost"
})
public class Drug
    extends Entity
{

    @XmlElement(nillable = true)
    protected List<Drug> alternatives;
    protected String code;
    protected Manufacturer manufacturer;
    protected String name;
    protected Long numberOfPills;
    protected Long owningCost;
    protected PrescriptionType prescriptionType;
    protected Long sellingCost;

    /**
     * Gets the value of the alternatives property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternatives property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternatives().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Drug }
     * 
     * 
     */
    public List<Drug> getAlternatives() {
        if (alternatives == null) {
            alternatives = new ArrayList<Drug>();
        }
        return this.alternatives;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link Manufacturer }
     *     
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Manufacturer }
     *     
     */
    public void setManufacturer(Manufacturer value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the numberOfPills property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumberOfPills() {
        return numberOfPills;
    }

    /**
     * Sets the value of the numberOfPills property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumberOfPills(Long value) {
        this.numberOfPills = value;
    }

    /**
     * Gets the value of the owningCost property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOwningCost() {
        return owningCost;
    }

    /**
     * Sets the value of the owningCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOwningCost(Long value) {
        this.owningCost = value;
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

    /**
     * Gets the value of the sellingCost property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSellingCost() {
        return sellingCost;
    }

    /**
     * Sets the value of the sellingCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSellingCost(Long value) {
        this.sellingCost = value;
    }

}
