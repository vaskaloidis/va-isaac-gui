//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:23 PM PDT 
//

package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Combine operator combines a list of strings, optionally separating each
 * string with the given separator.
 * 
 * <p>
 * Java class for Combine complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Combine">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:knowledgeartifact:r1}Expression">
 *       &lt;sequence>
 *         &lt;element name="source" type="{urn:hl7-org:knowledgeartifact:r1}Expression"/>
 *         &lt;element name="separator" type="{urn:hl7-org:knowledgeartifact:r1}Expression" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Combine", propOrder = {
    "source", "separator"
})
public class Combine extends Expression {

  @XmlElement(required = true)
  protected Expression source;

  protected Expression separator;

  /**
   * Gets the value of the source property.
   * 
   * @return possible object is {@link Expression }
   * 
   */
  public Expression getSource() {
    return source;
  }

  /**
   * Sets the value of the source property.
   * 
   * @param value allowed object is {@link Expression }
   * 
   */
  public void setSource(Expression value) {
    this.source = value;
  }

  /**
   * Gets the value of the separator property.
   * 
   * @return possible object is {@link Expression }
   * 
   */
  public Expression getSeparator() {
    return separator;
  }

  /**
   * Sets the value of the separator property.
   * 
   * @param value allowed object is {@link Expression }
   * 
   */
  public void setSeparator(Expression value) {
    this.separator = value;
  }

}
