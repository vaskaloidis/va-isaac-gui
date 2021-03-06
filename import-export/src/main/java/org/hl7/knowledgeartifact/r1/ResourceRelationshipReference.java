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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ResourceRelationshipReference defines the
 * 				association between a resource and a resource set. If one consider
 * 				such relationship as a triple (subject-predicate-object or
 * 				node-arc-node) with the source entity as the subject of the triple,
 * 				then ResourceRelationshipType defines the predicate and the object
 * 				of this relationship.
 * 			
 * 
 * <p>Java class for ResourceRelationshipReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResourceRelationshipReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="relationship">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="value" use="required" type="{urn:hl7-org:knowledgeartifact:r1}ResourceRelationshipType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="resources">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resource" type="{urn:hl7-org:knowledgeartifact:r1}KnowledgeResource" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceRelationshipReference", propOrder = {
    "relationship",
    "resources"
})
public class ResourceRelationshipReference {

    @XmlElement(required = true)
    protected ResourceRelationshipReference.Relationship relationship;
    @XmlElement(required = true)
    protected ResourceRelationshipReference.Resources resources;

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceRelationshipReference.Relationship }
     *     
     */
    public ResourceRelationshipReference.Relationship getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceRelationshipReference.Relationship }
     *     
     */
    public void setRelationship(ResourceRelationshipReference.Relationship value) {
        this.relationship = value;
    }

    /**
     * Gets the value of the resources property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceRelationshipReference.Resources }
     *     
     */
    public ResourceRelationshipReference.Resources getResources() {
        return resources;
    }

    /**
     * Sets the value of the resources property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceRelationshipReference.Resources }
     *     
     */
    public void setResources(ResourceRelationshipReference.Resources value) {
        this.resources = value;
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
     *       &lt;attribute name="value" use="required" type="{urn:hl7-org:knowledgeartifact:r1}ResourceRelationshipType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Relationship {

        @XmlAttribute(name = "value", required = true)
        protected ResourceRelationshipType value;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link ResourceRelationshipType }
         *     
         */
        public ResourceRelationshipType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link ResourceRelationshipType }
         *     
         */
        public void setValue(ResourceRelationshipType value) {
            this.value = value;
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
     *         &lt;element name="resource" type="{urn:hl7-org:knowledgeartifact:r1}KnowledgeResource" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "resource"
    })
    public static class Resources {

        @XmlElement(required = true)
        protected List<KnowledgeResource> resource;

        /**
         * Gets the value of the resource property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resource property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResource().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link KnowledgeResource }
         * 
         * 
         */
        public List<KnowledgeResource> getResource() {
            if (resource == null) {
                resource = new ArrayList<KnowledgeResource>();
            }
            return this.resource;
        }

    }

}
