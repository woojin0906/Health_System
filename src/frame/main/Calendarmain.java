package frame.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//2022-05-18 201945012 윤선호 달력
class Calendarmain extends JFrame implements ActionListener{
	
	Container container = getContentPane();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	JButton buttonBefore = new JButton(" << ");
	JButton buttonAfter = new JButton(" >> ");
	
	JLabel label = new JLabel("0000년 00월");
	
	JButton[] buttons = new JButton[49];
	String[] dayNames = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
	
	CalendarFunction cf = new CalendarFunction();
	private Date nowtoday;
	private JLabel lblToday;
	
	public Calendarmain(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocation(250, 150);
		init();
		
		setVisible(true);
	}
	
	private void init() {
		 container.setLayout(new BorderLayout());
		 container.add("North", panel1);
		 container.add("Center", panel2);
		 
		 SimpleDateFormat today = new SimpleDateFormat("yyyy년 MM월 dd일");
		 nowtoday = new Date();
		 String nowDate = today.format(nowtoday);
		 lblToday = new JLabel(nowDate);
		 
		 panel1.setLayout(new FlowLayout());
		 panel1.add(buttonBefore);
		 panel1.add(label);
		 panel1.add(buttonAfter);
		 panel1.setBackground(new Color(189, 215, 238));
		 //panel1.add(lblToday, RIGHT_ALIGNMENT);
		 
		 Font font = new Font("210 맨발의청춘 L", Font.BOLD, 30);
		 buttonBefore.addActionListener(this);
		 buttonBefore.setBackground(Color.LIGHT_GRAY);
		 //buttonBefore.setFont(font);
		 
		 buttonAfter.addActionListener(this);
		 buttonAfter.setBackground(Color.LIGHT_GRAY);
		 //buttonAfter.setFont(font);
		 
		 label.setFont(font);
		 label.setText(cf.getCalText());
		 
		 panel2.setLayout(new GridLayout(7, 7));
		 
		 //오늘 날짜 버튼 글자 색 변경하기 위해 오늘 날짜 데이터 받기
		 //Calendar cal2 = Calendar.getInstance();
		 //int date = cal2.get(Calendar.DATE) + 6;
		 
		 for(int i = 0; i < buttons.length; i++) {
			 buttons[i] = new JButton();
			 //buttons[i].addActionListener(this);
			 buttons[i].setBackground(Color.white);
			 panel2.add(buttons[i]);
			 buttons[i].setFont(new Font("210 맨발의 청춘 L", Font.BOLD, 16));
			 
			 //오늘 날짜 버튼 글자색 변경
			 //if(i == date) buttons[i].setForeground(Color.green);
				 //buttons[i].setBackground(Color.green);
			 if(i < 7) {
				 buttons[i].setText(dayNames[i]); 
				 buttons[i].setBackground(Color.GRAY);
				 buttons[i].setForeground(Color.white);
			 }
			 
		 }
		 cf.setButtons(buttons);
		 cf.calSet();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int gap = 0;
		if(e.getSource() == buttonAfter) {				// 1달 후
			gap = 1;
		} else if(e.getSource() == buttonBefore ) {		// 1달 전
			gap = -1;
		}
		cf.allInit(gap);
		label.setText(cf.getCalText());		// 년 월 글자 갱신		
	}	
}

 