package frame.main;
//2022-05-18 201945012 윤선호 메인화면 수정
import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class MainFrame extends JFrame implements Runnable {

	private JPanel panelNorth;
	private JPanel panelSouth;
	private JTextArea ta;
	private JButton btn_icon;
	private JLabel lbl_welcome;
	private JLabel lbl_lastday;
	private JPanel panelWest;
	private JPanel panelEast;
	private JButton btn_logout;
	private JLabel lblDay;
	private JLabel lblTime;
	private JPanel paTime;
	private JButton btn_modify;
	private JButton btn_buy;
	private JButton btn_board1;
	private JButton btn_board2;

	public MainFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300,200);
		setSize(800, 550);
		setLayout(new BorderLayout());
		
		setInfo();
		setMenu();
		
		setResizable(false); // 화면 크기 조절 못하게 해줌
		setVisible(true);
		
	}
	
	private void setInfo() {
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		
		panelWest = new JPanel();
		panelWest.setLayout(new FlowLayout());
		btn_icon = new JButton("이미지 넣을 예정");
		btn_icon.setPreferredSize(new Dimension(250, 250));
		
		panelWest.add(btn_icon);
		panelWest.setBackground(new Color(189, 215, 238));
		
		panelEast = new JPanel();
		panelEast.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelEast.setBackground(new Color(189, 215, 238));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//시간이 흐르는 시계를 사용하기 위한 Thread
		Thread thread = new Thread(this);
		thread.start();
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy년 MM월 dd일");
		Calendar calendar = Calendar.getInstance();
		Date dateObj = calendar.getTime();
		String formattedDate = dtf.format(dateObj);
		
		lblDay = new JLabel(formattedDate);
		Font font3 = new Font("맑은 고딕", Font.BOLD, 16);
		lblDay.setFont(font3);
		panelEast.add(lblDay);
		
		lblTime = new JLabel("12:35:05");
		lblTime.setFont(font3);
		
		paTime = new JPanel();
		paTime.setBackground(new Color(189, 215, 238));
		paTime.setLayout(new FlowLayout());
		paTime.add(lblTime);
		panelEast.add(paTime);
		
		ImageIcon logoutimg = new ImageIcon("imges/outbutton.png");
		
		btn_logout = new JButton(logoutimg);
		btn_logout.setPreferredSize(new Dimension(40, 40));
		//btn_logout.setBorderPainted(false);
		btn_logout.setContentAreaFilled(false);
		panelEast.add(btn_logout);
		
		lbl_welcome = new JLabel("반갑습니다 윤선호 회원님!");
		Font font = new Font("맑은 고딕", Font.BOLD, 30);
		lbl_welcome.setFont(font);
		panelNorth.add(lbl_welcome);
		
		lbl_lastday = new JLabel("윤선호님 만료일까지 359일 남았습니다.");
		Font font2 = new Font("맑은 고딕", Font.BOLD, 20);
		lbl_lastday.setFont(font2);
		panelNorth.add(lbl_lastday);
		
		JTextArea ta = new JTextArea("", 8, 30);
		Font font_ta = new Font("맑은 고딕", Font.ROMAN_BASELINE, 14);
		ta.setEditable(true);
		ta.setLineWrap(true);
		ta.setFont(font_ta);
		ta.setBackground(Color.LIGHT_GRAY);
		panelNorth.add(ta);
		
		panelNorth.setBackground(new Color(189, 215, 238)); // 패널 백그라운드 색상 지정
		add(panelWest, BorderLayout.WEST);
		add(panelNorth, BorderLayout.CENTER);
		add(panelEast, BorderLayout.NORTH);
		
	}

	private void setMenu() {
		panelSouth = new JPanel();
		
		btn_modify = new JButton("회원정보수정");
		btn_modify.setPreferredSize(new Dimension(140, 40)); // 버튼 크기 설정
		
		btn_buy = new JButton("이용권 구매");
		btn_buy.setPreferredSize(new Dimension(140, 40)); //버튼 크기 설정
		
		btn_board1 = new JButton("게시판1");
		btn_board1.setPreferredSize(new Dimension(140, 40)); // 버튼 크기 설정
		
		btn_board2 = new JButton("게시판2");
		btn_board2.setPreferredSize(new Dimension(140, 40)); // 버튼 크기 설정
		
		panelSouth.add(btn_modify);
		panelSouth.add(btn_buy);
		panelSouth.add(btn_board1);
		panelSouth.add(btn_board2);
		
		add(panelSouth, BorderLayout.SOUTH);
	}

	//public void paint(Graphics g) {
		//SimpleDateFormat SDF = new SimpleDateFormat("a hh시 mm분 ss초");
		//Date d = new Date();
		//g.drawString(SDF.format(d), 40, 80);
	//}

	@Override
	public void run() {
		//시간이 흐르게 해준다
		while(true) {
			String currentTime = LocalTime.now().toString();
			currentTime = currentTime.split("[.]")[0];
			//repaint();
			try {
				Thread.sleep(1000);
				lblTime.setText(currentTime);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
}
