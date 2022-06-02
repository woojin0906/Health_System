package frame.main;
//2022-05-20 201945012 윤선호 디자인 변경 및 WindowListener 추가

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.basic.DefaultMenuLayout;

import frame.board.Board;
import frame.board.Board2_PT;
import frame.db.dbOpen;
import frame.login.ChangeInfo;
import frame.login.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.filechooser.*;

public class MainFrame extends JFrame implements Runnable, ActionListener, WindowListener {

	private JPanel panelNorth, panelSouth, panelWest, panelEast, paTime;
	private JTextArea ta;
	private JLabel lbl_icon, lbl_member, lbl_lastday, lblDay, lblTime, lbl_day, lbl_coment;
	private JButton btn_logout, btn_modify, btn_buy, btn_board1, btn_board2, btn_cal;
	private String id; //로그인 한 계정의 id값을 받아옴.
	private String name; //로그인 한 계정의 이름 값을 받아옴.

	// 전우진 5/29 db
	private dbOpen db;
	private Font mainFont;
	
	public MainFrame(String id) {
		this.id = id;
		setTitle("메인 화면");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(300,200);
		setSize(800, 550);
		setLayout(new BorderLayout());
		//setUndecorated(true);
		
		setInfo();
		setMenu();
		
		
		// 전우진 5/29 12:34 db 연결
		db = new dbOpen();
		db.pullInfoMain(id, lbl_day);
		
		this.addWindowListener(this);
		setLocationRelativeTo(null); //화면 가운데에 보여줌
		setResizable(false); // 화면 크기 조절 못하게 해줌
		setVisible(true);
		
	}
	public MainFrame(String id, String name) {
		this.id = id;
		this.name = name;
		setTitle("메인 화면");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(300,200);
		setSize(800, 550);
		setLayout(new BorderLayout());
		//setUndecorated(true);
		
		setInfo();
		setMenu();
		
		
		// 전우진 5/29 12:34 db 연결
		db = new dbOpen();
		db.pullInfoMain(id, lbl_day);
		
		this.addWindowListener(this);
		setLocationRelativeTo(null); //화면 가운데에 보여줌
		setResizable(false); // 화면 크기 조절 못하게 해줌
		setVisible(true);
		
	}
	
