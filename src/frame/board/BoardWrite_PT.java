package frame.board;

//PT게시판 글쓰기(올리기 버튼있는 프레임)

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import frame.db.DBPT;


public class BoardWrite_PT extends JFrame implements ActionListener, WindowListener{
	private Font mainFont;
	private JTextArea ta;
	private JScrollPane sp;
	private JButton btnupload;
	private Color skyblue;
	private JTextField[] TxField;
	private Date today;
	private String ID;
	private String namept;
	private Board2_PT bdpt;

	
	public BoardWrite_PT(String title, String ID, String namept, Board2_PT bdpt) {
		this.ID = ID;
		this.namept = namept;
		this.bdpt = bdpt;
		
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 200);
		setSize(450, 535);
		setLayout(new BorderLayout());
		skyblue = new Color(189, 215, 238);
		mainFont = new Font("210 맨발의청춘 L", 0, 16);
		
		setResizable(false); 
		
		setNorth();
		setCenter();
		setSouth();
		addWindowListener(this);
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
	      
	      
	     TxField = new JTextField[4];
	      
	      x = 100;
	      y = 10;
	      
	      for(int i = 0; i < TxField.length; i++, y+=40) {
	         TxField[i] = new JTextField(15);
	         TxField[i].setBounds(x, y, 310, 20);
	         TxField[i].setBorder(BorderFactory.createEmptyBorder());
	         TxField[i].setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
	         NorthPanel.add(TxField[i]);
	      }
	      //작성하는 날짜 넣기
	      SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
	      Date now = new Date(); 
	      String nowdate = temp.format(now); 
	      TxField[1].setText(nowdate);
	      
	      //작성자 이름 자동으로 넣기
	     TxField[2].setText(namept);
	     
	     TxField[1].setEditable(false);
	     TxField[1].setBackground(Color.white);
	     TxField[2].setEditable(false);
	     TxField[2].setBackground(Color.white);
	      
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
	
	//올리기 버튼
	private void setSouth() {
		JPanel SouthPanel = new JPanel();
		SouthPanel.setBackground(skyblue);
		SouthPanel.setPreferredSize(new Dimension(300, 50));
		SouthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,0));
		btnupload = new JButton(new ImageIcon("imges/upload.png"));
		btnupload.setBorderPainted(false);
		btnupload.setContentAreaFilled(false);
		btnupload.addActionListener(this);
		
		SouthPanel.add(btnupload);
		add(SouthPanel, BorderLayout.SOUTH);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =  e.getSource();
		
		//올리기 버튼 누르면 디비에 정보 전송하기
		if (obj == btnupload) {
			if(TxField[3].getText().equals("")){
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
			TxField[3].requestFocus();	
			
			}else if(TxField[3].getText().length()>4){
				JOptionPane.showMessageDialog(null,"비밀번호는 4글자입니다." ,"알림", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			DBPT dbpt = new DBPT(null);
			dbpt.PtInsert(TxField[0].getText(),TxField[1].getText(),TxField[3].getText(),ta.getText(),TxField[2].getText(),ID);
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
		Board2_PT bdpt = new Board2_PT(null,namept);
		
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