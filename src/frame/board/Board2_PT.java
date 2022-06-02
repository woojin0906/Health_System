package frame.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import frame.db.DBPT;
import frame.login.QuestionPW;
import frame.main.MainFrame;

public class Board2_PT extends JFrame implements ActionListener, MouseListener, WindowListener{
	
	private JButton btn_Insert, btn_Delete, btn_Exit;
	private String[][]datas = new String[0][7];
	private String[] title = {"글번호", "제목", "작성자", "작성날짜", "내용"};
	private DefaultTableModel model = new DefaultTableModel(datas, title);
	private JTable table = new JTable(model);
	private JLabel lbl_Count = new JLabel("게시물 수 : + 0");
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private JPanel panelUP;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel lblTilte;
	private JTextArea ta;
	private JTextField tfsearch;
	private JButton btnsearch;
	private JButton btnWrite;
	private JScrollPane sp;
	private Color skyblue;
	private MainFrame mf;
	private TableModel data;
	private BoardEdit be;
	private String pre_i;
	private JPanel bdpanel;
	private ArrayList<String> alpt;
	private String pre_titlept;
	private String pre_writerpt;
	private String pre_writedaypt;
	private String pre_passwordpt;
	private String pre_contentpt;
	private String pre_ipt;
	private String ID;
	private String bd_ID;

	public Board2_PT(MainFrame mf) {
		skyblue = new Color(189, 215, 238);
		setTitle("PT 게시판");
		//setResizable(false); 
		//addWindowListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(420, 600);
		setLayout(new BorderLayout());
		
		PanelUP();
		PanelDowm();
		addWindowListener(this);
		displayData();
		setVisible(true);
	}
	
	public Board2_PT(MainFrame mf, String ID) {
		this.ID = ID;
		skyblue = new Color(189, 215, 238);
		setTitle("PT 게시판");
		//setResizable(false); 
		//addWindowListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(420, 600);
		setLayout(new BorderLayout());
		
		PanelUP();
		PanelDowm();
		addWindowListener(this);
		displayData();
		setVisible(true);
	}
	
	public Board2_PT(String pre_title, String pre_writer, String pre_writeday, String pre_content) {
		// TODO Auto-generated constructor stub
	}


