//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.vmr.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.INT;
import org.hl7.cdsdt.r2.IVLTS;


/**
 * Proposal for a substance administration.  Used, for example, when a CDS system proposes that a medication or vaccination be given.
 * 
 * <p>Java class for SubstanceAdministrationProposal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubstanceAdministrationProposal">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}SubstanceClinicalStatementBase">
 *       &lt;sequence>
 *         &lt;element name="proposedAdministrationTimeInterval" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="numberFillsAllowed" type="{urn:hl7-org:cdsdt:r2}INT" minOccurs="0"/>
 *         &lt;element name="prnReason" type="{urn:hl7-org:cdsdt:r2}CD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="urgency" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="validAdministrationTimeInterval" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="proposalEventTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="originationMode" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubstanceAdministrationProposal", propOrder = {
    "proposedAdministrationTimeInterval",
    "numberFillsAllowed",
    "prnReason",
    "urgency",
    "validAdministrationTimeInterval",
    "proposalEventTime",
    "originationMode"
})
@XmlSeeAlso({
    PCAProposal.class,
    EnteralFeedingProposal.class,
    CompositeSubstanceProposal.class
})
public class SubstanceAdministrationProposal
    extends SubstanceClinicalStatementBase
{

    protected IVLTS proposedAdministrationTimeInterval;
    protected INT numberFillsAllowed;
    protected List<CD> prnReason;
    protected CD urgency;
    protected IVLTS validAdministrationTimeInterval;
    protected IVLTS proposalEventTime;
    protected CD originationMode;

    /**
     * Gets the value of the proposedAdministrationTimeInterval property.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getProposedAdministrationTimeInterval() {
        return proposedAdministrationTimeInterval;
    }

    /**
     * Sets the value of the proposedAdministrationTimeInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setProposedAdministrationTimeInterval(IVLTS value) {
        this.proposedAdministrationTimeInterval = value;
    }

    /**
     * Gets the value of the numberFillsAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link INT }
     *     
     */
    public INT getNumberFillsAllowed() {
        return numberFillsAllowed;
    }

    /**
     * Sets the value of the numberFillsAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link INT }
     *     
     */
    public void setNumberFillsAllowed(INT value) {
        this.numberFillsAllowed = value;
    }

    /**
     * Gets the value of the prnReason property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prnReason property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrnReason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CD }
     * 
     * 
     */
    public List<CD> getPrnReason() {
        if (prnReason == null) {
            prnReason = new ArrayList<CD>();
        }
        return this.prnReason;
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
     * Gets the value of the validAdministrationTimeInterval property.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getValidAdministrationTimeInterval() {
        return validAdministrationTimeInterval;
    }

    /**
     * Sets the value of the validAdministrationTimeInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setValidAdministrationTimeInterval(IVLTS value) {
        this.validAdministrationTimeInterval = value;
    }

    /**
     * Gets the value of the proposalEventTime property.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getProposalEventTime() {
        return proposalEventTime;
    }

    /**
     * Sets the value of the proposalEventTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setProposalEventTime(IVLTS value) {
        this.proposalEventTime = value;
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

}
