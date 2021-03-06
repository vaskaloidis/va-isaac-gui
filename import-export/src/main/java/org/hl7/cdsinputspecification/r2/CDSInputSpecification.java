//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.cdsinputspecification.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.CS;
import org.hl7.vmr.r2.CodedIdentifier;


/**
 * The parent class containing the data required by a specific CDS use case.  For example, this class can be used to specify that the evaluation of a patient for the need for a mammogram requires the following data: (i) gender; (ii) age; (iii) past mastectomy history; and (iv) past mammogram history.
 * 
 * Can include a detailed input specification for the focal patient as well as for related evaluated persons.  Note that it is assumed that the superset of data required for related evaluated persons are the same for each of the related evaluated persons (e.g., relatives).  If input specifications are not provided regarding patients or other evaluated persons, then this signifies that no further constraints are being placed on required data other than what is expressed through the input data model and its existing template(s).
 * 
 *  As a specific example, a CDSInputSpecification may be used to specify required CDS input by a CDS guidance service compliant with the HL7 Decision Support Service standard.  Specifically, this type of specification can be encapsulated within the �query� section of a Decision Support Service�s specification of knowledge module data requirements.  Further information regarding this type of use case can be found in the HL7 Decision Support Service specification and the HL7 Decision Support Service Implementation Guide.
 * 
 * <p>Java class for CDSInputSpecification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CDSInputSpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requiredCdsResourceType" type="{urn:hl7-org:cdsdt:r2}CD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requiredCdsContextAttribute" type="{urn:hl7-org:cdsdt:r2}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requiredInputVmrTemplate" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requiredCdsInputTemplate" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="patientInputSpecification" type="{urn:hl7-org:cdsinputspecification:r2}PatientInputSpecification" minOccurs="0"/>
 *         &lt;element name="relatedEvaluatedPersonInputSpecification" type="{urn:hl7-org:cdsinputspecification:r2}RelatedEvaluatedPersonInputSpecification" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CDSInputSpecification", propOrder = {
    "requiredCdsResourceType",
    "requiredCdsContextAttribute",
    "requiredInputVmrTemplate",
    "requiredCdsInputTemplate",
    "patientInputSpecification",
    "relatedEvaluatedPersonInputSpecification"
})
public class CDSInputSpecification {

    protected List<CD> requiredCdsResourceType;
    protected List<CS> requiredCdsContextAttribute;
    protected List<CodedIdentifier> requiredInputVmrTemplate;
    protected List<CodedIdentifier> requiredCdsInputTemplate;
    protected PatientInputSpecification patientInputSpecification;
    protected RelatedEvaluatedPersonInputSpecification relatedEvaluatedPersonInputSpecification;

    /**
     * Gets the value of the requiredCdsResourceType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredCdsResourceType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredCdsResourceType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CD }
     * 
     * 
     */
    public List<CD> getRequiredCdsResourceType() {
        if (requiredCdsResourceType == null) {
            requiredCdsResourceType = new ArrayList<CD>();
        }
        return this.requiredCdsResourceType;
    }

    /**
     * Gets the value of the requiredCdsContextAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredCdsContextAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredCdsContextAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CS }
     * 
     * 
     */
    public List<CS> getRequiredCdsContextAttribute() {
        if (requiredCdsContextAttribute == null) {
            requiredCdsContextAttribute = new ArrayList<CS>();
        }
        return this.requiredCdsContextAttribute;
    }

    /**
     * Gets the value of the requiredInputVmrTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredInputVmrTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredInputVmrTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedIdentifier }
     * 
     * 
     */
    public List<CodedIdentifier> getRequiredInputVmrTemplate() {
        if (requiredInputVmrTemplate == null) {
            requiredInputVmrTemplate = new ArrayList<CodedIdentifier>();
        }
        return this.requiredInputVmrTemplate;
    }

    /**
     * Gets the value of the requiredCdsInputTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredCdsInputTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredCdsInputTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedIdentifier }
     * 
     * 
     */
    public List<CodedIdentifier> getRequiredCdsInputTemplate() {
        if (requiredCdsInputTemplate == null) {
            requiredCdsInputTemplate = new ArrayList<CodedIdentifier>();
        }
        return this.requiredCdsInputTemplate;
    }

    /**
     * Gets the value of the patientInputSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link PatientInputSpecification }
     *     
     */
    public PatientInputSpecification getPatientInputSpecification() {
        return patientInputSpecification;
    }

    /**
     * Sets the value of the patientInputSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientInputSpecification }
     *     
     */
    public void setPatientInputSpecification(PatientInputSpecification value) {
        this.patientInputSpecification = value;
    }

    /**
     * Gets the value of the relatedEvaluatedPersonInputSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedEvaluatedPersonInputSpecification }
     *     
     */
    public RelatedEvaluatedPersonInputSpecification getRelatedEvaluatedPersonInputSpecification() {
        return relatedEvaluatedPersonInputSpecification;
    }

    /**
     * Sets the value of the relatedEvaluatedPersonInputSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedEvaluatedPersonInputSpecification }
     *     
     */
    public void setRelatedEvaluatedPersonInputSpecification(RelatedEvaluatedPersonInputSpecification value) {
        this.relatedEvaluatedPersonInputSpecification = value;
    }

}
