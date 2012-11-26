
package com.drugstore.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for persistSocialSecurityProvider complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="persistSocialSecurityProvider">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="socialSecurityProvider" type="{http://ws.server.drugstore.com/}socialSecurityProvider" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "persistSocialSecurityProvider", propOrder = {
    "socialSecurityProvider"
})
public class PersistSocialSecurityProvider {

    protected SocialSecurityProvider socialSecurityProvider;

    /**
     * Gets the value of the socialSecurityProvider property.
     * 
     * @return
     *     possible object is
     *     {@link SocialSecurityProvider }
     *     
     */
    public SocialSecurityProvider getSocialSecurityProvider() {
        return socialSecurityProvider;
    }

    /**
     * Sets the value of the socialSecurityProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link SocialSecurityProvider }
     *     
     */
    public void setSocialSecurityProvider(SocialSecurityProvider value) {
        this.socialSecurityProvider = value;
    }

}
