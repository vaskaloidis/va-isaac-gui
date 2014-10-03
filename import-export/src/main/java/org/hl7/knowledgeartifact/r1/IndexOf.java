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
 * The IndexOf operator returns the 1-based index of the given element in the
 * given source list.
 * 
 * If either argument is null, the result is null.
 * 
 * <p>
 * Java class for IndexOf complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="IndexOf">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:knowledgeartifact:r1}Expression">
 *       &lt;sequence>
 *         &lt;element name="source" type="{urn:hl7-org:knowledgeartifact:r1}Expression"/>
 *         &lt;element name="element" type="{urn:hl7-org:knowledgeartifact:r1}Expression"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndexOf", propOrder = {
    "source", "element"
})
public class IndexOf extends Expression {

  @XmlElement(required = true)
  protected Expression source;

  @XmlElement(required = true)
  protected Expression element;

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
   * Gets the value of the element property.
   * 
   * @return possible object is {@link Expression }
   * 
   */
  public Expression getElement() {
    return element;
  }

  /**
   * Sets the value of the element property.
   * 
   * @param value allowed object is {@link Expression }
   * 
   */
  public void setElement(Expression value) {
    this.element = value;
  }

}