	private void setInfo() {
		panelNorth = new JPanel();
		panelNorth.setLayout(null);
		
		// 전우진 5/31
		ImageIcon icn = new ImageIcon("C://Users/jwjle/git/22222/imges/" + id + ".png");
		Image img = icn.getImage();
		Image changeImg = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		ImageIcon changeimgicon = new ImageIcon(changeImg);
		
		panelWest = new JPanel();
		panelWest.setLayout(new FlowLayout());
		lbl_icon = new JLabel(changeimgicon);
		lbl_icon.setPreferredSize(new Dimension(300, 300));

		panelWest.add(lbl_icon);
		
		panelWest.setBackground(new Color(189, 215, 238));
		
		panelEast = new JPanel();
		panelEast.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelEast.setBackground(new Color(189, 215, 238));
		
		//시간이 흐르는 시계를 사용하기 위한 Thread
		Thread thread = new Thread(this);
		thread.start(); // start를 하면 run()메소드에서 스레드 실행 시작
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy년 MM월 dd일");
		Calendar calendar = Calendar.getInstance();
		Date dateObj = calendar.getTime();
		String formattedDate = dtf.format(dateObj);
		
		//오늘 날짜를 보여준다.
		lblDay = new JLabel(formattedDate);
		Font font3 = new Font("210 맨발의청춘 L", Font.BOLD, 16);
		lblDay.setFont(font3);
		panelEast.add(lblDay);
		
		lblTime = new JLabel("12:35:05");
		lblTime.setFont(font3);
		
		paTime = new JPanel();
		paTime.setBackground(new Color(189, 215, 238));
		paTime.setLayout(new FlowLayout());
		paTime.add(lblTime);
		panelEast.add(paTime);
		
		//로그아웃 버튼
		ImageIcon logoutimg = new ImageIcon("imges/outbutton.png");
		btn_logout = new JButton(logoutimg);
		btn_logout.setPreferredSize(new Dimension(40, 40));
		//btn_logout.setBorderPainted(false);
		btn_logout.setContentAreaFilled(false);
		btn_logout.addActionListener(this);
		panelEast.add(btn_logout);
		
		// 전우진 5/29 1:48 라벨 분리
		// 반갑습니다
//		if(lbl_name.getText().length() == 2) {
//			
//		}
//		lbl_welcome = new JLabel("반갑습니다 ");
//		font = new Font("맑은 고딕", Font.BOLD, 30);
//		lbl_welcome.setBounds(20, 25, 180, 30);
//		lbl_welcome.setFont(font);
//		panelNorth.add(lbl_welcome);
		
		// 전우진 5/29 1:48 라벨 분리
		//로그인한 회원 이름 (lbl_name으로 변경)
//		lbl_name = new JLabel();	
//		Font font = new Font("맑은 고딕", Font.BOLD, 30);
//		lbl_name.setFont(font);
//		lbl_name.setBounds(50, 25, 200, 30);
//		lbl_name.setHorizontalAlignment(JLabel.CENTER);
//		panelNorth.add(lbl_name);
		
		// 전우진 5/29 1:48 라벨 분리
		// 회원님!
		lbl_member = new JLabel(name + " 회원님!");
		lbl_member.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 30));
		lbl_member.setBounds(20, 30, 300, 40);
		lbl_member.setHorizontalAlignment(JLabel.LEFT);
		panelNorth.add(lbl_member);
		
		// 전우진 5/29 1:48 라벨 분리
		// 님 만료일까지
		lbl_lastday = new JLabel("만료일까지 ");
		lbl_lastday.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 22));
		lbl_lastday.setBounds(20, 70, 150, 40);
		lbl_member.setHorizontalAlignment(JLabel.LEFT);
		panelNorth.add(lbl_lastday);
		
		// 전우진 5/29 1:48 라벨 분리
		// 로그인한 회원의 만료일 수
		lbl_day = new JLabel();
		lbl_day.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 22));
		lbl_day.setBounds(130, 70, 60, 40);
		lbl_day.setOpaque(true);
		lbl_day.setForeground(Color.RED);
		lbl_day.setBackground(new Color(189, 215, 238));
		lbl_day.setHorizontalAlignment(JLabel.CENTER);
		panelNorth.add(lbl_day);
		
		// 전우진 5/29 1:48 라벨 분리
		lbl_coment = new JLabel("일 남았습니다.");
		lbl_coment.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 22));
		lbl_coment.setBounds(210, 70, 150, 40);
		panelNorth.add(lbl_coment);
		
		String random[] = {"오늘 들 무게를 내일로 미루지 마라.", "일어나 하체 해야지", "이거 못들면 죽는거야", 
							"복근 없으면 치팅데이는 없다", "바다갈 준비 안할거야?", "Light Weight", 
							"너도 3대 500 될 수 있어", "너가 먹은 치킨이 불쌍하지 않아?"};
		Random rd = new Random();
		JLabel ta = new JLabel(random[rd.nextInt(8)]);
		Font font_ta = new Font("210 맨발의청춘 L", Font.ROMAN_BASELINE, 28);
		//ta.setEditable(true);
		//ta.setLineWrap(true);
		ta.setFont(font_ta);
		ta.setForeground(Color.WHITE);
		ta.setBounds(20, 110, 400, 170);
		ta.setBackground(Color.LIGHT_GRAY);
		ta.setOpaque(true); // 라벨은 투명해서 불투명도를 true로 설정
		ta.setHorizontalAlignment(JLabel.CENTER);
		panelNorth.add(ta);
		
		panelNorth.setBackground(new Color(189, 215, 238)); // 패널 백그라운드 색상 지정
		add(panelWest, BorderLayout.WEST);
		add(panelNorth, BorderLayout.CENTER);
		add(panelEast, BorderLayout.NORTH);
		
	}

	private void setMenu() {
		panelSouth = new JPanel();
		panelSouth.setLayout(null);
		panelSouth.setPreferredSize(new DimensionUIResource(100, 100));
		
		//백그라운드 이미지 크기 및 위치 지정
		ImageIcon bg_img = new ImageIcon("imges/icon_tray.png");
		JLabel bg_label = new JLabel(new ImageIcon("imges/icon_tray.png"));
		bg_label.setBounds(5, 25, 800, 80);
		
		//달력 화면 이동 이미지버튼
		ImageIcon cal_icon = new ImageIcon("imges/calender.png");
		JLabel cal_label = new JLabel(cal_icon);
		cal_label.setBounds(70, 0, 140, 80);
		btn_cal = new JButton();
		btn_cal.setContentAreaFilled(false);
		btn_cal.setBorderPainted(false);
		btn_cal.setBounds(90, 10, 105, 65);
		btn_cal.setPreferredSize(new Dimension(140, 40));
		btn_cal.addActionListener(this);
		
		//이용권 구매 이미지버튼
		ImageIcon ticket_icon = new ImageIcon("imges/buy_ticket.png");
		JLabel buy_label = new JLabel(ticket_icon);
		buy_label.setBounds(170, 9, 200, 80);
		btn_buy = new JButton();
		btn_buy.setContentAreaFilled(false);
		btn_buy.setBorderPainted(false);
		btn_buy.setBounds(237, 25, 70, 50);
		btn_buy.setPreferredSize(new Dimension(140, 40)); //버튼 크기 설정
		btn_buy.addActionListener(this);
		
		//자유게시판 이동 이미지버튼
		ImageIcon frcm_icon = new ImageIcon("imges/comm_community.png");
		JLabel comm_label = new JLabel(frcm_icon);
		comm_label.setBounds(360, 15, 100, 80);
		btn_board1 = new JButton();
		btn_board1.setContentAreaFilled(false);
		btn_board1.setBorderPainted(false);
		btn_board1.setBounds(372, 26, 75, 60);
		btn_board1.setPreferredSize(new Dimension(140, 40)); // 버튼 크기 설정
		btn_board1.addActionListener(this);
		
		//PT 게시판 이동 이미지버튼
		ImageIcon PT_icon = new ImageIcon("imges/health_community.png");
		JLabel pt_label = new JLabel(PT_icon);
		pt_label.setBounds(500, 8, 100, 80);
		btn_board2 = new JButton();
		btn_board2.setContentAreaFilled(false);
		btn_board2.setBorderPainted(false);
		btn_board2.setBounds(514, 23, 75, 60);
		btn_board2.setPreferredSize(new Dimension(140, 40)); // 버튼 크기 설정
		btn_board2.addActionListener(this);
		
		//회원정보 수정 이미지 버튼
		ImageIcon my_icon = new ImageIcon("imges/edit_info.png");
		JLabel edit_label = new JLabel(my_icon);
		edit_label.setBounds(615, -6, 120, 80);
		btn_modify = new JButton();
		btn_modify.setContentAreaFilled(false);
		btn_modify.setBorderPainted(false);
		btn_modify.setBounds(625, 2, 95, 70);
		btn_modify.addActionListener(this);
		btn_modify.setPreferredSize(new Dimension(140, 100)); // 버튼 크기 설정
		
		panelSouth.add(edit_label);
		panelSouth.add(buy_label);
		panelSouth.add(comm_label);
		panelSouth.add(pt_label);
		panelSouth.add(cal_label);
		
		panelSouth.add(btn_modify);
		panelSouth.add(btn_buy);
		panelSouth.add(btn_board1);
		panelSouth.add(btn_board2);
		panelSouth.add(btn_cal);
		
		panelSouth.add(bg_label);
		add(panelSouth, BorderLayout.SOUTH);
	}

	@Override
	//쓰레드 실행 시작
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//2022-05-19 윤선호 이용권 구매 버튼을 누르면 이용권 구매 화면이 뜬다.
		if(obj == btn_buy) {
			Ticket tk = new Ticket(this, id);
			this.dispose(); //이용권 구매 프레임 호출시 메인프레임 종료
		}
		else if(obj == btn_board1) {
			// 5/31 전우진 자유게시판 생성시 메인 꺼짐
			//2022-05-19 윤선호 자유게시판과 메인프레임 연결
			Board bd = new Board(id);
			this.dispose();
		}
		else if(obj == btn_board2) {
			// 5/31 전우진 자유게시판 생성시 메인 꺼짐
			//2022-05-19 윤선호 PT 게시판과 메인프레임 연결
			Board2_PT pt = new Board2_PT(this, id);
			this.dispose();
			
		}else if(obj == btn_cal) {
			// 5/31 전우진 자유게시판 생성시 메인 꺼짐
			Calendarmain cm = new Calendarmain(this, id);
			this.dispose();
			
		}else if(obj == btn_modify) {
			// 5/31 전우진 자유게시판 생성시 메인 꺼짐
			ChangeInfo ci = new ChangeInfo(this, id);
			this.dispose();
			
		}else if(obj == btn_logout) {
			Login login = new Login();
			this.dispose();
		}
