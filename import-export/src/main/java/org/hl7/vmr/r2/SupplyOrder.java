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
import org.hl7.cdsdt.r2.IVLTS;


/**
 * A provider's order to deliver the supply.
 * 
 * <p>Java class for SupplyOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SupplyOrder">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}SupplyBase">
 *       &lt;sequence>
 *         &lt;element name="supplyTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="frequency" type="{urn:hl7-org:vmr:r2}Schedule" minOccurs="0"/>
 *         &lt;element name="reason" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="urgency" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="originationMode" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="orderEventTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupplyOrder", propOrder = {
    "supplyTime",
    "frequency",
    "reason",
    "urgency",
    "originationMode",
    "orderEventTime"
})
public class SupplyOrder
    extends SupplyBase
{

    protected IVLTS supplyTime;
    protected Schedule frequency;
    protected CD reason;
    protected CD urgency;
    protected CD originationMode;
    protected IVLTS orderEventTime;

    /**
     * Gets the value of the supplyTime property.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getSupplyTime() {
        return supplyTime;
    }

    /**
     * Sets the value of the supplyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setSupplyTime(IVLTS value) {
        this.supplyTime = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link Schedule }
     *     
     */
    public Schedule getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Schedule }
     *     
     */
    public void setFrequency(Schedule value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setReason(CD value) {
        this.reason = value;
    }

    /**
     * Gets the value of the urgency property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getUrgency() {
        return urgency;
    }

    /**
     * Sets the value of the urgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setUrgency(CD value) {
        this.urgency = value;
    }

    /**
     * Gets the value of the originationMode property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getOriginationMode() {
        return originationMode;
    }

    /**
     * Sets the value of the originationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setOriginationMode(CD value) {
        this.originationMode = value;
    }

    /**
     * Gets the value of the orderEventTime property.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getOrderEventTime() {
        return orderEventTime;
    }

    /**
     * Sets the value of the orderEventTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setOrderEventTime(IVLTS value) {
        this.orderEventTime = value;
    }

}
