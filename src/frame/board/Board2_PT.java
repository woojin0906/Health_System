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
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import frame.db.DBPT;
import frame.login.QuestionPW;
import frame.main.MainFrame;

public class Board2_PT extends JFrame implements ActionListener, MouseListener, WindowListener{
	
	private String[][]datas = new String[0][7];
	private String[] title = {"글번호", "제목", "작성자", "작성날짜", "내용"};
	private DefaultTableModel model = new DefaultTableModel(datas, title);
	private JTable table = new JTable(model);
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private JPanel panelUP, panelUPUP, panelUPDown, bdpanel;
	private JLabel lblTilte;
	private JTextField tfsearch;
	private JButton btnsearch ,btnWrite;
	private JScrollPane sptable;
	private Color skyblue;
	private TableModel data;
	private String pre_ipt, pre_titlept, pre_writerpt,pre_writedaypt,pre_contentpt,ID, bd_ID ,namept;
	private ArrayList<String> alpt;
	private JButton btnRefresh;

	public Board2_PT(MainFrame mf ,String namept) {//수정||삭제 -> pt게시판
		this.namept = namept;
		
		skyblue = new Color(189, 215, 238);
		setTitle("PT 게시판");
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(470, 560);
		setLayout(new BorderLayout());
														 //문제		
		PanelUP();                                       //테이블 누르고 버튼 나오는 거 할때 게시판 못누르게 모달창
														 //수정/삭제 누르고 나오는 게시판 닫기 버튼 누르면 아예 다꺼짐 
														 //edit창 넘어갈때 게시물 안없어짐
		PanelDowm();
		addWindowListener(this);
		displayData();
		setVisible(true);
	}
	
	public Board2_PT(MainFrame mf, String ID, String namept) {//메인->PT했을때 나오는 창
		this.ID = ID;
		this.namept = namept;
		skyblue = new Color(189, 215, 238);
		setTitle("PT 게시판");
		setResizable(false); 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(470, 560);
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
		
		panelUPUP = new JPanel();
		panelUPUP.setLayout(null);
		panelUPUP.setPreferredSize(new DimensionUIResource(100,50));
		panelUPUP.setBackground(skyblue);
		lblTilte = new JLabel("PT게시판");
		lblTilte.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 25));
		lblTilte.setLocation(20, 20);
		lblTilte.setSize(150, 30);
		panelUPUP.add(lblTilte);
		
		
		//리프레쉬 버튼
		btnRefresh = new JButton("리프레쉬");//기능 아직 적용안함
		btnRefresh.addActionListener(this);
		btnRefresh.setBounds(300, 20, 100, 30);
		panelUPUP.add(btnRefresh);
		
		
		//글쓰기 버튼
		btnWrite = new JButton(new ImageIcon("imges/pencil1.png"));
		//btnWrite.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
		btnWrite.setBorderPainted(false);
		btnWrite.setContentAreaFilled(false);
		btnWrite.setFocusPainted(false);
		btnWrite.setLocation(355, 20);
		btnWrite.setSize(130, 24);
		
		btnWrite.addActionListener(this);
		panelUPUP.add(btnWrite);
		
		panelUP.add(panelUPUP);
		
		panelUPDown = new JPanel();
		panelUPDown.setLayout(null);
		panelUPDown.setPreferredSize(new DimensionUIResource(100,50));
		panelUPDown.setBackground(skyblue);
		
		//검색필드
		tfsearch = new JTextField(30);
		tfsearch.setBounds(25, 10, 335, 25);
		tfsearch.setBorder(BorderFactory.createEmptyBorder());
		tfsearch.addActionListener(this);
		tfsearch.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
		
		ImageIcon imgtfsearch = new ImageIcon("imges/Testsearch.png");
		JLabel lbltfsearch = new JLabel(imgtfsearch);
		lbltfsearch.setBounds(10, 2, 370, 40);
		
		panelUPDown.add(lbltfsearch);
		panelUPDown.add(tfsearch);
		
		//검색버튼
		btnsearch = new JButton(new ImageIcon("imges/btnsearch2.png"));
		btnsearch.setBounds(375, 10, 70, 25);
		btnsearch.setBorderPainted(false);
		btnsearch.setContentAreaFilled(false);
		btnsearch.setFocusPainted(false);
		btnsearch.addActionListener(this);
		btnsearch.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
		panelUPDown.add(btnsearch);
		
		panelUP.add(panelUPDown);
		
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

		sptable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일 선택
		
		//DafaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
		
		//DfaultTableCellHeaderRender의 정렬을 가운데 정렬로 지정
		tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		//정렬할 테이블의 ColumnModel 을 가져옴
		TableColumnModel tableColumnModel = table.getColumnModel();
		
		//반복문을 이용하여 테이블 가운데 정렬로 지정
		for (int i = 0; i < tableColumnModel.getColumnCount(); i++) {
			tableColumnModel.getColumn(i).setCellRenderer(tableCellRenderer);
		}

		table.addMouseListener(this);

		add(sptable, BorderLayout.SOUTH);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//글쓰기 버튼 누를 시 pt게시판 글쓰기 창이 뜬다.
		if(obj == btnWrite) {
		BoardWrite_PT be = new BoardWrite_PT("글쓰기", ID,namept);
		this.dispose();
		}
		else if(obj == tfsearch || obj == btnsearch) {
	      String src = tfsearch.getText();
	      System.out.println(src);
	      scDisplay(src);
		}else if(obj == btnRefresh){
			
		}

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
	
	public String getPre_ipt() {
		return pre_ipt;
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
		dispose();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		MainFrame mf = new MainFrame(null);
		
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