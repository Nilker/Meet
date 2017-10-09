
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
 *         &lt;element name="GetEmployeeByEmployeeNumberResult" type="{http://tempuri.org/}Employee" minOccurs="0"/>
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
    "getEmployeeByEmployeeNumberResult"
})
@XmlRootElement(name = "GetEmployeeByEmployeeNumberResponse")
public class GetEmployeeByEmployeeNumberResponse {

    @XmlElement(name = "GetEmployeeByEmployeeNumberResult")
    protected Employee getEmployeeByEmployeeNumberResult;

    /**
     * 获取getEmployeeByEmployeeNumberResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Employee }
     *     
     */
    public Employee getGetEmployeeByEmployeeNumberResult() {
        return getEmployeeByEmployeeNumberResult;
    }

    /**
     * 设置getEmployeeByEmployeeNumberResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Employee }
     *     
     */
    public void setGetEmployeeByEmployeeNumberResult(Employee value) {
        this.getEmployeeByEmployeeNumberResult = value;
    }

}
