//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.cdsoutputspecification.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.hl7.vmr.r2.CodedIdentifier;


/**
 * Specifies the output clinical statements regarding the evaluated person of interest.  
 * 
 * <p>Java class for ClinicalStatementOutputSpecification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClinicalStatementOutputSpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clinicalStatementTemplate" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="potentialClinicalStatementTemplate" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="relatedClinicalStatementOutputSpecification" type="{urn:hl7-org:cdsoutputspecification:r2}RelatedClinicalStatementOutputSpecification" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="relatedEntityOutputSpecification" type="{urn:hl7-org:cdsoutputspecification:r2}RelatedEntityOutputSpecification" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="attributeOutputSpecification" type="{urn:hl7-org:cdsoutputspecification:r2}AttributeOutputSpecification" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClinicalStatementOutputSpecification", propOrder = {
    "clinicalStatementTemplate",
    "potentialClinicalStatementTemplate",
    "relatedClinicalStatementOutputSpecification",
    "relatedEntityOutputSpecification",
    "attributeOutputSpecification"
})
public class ClinicalStatementOutputSpecification {

    protected List<CodedIdentifier> clinicalStatementTemplate;
    protected List<CodedIdentifier> potentialClinicalStatementTemplate;
    protected List<RelatedClinicalStatementOutputSpecification> relatedClinicalStatementOutputSpecification;
    protected List<RelatedEntityOutputSpecification> relatedEntityOutputSpecification;
    protected List<AttributeOutputSpecification> attributeOutputSpecification;

    /**
     * Gets the value of the clinicalStatementTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clinicalStatementTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClinicalStatementTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedIdentifier }
     * 
     * 
     */
    public List<CodedIdentifier> getClinicalStatementTemplate() {
        if (clinicalStatementTemplate == null) {
            clinicalStatementTemplate = new ArrayList<CodedIdentifier>();
        }
        return this.clinicalStatementTemplate;
    }

    /**
     * Gets the value of the potentialClinicalStatementTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the potentialClinicalStatementTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPotentialClinicalStatementTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedIdentifier }
     * 
     * 
     */
    public List<CodedIdentifier> getPotentialClinicalStatementTemplate() {
        if (potentialClinicalStatementTemplate == null) {
            potentialClinicalStatementTemplate = new ArrayList<CodedIdentifier>();
        }
        return this.potentialClinicalStatementTemplate;
    }

    /**
     * Gets the value of the relatedClinicalStatementOutputSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedClinicalStatementOutputSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedClinicalStatementOutputSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedClinicalStatementOutputSpecification }
     * 
     * 
     */
    public List<RelatedClinicalStatementOutputSpecification> getRelatedClinicalStatementOutputSpecification() {
        if (relatedClinicalStatementOutputSpecification == null) {
            relatedClinicalStatementOutputSpecification = new ArrayList<RelatedClinicalStatementOutputSpecification>();
        }
        return this.relatedClinicalStatementOutputSpecification;
    }

    /**
     * Gets the value of the relatedEntityOutputSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedEntityOutputSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedEntityOutputSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedEntityOutputSpecification }
     * 
     * 
     */
    public List<RelatedEntityOutputSpecification> getRelatedEntityOutputSpecification() {
        if (relatedEntityOutputSpecification == null) {
            relatedEntityOutputSpecification = new ArrayList<RelatedEntityOutputSpecification>();
        }
        return this.relatedEntityOutputSpecification;
    }

    /**
     * Gets the value of the attributeOutputSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeOutputSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeOutputSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeOutputSpecification }
     * 
     * 
     */
    public List<AttributeOutputSpecification> getAttributeOutputSpecification() {
        if (attributeOutputSpecification == null) {
            attributeOutputSpecification = new ArrayList<AttributeOutputSpecification>();
        }
        return this.attributeOutputSpecification;
    }

}
