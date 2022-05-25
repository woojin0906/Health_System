package frame.board;
//PT게시판 수정하는 폼

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class BoardWrite_PT2 extends JFrame{
	private Font mainFont;
	private JTextArea ta;
	private JScrollPane sp;
	private JButton btnsend;
	private Color skyblue;

	
	public BoardWrite_PT2(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(450, 535);
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
	      NorthPanel.setPreferredSize(new Dimension(300, 170));
	      NorthPanel.setLayout(null);
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
	      
	      
	      JTextField[] TxField = new JTextField[4];
	      
	      x = 100;
	      y = 10;
	      
	      for(int i = 0; i < TxField.length; i++, y+=40) {
	         TxField[i] = new JTextField(15);
	         TxField[i].setBorder(BorderFactory.createEmptyBorder());
	         TxField[i].setBounds(x, y, 310, 20);
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
	      
		ta =new  JTextArea(16,30);
		ta.setLineWrap(true);
		ta.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TextPanel.add(sp);
		
	      add(TextPanel, BorderLayout.CENTER);
		
	}
	
	private void setSouth() {
		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(skyblue);
		SouthPanel.setPreferredSize(new Dimension(300, 50));
		SouthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,0));
		btnsend = new JButton(new ImageIcon("imges/edit2.png"));
		btnsend.setBorderPainted(false);
		btnsend.setContentAreaFilled(false);
		SouthPanel.add(btnsend);
		
		add(SouthPanel, BorderLayout.SOUTH);
	}
}