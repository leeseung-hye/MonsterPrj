package kr.kosa.mon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MonDao {

	DataSource dataSource;
	// 데이터 베이스와 연결 코드
	public MonDao() {
		try {
			Context initCtx = new InitialContext();
			dataSource = (DataSource)initCtx.lookup("java:comp/env/dbcp_myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 검색 조건에 맞는 개시물수 계산
	public int getSearchMonCount(Map<String, Object> map) {
		int Count = 0;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT COUNT(*) FROM mon ";
			if(map.get("searchWord") != null) {
				sql += "WHERE " + map.get("searchField") + " "
						+ " LIKE '%" + map.get("searchWord") + "%'";
			}
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Count = rs.getInt(1);
		}catch (SQLException e) {
			System.out.println("나타낼 목록 수 계산중 오류 발생");
			throw new RuntimeException();
		}
		return Count;
	}

	// 검색 조건에 맞는 페이징
	public List<MonVo> getSearchMonsByPage(Map<String, Object> map, int page) {
		List<MonVo> monList = new ArrayList<>();
		int start = (page-1) * 10 + 1;
		Connection con = null;
		try {
			con = dataSource.getConnection(); // Connection 객체 생성
			String sql = "SELECT * FROM ( "
					+ "SELECT Tb.*, ROWNUM rNUM FROM ( "
					+ "SELECT * FROM MON ";				
			if(map.get("searchWord") != null) {
				sql += " WHERE " + map.get("searchField")
				+ " LIKE '%" + map.get("searchWord") + "%' ";
			}
			sql += " ORDER BY monid ASC "
					+  ") Tb "
					+  ") "
					+  "WHERE rNum BETWEEN ? AND ?";
			PreparedStatement stmt = con.prepareStatement(sql); // Statement 생성
			stmt.setInt(1, start);
			stmt.setInt(2, start+9);
			ResultSet rs = stmt.executeQuery(); // 쿼리 실행
			while(rs.next()) {
				MonVo mon = new MonVo();           
				mon.setMonId(rs.getInt("monid"));    
				mon.setMonName(rs.getString("monName"));
				mon.setMonLevel(rs.getInt("monLevel"));
				mon.setRegionList(rs.getString("regionList"));
				mon.setTypeList(rs.getString("typeList"));
				mon.setStarList(rs.getInt("starList"));
				mon.setDifficultyList(rs.getString("difficultyList"));
				monList.add(mon);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return monList;
	}

	// 모든 몬스터 목록 출력 코드
	public List<MonVo> getAllMons(){
		List<MonVo> monList = new ArrayList<>();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from MON "
					+ "order by monid";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				MonVo mon = new MonVo();
				mon.setMonId(rs.getInt("monid"));
				mon.setRegionList(rs.getString("regionlist"));
				mon.setMonLevel(rs.getInt("monlevel"));
				mon.setTypeList(rs.getString("typelist"));
				mon.setMonName(rs.getString("monname"));
				mon.setStarList(rs.getInt("starlist"));
				mon.setDifficultyList(rs.getString("difficultylist"));
				monList.add(mon);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return monList;
	}
	// 몬스터의 상세 정보 출력
	public MonVo getMonDetails(int monid) {
		MonVo mon = new MonVo();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select monId, monName, monLevel, regionList, typeList, starList, difficultyList "
					+"from mon where monId=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, monid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				mon.setMonId(rs.getInt("monid"));
				mon.setMonName(rs.getString("monName"));
				mon.setMonLevel(rs.getInt("monLevel"));
				mon.setRegionList(rs.getString("regionList"));
				mon.setTypeList(rs.getString("typeList"));
				mon.setStarList(rs.getInt("starList"));
				mon.setDifficultyList(rs.getString("difficultyList"));
			}else {
				mon = null;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return mon;
	}

	// 지역 리스트
	public List<Map<String, Object>> getregionList(){
		List<Map<String, Object>> rList = new ArrayList<Map<String, Object>>();
		Connection con = null;
		try {
			con= dataSource.getConnection();
			String sql = "select distinct regionlist from mon "
					+ "order by regionlist";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String regionEntry = rs.getString("regionlist");
				Map<String, Object> rg = new HashMap<String, Object>();
				rg.put("region", regionEntry);
				rList.add(rg);
			}
		}catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return rList;
	}
	//타입 리스트
	public List<Map<String, Object>> gettypeList(){
		List<Map<String, Object>> tlist = new ArrayList<Map<String, Object>>();
		Connection con = null;
		try {
			con= dataSource.getConnection();
			String sql = "select distinct typelist from mon "
					+ "order by typelist";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String typeEntry = rs.getString("typelist");
				Map<String, Object> tp = new HashMap<String, Object>();
				tp.put("type", typeEntry);
				tlist.add(tp);
			}
		}catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			if(con!=null) try { con.close(); } catch(Exception e) {}
		}
		return tlist;
	}	
	// 별 리스트
	public List<Map<String, Object>> getstarList(){  //getstarList를 쓰면 monlist에 들어감, getstarList는 함수
		List<Map<String, Object>> sList = new ArrayList<Map<String,Object>>(); 
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select distinct starlist from mon "
					+ "order by starlist"; // sql에선 소문자로
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String starEntry = rs.getString("starlist");
				Map<String, Object> st = new HashMap<String, Object>();
				st.put("star", starEntry);
				sList.add(st); //스트링은 add를 못받음, monlist는 mon을 add해서 Map 스트링, 오브젝트로 들어감
				// 빈 배열에 add를 해서 값을 넣은 것 = monlist
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return sList;
	}
	// 난이도 리스트
	public List<Map<String, Object>> getdifficultyList(){ 
		List<Map<String, Object>> dList = new ArrayList<Map<String,Object>>(); 
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "select distinct difficultylist from mon "
					+ "order by difficultylist"; 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String difficultyEntry = rs.getString("difficultylist");
				Map<String, Object> df = new HashMap<String, Object>();
				df.put("difficulty", difficultyEntry);
				dList.add(df); 
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return dList;
	}

	// 데이터베이스에 정보 입력한것 등록하기
	public void InsertMon(MonVo mon) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql ="insert into mon (monid, regionlist, monlevel, "
					+ "typelist, monname, starlist, difficultylist) "
					+ "values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, mon.getMonId());
			stmt.setString(2, mon.getRegionList());
			stmt.setInt(3, mon.getMonLevel());
			stmt.setString(4, mon.getTypeList());
			stmt.setString(5, mon.getMonName());	
			stmt.setInt(6, mon.getStarList());
			stmt.setString(7, mon.getDifficultyList());
			stmt.executeUpdate();
			System.out.println("몬스터가 생성되었습니다.");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(con!=null) try { con.close(); } catch (Exception e) {}
		}
	}

	//업데이트
	public void updateMon(MonVo mon) {
		Connection con = null; 
		try {
			con=dataSource.getConnection();
			String sql = "update mon set  regionlist=?, monlevel=?, "
					+ "typelist=?, monname=?, starlist=?, difficultylist=? " 
					+ "where monid=? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,mon.getRegionList());
			stmt.setInt(2,mon.getMonLevel());
			stmt.setString(3,mon.getTypeList());
			stmt.setString(4,mon.getMonName());
			stmt.setInt(5,mon.getStarList());
			stmt.setString(6,mon.getDifficultyList());
			stmt.setInt(7, mon.getMonId());
			stmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
	}

	// 삭제
	public void deleteMon(int monid, String monname) {
		Connection con = null;
		try {
			con = dataSource.getConnection();

			String sql = "delete mon where monid=? and monname=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, monid);
			stmt.setString(2, monname);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(con!=null) try { con.close(); } catch (Exception e) {}
		}
	}
}