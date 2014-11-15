
/**
 * ServiceMainCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package com.mingdasoft;

    /**
     *  ServiceMainCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ServiceMainCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ServiceMainCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ServiceMainCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for execFunEx method
            * override this method for handling normal response from execFunEx operation
            */
           public void receiveResultexecFunEx(
                    com.mingdasoft.ExecFunExResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from execFunEx operation
           */
            public void receiveErrorexecFunEx(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAlreadyGet method
            * override this method for handling normal response from getAlreadyGet operation
            */
           public void receiveResultgetAlreadyGet(
                    com.mingdasoft.GetAlreadyGetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAlreadyGet operation
           */
            public void receiveErrorgetAlreadyGet(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bizInfoQuePrintList method
            * override this method for handling normal response from bizInfoQuePrintList operation
            */
           public void receiveResultbizInfoQuePrintList(
                    com.mingdasoft.BizInfoQuePrintListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bizInfoQuePrintList operation
           */
            public void receiveErrorbizInfoQuePrintList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getConfirm_ID method
            * override this method for handling normal response from getConfirm_ID operation
            */
           public void receiveResultgetConfirm_ID(
                    com.mingdasoft.GetConfirm_IDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getConfirm_ID operation
           */
            public void receiveErrorgetConfirm_ID(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setAssistFlag method
            * override this method for handling normal response from setAssistFlag operation
            */
           public void receiveResultsetAssistFlag(
                    com.mingdasoft.SetAssistFlagResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setAssistFlag operation
           */
            public void receiveErrorsetAssistFlag(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for calculateChargeNotSave method
            * override this method for handling normal response from calculateChargeNotSave operation
            */
           public void receiveResultcalculateChargeNotSave(
                    com.mingdasoft.CalculateChargeNotSaveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from calculateChargeNotSave operation
           */
            public void receiveErrorcalculateChargeNotSave(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bIZExcSQL method
            * override this method for handling normal response from bIZExcSQL operation
            */
           public void receiveResultbIZExcSQL(
                    com.mingdasoft.BIZExcSQLResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bIZExcSQL operation
           */
            public void receiveErrorbIZExcSQL(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setPhotoPath2 method
            * override this method for handling normal response from setPhotoPath2 operation
            */
           public void receiveResultsetPhotoPath2(
                    com.mingdasoft.SetPhotoPath2Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setPhotoPath2 operation
           */
            public void receiveErrorsetPhotoPath2(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bizInfoQue method
            * override this method for handling normal response from bizInfoQue operation
            */
           public void receiveResultbizInfoQue(
                    com.mingdasoft.BizInfoQueResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bizInfoQue operation
           */
            public void receiveErrorbizInfoQue(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkSSN_DBLK method
            * override this method for handling normal response from checkSSN_DBLK operation
            */
           public void receiveResultcheckSSN_DBLK(
                    com.mingdasoft.CheckSSN_DBLKResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkSSN_DBLK operation
           */
            public void receiveErrorcheckSSN_DBLK(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bizInfoQueV1 method
            * override this method for handling normal response from bizInfoQueV1 operation
            */
           public void receiveResultbizInfoQueV1(
                    com.mingdasoft.BizInfoQueV1Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bizInfoQueV1 operation
           */
            public void receiveErrorbizInfoQueV1(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for idCheck method
            * override this method for handling normal response from idCheck operation
            */
           public void receiveResultidCheck(
                    com.mingdasoft.IdCheckResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from idCheck operation
           */
            public void receiveErroridCheck(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bIZExcSQLEX method
            * override this method for handling normal response from bIZExcSQLEX operation
            */
           public void receiveResultbIZExcSQLEX(
                    com.mingdasoft.BIZExcSQLEXResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bIZExcSQLEX operation
           */
            public void receiveErrorbIZExcSQLEX(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for calculateCharge method
            * override this method for handling normal response from calculateCharge operation
            */
           public void receiveResultcalculateCharge(
                    com.mingdasoft.CalculateChargeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from calculateCharge operation
           */
            public void receiveErrorcalculateCharge(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for savePic method
            * override this method for handling normal response from savePic operation
            */
           public void receiveResultsavePic(
                    com.mingdasoft.SavePicResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from savePic operation
           */
            public void receiveErrorsavePic(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOutFlag method
            * override this method for handling normal response from getOutFlag operation
            */
           public void receiveResultgetOutFlag(
                    com.mingdasoft.GetOutFlagResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOutFlag operation
           */
            public void receiveErrorgetOutFlag(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getConfirmFlag method
            * override this method for handling normal response from getConfirmFlag operation
            */
           public void receiveResultgetConfirmFlag(
                    com.mingdasoft.GetConfirmFlagResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getConfirmFlag operation
           */
            public void receiveErrorgetConfirmFlag(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkFeeBatchOutFlag method
            * override this method for handling normal response from checkFeeBatchOutFlag operation
            */
           public void receiveResultcheckFeeBatchOutFlag(
                    com.mingdasoft.CheckFeeBatchOutFlagResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkFeeBatchOutFlag operation
           */
            public void receiveErrorcheckFeeBatchOutFlag(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for delBizRecord method
            * override this method for handling normal response from delBizRecord operation
            */
           public void receiveResultdelBizRecord(
                    com.mingdasoft.DelBizRecordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delBizRecord operation
           */
            public void receiveErrordelBizRecord(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkOutFlagInfo method
            * override this method for handling normal response from checkOutFlagInfo operation
            */
           public void receiveResultcheckOutFlagInfo(
                    com.mingdasoft.CheckOutFlagInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkOutFlagInfo operation
           */
            public void receiveErrorcheckOutFlagInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPayItem method
            * override this method for handling normal response from getPayItem operation
            */
           public void receiveResultgetPayItem(
                    com.mingdasoft.GetPayItemResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPayItem operation
           */
            public void receiveErrorgetPayItem(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkOutFlag method
            * override this method for handling normal response from checkOutFlag operation
            */
           public void receiveResultcheckOutFlag(
                    com.mingdasoft.CheckOutFlagResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkOutFlag operation
           */
            public void receiveErrorcheckOutFlag(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkSSN method
            * override this method for handling normal response from checkSSN operation
            */
           public void receiveResultcheckSSN(
                    com.mingdasoft.CheckSSNResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkSSN operation
           */
            public void receiveErrorcheckSSN(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bIZDataInsert method
            * override this method for handling normal response from bIZDataInsert operation
            */
           public void receiveResultbIZDataInsert(
                    com.mingdasoft.BIZDataInsertResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bIZDataInsert operation
           */
            public void receiveErrorbIZDataInsert(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBizINList method
            * override this method for handling normal response from getBizINList operation
            */
           public void receiveResultgetBizINList(
                    com.mingdasoft.GetBizINListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBizINList operation
           */
            public void receiveErrorgetBizINList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setPhotoPath method
            * override this method for handling normal response from setPhotoPath operation
            */
           public void receiveResultsetPhotoPath(
                    com.mingdasoft.SetPhotoPathResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setPhotoPath operation
           */
            public void receiveErrorsetPhotoPath(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for chargeCancel method
            * override this method for handling normal response from chargeCancel operation
            */
           public void receiveResultchargeCancel(
                    com.mingdasoft.ChargeCancelResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from chargeCancel operation
           */
            public void receiveErrorchargeCancel(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for idCheck_DBLK method
            * override this method for handling normal response from idCheck_DBLK operation
            */
           public void receiveResultidCheck_DBLK(
                    com.mingdasoft.IdCheck_DBLKResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from idCheck_DBLK operation
           */
            public void receiveErroridCheck_DBLK(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setAlreadyGet method
            * override this method for handling normal response from setAlreadyGet operation
            */
           public void receiveResultsetAlreadyGet(
                    com.mingdasoft.SetAlreadyGetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setAlreadyGet operation
           */
            public void receiveErrorsetAlreadyGet(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setAssistFlag_DBLK method
            * override this method for handling normal response from setAssistFlag_DBLK operation
            */
           public void receiveResultsetAssistFlag_DBLK(
                    com.mingdasoft.SetAssistFlag_DBLKResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setAssistFlag_DBLK operation
           */
            public void receiveErrorsetAssistFlag_DBLK(java.lang.Exception e) {
            }
                


    }
    