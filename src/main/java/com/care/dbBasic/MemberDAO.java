package com.care.dbBasic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	private Connection con;
	
	public MemberDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String password = "oracle";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberDTO selectId(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM session_exam WHERE id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				MemberDTO memberDto = new MemberDTO();
				memberDto.setId(rs.getString("id"));
				memberDto.setPw(rs.getString("pw"));
				memberDto.setName(rs.getString("name"));
				memberDto.setEmail(rs.getString("email"));
				return memberDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void register(MemberDTO member) {
		String sql = "INSERT INTO session_exam VALUES(?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPw());
			ps.setString(3, member.getName());
			ps.setString(4, member.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(MemberDTO member) {
		String sql = "UPDATE session_exam SET pw=?, name=?, email=? WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(4, member.getId());
			ps.setString(1, member.getPw());
			ps.setString(2, member.getName());
			ps.setString(3, member.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String id) {
		String sql = "DELETE FROM session_exam WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disConnection() {
		try {
			if(con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int count(String select, String search) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		
		int count = 0;
		try {
			if(select.isEmpty()) { //검색을 누르지 않았을 경우를 위해서.
				sql ="SELECT count(id) FROM session_exam";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
			}else if(select.equals("id")) {
				sql ="SELECT count(id) FROM session_exam WHERE id like ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+search+"%");
				rs = ps.executeQuery();
			}else if(select.equals("email")) {
				sql ="SELECT count(id) FROM session_exam WHERE email like ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+search+"%");
				rs = ps.executeQuery();
			}else if(select.equals("total")){ // id, name, email 컬럼중에 하나라도 검색어와 일치하면 출력되도록.
				sql ="SELECT count(id) FROM session_exam WHERE(id LIKE ? OR name LIKE ? OR email LIKE ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+search+"%");
				ps.setString(2, "%"+search+"%");
				ps.setString(3, "%"+search+"%");
				rs = ps.executeQuery();
			}
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<MemberDTO> selectAll(int begin, int end, String select, String search) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> members = new ArrayList<MemberDTO>();
		String sql = "";

		try {
			if(select.isEmpty()) { //검색을 누르지 않았을 경우를 위해서.
				sql ="SELECT AAA.* "
				+ "FROM (SELECT rownum as rn, session_exam.* FROM session_exam)AAA "
				+ "WHERE AAA.rn <= ? AND AAA.rn >= ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, end);
				ps.setInt(2, begin);
			}else if(select.equals("id")) {
				sql ="SELECT AAA.* "
				+ "FROM (SELECT rownum as rn, id, pw, name, email FROM session_exam WHERE id like ?)AAA "
				+ "WHERE AAA.rn <= ? AND AAA.rn >= ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+search+"%");
				ps.setInt(2, end);
				ps.setInt(3, begin);
			}else if(select.equals("email")) {
				sql ="SELECT AAA.* "
				+ "FROM (SELECT rownum as rn, id, pw, name, email FROM session_exam WHERE email like ?)AAA "
				+ "WHERE AAA.rn <= ? AND AAA.rn >= ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+search+"%");
				ps.setInt(2, end);
				ps.setInt(3, begin);
			}else if(select.equals("total")){ //전체 검색 추가해 봄. id, name, email 컬럼중에 하나라도 검색어와 일치하면 출력되도록.
				sql ="SELECT AAA.* "
				+ "FROM (SELECT rownum as rn, id, pw, name, email FROM session_exam WHERE(id LIKE ? OR name LIKE ? OR email LIKE ?))AAA "
				+ "WHERE AAA.rn <= ? AND AAA.rn >= ?";
				
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+search+"%");
				ps.setString(2, "%"+search+"%");
				ps.setString(3, "%"+search+"%");
				ps.setInt(4, end);
				ps.setInt(5, begin);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO memberDto = new MemberDTO();
				memberDto.setId(rs.getString("id"));
				memberDto.setPw(rs.getString("pw"));
				memberDto.setName(rs.getString("name"));
				memberDto.setEmail(rs.getString("email"));
				members.add(memberDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
}













