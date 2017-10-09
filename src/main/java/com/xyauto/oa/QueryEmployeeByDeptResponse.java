
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
 *         &lt;element name="QueryEmployeeByDeptResult" type="{http://tempuri.org/}ArrayOfEmployee" minOccurs="0"/>
 *         &lt;element name="employeeCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "queryEmployeeByDeptResult",
    "employeeCount"
})
@XmlRootElement(name = "QueryEmployeeByDeptResponse")
public class QueryEmployeeByDeptResponse {

    @XmlElement(name = "QueryEmployeeByDeptResult")
    protected ArrayOfEmployee queryEmployeeByDeptResult;
    protected int employeeCount;

    /**
     * 获取queryEmployeeByDeptResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public ArrayOfEmployee getQueryEmployeeByDeptResult() {
        return queryEmployeeByDeptResult;
    }

    /**
     * 设置queryEmployeeByDeptResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public void setQueryEmployeeByDeptResult(ArrayOfEmployee value) {
        this.queryEmployeeByDeptResult = value;
    }

    /**
     * 获取employeeCount属性的值。
     * 
     */
    public int getEmployeeCount() {
        return employeeCount;
    }

    /**
     * 设置employeeCount属性的值。
     * 
     */
    public void setEmployeeCount(int value) {
        this.employeeCount = value;
    }

}
