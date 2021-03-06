//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This action removes another proposed action or an
 * 				ongoing action.
 * 			
 * 
 * <p>Java class for RemoveAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoveAction">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:knowledgeartifact:r1}AtomicAction">
 *       &lt;sequence>
 *         &lt;element name="actionSentence" type="{urn:hl7-org:knowledgeartifact:r1}Expression"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoveAction", propOrder = {
    "actionSentence"
})
public class RemoveAction
    extends AtomicAction
{

    @XmlElement(required = true)
    protected Expression actionSentence;

    /**
     * Gets the value of the actionSentence property.
     * 
     * @return
     *     possible object is
     *     {@link Expression }
     *     
     */
    public Expression getActionSentence() {
        return actionSentence;
    }

    /**
     * Sets the value of the actionSentence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Expression }
     *     
     */
    public void setActionSentence(Expression value) {
        this.actionSentence = value;
    }

}
