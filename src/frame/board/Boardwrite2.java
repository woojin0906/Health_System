package frame.board;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import frame.db.DB;
//허유진 글쓰기 변형해서 수정하는 폼
public class Boardwrite2 extends JFrame implements ActionListener, WindowListener{
	
	private Color skyblue;
	private JTextArea ta;
	private JPanel PanelUp;
	private JPanel PanelDown;
	private JPanel PanelCenter;
	
	private JButton btnSend;
	private JLabel lblboardname;
	private JLabel lbltitle;
	private JLabel lblWriter;
	private JLabel lblWriteday;
	private JLabel lblselection;
	
	private JTextField tftitle;
	private JTextField tfWriter;
	private JTextField tfWriteday;
	
	private JPanel panel_title;
	private JPanel panel_titleUP;
	private JPanel panel_titleCenter;
	private JPanel panel_titleDown;
	private JPanel panel_combo;
	
	private Vector<String> vecCombo;
	private JComboBox<String> comboselection;
	
	private JScrollPane sp;
	private Board user;
	private ArrayList<String> al;
	private String id;
	private String name;
	
	public Boardwrite2(ArrayList<String> al, String id, String name) {
		this.al = al;
		this.id = id;
		this.name = name;
		
		setTitle("자유게시판 글 수정");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(440, 580);
		
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		
		PanelUP();
		PanelCenter();
		PanelDown();
		
		setResizable(false);
		addWindowListener(this);
		setVisible(true);
	}

	private void PanelUP() {
		PanelUp = new JPanel();
		PanelUp.setLayout(new BorderLayout());
		
		
		panel_title = new JPanel();
		panel_title.setLayout(new GridLayout(4,1));
		
		panel_titleUP = new JPanel();
		panel_titleUP.setLayout(null);
		panel_titleUP.setPreferredSize(new DimensionUIResource(100, 50));
		panel_titleUP.setBackground(skyblue);
		
		lbltitle = new JLabel("제목");
		lbltitle.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 18));
		lbltitle.setLocation(20, 20);
		lbltitle.setSize(40, 20);
		panel_titleUP.add(lbltitle);
		
		
		tftitle = new JTextField(25);
		tftitle.setLocation(110, 20);
		tftitle.setSize(280, 20);
		tftitle.setText(al.get(1));
		//System.out.println(al.get(1));
		tftitle.setBorder(BorderFactory.createEmptyBorder());
		tftitle.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		
		panel_titleUP.add(tftitle);
		
		// 제목 텍스트 배경
		ImageIcon imgtitle = new ImageIcon("imges/writefield.png");
		JLabel lbltitle = new JLabel(imgtitle);
		lbltitle.setBounds(98, 15, 330, 30);
		
		panel_titleUP.add(lbltitle);
		panel_title.add(panel_titleUP); //제목
		
		panel_titleCenter = new JPanel();
		panel_titleCenter.setLayout(null);
		panel_titleCenter.setPreferredSize(new DimensionUIResource(100, 50));
		panel_titleCenter.setBackground(skyblue);
		
		lblWriteday = new JLabel("작성일자");
		lblWriteday.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 18));
		lblWriteday.setLocation(20, 20);
		lblWriteday.setSize(150, 20);
		panel_titleCenter.add(lblWriteday);
		
		tfWriteday = new JTextField(25);
		tfWriteday.setLocation(110, 20);
		tfWriteday.setSize(280, 20);
		tfWriteday.setText(al.get(3));
		tfWriteday.setBorder(BorderFactory.createEmptyBorder());
		tfWriteday.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panel_titleCenter.add(tfWriteday);
		
		ImageIcon imgwriteday = new ImageIcon("imges/writefield.png");
		JLabel lblwriteday = new JLabel(imgwriteday);
		lblwriteday.setBounds(98, 15, 330, 30);
		panel_titleCenter.add(lblwriteday);
		
		panel_title.add(panel_titleCenter);//작성일자
		
		panel_titleDown = new JPanel();
		panel_titleDown.setLayout(null);
		panel_titleDown.setPreferredSize(new DimensionUIResource(100, 50));
		panel_titleDown.setBackground(skyblue);
		
		lblWriter = new JLabel("작성자");
		lblWriter.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 18));
		lblWriter.setLocation(20, 20);
		lblWriter.setSize(150, 20);
		panel_titleDown.add(lblWriter);
		
		tfWriter = new JTextField(25);
		tfWriter.setLocation(110, 20);
		tfWriter.setSize(280, 20);
		tfWriter.setText(al.get(2));
		tfWriter.setBorder(BorderFactory.createEmptyBorder());
		tfWriter.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panel_titleDown.add(tfWriter);
		
		ImageIcon imgwriter = new ImageIcon("imges/writefield.png");
		JLabel lblwriter = new JLabel(imgwriter);
		lblwriter.setBounds(98, 15, 330, 30);
		panel_titleDown.add(lblwriter);
		
		panel_title.add(panel_titleDown);//작성자
		
		panel_combo = new JPanel();
		panel_combo.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));
		panel_combo.setBackground(skyblue);
		lblselection = new JLabel("카테고리");
		lblselection.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 18));
		panel_combo.add(lblselection);
		
		vecCombo = new Vector<String>();
	    vecCombo.add("운동");
	    vecCombo.add("나눔");
	      
	    comboselection = new JComboBox<String>(vecCombo);
	    comboselection.setPreferredSize(new Dimension(100, 35));
	    comboselection.setBackground(Color.white);
	      
		panel_combo.add(comboselection);//콤보박스
		panel_title.add(panel_combo);

		PanelUp.add(panel_title);
		add(PanelUp, BorderLayout.NORTH);
	}
	
	private void PanelCenter() {
		
		PanelCenter = new JPanel();
		PanelCenter.setBackground(skyblue);
		ta = new JTextArea(16,30);
		ta.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		ta.setLineWrap(true);
		
		sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		ta.setText(al.get(5));
		
		PanelCenter.add(sp);
		add(PanelCenter, BorderLayout.CENTER);
	}
	
	private void PanelDown() {
		PanelDown = new JPanel();
		PanelDown.setBackground(skyblue);
		PanelDown.setLayout(new FlowLayout(FlowLayout.RIGHT,5,0));
		
		btnSend = new JButton(new ImageIcon("imges/edit2.png"));
		btnSend.setBorderPainted(false);
		btnSend.setContentAreaFilled(false);
		btnSend.addActionListener(this);
		
		PanelDown.add(btnSend);
		add(PanelDown, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//2022-05-27 윤선호 수정 버튼 이벤트
		if(obj == btnSend) {
			DB db = new DB(null, null);
			db.BDUpdate(al.get(0),tftitle.getText(), tfWriteday.getText(), tfWriter.getText(), comboselection.getSelectedItem().toString(), ta.getText());
			dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		Board bd = new Board(id, name);
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


