package frame.board;
//PT게시판 수정하는 폼

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frame.db.DB;
import frame.db.DBPT;

//비밀 번호 창이 널이면 메서지 창 뜨게 하기
public class BoardWrite_PT2 extends JFrame implements ActionListener, WindowListener{
	private Font mainFont;
	private JTextArea ta;
	private JScrollPane sp;
	private JButton btnsend;
	private Color skyblue;
	private ArrayList<String> alpt;
	private JTextField[] TxField;
	private String ID, namept;
	private JLabel lbl1, lbl2, lbl3, lbl4;
	private Board2_PT bdpt;
	
	public BoardWrite_PT2(ArrayList<String> alpt, String ID, String namept, Board2_PT bdpt) {
		this.alpt = alpt;
		this.ID = ID;
		this.namept = namept;
		this.bdpt = bdpt;
		
		setTitle("PT게시판 글 수정");
		
		setLocation(300, 200);
		setSize(450, 535);
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		mainFont = new Font("210 맨발의청춘 L", 0, 16);
		setResizable(false); 
		addWindowListener(this);
		setNorth();
		setCenter();
		
		setSouth();
		
		setVisible(true);
	}


	public BoardWrite_PT2(String string) {
		// TODO Auto-generated constructor stub
	}


	private void setNorth() {
		JPanel NorthPanel = new JPanel();
	      NorthPanel.setPreferredSize(new Dimension(300, 170));
	      NorthPanel.setLayout(null);
	      NorthPanel.setBackground(skyblue);
	      
	      String[] name = {"제목", "작성자", "작성일자", "비밀번호"}; 
	      
	      JLabel[] TxValue = new JLabel[4];
	   
	      int x = 20;
	      int y = 10;
	      
	      for(int i = 0; i < TxValue.length; i++, y+=40) {
	         TxValue[i] = new JLabel(name[i]);
	         TxValue[i].setBounds(x, y, 100, 25);
	         TxValue[i].setFont(mainFont);
	         NorthPanel.add(TxValue[i]);
	      }
	      
	      
	       TxField = new JTextField[4];
	      
	      x = 100;
	      y = 10;
	      
	      for(int i = 0; i < TxField.length; i++, y+=40) {
	         TxField[i] = new JTextField(15);
	         TxField[i].setBorder(BorderFactory.createEmptyBorder());
	         TxField[i].setBounds(x, y, 310, 20);
	         TxField[i].addActionListener(this);
	         TxField[i].setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
	         NorthPanel.add(TxField[i]);
	      }
	      TxField[0].setText(alpt.get(1));
	      
	      TxField[1].setEditable(false);
	      TxField[1].setBackground(Color.white);
	      TxField[1].setText(alpt.get(3));
	      
	      TxField[2].setEditable(false);
	      TxField[2].setBackground(Color.white);
	      TxField[2].setText(alpt.get(2));
	     // TxField[3].setText(al.get(2));
	      
	      ImageIcon img1 = new ImageIcon("imges/textimage_edit.png");
			lbl1 = new JLabel(img1);
			lbl2 = new JLabel(img1);
			lbl3 = new JLabel(img1);
			lbl4 = new JLabel(img1);
		      
		      JLabel[] lblimg  ={lbl1 , lbl2 , lbl3,lbl4};
		      
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
		ta.setText(alpt.get(4));
		ta.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TextPanel.add(sp);
		
	      add(TextPanel, BorderLayout.CENTER);
		
	}
	
	
	//수정버튼 
	private void setSouth() {
		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(skyblue);
		SouthPanel.setPreferredSize(new Dimension(300, 50));
		SouthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,0));
		btnsend = new JButton(new ImageIcon("imges/edit2.png"));
		btnsend.setBorderPainted(false);
		btnsend.setContentAreaFilled(false);
		btnsend.addActionListener(this);
		SouthPanel.add(btnsend);
		
		add(SouthPanel, BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 수정 버튼 이벤트
		if(obj == btnsend) {

			if(TxField[3].getText().equals("")){
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				TxField[3].requestFocus();	
			}else {
				DBPT dbpt = new DBPT(null);
				dbpt.BDUpdate(alpt.get(0),TxField[0].getText(), TxField[2].getText(), TxField[1].getText(), TxField[3].getText(), ta.getText());
				dbpt.PtRefresh(bdpt);
				dispose();
			}
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
		//Board2_PT bdpt = new Board2_PT(null, namept);
		
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