
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
 *         &lt;element name="QueryManagerByDeptNameResult" type="{http://tempuri.org/}ArrayOfEmployee" minOccurs="0"/>
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
    "queryManagerByDeptNameResult"
})
@XmlRootElement(name = "QueryManagerByDeptNameResponse")
public class QueryManagerByDeptNameResponse {

    @XmlElement(name = "QueryManagerByDeptNameResult")
    protected ArrayOfEmployee queryManagerByDeptNameResult;

    /**
     * 获取queryManagerByDeptNameResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public ArrayOfEmployee getQueryManagerByDeptNameResult() {
        return queryManagerByDeptNameResult;
    }

    /**
     * 设置queryManagerByDeptNameResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public void setQueryManagerByDeptNameResult(ArrayOfEmployee value) {
        this.queryManagerByDeptNameResult = value;
    }

}
