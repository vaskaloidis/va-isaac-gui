//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.vmr.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;


/**
 * An order for a laboratory test.
 * 
 * <p>Java class for LaboratoryOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LaboratoryOrder">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ProcedureOrder">
 *       &lt;sequence>
 *         &lt;element name="specialHandling" type="{urn:hl7-org:cdsdt:r2}CD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="specimen" type="{urn:hl7-org:vmr:r2}Specimen" minOccurs="0"/>
 *         &lt;element name="suspectedPathogen" type="{urn:hl7-org:cdsdt:r2}CD" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LaboratoryOrder", propOrder = {
    "specialHandling",
    "specimen",
    "suspectedPathogen"
})
public class LaboratoryOrder
    extends ProcedureOrder
{

    protected List<CD> specialHandling;
    protected Specimen specimen;
    protected List<CD> suspectedPathogen;

    /**
     * Gets the value of the specialHandling property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specialHandling property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecialHandling().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CD }
     * 
     * 
     */
    public List<CD> getSpecialHandling() {
        if (specialHandling == null) {
            specialHandling = new ArrayList<CD>();
        }
        return this.specialHandling;
    }

    /**
     * Gets the value of the specimen property.
     * 
     * @return
     *     possible object is
     *     {@link Specimen }
     *     
     */
    public Specimen getSpecimen() {
        return specimen;
    }

    /**
     * Sets the value of the specimen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Specimen }
     *     
     */
    public void setSpecimen(Specimen value) {
        this.specimen = value;
    }

    /**
     * Gets the value of the suspectedPathogen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suspectedPathogen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuspectedPathogen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CD }
     * 
     * 
     */
    public List<CD> getSuspectedPathogen() {
        if (suspectedPathogen == null) {
            suspectedPathogen = new ArrayList<CD>();
        }
        return this.suspectedPathogen;
    }

}
