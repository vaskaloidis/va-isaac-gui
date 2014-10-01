//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.vmr.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * An order for a radiotherapy procedure.
 * 
 * <p>
 * Java class for RadiotherapyOrder complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="RadiotherapyOrder">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ProcedureOrder">
 *       &lt;sequence>
 *         &lt;element name="simulationMethod" type="{urn:hl7-org:vmr:r2}RadiotherapySimulation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="motionManagement" type="{urn:hl7-org:vmr:r2}MotionManagement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="localizationMethod" type="{urn:hl7-org:vmr:r2}LocalizationMethod" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="treatmentPlanningInstructions" type="{urn:hl7-org:vmr:r2}Dose" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RadiotherapyOrder", propOrder = {
    "simulationMethod", "motionManagement", "localizationMethod",
    "treatmentPlanningInstructions"
})
public class RadiotherapyOrder extends ProcedureOrder {

  protected List<RadiotherapySimulation> simulationMethod;

  protected List<MotionManagement> motionManagement;

  protected List<LocalizationMethod> localizationMethod;

  protected List<Dose> treatmentPlanningInstructions;

  /**
   * Gets the value of the simulationMethod property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the simulationMethod property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getSimulationMethod().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link RadiotherapySimulation }
   * 
   * 
   */
  public List<RadiotherapySimulation> getSimulationMethod() {
    if (simulationMethod == null) {
      simulationMethod = new ArrayList<RadiotherapySimulation>();
    }
    return this.simulationMethod;
  }

  /**
   * Gets the value of the motionManagement property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the motionManagement property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getMotionManagement().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link MotionManagement }
   * 
   * 
   */
  public List<MotionManagement> getMotionManagement() {
    if (motionManagement == null) {
      motionManagement = new ArrayList<MotionManagement>();
    }
    return this.motionManagement;
  }

  /**
   * Gets the value of the localizationMethod property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the localizationMethod property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getLocalizationMethod().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link LocalizationMethod }
   * 
   * 
   */
  public List<LocalizationMethod> getLocalizationMethod() {
    if (localizationMethod == null) {
      localizationMethod = new ArrayList<LocalizationMethod>();
    }
    return this.localizationMethod;
  }

  /**
   * Gets the value of the treatmentPlanningInstructions property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the treatmentPlanningInstructions property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getTreatmentPlanningInstructions().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Dose }
   * 
   * 
   */
  public List<Dose> getTreatmentPlanningInstructions() {
    if (treatmentPlanningInstructions == null) {
      treatmentPlanningInstructions = new ArrayList<Dose>();
    }
    return this.treatmentPlanningInstructions;
  }

}
