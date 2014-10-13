//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.ST;


/**
 * Class that represents a generic Name-Value-Pair object where the name is generally a token (a string without spaces), a semantic category that is controlled by a terminology and a value which may be any type deriving from ANY and/or defined by a template.
 * 
 * <p>Java class for NameValuePair complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NameValuePair">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ExtendedVmrTypeBase">
 *       &lt;sequence>
 *         &lt;element name="name" type="{urn:hl7-org:cdsdt:r2}ST"/>
 *         &lt;element name="semanticCode" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="value" type="{urn:hl7-org:vmr:r2}ExtendedVmrTypeBase"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameValuePair", propOrder = {
    "name",
    "semanticCode",
    "value"
})
public class NameValuePair
    extends ExtendedVmrTypeBase
{

    @XmlElement(required = true)
    protected ST name;
    protected CD semanticCode;
    @XmlElement(required = true)
    protected ExtendedVmrTypeBase value;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link ST }
     *     
     */
    public ST getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link ST }
     *     
     */
    public void setName(ST value) {
        this.name = value;
    }

    /**
     * Gets the value of the semanticCode property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getSemanticCode() {
        return semanticCode;
    }

    /**
     * Sets the value of the semanticCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setSemanticCode(CD value) {
        this.semanticCode = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link ExtendedVmrTypeBase }
     *     
     */
    public ExtendedVmrTypeBase getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedVmrTypeBase }
     *     
     */
    public void setValue(ExtendedVmrTypeBase value) {
        this.value = value;
    }

}
