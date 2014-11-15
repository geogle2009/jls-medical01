package com.medical.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	private int LENGTH = 3000;

	@SuppressWarnings("rawtypes")
	public ByteArrayOutputStream genExcelData(HashMap title, List<HashMap> rs) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		Iterator columes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			columes = title.keySet().iterator();
			if (null != rs && rs.size() > 0) {

				int sheetcnt = rs.size() / LENGTH;
				int restcnt = rs.size() % LENGTH;

				if (sheetcnt > 0) {
					for (int i = 0; i < sheetcnt; i++) {
						int sq = i + 1;
						sheet = workbook.createSheet("ตฺ" + sq + "าณ");
						row = sheet.createRow(0);
						int c = 0;
						columes = title.keySet().iterator();
						while (columes.hasNext()) {
							String colname = (String) columes.next();
							String colvalue = (String) title.get(colname);
							cell = row.createCell(c);
							cell.setCellValue(colvalue);
							c++;
						}
						for (int j = i * LENGTH; j < (i + 1) * LENGTH; j++) {
							row = sheet.createRow(j - (i * LENGTH) + 1);
							HashMap map = (HashMap) rs.get(j);
							c = 0;
							columes = title.keySet().iterator();
							while (columes.hasNext()) {
								String colname = (String) columes.next();
								Object colvalue = (Object) map.get(colname);
								cell = row.createCell(c);
								if (null != colvalue) {
									if ("java.math.BigDecimal".equals(colvalue
											.getClass().getName())) {
										cell.setCellValue(colvalue.toString());
									} else if ("java.sql.Timestamp"
											.equals(colvalue.getClass()
													.getName())) {
										cell.setCellValue(colvalue.toString()
												.substring(0, 10));
									} else {
										cell.setCellValue(colvalue.toString());
									}
								}
								c++;
							}
						}
					}

					if (restcnt > 0) {
						int sq =sheetcnt + 1;
						sheet = workbook.createSheet("ตฺ" + sq + "าณ");
						row = sheet.createRow(0);
						int c = 0;
						columes = title.keySet().iterator();
						while (columes.hasNext()) {
							String colname = (String) columes.next();
							String colvalue = (String) title.get(colname);
							cell = row.createCell(c);
							cell.setCellValue(colvalue);
							c++;
						}
						for (int j = sheetcnt * LENGTH; j < (sheetcnt * LENGTH)+restcnt; j++) {
							row = sheet.createRow(j - (sheetcnt * LENGTH) + 1);
							HashMap map = (HashMap) rs.get(j);
							c = 0;
							columes = title.keySet().iterator();
							while (columes.hasNext()) {
								String colname = (String) columes.next();
								Object colvalue = (Object) map.get(colname);
								cell = row.createCell(c);
								if (null != colvalue) {
									if ("java.math.BigDecimal".equals(colvalue
											.getClass().getName())) {
										cell.setCellValue(colvalue.toString());
									} else if ("java.sql.Timestamp"
											.equals(colvalue.getClass()
													.getName())) {
										cell.setCellValue(colvalue.toString()
												.substring(0, 10));
									} else {
										cell.setCellValue(colvalue.toString());
									}
								}
								c++;
							}
						}
					}
				} else {
					sheet = workbook.createSheet("ตฺ1าณ");
					row = sheet.createRow(0);
					int c = 0;
					columes = title.keySet().iterator();
					while (columes.hasNext()) {
						String colname = (String) columes.next();
						String colvalue = (String) title.get(colname);
						cell = row.createCell(c);
						cell.setCellValue(colvalue);
						c++;
					}

					for (int j = sheetcnt * LENGTH; j < restcnt; j++) {
						row = sheet.createRow(j - (sheetcnt * LENGTH) + 1);
						HashMap map = (HashMap) rs.get(j);
						c = 0;
						columes = title.keySet().iterator();
						while (columes.hasNext()) {
							String colname = (String) columes.next();
							Object colvalue = (Object) map.get(colname);
							cell = row.createCell(c);
							if (null != colvalue) {
								if ("java.math.BigDecimal".equals(colvalue
										.getClass().getName())) {
									cell.setCellValue(new BigDecimal(colvalue
											.toString()).doubleValue());
								} else if ("java.sql.Timestamp".equals(colvalue
										.getClass().getName())) {
									cell.setCellValue(colvalue.toString()
											.substring(0, 10));
								} else {
									cell.setCellValue(colvalue.toString());
								}
							}
							c++;
						}
					}
				}
			}
			workbook.write(baos);
		} catch (RuntimeException re) {
			re.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return baos;
	}
}
