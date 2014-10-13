//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequiredBehaviorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RequiredBehaviorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Must"/>
 *     &lt;enumeration value="Could"/>
 *     &lt;enumeration value="MustUnlessDocumented"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RequiredBehaviorType")
@XmlEnum
public enum RequiredBehaviorType {


    /**
     * An action with this behavior must be included in the actions processed by the end user; the end user may not choose not to include this action.
     * 
     */
    @XmlEnumValue("Must")
    MUST("Must"),

    /**
     * An action with this behavior may be included in the set of actions processed by the end user.
     * 
     */
    @XmlEnumValue("Could")
    COULD("Could"),

    /**
     * An action with this behavior must be included in the set of actions processed by the end user, unless the end user provides documentation as to why the action was not included.
     * 
     */
    @XmlEnumValue("MustUnlessDocumented")
    MUST_UNLESS_DOCUMENTED("MustUnlessDocumented");
    private final String value;

    RequiredBehaviorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RequiredBehaviorType fromValue(String v) {
        for (RequiredBehaviorType c: RequiredBehaviorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
