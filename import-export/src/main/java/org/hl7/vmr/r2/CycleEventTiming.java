//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;

/**
 * Identifies a repeating pattern to the intended time periods such as the
 * number of occurrences in a given time period, the days in a multi-day cycle,
 * or a code representing the frequency of occurrence for a given cycle.
 * 
 * <p>
 * Java class for CycleEventTiming complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CycleEventTiming">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ExtendedVmrTypeBase">
 *       &lt;sequence>
 *         &lt;element name="when" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CycleEventTiming", propOrder = {
  "when"
})
@XmlSeeAlso({
    CodedRecurringEvent.class, AnchoredEvent.class, RecurringEvent.class
})
public abstract class CycleEventTiming extends ExtendedVmrTypeBase {

  protected CD when;

  /**
   * Gets the value of the when property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getWhen() {
    return when;
  }

  /**
   * Sets the value of the when property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setWhen(CD value) {
    this.when = value;
  }

}
