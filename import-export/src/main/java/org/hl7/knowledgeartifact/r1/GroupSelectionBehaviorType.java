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
 * <p>Java class for GroupSelectionBehaviorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GroupSelectionBehaviorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Any"/>
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="AllOrNone"/>
 *     &lt;enumeration value="ExactlyOne"/>
 *     &lt;enumeration value="AtMostOne"/>
 *     &lt;enumeration value="OneOrMore"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GroupSelectionBehaviorType")
@XmlEnum
public enum GroupSelectionBehaviorType {


    /**
     * Any number of the items in the group may be chosen, from zero to all.
     * 
     */
    @XmlEnumValue("Any")
    ANY("Any"),

    /**
     * All the items in the group must be selected as a single unit.
     * 
     */
    @XmlEnumValue("All")
    ALL("All"),

    /**
     * All the items in the group are meant to be chosen as a single unit:  either all must be selected by the end user, or none may be selected.
     * 
     */
    @XmlEnumValue("AllOrNone")
    ALL_OR_NONE("AllOrNone"),

    /**
     * The end user must choose one and only one of the selectable items in the group.  The user may not choose none of the items in the group.
     * 
     */
    @XmlEnumValue("ExactlyOne")
    EXACTLY_ONE("ExactlyOne"),

    /**
     * The end user may choose zero or at most one of the items in the group.
     * 
     */
    @XmlEnumValue("AtMostOne")
    AT_MOST_ONE("AtMostOne"),

    /**
     * The end user must choose a minimum of one, and as many additional as desired.
     * 
     */
    @XmlEnumValue("OneOrMore")
    ONE_OR_MORE("OneOrMore");
    private final String value;

    GroupSelectionBehaviorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GroupSelectionBehaviorType fromValue(String v) {
        for (GroupSelectionBehaviorType c: GroupSelectionBehaviorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
