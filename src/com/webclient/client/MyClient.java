package com.webclient.client;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.mingdasoft.CalculateCharge;
import com.mingdasoft.CalculateChargeNotSave;
import com.mingdasoft.CalculateChargeNotSaveResponse;
import com.mingdasoft.CalculateChargeResponse;
import com.mingdasoft.ServiceMainStub;

public class MyClient {
	// type 0 ²»±£´æ 1±£´æ
	public String run(String bizId, String type) {

		ServiceMainStub service;
		String result = "";
		try {
			service = new ServiceMainStub();
			if ("1".equals(type)) {
				CalculateCharge parm = new CalculateCharge();
				parm.setBIZ_ID(bizId);
				CalculateChargeResponse response = service
						.calculateCharge(parm);
				result = response.getCalculateChargeResult();
			} else if ("0".equals(type)) {
				CalculateChargeNotSave parm = new CalculateChargeNotSave();
				parm.setBIZ_ID(bizId);
				CalculateChargeNotSaveResponse response = service
						.calculateChargeNotSave(parm);
				result = response.getCalculateChargeNotSaveResult();
			} else {

			}

		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;

	}
}