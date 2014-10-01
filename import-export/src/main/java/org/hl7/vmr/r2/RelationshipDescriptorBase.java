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
 * The relationship between one class and another.
 * 
 * <p>
 * Java class for RelationshipDescriptorBase complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="RelationshipDescriptorBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="templateId" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="relationshipTimeInterval" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="targetRole" type="{urn:hl7-org:cdsdt:r2}CD"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationshipDescriptorBase", propOrder = {
    "templateId", "relationshipTimeInterval", "targetRole"
})
@XmlSeeAlso({
    RelatedClinicalStatement.class, RelatedEvaluatedPerson.class,
    RelatedEntity.class
})
public abstract class RelationshipDescriptorBase {

  protected List<CodedIdentifier> templateId;

  protected IVLTS relationshipTimeInterval;

  @XmlElement(required = true)
  protected CD targetRole;

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
   * Gets the value of the relationshipTimeInterval property.
   * 
   * @return possible object is {@link IVLTS }
   * 
   */
  public IVLTS getRelationshipTimeInterval() {
    return relationshipTimeInterval;
  }

  /**
   * Sets the value of the relationshipTimeInterval property.
   * 
   * @param value allowed object is {@link IVLTS }
   * 
   */
  public void setRelationshipTimeInterval(IVLTS value) {
    this.relationshipTimeInterval = value;
  }

  /**
   * Gets the value of the targetRole property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getTargetRole() {
    return targetRole;
  }

  /**
   * Sets the value of the targetRole property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setTargetRole(CD value) {
    this.targetRole = value;
  }

}
