package frame.board;
//PT게시판 댓글 - 202045016 허유진

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;


public class BoardEdit_PT extends JFrame implements ActionListener{
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

	
	public BoardEdit_PT(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 200);
		setSize(450, 643);
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		mainFont = new Font("210 맨발의청춘 L", 0, 16);
		
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
	      
	      String[] name = {"제목", "작성일자", "작성자", "비밀번호"}; 
	      
	      JLabel[] TxValue = new JLabel[4];
	   
	      int x = 20;
	      int y = 10;
	      
	      for(int i = 0; i < TxValue.length; i++, y+=40) {
	         TxValue[i] = new JLabel(name[i]);
	         TxValue[i].setBounds(x, y, 100, 25);
	         TxValue[i].setFont(mainFont);
	         NorthPanel.add(TxValue[i]);
	      }
	      
  
	      JPasswordField[] TxField = new JPasswordField[4];//tf 텍스트
	      
	      x = 100;
	      y = 10;
	      
	      for(int i = 0; i < TxField.length; i++, y+=40) {
	         TxField[i] =  new JPasswordField(15);
	         TxField[i].setBounds(x, y, 310, 20);
	         TxField[i].setBorder(BorderFactory.createEmptyBorder());
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
		
		tfcomment = new JTextField(20);
		tfcomment.setBounds(15, 20, 300, 20);
		tfcomment.setBorder(BorderFactory.createEmptyBorder());
		tfcomment.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panelS3.add(tfcomment);
		
		//답글 이미지
		ImageIcon imgcomment = new ImageIcon("imges/textimage_edit.png");
		JLabel lblcomment = new JLabel(imgcomment);
		lblcomment.setBounds(8, 3, 330, 50);
		panelS3.add(lblcomment);
		
		btn3 = new JButton(new ImageIcon("imges/comment2_3.png"));	
		btn3.setBounds(330, 8, 100, 40);
		btn3.setBorderPainted(false);
		btn3.setContentAreaFilled(false);
		
		panelS3.add(btn3);
		SouthPanel.add(panelS3, BorderLayout.SOUTH);
		
		
		SouthPanel.setBackground(skyblue);
		add(SouthPanel, BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		BoardWrite_PT2 bwpt2 = new BoardWrite_PT2("수정");
		
	}
	
}