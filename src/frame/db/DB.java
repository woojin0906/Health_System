package frame.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.beans.Statement;
import java.sql.Statement; 

public class DB {
	//private java.sql.Statement stmt;
	private ResultSet result;
	private Connection conn = null;
	private Statement stmt = null;
	private int id;
	public DB() {
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
		} catch(SQLException e) {
			System.out.println("예외발생 : 접속 정보 확인 필요");
			e.printStackTrace();
		}
		
	}

	//자유게시판 글 작성 DB
	public void BDInsert(int i, String title, String writeday, String writer, String category, String bd_contents) {
		System.out.println("입력할 데이터 : ");
		System.out.println(i);
		System.out.println(title);
		System.out.println(writeday);
		System.out.println(writer);
		System.out.println(category);
		System.out.println(bd_contents);
		
	try {
		String sqlInsert = "insert into FREETALK (BD_ID, BD_TITLE, WRITE_DAY, BD_WRITER, category, bd_content) values('" + i + "', '" + title + "', '" + writeday + "', '" + writer + "', '" + category +"', '" + bd_contents +"')";
		stmt.executeUpdate(sqlInsert);
		
		System.out.println("입력 성공");
		
	}catch(SQLException e){
		System.out.println("Insert fail");
		e.printStackTrace();
		
	}finally {
		try {
			stmt.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
}
	
	//자유게시판 id DB에서 불러오기
	public int GetBDID() {
		try {
			//int id;
			result = stmt.executeQuery("select MAX(bd_id) from freetalk");
			if(result.next()) {
				String max = String.valueOf(result.getInt(1));
				id = Integer.parseInt(max);
				//System.out.println("마지막 값 : " + id);
			}
		}catch(SQLException e) {
			System.out.println("select Query Error!");
			e.printStackTrace();
		}//finally {
			//try {
				//result.close();
				//stmt.close();
				//conn.close();
			//} catch (SQLException e) {
				//e.printStackTrace();
			//}
			
		//}
		System.out.println(id + "ddddddddddddddddd");
		return id ;
	}

	//자유게시판 글 수정
	public void BDUpdate(int i, String title, String writeday, String writer, String category, String bd_contents ) {
		try {
			//String sqlInsert = "update FREETALK (BD_ID, BD_TITLE, WRITE_DAY, BD_WRITER, category, bd_content) values('" + i + "', '" + title + "', '" + writeday + "', '" + writer + "', '" + category +"', '" + bd_contents +"')";
			String sqlUpdate = "update FREETALK SET BD_TITLE = '" + title +"', WRITE_DAY = '" + writeday + "', BD_WRITER = '" + writer + "', CATEGORY = '" + category + "', BD_CONTENT = '" + bd_contents + "' where BD_ID = '"+ i +"'";
			stmt.executeUpdate(sqlUpdate);
			
			System.out.println("수정 성공!");
			
		}catch(SQLException e){
			System.out.println("수정 실패");
			e.printStackTrace();
			
		}finally {
			try {
				stmt.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	}
}
