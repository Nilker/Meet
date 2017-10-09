
package com.xyauto.oa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Employee complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EmployeeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DomainAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NickName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EmployeeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Telephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Department" type="{http://tempuri.org/}Department" minOccurs="0"/>
 *         &lt;element name="Seat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TitleRank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ManagerNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DimissionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EntryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="OfficePlaceID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OfficePlace" type="{http://tempuri.org/}OfficePlace" minOccurs="0"/>
 *         &lt;element name="ServiceLengthStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeriateWorkStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegularWorkDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastWorkDimissionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WorkCharacter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BirthDay" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IDType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IDCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Employee", propOrder = {
    "employeeID",
    "domainAccount",
    "password",
    "cnName",
    "enName",
    "nickName",
    "employeeNumber",
    "postTitle",
    "email",
    "telephone",
    "mobile",
    "department",
    "seat",
    "isActive",
    "titleRank",
    "managerNumber",
    "dimissionDate",
    "entryDate",
    "officePlaceID",
    "officePlace",
    "serviceLengthStartTime",
    "seriateWorkStartTime",
    "regularWorkDate",
    "lastWorkDimissionDate",
    "workCharacter",
    "birthDay",
    "idType",
    "idCode",
    "sex"
})
public class Employee {

    @XmlElement(name = "EmployeeID")
    protected int employeeID;
    @XmlElement(name = "DomainAccount")
    protected String domainAccount;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "CnName")
    protected String cnName;
    @XmlElement(name = "EnName")
    protected String enName;
    @XmlElement(name = "NickName")
    protected String nickName;
    @XmlElement(name = "EmployeeNumber")
    protected String employeeNumber;
    @XmlElement(name = "PostTitle")
    protected String postTitle;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Telephone")
    protected String telephone;
    @XmlElement(name = "Mobile")
    protected String mobile;
    @XmlElement(name = "Department")
    protected Department department;
    @XmlElement(name = "Seat")
    protected String seat;
    @XmlElement(name = "IsActive")
    protected boolean isActive;
    @XmlElement(name = "TitleRank")
    protected int titleRank;
    @XmlElement(name = "ManagerNumber")
    protected String managerNumber;
    @XmlElement(name = "DimissionDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dimissionDate;
    @XmlElement(name = "EntryDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entryDate;
    @XmlElement(name = "OfficePlaceID")
    protected int officePlaceID;
    @XmlElement(name = "OfficePlace")
    protected OfficePlace officePlace;
    @XmlElement(name = "ServiceLengthStartTime")
    protected String serviceLengthStartTime;
    @XmlElement(name = "SeriateWorkStartTime")
    protected String seriateWorkStartTime;
    @XmlElement(name = "RegularWorkDate")
    protected String regularWorkDate;
    @XmlElement(name = "LastWorkDimissionDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastWorkDimissionDate;
    @XmlElement(name = "WorkCharacter")
    protected int workCharacter;
    @XmlElement(name = "BirthDay", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthDay;
    @XmlElement(name = "IDType")
    protected int idType;
    @XmlElement(name = "IDCode")
    protected String idCode;
    @XmlElement(name = "Sex")
    protected int sex;

    /**
     * 获取employeeID属性的值。
     * 
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * 设置employeeID属性的值。
     * 
     */
    public void setEmployeeID(int value) {
        this.employeeID = value;
    }

    /**
     * 获取domainAccount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainAccount() {
        return domainAccount;
    }

    /**
     * 设置domainAccount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainAccount(String value) {
        this.domainAccount = value;
    }

    /**
     * 获取password属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置password属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * 获取cnName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 设置cnName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnName(String value) {
        this.cnName = value;
    }

    /**
     * 获取enName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置enName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnName(String value) {
        this.enName = value;
    }

    /**
     * 获取nickName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置nickName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * 获取employeeNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * 设置employeeNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeNumber(String value) {
        this.employeeNumber = value;
    }

    /**
     * 获取postTitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * 设置postTitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostTitle(String value) {
        this.postTitle = value;
    }

    /**
     * 获取email属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * 获取telephone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置telephone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * 获取mobile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置mobile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * 获取department属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Department }
     *     
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * 设置department属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Department }
     *     
     */
    public void setDepartment(Department value) {
        this.department = value;
    }

    /**
     * 获取seat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeat() {
        return seat;
    }

    /**
     * 设置seat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeat(String value) {
        this.seat = value;
    }

    /**
     * 获取isActive属性的值。
     * 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * 设置isActive属性的值。
     * 
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    /**
     * 获取titleRank属性的值。
     * 
     */
    public int getTitleRank() {
        return titleRank;
    }

    /**
     * 设置titleRank属性的值。
     * 
     */
    public void setTitleRank(int value) {
        this.titleRank = value;
    }

    /**
     * 获取managerNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagerNumber() {
        return managerNumber;
    }

    /**
     * 设置managerNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagerNumber(String value) {
        this.managerNumber = value;
    }

    /**
     * 获取dimissionDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDimissionDate() {
        return dimissionDate;
    }

    /**
     * 设置dimissionDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDimissionDate(XMLGregorianCalendar value) {
        this.dimissionDate = value;
    }

    /**
     * 获取entryDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntryDate() {
        return entryDate;
    }

    /**
     * 设置entryDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntryDate(XMLGregorianCalendar value) {
        this.entryDate = value;
    }

    /**
     * 获取officePlaceID属性的值。
     * 
     */
    public int getOfficePlaceID() {
        return officePlaceID;
    }

    /**
     * 设置officePlaceID属性的值。
     * 
     */
    public void setOfficePlaceID(int value) {
        this.officePlaceID = value;
    }

    /**
     * 获取officePlace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OfficePlace }
     *     
     */
    public OfficePlace getOfficePlace() {
        return officePlace;
    }

    /**
     * 设置officePlace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OfficePlace }
     *     
     */
    public void setOfficePlace(OfficePlace value) {
        this.officePlace = value;
    }

    /**
     * 获取serviceLengthStartTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceLengthStartTime() {
        return serviceLengthStartTime;
    }

    /**
     * 设置serviceLengthStartTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceLengthStartTime(String value) {
        this.serviceLengthStartTime = value;
    }

    /**
     * 获取seriateWorkStartTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriateWorkStartTime() {
        return seriateWorkStartTime;
    }

    /**
     * 设置seriateWorkStartTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriateWorkStartTime(String value) {
        this.seriateWorkStartTime = value;
    }

    /**
     * 获取regularWorkDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegularWorkDate() {
        return regularWorkDate;
    }

    /**
     * 设置regularWorkDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegularWorkDate(String value) {
        this.regularWorkDate = value;
    }

    /**
     * 获取lastWorkDimissionDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastWorkDimissionDate() {
        return lastWorkDimissionDate;
    }

    /**
     * 设置lastWorkDimissionDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastWorkDimissionDate(XMLGregorianCalendar value) {
        this.lastWorkDimissionDate = value;
    }

    /**
     * 获取workCharacter属性的值。
     * 
     */
    public int getWorkCharacter() {
        return workCharacter;
    }

    /**
     * 设置workCharacter属性的值。
     * 
     */
    public void setWorkCharacter(int value) {
        this.workCharacter = value;
    }

    /**
     * 获取birthDay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDay() {
        return birthDay;
    }

    /**
     * 设置birthDay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDay(XMLGregorianCalendar value) {
        this.birthDay = value;
    }

    /**
     * 获取idType属性的值。
     * 
     */
    public int getIDType() {
        return idType;
    }

    /**
     * 设置idType属性的值。
     * 
     */
    public void setIDType(int value) {
        this.idType = value;
    }

    /**
     * 获取idCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDCode() {
        return idCode;
    }

    /**
     * 设置idCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDCode(String value) {
        this.idCode = value;
    }

    /**
     * 获取sex属性的值。
     * 
     */
    public int getSex() {
        return sex;
    }

    /**
     * 设置sex属性的值。
     * 
     */
    public void setSex(int value) {
        this.sex = value;
    }

}
