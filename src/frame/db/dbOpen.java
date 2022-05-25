package frame.db;

//작성자: 김지웅
//DB 연결 및 기타 메소드

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import frame.login.JoinFrame;
import frame.login.Login;
import frame.main.MainFrame;

public class dbOpen {

	private java.sql.Statement statement;
	private ResultSet result;
	private Connection conn;
	private String pw; //로그인 시 비밀번호 검증용 변수
	private String hint;
	private MainFrame mainFrame;
	private int period;
	private String db_pw;
	private String db_id;

	public dbOpen() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:XE", 
						"barbelljava", 
						"kkt1004");
				
				statement = conn.createStatement();
				
			} catch (ClassNotFoundException e) {
				System.out.println("예외발생 : 해당 드라이버가 없습니다.");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("예외발생 : 접속 정보 확인이 필요합니다.");
				e.printStackTrace();
			}
			
			
	}
	// 회원가입 할 때 텍스트필드에서 값 긁어와서 DB에 던지기
	public void infoInsert(String id, String name, String ph, String add, String pw, String pwhint) {
		String sqlInsert = "insert into memberinfo (id, name, phonenumber, address, password, passwordhint)"
				+ " values('" + id + "', '" + name + "', '" + ph + "', '" + add + "', '" + pw + "', '" + pwhint + "')";
	
		try {
			statement.executeUpdate(sqlInsert);
		} catch (SQLException e) {
			System.out.println("Insert Error!");
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//로그인 검증, id값 돌려쓰기 위해서 String으로 id 리턴
	public String loginSelect(Login frame, String inputId, String inputPw) {
		String id = inputId;
		
		try {
			result = statement.executeQuery("select password from memberinfo where id = '" + inputId + "'" );
			if(result.next()) {
				pw = result.getString("password");
			}
			
			//입력한 비밀번호와 DB에 저장된 비밀번호가 일치하는지 검증
			
			if(pw.equals(inputPw)) {
				mainFrame = new MainFrame(null);
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(frame, "정보를 다시 확인해주세요.", "정보 오류", JOptionPane.ERROR_MESSAGE);
				
			}
			
		} catch (SQLException e) {
			System.out.println("select Query Error!");
			e.printStackTrace();
		} 
		finally {
			try {
				result.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	//패스워드 초기화하는 힌트값 DB에서 긁어와서 검증하기
	public void resetPW(String id, String inputHint) {
		try {
			result = statement.executeQuery("select passwordhint from memberinfo where id = '" + id + "'" );
		
			if(result.next()) {
				hint = result.getString("passwordhint");
			}
			
			if(inputHint.equals(hint)) {
				String sqlUpdate = "update memberinfo set password = '0000' where id = '" + id + "'";
				statement.executeUpdate(sqlUpdate);
				System.out.println("교체된 비밀번호는 '0000' 입니다. 로그인 후 비밀번호를 변경해 주세요.");
			}
			else {
				System.out.println("입력한 아이디 또는 힌트가 일치하지 않습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				result.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//전달받은 ID에 해당하는 정보를 가져옴
	public void pullInfo(String id, JTextField name, JTextField phonenumber, JTextField address, JPasswordField pw, JPasswordField pwCh) {
		try {
				result = statement.executeQuery("select name from memberinfo where id = '" + id + "'");
				if(result.next()) {
					name.setText(result.getString("name"));
				}
				result = statement.executeQuery("select phonenumber from memberinfo where id = '" + id + "'");
				if(result.next()) {
					phonenumber.setText(result.getString("phonenumber"));
				}
				result = statement.executeQuery("select address from memberinfo where id = '" + id + "'");
				if(result.next()) {
					address.setText(result.getString("address"));
				}
				result = statement.executeQuery("select password from memberinfo where id = '" + id + "'");
				if(result.next()) {
					pw.setText(result.getString("password"));
					pwCh.setText(result.getString("password"));
				}
				
		} catch (SQLException e) {
			System.out.println("pull Informaiton Error!");
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				result.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//회원정보 업데이트
	public void chMemberInfo(String id, JPasswordField pw, JTextField phone, JTextField address) {
		char[] temp = pw.getPassword();
		String result = "";
		
		for(char ch	: temp) {
			Character.toString(ch);
			result += ""+ch+"";
		}
		
		String sqlUpdate = "update memberinfo set password = '" + result + "', phonenumber = '" + phone.getText() + "', address = '" + address.getText() + "' where id = '" + id + "'";
		try {
			statement.executeUpdate(sqlUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//이용권 구매시 이용권 기간 늘림
	public void plusPeriod(String id, int addPeriod) {
		try {
			result = statement.executeQuery("select period from memberinfo where id = '" + id + "'" );
			
			if(result.next()) {
				period = result.getInt("period");
			}
			addPeriod += period;
			
			String sqlUpdate = "update memberinfo set period = '" + addPeriod +  "' where id = '" + id + "'";
			statement.executeUpdate(sqlUpdate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	// 비밀번호 확인 프레임에 사용되는 메소드
	public String checkPW(String id) {
		try {
			result = statement.executeQuery("select password from memberinfo where id = '" + id + "'");
			if(result.next()) {
				db_pw = result.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				result.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return db_pw;
	}
	
	public void checkID(JoinFrame jf, String idInput) {
		try {
			result = statement.executeQuery("select id from memberinfo where id = '" + idInput + "'");
			
			if(result.next()) {
				JOptionPane.showMessageDialog(jf, "이미 사용중인 아이디 입니다.");
			}else {
				JOptionPane.showMessageDialog(jf, "사용 가능한 아이디 입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
