//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.vmr.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.IVLTS;

/**
 * Abstract base class for both adverse events and denied adverse events.
 * 
 * <p>
 * Java class for AdverseEventBase complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="AdverseEventBase">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ClinicalStatement">
 *       &lt;sequence>
 *         &lt;element name="adverseEventCode" type="{urn:hl7-org:cdsdt:r2}CD"/>
 *         &lt;element name="adverseEventAgent" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="certainty" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="adverseEventTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="affectedBodySite" type="{urn:hl7-org:vmr:r2}BodySite" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdverseEventBase", propOrder = {
    "adverseEventCode", "adverseEventAgent", "certainty", "adverseEventTime",
    "affectedBodySite"
})
@XmlSeeAlso({
    AdverseEvent.class, DeniedAdverseEvent.class
})
public abstract class AdverseEventBase extends ClinicalStatement {

  @XmlElement(required = true)
  protected CD adverseEventCode;

  protected CD adverseEventAgent;

  protected CD certainty;

  protected IVLTS adverseEventTime;

  protected List<BodySite> affectedBodySite;

  /**
   * Gets the value of the adverseEventCode property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getAdverseEventCode() {
    return adverseEventCode;
  }

  /**
   * Sets the value of the adverseEventCode property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setAdverseEventCode(CD value) {
    this.adverseEventCode = value;
  }

  /**
   * Gets the value of the adverseEventAgent property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getAdverseEventAgent() {
    return adverseEventAgent;
  }

  /**
   * Sets the value of the adverseEventAgent property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setAdverseEventAgent(CD value) {
    this.adverseEventAgent = value;
  }

  /**
   * Gets the value of the certainty property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getCertainty() {
    return certainty;
  }

  /**
   * Sets the value of the certainty property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setCertainty(CD value) {
    this.certainty = value;
  }

  /**
   * Gets the value of the adverseEventTime property.
   * 
   * @return possible object is {@link IVLTS }
   * 
   */
  public IVLTS getAdverseEventTime() {
    return adverseEventTime;
  }

  /**
   * Sets the value of the adverseEventTime property.
   * 
   * @param value allowed object is {@link IVLTS }
   * 
   */
  public void setAdverseEventTime(IVLTS value) {
    this.adverseEventTime = value;
  }

  /**
   * Gets the value of the affectedBodySite property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the affectedBodySite property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getAffectedBodySite().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link BodySite }
   * 
   * 
   */
  public List<BodySite> getAffectedBodySite() {
    if (affectedBodySite == null) {
      affectedBodySite = new ArrayList<BodySite>();
    }
    return this.affectedBodySite;
  }

}
