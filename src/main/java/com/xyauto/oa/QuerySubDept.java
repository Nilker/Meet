
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
 *         &lt;element name="deptId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="includeAll" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "deptId",
    "includeAll"
})
@XmlRootElement(name = "QuerySubDept")
public class QuerySubDept {

    protected int deptId;
    protected boolean includeAll;

    /**
     * 获取deptId属性的值。
     * 
     */
    public int getDeptId() {
        return deptId;
    }

    /**
     * 设置deptId属性的值。
     * 
     */
    public void setDeptId(int value) {
        this.deptId = value;
    }

    /**
     * 获取includeAll属性的值。
     * 
     */
    public boolean isIncludeAll() {
        return includeAll;
    }

    /**
     * 设置includeAll属性的值。
     * 
     */
    public void setIncludeAll(boolean value) {
        this.includeAll = value;
    }

}
