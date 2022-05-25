package frame.board;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Boardwrite extends JFrame{
	
	

	private JPanel PanelUp;
	private JPanel PanelDown;
	private JPanel PanelCenter;
	private JTextArea ta;
	private JButton btnSend;
	private JLabel lblboardname;
	private JLabel lbltitle;
	private JTextField tftitle;
	private JLabel lblWriter;
	private JTextField tfWriter;
	private JLabel lblWriteday;
	private JTextField tfWriteday;
	private JPanel panel1;
	private JPanel panel1UP;
	private JPanel panel1Center;
	private JPanel panel1Down;
	private JPanel panel2;
	private JLabel lblselection;
	private Vector<String> vecCombo;
	private JComboBox<String> comboselection;
	private JPanel panelUpp;
	private Color skyblue;
	private JScrollPane sp;

	public Boardwrite(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(390, 500);
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		PanelUP();
		PanelCenter();
		PanelDown();
		
		
		setVisible(true);
	}

	private void PanelUP() {
		PanelUp = new JPanel();
		PanelUp.setLayout(new BorderLayout());
		
//		panelUpp = new JPanel();
//		panelUpp.setLayout(null);
//		lblboardname = new JLabel("자유게시판");
//		lblboardname.setLocation(10, 20);
//		lblboardname.setSize(40, 20);
//		panelUpp.add(lblboardname);
//		PanelUp.add(panelUpp, BorderLayout.NORTH);
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4,1));
		
		panel1UP=new JPanel();
		panel1UP.setLayout(null);
		panel1UP.setBackground(skyblue);
		
		lbltitle = new JLabel("제목");
		lbltitle.setLocation(10, 20);
		lbltitle.setSize(40, 20);
		panel1UP.add(lbltitle);
		tftitle = new JTextField(25);
		tftitle.setLocation(70, 20);
		tftitle.setSize(300, 25);
		
		panel1UP.add(tftitle);
		panel1.add(panel1UP);//제목
		
		
		
		panel1Center = new JPanel();
		panel1Center.setLayout(null);
		panel1Center.setBackground(skyblue);
		
		lblWriteday = new JLabel("작성일자");
		lblWriteday.setLocation(10, 20);
		lblWriteday.setSize(150, 20);
		panel1Center.add(lblWriteday);
		
		tfWriteday = new JTextField(25);
		tfWriteday.setLocation(70, 20);
		tfWriteday.setSize(300, 25);
		
		panel1Center.add(tfWriteday);
		panel1.add(panel1Center);//작성일자
		
		panel1Down = new JPanel();
		panel1Down.setLayout(null);
		panel1Down.setBackground(skyblue);
		
		lblWriter = new JLabel("작성자");
		lblWriter.setLocation(10, 20);
		lblWriter.setSize(150, 20);
		panel1Down.add(lblWriter);
		
		tfWriter = new JTextField(25);
		tfWriter.setLocation(70, 20);
		tfWriter.setSize(300, 25);
		panel1Down.add(tfWriter);
		panel1.add(panel1Down);//작성자
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel2.setBackground(skyblue);
		lblselection = new JLabel("카테고리");
		panel2.add(lblselection);
		
		vecCombo = new Vector<String>();
	      vecCombo.add("운동");
	      vecCombo.add("나눔");
	      
	      comboselection = new JComboBox<String>(vecCombo);
	      comboselection.setPreferredSize(new Dimension(100, 35));
		panel2.add(comboselection);//콤보박스
		panel1.add(panel2);

		PanelUp.add(panel1);
		add(PanelUp, BorderLayout.NORTH);
	}
	
	private void PanelCenter() {
		PanelCenter = new JPanel();
		PanelCenter.setBackground(skyblue);
		ta = new JTextArea(20,38);
		ta.setLineWrap(true);
		sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		PanelCenter.add(sp);
		add(PanelCenter, BorderLayout.CENTER);
	}
	
	private void PanelDown() {
		PanelDown = new JPanel();
		PanelDown.setBackground(skyblue);
		PanelDown.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnSend = new JButton("올리기");
		PanelDown.add(btnSend);
		add(PanelDown, BorderLayout.SOUTH);
		
	}


}


