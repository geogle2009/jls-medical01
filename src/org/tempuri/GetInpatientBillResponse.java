/**
 * GetInpatientBillResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class GetInpatientBillResponse  implements java.io.Serializable {
    private org.tempuri.GetInpatientBillResponseGetInpatientBillResult getInpatientBillResult;

    public GetInpatientBillResponse() {
    }

    public GetInpatientBillResponse(
           org.tempuri.GetInpatientBillResponseGetInpatientBillResult getInpatientBillResult) {
           this.getInpatientBillResult = getInpatientBillResult;
    }


    /**
     * Gets the getInpatientBillResult value for this GetInpatientBillResponse.
     * 
     * @return getInpatientBillResult
     */
    public org.tempuri.GetInpatientBillResponseGetInpatientBillResult getGetInpatientBillResult() {
        return getInpatientBillResult;
    }


    /**
     * Sets the getInpatientBillResult value for this GetInpatientBillResponse.
     * 
     * @param getInpatientBillResult
     */
    public void setGetInpatientBillResult(org.tempuri.GetInpatientBillResponseGetInpatientBillResult getInpatientBillResult) {
        this.getInpatientBillResult = getInpatientBillResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetInpatientBillResponse)) return false;
        GetInpatientBillResponse other = (GetInpatientBillResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getInpatientBillResult==null && other.getGetInpatientBillResult()==null) || 
             (this.getInpatientBillResult!=null &&
              this.getInpatientBillResult.equals(other.getGetInpatientBillResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGetInpatientBillResult() != null) {
            _hashCode += getGetInpatientBillResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetInpatientBillResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetInpatientBillResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getInpatientBillResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetInpatientBillResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">>GetInpatientBillResponse>GetInpatientBillResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
