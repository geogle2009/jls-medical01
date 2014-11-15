package com.medical.system.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.medical.system.DictionaryHandle;

public class DictionaryHandleImpl implements DictionaryHandle {

	private static String filepath = "com/medical/system/impl/dictionary.xml";

	public String getDictValue(String dvid) {

		String text = "";
		Document dict = this.readDictXml();
		Element root = dict.getRootElement();
		Node dvNode = root.selectSingleNode("/DICTIONARY/DS/DV[@id='" + dvid
				+ "']");
		if (null == dvNode) {
		} else {
			text = dvNode.getText();
		}
		return text;
	}

	public String getOptionsByDS(String dsid, String selcetValue) {
		return null;
	}

	public Document getOptionsByDSID(String dsid, String selcetValue) {
		return null;
	}

	public Document readDictXml() {
		String path = this.getClass().getClassLoader().getResource("/")
				.getPath()
				+ filepath;
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new File(path));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getDsMap(String dsid) {
		HashMap<String, String> map = new HashMap<String, String>();
		Document dict = this.readDictXml();
		Element root = dict.getRootElement();
		Node dsNode = root.selectSingleNode("/DICTIONARY/DS[@id='" + dsid
				+ "']");
		if (null != dsNode) {
			Element eds = (Element) dsNode;
			Iterator it = eds.elementIterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				map.put(e.attributeValue("id"), e.getText());
			}
		} else {

		}
		return map;
	}

}
