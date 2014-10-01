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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Abstract base class for extended vMR types.
 * 
 * <p>
 * Java class for ExtendedVmrTypeBase complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ExtendedVmrTypeBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="templateId" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{urn:hl7-org:vmr:r2}CodedIdentifier" minOccurs="0"/>
 *         &lt;element name="attribute" type="{urn:hl7-org:vmr:r2}NameValuePair" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedVmrTypeBase", propOrder = {
    "templateId", "id", "attribute"
})
@XmlSeeAlso({
    Qualification.class, DoseRestriction.class, TextureModification.class,
    Cycle.class, StringNameValuePair.class, Schedule.class, BodySite.class,
    Constituent.class, Dose.class, Value.class, CycleEventTiming.class,
    NutrientModification.class, Documentation.class, MotionManagement.class,
    RadiotherapySimulation.class, LocalizationMethod.class,
    VaccinationProtocol.class, NameValuePair.class
})
public abstract class ExtendedVmrTypeBase {

  protected List<CodedIdentifier> templateId;

  protected CodedIdentifier id;

  protected List<NameValuePair> attribute;

  /**
   * Gets the value of the templateId property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the templateId property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getTemplateId().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link CodedIdentifier }
   * 
   * 
   */
  public List<CodedIdentifier> getTemplateId() {
    if (templateId == null) {
      templateId = new ArrayList<CodedIdentifier>();
    }
    return this.templateId;
  }

  /**
   * Gets the value of the id property.
   * 
   * @return possible object is {@link CodedIdentifier }
   * 
   */
  public CodedIdentifier getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value allowed object is {@link CodedIdentifier }
   * 
   */
  public void setId(CodedIdentifier value) {
    this.id = value;
  }

  /**
   * Gets the value of the attribute property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the attribute property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getAttribute().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link NameValuePair }
   * 
   * 
   */
  public List<NameValuePair> getAttribute() {
    if (attribute == null) {
      attribute = new ArrayList<NameValuePair>();
    }
    return this.attribute;
  }

}
