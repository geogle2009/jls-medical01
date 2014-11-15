package com.medical.system;

import java.util.HashMap;

import org.dom4j.Document;

public interface DictionaryHandle {
	/**
	 * 读取xml文件
	 * 
	 * @param filepath
	 * @return
	 */
	public Document readDictXml();

	/**
	 * 返回字典值
	 * 
	 * @param dvid
	 * @return
	 */
	public String getDictValue(String dvid);

	/**
	 * 生成 xml option
	 * 
	 * @param dsid
	 * @return
	 */
	public Document getOptionsByDSID(String dsid, String selcetValue);

	/**
	 * 生成 html option
	 * 
	 * @param dsid
	 * @return
	 */
	public String getOptionsByDS(String dsid, String selcetValue);

	public HashMap<String, String> getDsMap(String dsid);
}
