package tae.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tae.member.dto.MemberDTO;
import tae.member.service.MemberService;

public class MemberDAO implements MemberService {
	private static final Log log = LogFactory.getLog(MemberDAO.class);

	@Override
	public ArrayList<MemberDTO> memberSelectAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select umail, upw, uname, to_char(joinday, 'yyyy/mm/dd')joinday from member";
			log.info("SQL확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setUmail(resultSet.getString("umail"));
				memberDTO.setUpw(resultSet.getString("upw"));
				memberDTO.setUname(resultSet.getString("uname"));
				memberDTO.setJoinday(resultSet.getString("joinday"));
				arrayList.add(memberDTO);
				log.info("조회 데이터 확인- " + memberDTO);
			}
			resultSet.getRow();
			if (resultSet.getRow() == 0) {
				log.info("등록한 회원이 없습니다.");
			}
		} catch (Exception e) {
			log.info("전체 회원 조회 실패- " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}

	@Override
	public MemberDTO memberSelect(String umail) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = new MemberDTO();

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select * from member ";
			sql += "where umail = ? ";
			log.info("SQL확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, umail);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString("umail").equals(umail)) {
					log.info("이메일 확인 - " + resultSet.getString("umail"));
					memberDTO.setUmail(resultSet.getString("umail"));
					memberDTO.setUpw(resultSet.getString("upw"));
					memberDTO.setUname(resultSet.getString("uname"));
					memberDTO.setJoinday(resultSet.getString("joinday"));
//					log.info("조회 데이터 확인 - " + memberDTO);
				}
			}
		} catch (Exception e) {
			log.info("회원 조회 실패- " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return memberDTO;
	}

	@Override
	public MemberDTO memberInsert(MemberDTO memberDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "insert into member(umail, upw, uname, joinday)";
			sql += " values (?, ?, ?, sysdate) ";
			log.info("SQL 확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getUmail());
			preparedStatement.setString(2, memberDTO.getUpw());
			preparedStatement.setString(3, memberDTO.getUname());
			int count = preparedStatement.executeUpdate();
			log.info("입력 데이터 확인- " + memberDTO);
			
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (Exception e) {
			log.info("입력 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}

	@Override
	public MemberDTO memberUpdate(MemberDTO memberDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "update member set upw = ?, uname = ? where umail = ?";
			log.info("SQL 확인- " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getUpw());
			preparedStatement.setString(2, memberDTO.getUname());
			preparedStatement.setString(3, memberDTO.getUmail());
			int count = preparedStatement.executeUpdate();
//			log.info("수정 데이터 확인 - " + memberDTO);
			if (count > 0) {
				connection.commit();
				log.info("커밋되었습니다");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (Exception e) {
			log.info("회원 수정 실패- " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}

	@Override
	public MemberDTO memberDelete(String umail, String upw) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
//		MemberDTO memberDTO = new MemberDTO();
//		memberDTO.setUmail(umail);
//		memberDTO.setUpw(upw);

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "delete from member ";
			sql += " where umail = ? and upw = ? ";
			log.info("SQL 확인- " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, umail);
			preparedStatement.setString(2, upw);

			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				connection.commit();
				log.info("커밋완료.");
			} else {
				connection.rollback();
				log.info("롤백완료.");
			}
		} catch (Exception e) {
			log.info("회원 삭제 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

//	@Override
//	public int memberNcheck(String uname) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		int nCheck = 0;
//
//		try {
//			Context context = new InitialContext();
//			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
//			connection = dataSource.getConnection();
//			String sql = "select * from member ";
//			sql += "where uname=? ";
//			log.info("SQL - " + sql);
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, uname);
//			resultSet = preparedStatement.executeQuery();
//			if (resultSet.next() || uname.equals("")) {
//			nCheck = 1;
//			}else {
//				nCheck = 0;
//			}
//		} catch (Exception e) {
//			log.info("회원 닉네임 체크 실패 - " + e);
//		} finally {
//			try {
//				resultSet.close();
//				preparedStatement.close();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return nCheck;
//	}

	

}
