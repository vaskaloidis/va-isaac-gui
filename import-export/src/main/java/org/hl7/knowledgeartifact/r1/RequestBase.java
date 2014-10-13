//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * The request expression defines the data that will be used by the artifact.
 * 			
 * The result of a request is defined to return the same data for subsequent invocations within the same evaluation request. This means in particular that patient data updates made during the evaluation request are not visible to the artifact. In effect, the patient data is a snapshot of the data as of the start of the evaluation. This ensures strict deterministic and functional behavior of the artifact, and allows the implementation engine freedom to cache intermediate results in order to improve performance.
 * 
 * <p>Java class for RequestBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestBase">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:knowledgeartifact:r1}Expression">
 *       &lt;sequence>
 *         &lt;element name="timeOffset" type="{urn:hl7-org:knowledgeartifact:r1}Expression" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="scope" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cardinality" use="required" type="{urn:hl7-org:knowledgeartifact:r1}RequestCardinality" />
 *       &lt;attribute name="dataType" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="templateId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idProperty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="triggerType" type="{urn:hl7-org:knowledgeartifact:r1}DataEventType" />
 *       &lt;attribute name="isInitial" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestBase", propOrder = {
    "timeOffset"
})
@XmlSeeAlso({
    DataRequest.class,
    ClinicalRequest.class
})
public abstract class RequestBase
    extends Expression
{

    protected Expression timeOffset;
    @XmlAttribute(name = "scope")
    protected String scope;
    @XmlAttribute(name = "cardinality", required = true)
    protected RequestCardinality cardinality;
    @XmlAttribute(name = "dataType", required = true)
    protected QName dataType;
    @XmlAttribute(name = "templateId")
    protected String templateId;
    @XmlAttribute(name = "idProperty")
    protected String idProperty;
    @XmlAttribute(name = "triggerType")
    protected DataEventType triggerType;
    @XmlAttribute(name = "isInitial")
    protected Boolean isInitial;

    /**
     * Gets the value of the timeOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Expression }
     *     
     */
    public Expression getTimeOffset() {
        return timeOffset;
    }

    /**
     * Sets the value of the timeOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Expression }
     *     
     */
    public void setTimeOffset(Expression value) {
        this.timeOffset = value;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScope(String value) {
        this.scope = value;
    }

    /**
     * Gets the value of the cardinality property.
     * 
     * @return
     *     possible object is
     *     {@link RequestCardinality }
     *     
     */
    public RequestCardinality getCardinality() {
        return cardinality;
    }

    /**
     * Sets the value of the cardinality property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestCardinality }
     *     
     */
    public void setCardinality(RequestCardinality value) {
        this.cardinality = value;
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setDataType(QName value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * Sets the value of the templateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateId(String value) {
        this.templateId = value;
    }

    /**
     * Gets the value of the idProperty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProperty() {
        return idProperty;
    }

    /**
     * Sets the value of the idProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProperty(String value) {
        this.idProperty = value;
    }

    /**
     * Gets the value of the triggerType property.
     * 
     * @return
     *     possible object is
     *     {@link DataEventType }
     *     
     */
    public DataEventType getTriggerType() {
        return triggerType;
    }

    /**
     * Sets the value of the triggerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataEventType }
     *     
     */
    public void setTriggerType(DataEventType value) {
        this.triggerType = value;
    }

    /**
     * Gets the value of the isInitial property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsInitial() {
        if (isInitial == null) {
            return true;
        } else {
            return isInitial;
        }
    }

    /**
     * Sets the value of the isInitial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInitial(Boolean value) {
        this.isInitial = value;
    }

}
