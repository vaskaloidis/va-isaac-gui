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
import org.hl7.cdsdt.r2.IVLPQ;


/**
 * The type of imaging and any accessories that will be used during a simulation session for radiotherapy.  For example, an order might indicate that the simulation should be done using a 4-dimensional PET-CT with 5mm slices, no bolus and wire (to mark surgical scar).
 * 
 * <p>Java class for RadiotherapySimulation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RadiotherapySimulation">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ExtendedVmrTypeBase">
 *       &lt;sequence>
 *         &lt;element name="simulationImagingType" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="simulationDimensions" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="scanThickness" type="{urn:hl7-org:cdsdt:r2}IVL_PQ" minOccurs="0"/>
 *         &lt;element name="bolusType" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="bolusThickness" type="{urn:hl7-org:cdsdt:r2}IVL_PQ" minOccurs="0"/>
 *         &lt;element name="markerType" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="simulationComment" type="{urn:hl7-org:vmr:r2}Documentation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RadiotherapySimulation", propOrder = {
    "simulationImagingType",
    "simulationDimensions",
    "scanThickness",
    "bolusType",
    "bolusThickness",
    "markerType",
    "simulationComment"
})
public class RadiotherapySimulation
    extends ExtendedVmrTypeBase
{

    protected CD simulationImagingType;
    protected CD simulationDimensions;
    protected IVLPQ scanThickness;
    protected CD bolusType;
    protected IVLPQ bolusThickness;
    protected CD markerType;
    protected List<Documentation> simulationComment;

    /**
     * Gets the value of the simulationImagingType property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getSimulationImagingType() {
        return simulationImagingType;
    }

    /**
     * Sets the value of the simulationImagingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setSimulationImagingType(CD value) {
        this.simulationImagingType = value;
    }

    /**
     * Gets the value of the simulationDimensions property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getSimulationDimensions() {
        return simulationDimensions;
    }

    /**
     * Sets the value of the simulationDimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setSimulationDimensions(CD value) {
        this.simulationDimensions = value;
    }

    /**
     * Gets the value of the scanThickness property.
     * 
     * @return
     *     possible object is
     *     {@link IVLPQ }
     *     
     */
    public IVLPQ getScanThickness() {
        return scanThickness;
    }

    /**
     * Sets the value of the scanThickness property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLPQ }
     *     
     */
    public void setScanThickness(IVLPQ value) {
        this.scanThickness = value;
    }

    /**
     * Gets the value of the bolusType property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getBolusType() {
        return bolusType;
    }

    /**
     * Sets the value of the bolusType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setBolusType(CD value) {
        this.bolusType = value;
    }

    /**
     * Gets the value of the bolusThickness property.
     * 
     * @return
     *     possible object is
     *     {@link IVLPQ }
     *     
     */
    public IVLPQ getBolusThickness() {
        return bolusThickness;
    }

    /**
     * Sets the value of the bolusThickness property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLPQ }
     *     
     */
    public void setBolusThickness(IVLPQ value) {
        this.bolusThickness = value;
    }

    /**
     * Gets the value of the markerType property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getMarkerType() {
        return markerType;
    }

    /**
     * Sets the value of the markerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setMarkerType(CD value) {
        this.markerType = value;
    }

    /**
     * Gets the value of the simulationComment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simulationComment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimulationComment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Documentation }
     * 
     * 
     */
    public List<Documentation> getSimulationComment() {
        if (simulationComment == null) {
            simulationComment = new ArrayList<Documentation>();
        }
        return this.simulationComment;
    }

}
