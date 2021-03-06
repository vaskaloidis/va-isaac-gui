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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CS;
import org.hl7.vmr.r2.CodedIdentifier;


/**
 * Specifies the data required for an evaluated person.  Can include (i) a specification of the person attributes (e.g., gender) required; (ii) a specification of the templates that must be applied; (iii) a specification of the data required for related entities; and (iv) a specification of the clinical statements required.
 * 
 * <p>Java class for EvaluatedPersonInputSpecification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EvaluatedPersonInputSpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requiredEvaluatedPersonAttribute" type="{urn:hl7-org:cdsdt:r2}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requiredEvaluatedPersonTemplate" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="relatedEntityInputSpecification" type="{urn:hl7-org:cdsinputspecification:r2}RelatedEntityInputSpecification" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clinicalStatementInputSpecification" type="{urn:hl7-org:cdsinputspecification:r2}ClinicalStatementInputSpecification" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvaluatedPersonInputSpecification", propOrder = {
    "requiredEvaluatedPersonAttribute",
    "requiredEvaluatedPersonTemplate",
    "relatedEntityInputSpecification",
    "clinicalStatementInputSpecification"
})
@XmlSeeAlso({
    PatientInputSpecification.class,
    RelatedEvaluatedPersonInputSpecification.class
})
public abstract class EvaluatedPersonInputSpecification {

    protected List<CS> requiredEvaluatedPersonAttribute;
    protected List<CodedIdentifier> requiredEvaluatedPersonTemplate;
    protected List<RelatedEntityInputSpecification> relatedEntityInputSpecification;
    protected List<ClinicalStatementInputSpecification> clinicalStatementInputSpecification;

    /**
     * Gets the value of the requiredEvaluatedPersonAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredEvaluatedPersonAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredEvaluatedPersonAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CS }
     * 
     * 
     */
    public List<CS> getRequiredEvaluatedPersonAttribute() {
        if (requiredEvaluatedPersonAttribute == null) {
            requiredEvaluatedPersonAttribute = new ArrayList<CS>();
        }
        return this.requiredEvaluatedPersonAttribute;
    }

    /**
     * Gets the value of the requiredEvaluatedPersonTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredEvaluatedPersonTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredEvaluatedPersonTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedIdentifier }
     * 
     * 
     */
    public List<CodedIdentifier> getRequiredEvaluatedPersonTemplate() {
        if (requiredEvaluatedPersonTemplate == null) {
            requiredEvaluatedPersonTemplate = new ArrayList<CodedIdentifier>();
        }
        return this.requiredEvaluatedPersonTemplate;
    }

    /**
     * Gets the value of the relatedEntityInputSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedEntityInputSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedEntityInputSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedEntityInputSpecification }
     * 
     * 
     */
    public List<RelatedEntityInputSpecification> getRelatedEntityInputSpecification() {
        if (relatedEntityInputSpecification == null) {
            relatedEntityInputSpecification = new ArrayList<RelatedEntityInputSpecification>();
        }
        return this.relatedEntityInputSpecification;
    }

    /**
     * Gets the value of the clinicalStatementInputSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clinicalStatementInputSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClinicalStatementInputSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClinicalStatementInputSpecification }
     * 
     * 
     */
    public List<ClinicalStatementInputSpecification> getClinicalStatementInputSpecification() {
        if (clinicalStatementInputSpecification == null) {
            clinicalStatementInputSpecification = new ArrayList<ClinicalStatementInputSpecification>();
        }
        return this.clinicalStatementInputSpecification;
    }

}
