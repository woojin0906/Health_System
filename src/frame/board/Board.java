package frame.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import frame.main.MainFrame;


public class Board extends JFrame implements ActionListener{
	private JButton btn_Insert, btn_Delete, btn_Exit;
	private String[][]datas = new String[0][7];
	private String[] title = {"글번호", "제목", "작성자", "작성날짜", "내용", "분류","비고"};
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
	private Color skyblue;
	private JScrollPane sp;
	private MainFrame mf;
	private JPanel bdpanel;

	public Board(MainFrame mf) {
		skyblue = new Color(189, 215, 238);
		setTitle("자유게시판");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(600, 600);
		//setResizable(false); // 사이즈 조절 못하게함
		setLayout(new BorderLayout());
		//addWindowListener(this);
		PanelUP();
		PanelDowm();
		
		displayData();
		setVisible(true);
	}
	
	public Board(String pre_title, String pre_writer, String pre_writeday, String pre_content) {
		// TODO Auto-generated constructor stub
	}

	public class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer, ActionListener {
		JButton btn_modify;
		private ArrayList<String> al;
		
		public TableCell() {
			btn_modify = new JButton("수정");
			btn_modify.addActionListener(this);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return btn_modify;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return btn_modify;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj == btn_modify) {
				//선택한 셀의 행 번호 가져오기
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
				//테이블의 모델 객체 받아온다
				TableModel data = table.getModel();
				//제목, 작성자, 작성날짜, 내용, 분류
				String pre_i = (String)data.getValueAt(row, 0);
				String pre_title = (String)data.getValueAt(row, 1);
				String pre_writer = (String)data.getValueAt(row, 2);
				String pre_writeday = (String)data.getValueAt(row, 3);
				String pre_content = (String)data.getValueAt(row, 4);
				
				
				System.out.println(pre_i);
				System.out.println(Integer.parseInt((pre_i).toString()) + 2);
				System.out.println(pre_title);
				System.out.println(pre_writer);
				System.out.println(pre_writeday);
				System.out.println(pre_content);
				
				al = new ArrayList<>();
				al.add(pre_i);
				al.add(pre_title);
				al.add(pre_writer);
				al.add(pre_writeday);
				al.add(pre_content);
				
				Boardwrite2 bd2 = new Boardwrite2(al);
			}
		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}
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
		
		panel2.add(btnsearch);
		panelUP.add(panel2);
		
		add(panelUP,BorderLayout.NORTH);
		
		
	}

	private void PanelDowm() {
		//ta = new JTextArea(40,40);
		//sp = new JScrollPane(ta, 
				//JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				//JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//ta.setLineWrap(true);
		//ta.setEditable(false);
		//add(sp,BorderLayout.CENTER);
		
		bdpanel = new JPanel();
		add(bdpanel, BorderLayout.CENTER);
		
		//테이블 크기 조절
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		JScrollPane ScrollPane = new JScrollPane(table);
		//DefaultTableCellRenderer render = new MyDefaultTableCellRender();
		table.getColumnModel().getColumn(6).setCellRenderer(new TableCell());
		table.getColumnModel().getColumn(6).setCellEditor(new TableCell());
		add(ScrollPane, BorderLayout.SOUTH);
		//add(lbl_Count, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//글쓰기 버튼 누를 시 자유게시판 글쓰기 창이 뜬다.
		if(obj == btnWrite) {
		Boardwrite be = new Boardwrite("글쓰기");
		this.dispose();
		}
	}
	
	public void displayData() {
		model.setNumRows(0);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"barbelljava",
					"kkt1004");
			
			stmt = conn.createStatement();
			//stmt = conn.prepareStatement("select * from freetalk order by code desc");
			rs = stmt.executeQuery("select * from freetalk order by BD_ID desc");
			int count = 0;
			
			while(rs.next()) {
				String[] imsi = {rs.getString("BD_ID"), rs.getString("BD_TITLE"), rs.getString("BD_WRITER"), rs.getString("WRITE_DAY"), rs.getString("BD_CONTENT"), rs.getString("CATEGORY")};
			model.addRow(imsi);
			count++;
			}
			
			//lbl_Count.setText("게시물 수 : " + count);
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
}
