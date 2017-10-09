
package com.xyauto.oa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetDeptByEmployeeResult" type="{http://tempuri.org/}Department" minOccurs="0"/>
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
    "getDeptByEmployeeResult"
})
@XmlRootElement(name = "GetDeptByEmployeeResponse")
public class GetDeptByEmployeeResponse {

    @XmlElement(name = "GetDeptByEmployeeResult")
    protected Department getDeptByEmployeeResult;

    /**
     * 获取getDeptByEmployeeResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Department }
     *     
     */
    public Department getGetDeptByEmployeeResult() {
        return getDeptByEmployeeResult;
    }

    /**
     * 设置getDeptByEmployeeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Department }
     *     
     */
    public void setGetDeptByEmployeeResult(Department value) {
        this.getDeptByEmployeeResult = value;
    }

}
