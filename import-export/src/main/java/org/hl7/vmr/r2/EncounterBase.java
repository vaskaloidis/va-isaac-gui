//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;


/**
 * The abstract base class for an encounter of an EvaluatedPerson with the healthcare system.  If an encounter or appointment has been canceled, it should simply not be provided using this model.  This allows the encounter and appointment classes to be used without an explicit encounter status check.
 * 
 * <p>Java class for EncounterBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncounterBase">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ClinicalStatement">
 *       &lt;sequence>
 *         &lt;element name="encounterType" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncounterBase", propOrder = {
    "encounterType"
})
@XmlSeeAlso({
    ScheduledAppointment.class,
    MissedAppointment.class,
    AppointmentRequest.class,
    EncounterEvent.class,
    AppointmentProposal.class
})
public abstract class EncounterBase
    extends ClinicalStatement
{

    protected CD encounterType;

    /**
     * Gets the value of the encounterType property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getEncounterType() {
        return encounterType;
    }

    /**
     * Sets the value of the encounterType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setEncounterType(CD value) {
        this.encounterType = value;
    }

}
