package frame.login;
//운동기록지 윤선호, 기타 기능 : (228줄) 김지웅
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
import java.util.Random;

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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import frame.board.BoardEdit;
import frame.db.DB;
import frame.main.MainFrame;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class MyRoutine extends JFrame implements WindowListener, ActionListener{
	private JButton btn_Insert, btn_Delete, btn_Exit;
	private String[] title = {"운동 날짜", "운동이름", "무게", "운동시간", "횟수", "세트수"};
	private String[][]datas = new String[0][5];
	private DefaultTableModel mr_model = new DefaultTableModel(datas, title);
	private JTable ex_table = new JTable(mr_model);
	
	private JPanel panelUP,panelUPUP , panelUPDown;
	private JLabel lblTilte;
	private JTextField tfrand;
	private JButton btnsearch, btnWrite;
	private Color skyblue;

	private MainFrame mf;
	private JPanel bdpanel;
	private ArrayList<String> mr_al;
	private TableModel data;
	private BoardEdit be;
	private String pre_i, id , name;
	private DB db = new DB(null, null);
	private JScrollPane sc;
	private JDatePickerImpl datePicker;
	private JButton datebtn;
	private JDatePanelImpl datePanel;
	private UtilDateModel model;
	private JButton all_btn;

	public DefaultTableModel getModel() {
		return mr_model;
	}

	public JTable getTable() {
		return ex_table;
	}

	public MyRoutine(String id, String name) {
		this.id = id;
		this.name = name;
		skyblue = new Color(189, 215, 238);
		
		setTitle("내가 한 운동");
		setLocation(300, 300);
		setSize(600, 560);
		setResizable(false);
		setLayout(new BorderLayout());
		
		addWindowListener(this);
		PanelUP();
		PanelDowm();
		
		db.EXdisplay(this, id);
		setVisible(true);
	}

	private void PanelUP() {
		panelUP = new JPanel();
		panelUP.setLayout(new GridLayout(2,1));
		
		panelUPUP = new JPanel();
		panelUPUP.setBackground(skyblue);
		panelUPUP.setLayout(null);
		panelUPUP.setPreferredSize(new DimensionUIResource(100,50));
		lblTilte = new JLabel("내가 한 운동");
		lblTilte.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 25));
		lblTilte.setLocation(20, 20);
		lblTilte.setSize(150, 30);
		panelUPUP.add(lblTilte);
		
		panelUP.add(panelUPUP);
		
		panelUPDown = new JPanel();
		panelUPDown.setLayout(null);
		panelUPDown.setPreferredSize(new DimensionUIResource(100,50));
		panelUPDown.setBackground(skyblue);
		
		//String random[] = {"오늘 들 무게를 내일로 미루지 마라.", "일어나 하체 해야지", "이거 못들면 죽는거야", 
				//"복근 없으면 치팅데이는 없다", "바다갈 준비 안할거야?", "Light Weight", 
				//"너도 3대 500 될 수 있어", "너가 먹은 치킨이 불쌍하지 않아?", "가벼운 무게도 무겁게"};
		
		//Random rd = new Random();
		//검색 텍스트 필드 출력
		//tfrand = new JTextField(random[rd.nextInt(8)]);
		
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(25, 10, 200, 25);
		datePicker.setBackground(skyblue);
		datePicker.addActionListener(this);
		
		datebtn = new JButton("날짜");
		datebtn.setBounds(250, 10, 85, 25);
		datebtn.addActionListener(this);
		
		all_btn = new JButton("모든기록");
		all_btn.setBounds(350, 10, 85, 25);
		all_btn.addActionListener(this);
		
		panelUPDown.add(datePicker);
		panelUPDown.add(datebtn);
		panelUPDown.add(all_btn);
		
		//tfrand = new JTextField();
		//tfrand.setBounds(25, 10, 450, 25);
		//tfrand.setEditable(false);
		//tfrand.setBorder(BorderFactory.createEmptyBorder());
		//tfrand.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 20));
		//tfrand.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		//tfrand.addActionListener(this);
		
		//ImageIcon imgtfrand = new ImageIcon("imges/tfrand.png");
		//JLabel lbltfrand = new JLabel();
		//lbltfrand.setBounds(8, 2, 500, 40);
		
		//panelUPDown.add(lbltfrand);
		//panelUPDown.add(tfrand);
		panelUP.add(panelUPDown);
		
		add(panelUP,BorderLayout.NORTH);
	}

	private void PanelDowm() {
		bdpanel = new JPanel();
		
		//테이블 크기 조절
		ex_table.getColumnModel().getColumn(0).setPreferredWidth(100);
		ex_table.getColumnModel().getColumn(1).setPreferredWidth(150);
		ex_table.getColumnModel().getColumn(2).setPreferredWidth(50);
		ex_table.getColumnModel().getColumn(3).setPreferredWidth(80);
		ex_table.getColumnModel().getColumn(4).setPreferredWidth(50);
		ex_table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		//table.setEnabled(true);
		
		sc = new JScrollPane(ex_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ex_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 단일 선택
		ex_table.setEnabled(false);
		
		bdpanel.add(sc);
		
		//테이블 가운데 정렬
		DefaultTableCellRenderer tscheduleCellRenderer = new DefaultTableCellRenderer();
		tscheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		//테이블의 columnmodel을 가져옴
		TableColumnModel tcmSchedule = ex_table.getColumnModel();
		
		//테이블을 가운데 정렬로 지정
		for(int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tscheduleCellRenderer);
		}
		
		add(bdpanel, BorderLayout.CENTER);
		add(sc, BorderLayout.SOUTH);
	}


	public ArrayList<String> getAl() {
		return mr_al;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		//0609 김지웅 메인프레임 연결
		MainFrame mf = new MainFrame(id);
		mf.setLocationRelativeTo(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == datebtn) {
			System.out.println(datePicker.getJFormattedTextField().getText());
			String date = datePicker.getJFormattedTextField().getText();
			db.ExDate(this, id, date);
			
		}else if(obj == all_btn) {
			db.EXdisplay(this, id);
			
		}
	}
}