	private void PanelUP() {
		panelUP = new JPanel();
		panelUP.setLayout(new GridLayout(2,1));
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setPreferredSize(new DimensionUIResource(100,50));
		panel1.setBackground(skyblue);
		lblTilte = new JLabel("PT게시판");
		lblTilte.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 25));
		lblTilte.setLocation(20, 20);
		lblTilte.setSize(150, 30);
		panel1.add(lblTilte);
		
		btnWrite = new JButton(new ImageIcon("imges/pencil1.png"));
		//btnWrite.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
		btnWrite.setBorderPainted(false);
		btnWrite.setContentAreaFilled(false);
		btnWrite.setFocusPainted(false);
		btnWrite.setLocation(300, 20);
		btnWrite.setSize(130, 24);
		
		btnWrite.addActionListener(this);
		panel1.add(btnWrite);
		
		panelUP.add(panel1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setPreferredSize(new DimensionUIResource(100,50));
		panel2.setBackground(skyblue);
		
		//검색필드
		tfsearch = new JTextField(30);
		tfsearch.setBounds(20, 10, 300, 25);
		tfsearch.setBorder(BorderFactory.createEmptyBorder());
		tfsearch.addActionListener(this);
		tfsearch.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
		
		ImageIcon imgtfsearch = new ImageIcon("imges/textimage_edit.png");
		JLabel lbltfsearch = new JLabel(imgtfsearch);
		lbltfsearch.setBounds(8, 2, 330, 40);
		
		panel2.add(lbltfsearch);
		panel2.add(tfsearch);
		
		//검색버튼
		btnsearch = new JButton(new ImageIcon("imges/btnsearch2.png"));
		btnsearch.setBounds(325, 10, 70, 25);
		btnsearch.setBorderPainted(false);
		btnsearch.setContentAreaFilled(false);
		btnsearch.setFocusPainted(false);
		btnsearch.addActionListener(this);
		btnsearch.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
		panel2.add(btnsearch);
		
		panelUP.add(panel2);
		
		add(panelUP,BorderLayout.NORTH);
		
		
	}

	private void PanelDowm() {

		bdpanel = new JPanel();
		add(bdpanel, BorderLayout.CENTER);
		
		//테이블 크기 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
	    table.getColumnModel().getColumn(3).setPreferredWidth(70);
	    table.getColumnModel().getColumn(4).setPreferredWidth(120);

		JScrollPane ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일 선택

		table.addMouseListener(this);

		add(ScrollPane, BorderLayout.SOUTH);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//글쓰기 버튼 누를 시 pt게시판 글쓰기 창이 뜬다.
		if(obj == btnWrite) {
		BoardWrite_PT be = new BoardWrite_PT("글쓰기", ID);
		this.dispose();
		}
		else if(obj == tfsearch || obj == btnsearch) {}
	      String src = tfsearch.getText();
	      System.out.println(src);
	      scDisplay(src);

}
	
	
	public void scDisplay(String src) {
	      model.setNumRows(0);
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         conn = DriverManager.getConnection(
	               "jdbc:oracle:thin:@127.0.0.1:1521:XE",
	               "barbelljava",
	               "inha1004");

	         stmt = conn.createStatement();
	         rs = stmt.executeQuery("select * from pttalk where PT_TITLE LIKE '%' || '" +  src + "' || '%' order by PT_ID desc");
	         
	         while(rs.next()) {
	            String[] sch = {rs.getString("PT_ID"), rs.getString("PT_TITLE"), rs.getString("PT_WRITER"), rs.getString("PT_WRITEDAY"), rs.getString("PT_CONTENT")};
	            model.addRow(sch);
	           
	         }
	         }catch (ClassNotFoundException e) {
	            System.out.println("예외발생 : 해당 드라이버가 없습니다.");
	            e.printStackTrace();
	            
	         } catch(SQLException e) {
	            System.out.println("예외발생 : 접속 정보 확인 필요");
	            e.printStackTrace();
	            
	         }finally {
	            try {
	               if(rs != null)
	                  rs.close();
	               if(stmt != null)
	                  stmt.close();
	               if(conn != null)
	                  conn.close();
	            }catch(Exception e) {
	               e.printStackTrace();
	            }
	         }
	   }

	
	//JTable에 모든 값 보여줌
		public void displayData() {
			model.setNumRows(0);//테이블 초기화 
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:XE",
						"barbelljava",
						"inha1004");
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from PTTALK order by PT_ID desc");
				int count = 0;
				
				while(rs.next()) {
					String[] imsi = {rs.getString("PT_ID"), rs.getString("PT_TITLE"), rs.getString("PT_WRITER"), rs.getString("PT_WRITEDAY"), rs.getString("PT_CONTENT")};
				model.addRow(imsi);
				count++;
				}
				

			} catch (ClassNotFoundException e) {
				System.out.println("예외발생 : 해당 드라이버가 없습니다.");
				e.printStackTrace();
				
			} catch(SQLException e) {
				System.out.println("예외발생 : 접속 정보 확인 필요");
				e.printStackTrace();
				
			}finally {
				try {
					if(rs != null)
						rs.close();
					if(stmt != null)
						stmt.close();
					if(conn != null)
						conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}


		//게시물 누르면 이거 다 긁어옴
		//BoardEdit_PT를 실행
		public void runBoard() {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			
			data = table.getModel();
			
			pre_ipt = (String)data.getValueAt(row, 0);
			//System.out.println(pre_i);
			pre_titlept = (String)data.getValueAt(row, 1);
			pre_writerpt = (String)data.getValueAt(row, 2);
			pre_writedaypt = (String)data.getValueAt(row, 3);
			//pre_passwordpt = (String)data.getValueAt(row, 5);
			pre_contentpt = (String)data.getValueAt(row, 4);
			
			alpt = new ArrayList<String>();
			alpt.add(pre_ipt);
			alpt.add(pre_titlept);
			alpt.add(pre_writerpt);
			alpt.add(pre_writedaypt);
			//alpt.add(pre_passwordpt);
			alpt.add(pre_contentpt);
			bd_ID = alpt.get(0);
			//BoardEdit_PT be2 = new BoardEdit_PT(alpt, ID);
			//DBPT dbpt = new DBPT(be2);
			//dbpt.DisplayCMT(pre_ipt);
			
			//JTable 값 -> boardEdit_PT
		}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//게시물 클릭시 게시물 내용 보여줌
	@Override
	public void mousePressed(MouseEvent e) {
		runBoard();
		QuestionPW pwCheck = new QuestionPW(this, 3, bd_ID);
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getPre_i() {
		return pre_i;
	}

	public ArrayList<String> getAl() {
		return alpt;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		tfsearch.requestFocus();
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String> getAlpt() {
		return alpt;
	}
	
}
