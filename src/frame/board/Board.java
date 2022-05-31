package frame.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Member;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
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
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import frame.db.DB;
import frame.main.MainFrame;


public class Board extends JFrame implements ActionListener, MouseListener, WindowListener{
	private JButton btn_Insert, btn_Delete, btn_Exit;
	private String[] title = {"글번호", "제목", "작성자", "작성날짜", "내용", "분류"};
	private String[][]datas = new String[0][5];
	private DefaultTableModel model = new DefaultTableModel(datas, title);
	private JTable table = new JTable(model);
	
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
	private Color skyblue;
	private JScrollPane sp;
	private MainFrame mf;
	private JPanel bdpanel;
	private ArrayList<String> al;
	private TableModel data;
	private BoardEdit be;
	private String pre_i;
	private DB db;
	private String id;
	
	public Board(String id) {
		this.id = id;
		skyblue = new Color(189, 215, 238);
		
		setTitle("자유게시판");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(600, 600);
		setResizable(false); // 사이즈 조절 못하게함
		setLayout(new BorderLayout());
		
		addWindowListener(this);
		PanelUP();
		PanelDowm();
		
		displayData();
		setVisible(true);
	}

	private void PanelUP() {
		panelUP = new JPanel();
		panelUP.setLayout(new GridLayout(2,1));
		
		panel1 = new JPanel();
		panel1.setBackground(skyblue);
		panel1.setLayout(null);
		panel1.setPreferredSize(new DimensionUIResource(100,50));
		lblTilte = new JLabel("자유게시판");
		lblTilte.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 25));
		lblTilte.setLocation(20, 20);
		lblTilte.setSize(150, 30);
		panel1.add(lblTilte);
		
		//글쓰기 버튼 출력
		btnWrite = new JButton(new ImageIcon("imges/pencil1.png"));
		//btnWrite.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
		btnWrite.setBorderPainted(false);
		btnWrite.setContentAreaFilled(false);
		btnWrite.setFocusPainted(false);
		btnWrite.setLocation(300,20);
		btnWrite.setSize(130, 30);
		
		btnWrite.addActionListener(this);
		panel1.add(btnWrite);
		
		panelUP.add(panel1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setPreferredSize(new DimensionUIResource(100,50));
		panel2.setBackground(skyblue);
		
		//검색 텍스트 필드 출력
		tfsearch = new JTextField(30);
		tfsearch.setBounds(20, 10, 300, 25);
		tfsearch.setBorder(BorderFactory.createEmptyBorder());
		tfsearch.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
		tfsearch.addActionListener(this);
		
		ImageIcon imgtfsearch = new ImageIcon("imges/textimage_edit.png");
		JLabel lbltfsearch = new JLabel(imgtfsearch);
		lbltfsearch.setBounds(8, 2, 330, 40);
		
		panel2.add(lbltfsearch);
		panel2.add(tfsearch);
		
		
		//검색 버튼 출력
		btnsearch = new JButton(new ImageIcon("imges/btnsearch2.png"));
		btnsearch.setBounds(325, 10, 70, 25);
		btnsearch.setBorderPainted(false);
		btnsearch.setContentAreaFilled(false);
		btnsearch.setFocusPainted(false);
		btnsearch.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
		btnsearch.addActionListener(this);
		
		panel2.add(btnsearch);
		panelUP.add(panel2);
		
		add(panelUP,BorderLayout.NORTH);
	}

	private void PanelDowm() {
		bdpanel = new JPanel();
		
		//테이블 크기 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		JScrollPane ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일 선택
		table.addMouseListener(this);
		
		bdpanel.add(ScrollPane);
		add(bdpanel, BorderLayout.CENTER);
		add(ScrollPane, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//글쓰기 버튼 누를 시 자유게시판 글쓰기 창이 뜬다.
		if(obj == btnWrite) {
		Boardwrite be = new Boardwrite("글쓰기", id);
		this.dispose();
		
		//게시물 검색 기능
		}else if(obj == tfsearch || obj == btnsearch) {}
		String src = tfsearch.getText();
		System.out.println(src);
		scDisplay(src);
		//db = new DB(null);
		
	}
	//2022-05-28 내용으로 글 검색하면 보여준다
	public void scDisplay(String src) {
		model.setNumRows(0);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"barbelljava",
					"kkt1004");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from freetalk where BD_CONTENT LIKE '%' || '" +  src + "' || '%' order by BD_ID desc");
			
			while(rs.next()) {
				String[] sch = {rs.getString("BD_ID"), rs.getString("BD_TITLE"), rs.getString("BD_WRITER"), rs.getString("WRITE_DAY"), rs.getString("BD_CONTENT"), rs.getString("CATEGORY")};
				model.addRow(sch);
				}
			
			}catch (ClassNotFoundException e) {
				System.out.println("예외발생 : 해당 드라이버가 없습니다.");
				e.printStackTrace();
				
			} catch(SQLException e) {
				System.out.println("예외발생 : 접속 정보 확인 필요");
				e.printStackTrace();
				
			} finally {
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
		model.setNumRows(0);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"barbelljava",
					"kkt1004");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from freetalk order by BD_ID desc");
			
			while(rs.next()) {
				String[] imsi = {rs.getString("BD_ID"), rs.getString("BD_TITLE"), rs.getString("BD_WRITER"), rs.getString("WRITE_DAY"), rs.getString("BD_CONTENT"),rs.getString("CATEGORY")};
				model.addRow(imsi);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//2022-05-26 201945012 윤선호 게시물 클릭시 게시물 내용 보여줌
	@Override
	public void mousePressed(MouseEvent e) {
		//게시물 누르면 이거 다 긁어옴
		//BoardEdit를 실행
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		
		data = table.getModel();
		
		pre_i = (String)data.getValueAt(row, 0);
		//System.out.println(pre_i);
		String pre_title = (String)data.getValueAt(row, 1);
		String pre_writer = (String)data.getValueAt(row, 2);
		String pre_writeday = (String)data.getValueAt(row, 3);
		String pre_category = (String)data.getValueAt(row, 5);
		String pre_content = (String)data.getValueAt(row, 4);
		
		al = new ArrayList<>();
		al.add(pre_i);
		al.add(pre_title);
		al.add(pre_writer);
		al.add(pre_writeday);
		al.add(pre_category);
		al.add(pre_content);
		
		BoardEdit be = new BoardEdit(al, id);
		
		DB db = new DB(be, null);
		db.DisplayCMT(pre_i);
		this.dispose();
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
		return al;
	}

	//2022-05-28 윤선호 자유게시판 창 뜨면 검색필드 활성화
	@Override
	public void windowOpened(WindowEvent e) {
		tfsearch.requestFocus();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MainFrame mf = new MainFrame(id);
		this.dispose();
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
}
