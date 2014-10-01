//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.IVLTS;

/**
 * Proposal, e.g., by a CDS system, for an Encounter to take place.
 * 
 * <p>
 * Java class for AppointmentProposal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="AppointmentProposal">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}EncounterBase">
 *       &lt;sequence>
 *         &lt;element name="frequency" type="{urn:hl7-org:vmr:r2}Schedule" minOccurs="0"/>
 *         &lt;element name="proposedAppointmentTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="urgency" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentProposal", propOrder = {
    "frequency", "proposedAppointmentTime", "urgency"
})
public class AppointmentProposal extends EncounterBase {

  protected Schedule frequency;

  protected IVLTS proposedAppointmentTime;

  protected CD urgency;

  /**
   * Gets the value of the frequency property.
   * 
   * @return possible object is {@link Schedule }
   * 
   */
  public Schedule getFrequency() {
    return frequency;
  }

  /**
   * Sets the value of the frequency property.
   * 
   * @param value allowed object is {@link Schedule }
   * 
   */
  public void setFrequency(Schedule value) {
    this.frequency = value;
  }

  /**
   * Gets the value of the proposedAppointmentTime property.
   * 
   * @return possible object is {@link IVLTS }
   * 
   */
  public IVLTS getProposedAppointmentTime() {
    return proposedAppointmentTime;
  }

  /**
   * Sets the value of the proposedAppointmentTime property.
   * 
   * @param value allowed object is {@link IVLTS }
   * 
   */
  public void setProposedAppointmentTime(IVLTS value) {
    this.proposedAppointmentTime = value;
  }

  /**
   * Gets the value of the urgency property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getUrgency() {
    return urgency;
  }

  /**
   * Sets the value of the urgency property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setUrgency(CD value) {
    this.urgency = value;
  }

}