//		//2022-05-20 03시 윤선호 프로필 사진 변경 기능 추가
//		else if(obj == lbl_icon) {
//			JFileChooser jfc = new JFileChooser();
//			jfc.setDialogTitle("프로필 사진 변경");
//			int returnVal = jfc.showSaveDialog(null);
//			
//			if(returnVal == 0) {
//				File file = jfc.getSelectedFile();
//				
//				try {
//					String tmp, str = null;
//					BufferedReader br = new BufferedReader(new FileReader(file));
//					while((tmp = br.readLine())!= null) {
//						str += tmp;
//					}
//					
//				}catch(Exception e1) {
//				e1.printStackTrace();
//				}
//				
//			} else {
//				System.out.println("이미지를 선택해주세요.");
//			}
			// 전우진 5/28 23:24 이미지 경로 확인 때문에 주석 처리
//			//이미지 경로를 불러서 바로 이미지 변경
//			String path = jfc.getSelectedFile().getPath();
//			
//			ImageIcon fileicon = new ImageIcon(path);
//			System.out.println(jfc.getSelectedFile().getPath());
//			btn_icon.setIcon(fileicon);
		//}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//윈도우 x 버튼을 눌렀을때 그 프레임만 닫기
	@Override
	public void windowClosing(WindowEvent e) {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
}
		
	

