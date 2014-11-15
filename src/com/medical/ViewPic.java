package com.medical;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import com.medical.common.DBconn;

/**
 * Servlet implementation class ViewPic
 */

public class ViewPic extends HttpServlet {
	private static final String GIF = "image/gif;charset=GB2312";// 设定输出的类型
	private static final String JPG = "image/jpeg;charset=GB2312";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewPic() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String ds = request.getParameter("ds");
		DBconn dbconn = new DBconn();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		OutputStream os = response.getOutputStream();// 得到输出流
		response.setContentType(JPG);// 设定输出的类型
		try {
			sql = "select mem.member_id,mem.ds,mem.onepic,t.b from member_baseinfo mem " +
					" right join PHOTO_TEST t on 1=1 and mem.member_id='"
					+ memberId + "' and mem.ds='" + ds + "'";
			System.out.println(sql);
			conn = dbconn.getJDBCConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			Blob one = null;
			Blob de = null;
			InputStream bis = null;
			if (rs.next()) {
				one = rs.getBlob("onepic");
				de = rs.getBlob("b");
				if (null == one) {
					one = de;
					bis = one.getBinaryStream();
				} else {
					ByteArrayInputStream teempbis = new ByteArrayInputStream(
							new BASE64Decoder().decodeBuffer(one
									.getBinaryStream()));
					bis = teempbis;
				}
				// 得到Blob实例的字节流
				int b = bis.read(); // 以下就是象操作其它java字节流一样操作了
				while (b != -1) {
					os.write((char) b);
					b = bis.read();
				}
				os.flush();
				os.close();
				bis.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
