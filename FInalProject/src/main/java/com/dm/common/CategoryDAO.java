package com.dm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
	final String CATEGORY_SELECT = "select * from categorys";
	final String CATEGORY_ALL_SELECT = "select * from categorys;";
	final String DELETE_CATEGORY = "delete from categorys where category_name = ?;";
	final String CATEGORY_INSERT = "insert into categorys values(?,null);";

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public void insertCategory(CategoryDTO category) throws SQLException {

		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_INSERT);
			stmt.setString(1, category.getCategory_name());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCutil.close(stmt, conn);
		}

	}

	ArrayList<CategoryDTO> alist = new ArrayList<CategoryDTO>();

	public ArrayList<CategoryDTO> selectQuestion() throws SQLException {

		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_SELECT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCategory_name(rs.getString("category_name"));
				dto.setImg_url(rs.getString("img_url"));

				alist.add(dto);
			}

		} catch (Exception e) {
			System.out.println(e);
//			return null;
		} finally {
			JDBCutil.close(rs, stmt, conn);
		}

		return alist;
	}

	public ArrayList<CategoryDTO> selectCategoryList() throws SQLException {
		ArrayList<CategoryDTO> cList = new ArrayList<CategoryDTO>();
		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_ALL_SELECT); // 3
			rs = stmt.executeQuery();

			while (rs.next()) {
				CategoryDTO rd = new CategoryDTO();
				rd.setCategory_name(rs.getString("category_name"));
				rd.setImg_url(rs.getString("img_url"));

				cList.add(rd);

			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			JDBCutil.close(stmt, conn);
		}
		return cList;
	}

	public void DeleteCategory(String categoryName) throws SQLException {
		try {
			conn = JDBCutil.getConnection();
			stmt = conn.prepareStatement(DELETE_CATEGORY);
			stmt.setString(1, categoryName);
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			JDBCutil.close(stmt, conn);
		}
	}
}
