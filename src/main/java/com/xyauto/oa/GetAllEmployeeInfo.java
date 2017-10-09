
package com.xyauto.oa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isQueryDB" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "isQueryDB"
})
@XmlRootElement(name = "GetAllEmployeeInfo")
public class GetAllEmployeeInfo {

    protected boolean isQueryDB;

    /**
     * 获取isQueryDB属性的值。
     * 
     */
    public boolean isIsQueryDB() {
        return isQueryDB;
    }

    /**
     * 设置isQueryDB属性的值。
     * 
     */
    public void setIsQueryDB(boolean value) {
        this.isQueryDB = value;
    }

}
