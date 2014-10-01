//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:02 PM PDT 
//

package org.hl7.kaoutput.r1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.II;
import org.hl7.cdsdt.r2.ST;
import org.hl7.knowledgeartifact.r1.ObjectDescriptor;

/**
 * <p>
 * Java class for CDSExecutionMessage complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CDSExecutionMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{urn:hl7-org:cdsdt:r2}II" minOccurs="0"/>
 *         &lt;element name="reason">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="value" type="{urn:hl7-org:kaoutput:r1}CDSExecutionMessageReason" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="level">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="value" type="{urn:hl7-org:kaoutput:r1}CDSExecutionMessageLevel" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="message" type="{urn:hl7-org:cdsdt:r2}ST"/>
 *         &lt;element name="sourceComponentType" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="value" type="{urn:hl7-org:kaoutput:r1}DSSComponentType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sourceComponentId" type="{urn:hl7-org:knowledgeartifact:r1}ObjectDescriptor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CDSExecutionMessage", propOrder = {
    "id", "reason", "level", "message", "sourceComponentType",
    "sourceComponentId"
})
public class CDSExecutionMessage {

  protected II id;

  @XmlElement(required = true)
  protected CDSExecutionMessage.Reason reason;

  @XmlElement(required = true)
  protected CDSExecutionMessage.Level level;

  @XmlElement(required = true)
  protected ST message;

  protected CDSExecutionMessage.SourceComponentType sourceComponentType;

  protected ObjectDescriptor sourceComponentId;

  /**
   * Gets the value of the id property.
   * 
   * @return possible object is {@link II }
   * 
   */
  public II getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value allowed object is {@link II }
   * 
   */
  public void setId(II value) {
    this.id = value;
  }

  /**
   * Gets the value of the reason property.
   * 
   * @return possible object is {@link CDSExecutionMessage.Reason }
   * 
   */
  public CDSExecutionMessage.Reason getReason() {
    return reason;
  }

  /**
   * Sets the value of the reason property.
   * 
   * @param value allowed object is {@link CDSExecutionMessage.Reason }
   * 
   */
  public void setReason(CDSExecutionMessage.Reason value) {
    this.reason = value;
  }

  /**
   * Gets the value of the level property.
   * 
   * @return possible object is {@link CDSExecutionMessage.Level }
   * 
   */
  public CDSExecutionMessage.Level getLevel() {
    return level;
  }

  /**
   * Sets the value of the level property.
   * 
   * @param value allowed object is {@link CDSExecutionMessage.Level }
   * 
   */
  public void setLevel(CDSExecutionMessage.Level value) {
    this.level = value;
  }

  /**
   * Gets the value of the message property.
   * 
   * @return possible object is {@link ST }
   * 
   */
  public ST getMessage() {
    return message;
  }

  /**
   * Sets the value of the message property.
   * 
   * @param value allowed object is {@link ST }
   * 
   */
  public void setMessage(ST value) {
    this.message = value;
  }

  /**
   * Gets the value of the sourceComponentType property.
   * 
   * @return possible object is {@link CDSExecutionMessage.SourceComponentType }
   * 
   */
  public CDSExecutionMessage.SourceComponentType getSourceComponentType() {
    return sourceComponentType;
  }

  /**
   * Sets the value of the sourceComponentType property.
   * 
   * @param value allowed object is
   *          {@link CDSExecutionMessage.SourceComponentType }
   * 
   */
  public void setSourceComponentType(
    CDSExecutionMessage.SourceComponentType value) {
    this.sourceComponentType = value;
  }

  /**
   * Gets the value of the sourceComponentId property.
   * 
   * @return possible object is {@link ObjectDescriptor }
   * 
   */
  public ObjectDescriptor getSourceComponentId() {
    return sourceComponentId;
  }

  /**
   * Sets the value of the sourceComponentId property.
   * 
   * @param value allowed object is {@link ObjectDescriptor }
   * 
   */
  public void setSourceComponentId(ObjectDescriptor value) {
    this.sourceComponentId = value;
  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained
   * within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;attribute name="value" type="{urn:hl7-org:kaoutput:r1}CDSExecutionMessageLevel" />
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "")
  public static class Level {

    @XmlAttribute(name = "value")
    protected CDSExecutionMessageLevel value;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link CDSExecutionMessageLevel }
     * 
     */
    public CDSExecutionMessageLevel getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link CDSExecutionMessageLevel }
     * 
     */
    public void setValue(CDSExecutionMessageLevel value) {
      this.value = value;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained
   * within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;attribute name="value" type="{urn:hl7-org:kaoutput:r1}CDSExecutionMessageReason" />
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "")
  public static class Reason {

    @XmlAttribute(name = "value")
    protected CDSExecutionMessageReason value;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link CDSExecutionMessageReason }
     * 
     */
    public CDSExecutionMessageReason getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link CDSExecutionMessageReason }
     * 
     */
    public void setValue(CDSExecutionMessageReason value) {
      this.value = value;
    }

  }

  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained
   * within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;attribute name="value" type="{urn:hl7-org:kaoutput:r1}DSSComponentType" />
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "")
  public static class SourceComponentType {

    @XmlAttribute(name = "value")
    protected DSSComponentType value;

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link DSSComponentType }
     * 
     */
    public DSSComponentType getValue() {
      return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value allowed object is {@link DSSComponentType }
     * 
     */
    public void setValue(DSSComponentType value) {
      this.value = value;
    }

  }

}
