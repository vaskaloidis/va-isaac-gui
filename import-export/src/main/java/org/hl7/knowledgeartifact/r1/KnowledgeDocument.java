//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.knowledgeartifact.r1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A knowledge document is an instance of a CDS
 * 				knowledge artifact such as a rule, an order set, or a documentation
 * 				template
 * 			
 * 
 * <p>Java class for KnowledgeDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KnowledgeDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadata" type="{urn:hl7-org:knowledgeartifact:r1}Metadata"/>
 *         &lt;element name="externalData" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameter" type="{urn:hl7-org:knowledgeartifact:r1}ParameterDef" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="def" type="{urn:hl7-org:knowledgeartifact:r1}ExpressionDef" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="expressions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="def" type="{urn:hl7-org:knowledgeartifact:r1}ExpressionDef" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="triggers" type="{urn:hl7-org:knowledgeartifact:r1}Triggers" minOccurs="0"/>
 *         &lt;element name="conditions" type="{urn:hl7-org:knowledgeartifact:r1}Conditions" minOccurs="0"/>
 *         &lt;element name="behaviors" type="{urn:hl7-org:knowledgeartifact:r1}Behaviors" minOccurs="0"/>
 *         &lt;element name="actionGroup" type="{urn:hl7-org:knowledgeartifact:r1}ActionGroup"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KnowledgeDocument", propOrder = {
    "metadata",
    "externalData",
    "expressions",
    "triggers",
    "conditions",
    "behaviors",
    "actionGroup"
})
@XmlRootElement
public class KnowledgeDocument {

    @XmlElement(required = true)
    protected Metadata metadata;
    protected KnowledgeDocument.ExternalData externalData;
    protected KnowledgeDocument.Expressions expressions;
    protected Triggers triggers;
    protected Conditions conditions;
    protected Behaviors behaviors;
    @XmlElement(required = true)
    protected ActionGroup actionGroup;

    /**
     * Gets the value of the metadata property.
     * 
     * @return
     *     possible object is
     *     {@link Metadata }
     *     
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * Sets the value of the metadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Metadata }
     *     
     */
    public void setMetadata(Metadata value) {
        this.metadata = value;
    }

    /**
     * Gets the value of the externalData property.
     * 
     * @return
     *     possible object is
     *     {@link KnowledgeDocument.ExternalData }
     *     
     */
    public KnowledgeDocument.ExternalData getExternalData() {
        return externalData;
    }

    /**
     * Sets the value of the externalData property.
     * 
     * @param value
     *     allowed object is
     *     {@link KnowledgeDocument.ExternalData }
     *     
     */
    public void setExternalData(KnowledgeDocument.ExternalData value) {
        this.externalData = value;
    }

    /**
     * Gets the value of the expressions property.
     * 
     * @return
     *     possible object is
     *     {@link KnowledgeDocument.Expressions }
     *     
     */
    public KnowledgeDocument.Expressions getExpressions() {
        return expressions;
    }

    /**
     * Sets the value of the expressions property.
     * 
     * @param value
     *     allowed object is
     *     {@link KnowledgeDocument.Expressions }
     *     
     */
    public void setExpressions(KnowledgeDocument.Expressions value) {
        this.expressions = value;
    }

    /**
     * Gets the value of the triggers property.
     * 
     * @return
     *     possible object is
     *     {@link Triggers }
     *     
     */
    public Triggers getTriggers() {
        return triggers;
    }

    /**
     * Sets the value of the triggers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Triggers }
     *     
     */
    public void setTriggers(Triggers value) {
        this.triggers = value;
    }

    /**
     * Gets the value of the conditions property.
     * 
     * @return
     *     possible object is
     *     {@link Conditions }
     *     
     */
    public Conditions getConditions() {
        return conditions;
    }

    /**
     * Sets the value of the conditions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Conditions }
     *     
     */
    public void setConditions(Conditions value) {
        this.conditions = value;
    }

    /**
     * Gets the value of the behaviors property.
     * 
     * @return
     *     possible object is
     *     {@link Behaviors }
     *     
     */
    public Behaviors getBehaviors() {
        return behaviors;
    }

    /**
     * Sets the value of the behaviors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Behaviors }
     *     
     */
    public void setBehaviors(Behaviors value) {
        this.behaviors = value;
    }

    /**
     * Gets the value of the actionGroup property.
     * 
     * @return
     *     possible object is
     *     {@link ActionGroup }
     *     
     */
    public ActionGroup getActionGroup() {
        return actionGroup;
    }

    /**
     * Sets the value of the actionGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionGroup }
     *     
     */
    public void setActionGroup(ActionGroup value) {
        this.actionGroup = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="def" type="{urn:hl7-org:knowledgeartifact:r1}ExpressionDef" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "def"
    })
    public static class Expressions {

        protected List<ExpressionDef> def;

        /**
         * Gets the value of the def property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the def property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ExpressionDef }
         * 
         * 
         */
        public List<ExpressionDef> getDef() {
            if (def == null) {
                def = new ArrayList<ExpressionDef>();
            }
            return this.def;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="parameter" type="{urn:hl7-org:knowledgeartifact:r1}ParameterDef" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="def" type="{urn:hl7-org:knowledgeartifact:r1}ExpressionDef" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "parameter",
        "def"
    })
    public static class ExternalData {

        protected List<ParameterDef> parameter;
        protected List<ExpressionDef> def;

        /**
         * Gets the value of the parameter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parameter property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParameter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ParameterDef }
         * 
         * 
         */
        public List<ParameterDef> getParameter() {
            if (parameter == null) {
                parameter = new ArrayList<ParameterDef>();
            }
            return this.parameter;
        }

        /**
         * Gets the value of the def property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the def property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ExpressionDef }
         * 
         * 
         */
        public List<ExpressionDef> getDef() {
            if (def == null) {
                def = new ArrayList<ExpressionDef>();
            }
            return this.def;
        }

    }

}
