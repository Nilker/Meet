
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
 *         &lt;element name="includeChildren" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="filterParttime" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "includeChildren",
    "filterParttime",
    "pageSize",
    "pageIndex"
})
@XmlRootElement(name = "QueryEmployeeByDept")
public class QueryEmployeeByDept {

    protected int deptId;
    protected boolean includeChildren;
    protected boolean filterParttime;
    protected int pageSize;
    protected int pageIndex;

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
     * 获取includeChildren属性的值。
     * 
     */
    public boolean isIncludeChildren() {
        return includeChildren;
    }

    /**
     * 设置includeChildren属性的值。
     * 
     */
    public void setIncludeChildren(boolean value) {
        this.includeChildren = value;
    }

    /**
     * 获取filterParttime属性的值。
     * 
     */
    public boolean isFilterParttime() {
        return filterParttime;
    }

    /**
     * 设置filterParttime属性的值。
     * 
     */
    public void setFilterParttime(boolean value) {
        this.filterParttime = value;
    }

    /**
     * 获取pageSize属性的值。
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * 获取pageIndex属性的值。
     * 
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置pageIndex属性的值。
     * 
     */
    public void setPageIndex(int value) {
        this.pageIndex = value;
    }

}
