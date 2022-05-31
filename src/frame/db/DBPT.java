package frame.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import frame.board.BoardEdit;
import frame.board.BoardEdit_PT;

public class DBPT {
	//허유진 
	//댓글테이블 수정
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet result = null;
	private int id;
	private BoardEdit_PT be2;
	private ArrayList<Object> list = new ArrayList<Object>();

	public  DBPT(BoardEdit_PT be2) {
		this.be2 = be2;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE", 
					"barbelljava", 
					"kkt1004");
			
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("예외발생 : 해당 드라이버가 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("예외발생 : 접속 정보 확인이 필요합니다.");
			e.printStackTrace();
		}
	}
	
	//허유진 DB insert 
	public void PtInsert(String pttitle, String ptwriteday, String password, String ptcontent, String ptwriter) {
		System.out.println("입력할 데이터 : ");
		//System.out.println(i);
		System.out.println(pttitle);
		System.out.println(ptwriteday);
		System.out.println(password);
		System.out.println(ptcontent);
		System.out.println(ptwriter);
		
		
		try {
			String sqlInsert = "insert into PTTALK (PT_ID, PT_TITLE, PT_WRITEDAY, PT_WRITER, PASSWORD, PT_CONTENT) "
					+ "values(emp_seq.NEXTVAL, '" + pttitle + "', '" + ptwriteday + "', '" + ptwriter + "', '" + password +"', '" + ptcontent +"')";
			stmt.executeUpdate(sqlInsert);
			
			System.out.println("입력 성공");
			
		}catch(SQLException e){
			System.out.println("Insert fail");
			e.printStackTrace();
			
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
			
	}
		
		//pt게시판 id DB에서 불러오기
		public int GetBDID() {
			try {
				//int id;
				result = stmt.executeQuery("select PT_ID from PTTALK");
				if(result.next()) {
					String max = String.valueOf(result.getInt(1));
					id = Integer.parseInt(max);
					//System.out.println("마지막 값 : " + id);
				}
			}catch(SQLException e) {
				System.out.println("select Query Error!");
				e.printStackTrace();
			}
			System.out.println(id + "======================");
			return id ;
		}
		
		//pt게시판 글 수정
		public void BDUpdate(String id, String pttitle, String ptwriteday, String ptwriter, String password, String ptcontent ) {
			try {
				String sqlUpdate = "update pttalk SET PT_TITLE = '" + pttitle +"', PT_WRITEDAY = '" + ptwriteday + "', PT_WRITER = '" + ptwriter + "', PASSWORD = '" + password + "', PT_CONTENT = '" + ptcontent + "' where PT_ID = '" + Integer.parseInt(id) +"'";
				stmt.executeUpdate(sqlUpdate);
				
				System.out.println("수정 성공!");
				
			}catch(SQLException e){
				System.out.println("수정 실패");
				e.printStackTrace();
				
			}finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		}
		//pt게시판 댓글 추가
		public void BDCMT(String id, String ptcontent) {
			
			try {
				Random rnd = new Random();
				//글번호의 최대값보다 큰 수부터 랜덤값 가져오게 바꾸자
				System.out.println(rnd.nextInt(99999 -10000 + 1) + 10000);
				System.out.println(id);
				String cmt = ptcontent;
				System.out.println(cmt);
			String sqlInsert = "insert into PT_COMMENT values('" + rnd.nextInt(99999 - 10000 + 1) + 10000 + "', '" + ptcontent + "', '" + id + "')";
			stmt.executeUpdate(sqlInsert);
			
			System.out.println("댓글 추가 성공");
			
		}catch(SQLException e){
			System.out.println("댓글 추가 실패");
			e.printStackTrace();
			
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
		//pt게시판 댓글 보여주기
		public void DisplayCMT(String id) {
			try {
				
				result = stmt.executeQuery("select \"COMMENTPT\" FROM PT_COMMENT where PT_ID = '" + Integer.parseInt(id) + "'");		
				while(result.next()) {
					String test = result.getString(1);
					be2.getTa2().append(test + "\n");
					System.out.println(result.getString(1));
					System.out.println("댓글 보여주기 성공");
					
				}
			} catch (SQLException e) {
				//System.out.println("댓글 못 불러옵니다.");
				//e.printStackTrace();
			}finally {
				try {
					//stmt.close();
					//result.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		// 테이블 삭제
		public void PT_DELETE(String id ) {
			try {
				String sqldeletechild = " delete from PT_comment where PT_ID = '"+ Integer.parseInt(id) +"'";
				String sqldelete = " delete from PTTALK where PT_ID = '"+ Integer.parseInt(id) +"'";
				stmt.executeQuery(sqldeletechild);
				stmt.executeQuery(sqldelete);
				
				
				System.out.println("삭제 성공");
			} catch (SQLException e) {
				System.out.println("삭제 실패");
				e.printStackTrace();
			}finally {
				try {
					stmt.close();
					//result.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void boardPwCheck() {
			try {
				
			} catch (Exception e) {
			}
		}

	}
