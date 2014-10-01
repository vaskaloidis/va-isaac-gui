//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.IVLTS;

/**
 * Qualifications obtained by training and certification.
 * 
 * <p>
 * Java class for Qualification complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Qualification">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ExtendedVmrTypeBase">
 *       &lt;sequence>
 *         &lt;element name="code" type="{urn:hl7-org:cdsdt:r2}CD"/>
 *         &lt;element name="validityPeriod" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="issuer" type="{urn:hl7-org:vmr:r2}Organization" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Qualification", propOrder = {
    "code", "validityPeriod", "issuer"
})
public class Qualification extends ExtendedVmrTypeBase {

  @XmlElement(required = true)
  protected CD code;

  protected IVLTS validityPeriod;

  protected Organization issuer;

  /**
   * Gets the value of the code property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getCode() {
    return code;
  }

  /**
   * Sets the value of the code property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setCode(CD value) {
    this.code = value;
  }

  /**
   * Gets the value of the validityPeriod property.
   * 
   * @return possible object is {@link IVLTS }
   * 
   */
  public IVLTS getValidityPeriod() {
    return validityPeriod;
  }

  /**
   * Sets the value of the validityPeriod property.
   * 
   * @param value allowed object is {@link IVLTS }
   * 
   */
  public void setValidityPeriod(IVLTS value) {
    this.validityPeriod = value;
  }

  /**
   * Gets the value of the issuer property.
   * 
   * @return possible object is {@link Organization }
   * 
   */
  public Organization getIssuer() {
    return issuer;
  }

  /**
   * Sets the value of the issuer property.
   * 
   * @param value allowed object is {@link Organization }
   * 
   */
  public void setIssuer(Organization value) {
    this.issuer = value;
  }

}
