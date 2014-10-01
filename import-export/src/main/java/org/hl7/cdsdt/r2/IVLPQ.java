//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.cdsdt.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * A set of consecutive values of an ordered base datatype.
 * 
 * Any ordered type can be the basis of an IVL; it does not matter whether the
 * base type is discrete or continuous. If the base datatype is only partially
 * ordered, all elements of the IVL must be elements of a totally ordered subset
 * of the partially ordered datatype. For example, PQ is considered ordered.
 * However the ordering of PQs is only partial; a total order is only defined
 * among comparable quantities (quantities of the same physical dimension).
 * While IVLs between 2 and 4 meter exists, there is no IVL between 2 meters and
 * 4 seconds.
 * 
 * <p>
 * Java class for IVL_PQ complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="IVL_PQ">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:cdsdt:r2}IVL">
 *       &lt;sequence>
 *         &lt;element name="low" type="{urn:hl7-org:cdsdt:r2}PQ" minOccurs="0"/>
 *         &lt;element name="high" type="{urn:hl7-org:cdsdt:r2}PQ" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="lowClosed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="highClosed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVL_PQ", propOrder = {
    "low", "high"
})
public class IVLPQ extends IVL {

  protected PQ low;

  protected PQ high;

  @XmlAttribute(name = "lowClosed")
  protected Boolean lowClosed;

  @XmlAttribute(name = "highClosed")
  protected Boolean highClosed;

  /**
   * Gets the value of the low property.
   * 
   * @return possible object is {@link PQ }
   * 
   */
  public PQ getLow() {
    return low;
  }

  /**
   * Sets the value of the low property.
   * 
   * @param value allowed object is {@link PQ }
   * 
   */
  public void setLow(PQ value) {
    this.low = value;
  }

  /**
   * Gets the value of the high property.
   * 
   * @return possible object is {@link PQ }
   * 
   */
  public PQ getHigh() {
    return high;
  }

  /**
   * Sets the value of the high property.
   * 
   * @param value allowed object is {@link PQ }
   * 
   */
  public void setHigh(PQ value) {
    this.high = value;
  }

  /**
   * Gets the value of the lowClosed property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isLowClosed() {
    return lowClosed;
  }

  /**
   * Sets the value of the lowClosed property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setLowClosed(Boolean value) {
    this.lowClosed = value;
  }

  /**
   * Gets the value of the highClosed property.
   * 
   * @return possible object is {@link Boolean }
   * 
   */
  public Boolean isHighClosed() {
    return highClosed;
  }

  /**
   * Sets the value of the highClosed property.
   * 
   * @param value allowed object is {@link Boolean }
   * 
   */
  public void setHighClosed(Boolean value) {
    this.highClosed = value;
  }

}
