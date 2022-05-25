package frame.board;
//자유게시판 댓글

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class BoardEdit extends JFrame{
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

	
	public BoardEdit(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(450, 500);
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		mainFont = new Font("굴림", 0, 16);
		
		setNorth();
		setCenter();
		
		setSouth();
		
		setVisible(true);
	}


	private void setNorth() {
		JPanel NorthPanel = new JPanel();
	      NorthPanel.setPreferredSize(new Dimension(300, 150));
	      NorthPanel.setLayout(null);
	      NorthPanel.setBackground(skyblue);
	      
	      String[] name = {"제목", "작성일자", "작성자", "카테고리"}; 
	      
	      JLabel[] TxValue = new JLabel[4];
	   
	      int x = 20;
	      int y = 10;
	      
	      for(int i = 0; i < TxValue.length; i++, y+=35) {
	         TxValue[i] = new JLabel(name[i]);
	         TxValue[i].setBounds(x, y, 100, 25);
	         TxValue[i].setFont(mainFont);
	         NorthPanel.add(TxValue[i]);
	      }
	      
	      
	      JTextField[] TxField = new JTextField[4];
	      
	      x = 100;
	      y = 10;
	      
	      for(int i = 0; i < TxField.length; i++, y+=35) {
	         TxField[i] = new JTextField(15);
	         TxField[i].setBounds(x, y, 320, 25);
	         NorthPanel.add(TxField[i]);
	      }
	      
	      add(NorthPanel, BorderLayout.NORTH);
		
	}

	private void setCenter() {
		JPanel TextPanel = new JPanel();
		TextPanel.setBackground(skyblue);
	    TextPanel.setLayout(new BorderLayout()); 
		
		panel1 = new JPanel();//글쓰기 파트
		panel1.setBackground(skyblue);
		ta =new  JTextArea(15,45);
		ta.setLineWrap(true);
		sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel1.add(sp);
		TextPanel.add(panel1,BorderLayout.CENTER);
		
		panel2 = new JPanel();
		panel2.setBackground(skyblue);
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btn = new JButton("수정");
		panel2.add(btn);
		btn2 = new JButton("삭제");
		panel2.add(btn2);
		TextPanel.add(panel2, BorderLayout.NORTH);
		
	      add(TextPanel, BorderLayout.CENTER);
		
	}
	
	private void setSouth() {
		JPanel SouthPanel = new JPanel();
		SouthPanel.setLayout(new BorderLayout());
		
		panelS1 = new JPanel();
		panelS1.setBackground(skyblue);
		panelS1.setLayout(new FlowLayout(FlowLayout.LEFT));
		lblcomment = new JLabel("댓글");
		panelS1.add(lblcomment);
		SouthPanel.add(panelS1, BorderLayout.NORTH);
		
		panelS2 = new JPanel();
		panelS2.setBackground(skyblue);
		ta2 =new  JTextArea(15,45);
		ta2.setLineWrap(true);
		sp2 = new JScrollPane(ta2, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelS2.add(sp2);
		SouthPanel.add(panelS2, BorderLayout.CENTER);
		
		panelS3 = new JPanel();
		panelS3.setBackground(skyblue);
		tfcomment = new JTextField(35);
		panelS3.add(tfcomment);
		btn3 = new JButton("답글");
		panelS3.add(btn3);
		SouthPanel.add(panelS3, BorderLayout.SOUTH);
		
		
		SouthPanel.setBackground(skyblue);
		add(SouthPanel, BorderLayout.SOUTH);
	}
}