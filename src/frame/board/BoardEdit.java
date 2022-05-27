package frame.board;
//자유게시판 댓글 - 202045016 허유진

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import frame.db.DB;


public class BoardEdit extends JFrame implements ActionListener, WindowListener{
	private Font mainFont;
	private JTextArea ta;
	private JScrollPane sp;
	private JButton btnsend;
	private Color skyblue;
	private JPanel panel1;
	private JPanel panel2;
	private JButton btn;
	private JButton btn2;
	private JPanel panelS1;
	private JPanel panelS2;
	private JPanel panelS3;
	private JLabel lblcomment;
	private JTextArea ta2;
	private JScrollPane sp2;
	private JButton btn3;
	private JTextField tfcomment;
	private Color skyblue2;
	private ArrayList<String> al;
	private int bdi;
	private Board bd;
	
	public BoardEdit(ArrayList<String> al) {

		this.al = al;
		setTitle("게시물 댓글 및 삭제");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 200);
		setSize(450, 643);
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		mainFont = new Font("210 맨발의청춘 L", 0, 16);
		
		//System.out.println(al.get(0));
		addWindowListener(this);
		setNorth();
		setCenter();
		
		setSouth();
		
		setVisible(true);
	}


	private void setNorth() {
		JPanel NorthPanel = new JPanel();
	      NorthPanel.setPreferredSize(new Dimension(300, 150));
	      NorthPanel.setLayout(null);
	      NorthPanel.setPreferredSize(new DimensionUIResource(100,160));
	      NorthPanel.setBackground(skyblue);
	      
	      String[] name = {"제목", "작성자", "작성일자", "카테고리"}; 
	      
	      JLabel[] TxValue = new JLabel[4];
	   
	      int x = 20;
	      int y = 10;
	      
	      for(int i = 0; i < TxValue.length; i++, y+=40) {
	         TxValue[i] = new JLabel(name[i]);
	         TxValue[i].setBounds(x, y, 100, 25);
	         TxValue[i].setFont(mainFont);
	         NorthPanel.add(TxValue[i]);
	      }
	      
  
	      JTextField[] TxField = new JTextField[4];//tf 텍스트
	      
	      x = 100;
	      y = 10;
	      
	      for(int i = 0; i < TxField.length; i++, y+=40) {
	         TxField[i] =  new JTextField(15);
	         TxField[i].setBounds(x, y, 310, 20);
	         TxField[i].setBorder(BorderFactory.createEmptyBorder());
	         TxField[i].setText(al.get(i+1));
	         TxField[i].setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
	         NorthPanel.add(TxField[i]);
	      }
	      
	    ImageIcon img1 = new ImageIcon("imges/textimage_edit.png");
		JLabel lbl1 = new JLabel(img1);
		JLabel lbl2 = new JLabel(img1);
		JLabel lbl3 = new JLabel(img1);
		JLabel lbl4 = new JLabel(img1);
	      
	      JLabel[] lblimg  ={lbl1 , lbl2 , lbl3, lbl4};
	      
	      x = 90;
	      y = 1;
	      
	      for(int i = 0; i < lblimg.length; i++, y+=40) {
	    	  lblimg[i].setBounds(x, y, 330, 40);
	    	  NorthPanel.add(lblimg[i]);
	      }
	      
	      add(NorthPanel, BorderLayout.NORTH);
		
	}

	private void setCenter() {
		JPanel TextPanel = new JPanel();
		TextPanel.setBackground(skyblue);
	    TextPanel.setLayout(new BorderLayout()); 
		
		panel1 = new JPanel();//글쓰기 파트
		panel1.setBackground(skyblue);
		ta =new  JTextArea(8,30);
		ta.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		ta.setLineWrap(true);
		ta.setText(al.get(5));
		sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel1.add(sp);
		TextPanel.add(panel1,BorderLayout.CENTER);
		
		
		panel2 = new JPanel();
		panel2.setBackground(skyblue);
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//수정버튼
		btn = new JButton(new ImageIcon("imges/edit2.png"));
		btn.addActionListener(this);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);

		panel2.add(btn);
		
		//삭제버튼
		btn2 = new JButton(new ImageIcon("imges/delete2.png"));
		btn2.setBorderPainted(false);
		btn2.setContentAreaFilled(false);

		panel2.add(btn2);
		TextPanel.add(panel2, BorderLayout.NORTH);
		
	    add(TextPanel, BorderLayout.CENTER);
		
	}
	
	private void setSouth() {
		JPanel SouthPanel = new JPanel();
		SouthPanel.setLayout(new BorderLayout());
		
		panelS1 = new JPanel();
		panelS1.setBackground(skyblue);
		panelS1.setLayout(new FlowLayout(FlowLayout.LEFT,20,7));
		lblcomment = new JLabel("댓글");
		lblcomment.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 13));
		panelS1.add(lblcomment);
		SouthPanel.add(panelS1, BorderLayout.NORTH);
		
		//댓글 목록 TextArea
		panelS2 = new JPanel();
		panelS2.setBackground(skyblue);
		ta2 =new  JTextArea(8,30);
		ta2.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		ta2.setLineWrap(true);
		sp2 = new JScrollPane(ta2, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS2.add(sp2);
		SouthPanel.add(panelS2, BorderLayout.CENTER);
		
		
		//마지막 부분 답글 텍스트 있는 부분!
		panelS3 = new JPanel();
		panelS3.setLayout(null);
		panelS3.setPreferredSize(new DimensionUIResource(100, 70));
		panelS3.setBackground(skyblue);
		
		//댓글 입력 TextField
		tfcomment = new JTextField(20);
		tfcomment.setBounds(15, 20, 300, 20);
		tfcomment.setBorder(BorderFactory.createEmptyBorder());
		tfcomment.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		//엔터 입력하면 댓글이 추가된다.
		tfcomment.addActionListener(this);
		panelS3.add(tfcomment);
		
		//답글 이미지
		ImageIcon imgcomment = new ImageIcon("imges/textimage_edit.png");
		JLabel lblcomment = new JLabel(imgcomment);
		lblcomment.setBounds(8, 3, 330, 50);
		panelS3.add(lblcomment);
		
		//댓글 버튼
		btn3 = new JButton(new ImageIcon("imges/comment2_3.png"));	
		btn3.setBounds(330, 8, 100, 40);
		btn3.setBorderPainted(false);
		btn3.setContentAreaFilled(false);
		btn3.addActionListener(this);
		
		panelS3.add(btn3);
		SouthPanel.add(panelS3, BorderLayout.SOUTH);
		
		
		SouthPanel.setBackground(skyblue);
		add(SouthPanel, BorderLayout.SOUTH);
	}

	//2022-05-27 윤선호 선택한 게시물에 대한 댓글 불러오기
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//Boardwrite2 bw2 = new Boardwrite2(null);
		if(obj == tfcomment || obj == btn3) {
			ta2.append(tfcomment.getText() + "\n");
			//tfcomment.setText("");
			
			//현재 선택한 게시물의 글번호를 가져옴
			bdi = Integer.parseInt(al.get(0).toString());
			System.out.println(bdi);
		
			DB db = new DB(this);
			//int in = Integer.parseInt(bd.getAl().get(0));
			System.out.println(al.get(1));
			db.BDCMT(al.get(0), tfcomment.getText());
			tfcomment.setText("");
			db.DisplayCMT(al.get(0));
			
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.dispose();
		Board bd = new Board(null);
		bd.dispose();
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


	public JTextArea getTa2() {
		return ta2;
	}
	
}