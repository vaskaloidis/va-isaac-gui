//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.PQ;


/**
 * A location on an EvaluatedPerson's body.  E.g., left breast, heart.
 * 
 * <p>Java class for BodySite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BodySite">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ExtendedVmrTypeBase">
 *       &lt;sequence>
 *         &lt;element name="bodySiteCode" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="laterality" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="directionality" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="percentBodySiteCovered" type="{urn:hl7-org:cdsdt:r2}PQ" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BodySite", propOrder = {
    "bodySiteCode",
    "laterality",
    "directionality",
    "percentBodySiteCovered"
})
public class BodySite
    extends ExtendedVmrTypeBase
{

    protected CD bodySiteCode;
    protected CD laterality;
    protected CD directionality;
    protected PQ percentBodySiteCovered;

    /**
     * Gets the value of the bodySiteCode property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getBodySiteCode() {
        return bodySiteCode;
    }

    /**
     * Sets the value of the bodySiteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setBodySiteCode(CD value) {
        this.bodySiteCode = value;
    }

    /**
     * Gets the value of the laterality property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getLaterality() {
        return laterality;
    }

    /**
     * Sets the value of the laterality property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setLaterality(CD value) {
        this.laterality = value;
    }

    /**
     * Gets the value of the directionality property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getDirectionality() {
        return directionality;
    }

    /**
     * Sets the value of the directionality property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setDirectionality(CD value) {
        this.directionality = value;
    }

    /**
     * Gets the value of the percentBodySiteCovered property.
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getPercentBodySiteCovered() {
        return percentBodySiteCovered;
    }

    /**
     * Sets the value of the percentBodySiteCovered property.
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setPercentBodySiteCovered(PQ value) {
        this.percentBodySiteCovered = value;
    }

}
