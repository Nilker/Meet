
package com.xyauto.oa;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LoginResult的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="LoginResult">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Success"/>
 *     &lt;enumeration value="UserNotExist"/>
 *     &lt;enumeration value="WrongPassword"/>
 *     &lt;enumeration value="Inactive"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LoginResult")
@XmlEnum
public enum LoginResult {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("UserNotExist")
    USER_NOT_EXIST("UserNotExist"),
    @XmlEnumValue("WrongPassword")
    WRONG_PASSWORD("WrongPassword"),
    @XmlEnumValue("Inactive")
    INACTIVE("Inactive");
    private final String value;

    LoginResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LoginResult fromValue(String v) {
        for (LoginResult c: LoginResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
