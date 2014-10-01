//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:15 PM PDT 
//

package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for CoverageType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="CoverageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PatientGender"/>
 *     &lt;enumeration value="PatientAgeGroup"/>
 *     &lt;enumeration value="ClinicalFocus"/>
 *     &lt;enumeration value="TargetUser"/>
 *     &lt;enumeration value="WorkflowSetting"/>
 *     &lt;enumeration value="WorkflowTask"/>
 *     &lt;enumeration value="ClinicalVenue"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CoverageType", namespace = "urn:hl7-org:knowledgeartifact:r1")
@XmlEnum
public enum CoverageType {

  /**
   * The gender of the patient. For this item type, use HL7 administrative
   * gender codes (OID: 2.16.840.1.113883.1.11.1)
   * 
   */
  @XmlEnumValue("PatientGender")
  PATIENT_GENDER("PatientGender"),

  /**
   * A patient demographic category for which this artifact is applicable.
   * Allows specification of age groups using coded values originating from the
   * MeSH Code system (OID: 2.16.840.1.113883.6.177). More specifically, only
   * codes from the AgeGroupObservationValue value set are valid for this field
   * [2.16.840.1.113883.11.75]
   * 
   */
  @XmlEnumValue("PatientAgeGroup")
  PATIENT_AGE_GROUP("PatientAgeGroup"),

  /**
   * The clinical concept(s) addressed by the artifact. For example, disease,
   * diagnostic test interpretation, medication ordering. Please refer to the
   * implementation guide on which code system and codes to use.
   * 
   */
  @XmlEnumValue("ClinicalFocus")
  CLINICAL_FOCUS("ClinicalFocus"),

  /**
   * The user types to which an artifact is targeted. For example, PCP, Patient,
   * Cardiologist, Behavioral Professional, Oral Health Professional,
   * Prescriber, etc... taken from the NUCC Health Care provider taxonomyCode
   * system (OID: 2.16.840.1.113883.6.101)
   * 
   */
  @XmlEnumValue("TargetUser")
  TARGET_USER("TargetUser"),

  /**
   * The settings in which the artifact is intended for use. For example,
   * admission, pre-op, etc.
   * 
   */
  @XmlEnumValue("WorkflowSetting")
  WORKFLOW_SETTING("WorkflowSetting"),

  /**
   * The context for the clinical task(s) represented by this artifact. Can be
   * any task context represented by the HL7 ActTaskCode value set (OID:
   * 2.16.840.1.113883.1.11.19846). General categories include: order entry,
   * patient documentation and patient information review
   * 
   */
  @XmlEnumValue("WorkflowTask")
  WORKFLOW_TASK("WorkflowTask"),

  /**
   * The venue in which an artifact could be used. For example, Outpatient,
   * Inpatient, Home, Nursing home. The code value may originate from either the
   * HL7 ActEncounter (OID: 2.16.840.1.113883.1.11.13955) or NUCC non-individual
   * provider codes OID: 2.16.840.1.113883.1.11.19465
   * 
   */
  @XmlEnumValue("ClinicalVenue")
  CLINICAL_VENUE("ClinicalVenue");
  private final String value;

  CoverageType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static CoverageType fromValue(String v) {
    for (CoverageType c : CoverageType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
