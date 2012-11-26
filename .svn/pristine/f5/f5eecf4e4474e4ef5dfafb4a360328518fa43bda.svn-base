
package com.drugstore.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for agreement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="agreement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.server.drugstore.com/}entity">
 *       &lt;sequence>
 *         &lt;element name="endingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ssp" type="{http://ws.server.drugstore.com/}socialSecurityProvider" minOccurs="0"/>
 *         &lt;element name="startingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agreement", propOrder = {
    "endingDate",
    "ssp",
    "startingDate"
})
public class Agreement
    extends Entity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endingDate;
    protected SocialSecurityProvider ssp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startingDate;

    /**
     * Gets the value of the endingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndingDate() {
        return endingDate;
    }

    /**
     * Sets the value of the endingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndingDate(XMLGregorianCalendar value) {
        this.endingDate = value;
    }

    /**
     * Gets the value of the ssp property.
     * 
     * @return
     *     possible object is
     *     {@link SocialSecurityProvider }
     *     
     */
    public SocialSecurityProvider getSsp() {
        return ssp;
    }

    /**
     * Sets the value of the ssp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SocialSecurityProvider }
     *     
     */
    public void setSsp(SocialSecurityProvider value) {
        this.ssp = value;
    }

    /**
     * Gets the value of the startingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartingDate() {
        return startingDate;
    }

    /**
     * Sets the value of the startingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartingDate(XMLGregorianCalendar value) {
        this.startingDate = value;
    }

}
