
package com.drugstore.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for doctor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="doctor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.server.drugstore.com/}entity">
 *       &lt;sequence>
 *         &lt;element name="expertise" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doctor", propOrder = {
    "expertise",
    "name",
    "surname"
})
public class Doctor
    extends Entity
{

    protected String expertise;
    protected String name;
    protected String surname;

    /**
     * Gets the value of the expertise property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * Sets the value of the expertise property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpertise(String value) {
        this.expertise = value;
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
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

}
