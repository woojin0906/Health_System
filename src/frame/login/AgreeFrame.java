package frame.login;
// 202145022 전우진
// 이용약관 프레임
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class AgreeFrame extends JFrame implements ActionListener, WindowListener {
	
	private JPanel panelNorth;
	private JPanel panelCenter;
	private JButton btnCancel;
	private JLabel lblAgree;
	private Color a;
	private JPanel panelOne;
	private Container panelTwo;
	private Container panelThree;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JTextArea ta1;
	private JTextArea ta2;
	private JTextArea ta3;
	private JButton btnAgree;
	private JScrollPane sp;
	private Font mainFont;
	private Font subFont;
	private Font mLabelFont;
	private JoinFrame joinFrame;


	public AgreeFrame(String title) {

		setTitle(title);
		setSize(350, 500);
		setResizable(false);
		setLayout(new BorderLayout());
		addWindowListener(this);
	
		mainFont = new Font("210 맨발의청춘 L", Font.BOLD, 20); // 메인 제목
	    subFont = new Font("210 맨발의청춘 L", Font.PLAIN, 13); 
	    mLabelFont = new Font("210 맨발의청춘 L", Font.BOLD, 13); // 각 장의 제목
	    
		setNorth();
		setCenter();
		
		setVisible(true);
	}

	private void setNorth() {
		panelNorth = new JPanel();
		a = new Color(189, 215, 238);
		panelNorth.setBackground(a);
		panelNorth.setLayout(null);
		panelNorth.setPreferredSize(new Dimension(100, 50));
		
		// 이용약관 화면 취소 버튼
		btnCancel = new JButton("취소");
		btnCancel.setFont(subFont);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBounds(0, 10, 70, 30);
		
		btnCancel.addActionListener(this);
		
		// 이용약관 화면 라벨(이용약관) 출력
		lblAgree = new JLabel("이용약관");
		lblAgree.setBounds(130, 15, 100, 20);
		lblAgree.setFont(mainFont);  // 글씨 크기

		// 이용약관 화면 확인 버튼
		btnAgree = new JButton("확인");
		btnAgree.setFont(subFont);
		btnAgree.setContentAreaFilled(false);
		btnAgree.setBorderPainted(false);
		btnAgree.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnAgree.setBounds(280, 10, 40, 30);
		
		btnAgree.addActionListener(this);
				
		panelNorth.add(btnCancel);
		panelNorth.add(lblAgree);
		panelNorth.add(btnAgree);

		add(panelNorth, BorderLayout.NORTH);
	}

	private void setCenter() {
		panelCenter = new JPanel(new GridLayout(3, 1));
		panelCenter.setBackground(Color.WHITE);
		
		// 이용약관 화면 그리드 첫번째 패널
		panelOne = new JPanel();
		panelOne.setLayout(null);
		panelOne.setBackground(Color.WHITE);
		panelOne.setBounds(0, 0, 330, 150);
		
		// 이용약관 화면 제 1장 라벨 출력
		lbl1 = new JLabel("제 1장 서비스 이용약관");
		lbl1.setBounds(10, 10, 130, 20);
		lbl1.setFont(mLabelFont);
		panelOne.add(lbl1);

		// 이용약관 화면 첫번째 패널 안에 패널
		panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(4, 28, 330, 150);
		ta1 = new JTextArea(
				"제 1조 본 약관은 (주)건강해GYM를 통해 제공하는 가맹점의 매장 내 각종 서비스 제공 "
				+ "및 서비스 이용에 필요한 모바일 애플리케이션, 홈페이지를 운영함에 있어 회사와 회원간의 권리, "
				+ "의무 및 책임사항과 절차 등을 정하기 위해 만들어졌습니다." + "\n"
				+ "회원으로 가입하여 서비스의 이용을 희망하는 자는 약관의 내용을 숙지한 후 동의함을 표시하고, "
				+ "회사가 제시하는 소정의 회원가입 양식에 관련사항을 기재하여 회원가입을 신청하여야 합니다.", 5, 33);

		ta1.setLineWrap(true);
		ta1.setEditable(false);
		
		// 이용약관 화면 스크롤팬 출력
		sp = new JScrollPane(ta1, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel1.add(sp);
		panelOne.add(panel1);
		
		// 이용약관 화면 그리드 두번째 패널
		panelTwo = new JPanel();
		panelTwo.setLayout(null);
		panelTwo.setBackground(Color.WHITE);
		panelTwo.setBounds(0, 0, 330, 150);
		
		// 이용약관 화면 제 2장 라벨 출력
		lbl2 = new JLabel("제 2장 개인정보 이용약관");
		lbl2.setBounds(10, 10, 200, 20);
		lbl2.setFont(mLabelFont);
		panelTwo.add(lbl2);
				
		// 이용약관 화면 두번째 패널 안에 패널
		panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(4, 28, 330, 150);
		ta2 = new JTextArea(
				"가. 회사는 회원가입, 상담을 위해 아래와 같이 최소한의 개인정보를 수집하고 있습니다. " + "\n"
				+ "- 필수항목 : 이름, 전화번호, 주소 " +"\n"
				+ "나.서비스 이용과정에서 아래와 같은 정보들이 생성되어 수집될 수 있습니다. " +"\n"
				+ "-사용 기록 : 회원의 서비스 이용에 대한 통계 " +"\n"
				+ "다. 개인정보의 처리 및 보유기간은 다음과 같습니다. " + "\n"
				+ "- 기간 : 이용계약에 따른 회원자격이 유지되는 기간동안", 5, 33);

		ta2.setLineWrap(true);
		ta2.setEditable(false);
	
		// 이용약관 화면 스크롤팬 출력
		sp = new JScrollPane(ta2, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
		panel2.add(sp);
		panelTwo.add(panel2);
	
		// 이용약관 화면 그리드 세번째 패널
		panelThree = new JPanel();
		panelThree.setLayout(null);
		panelThree.setBackground(Color.WHITE);
		panelThree.setBounds(0, 0, 330, 150);
		
		lbl3 = new JLabel("제 3장 전자금융거래 이용약관");
		lbl3.setBounds(10, 10, 200, 20);
		lbl3.setFont(mLabelFont);
		panelThree.add(lbl3);

		// 이용약관 화면 두번째 패널 안에 패널
		panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		panel3.setBounds(4, 28, 330, 150);
		ta3 = new JTextArea(
				"1. 이 전자금융 이용 약관은 서비스의 제공자인 건강해GYM과 "
				+ "이용자인 고객간에 전자금융 이용에 관한 제반 사항을 정함을 목적으로 합니다.", 5, 33);

		ta3.setLineWrap(true);
		ta3.setEditable(false);
		
		// 이용약관 화면 스크롤팬 출력
		sp = new JScrollPane(ta3, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel3.add(sp);
		panelThree.add(panel3);
		
		
		panelCenter.add(panelOne);
		panelCenter.add(panelTwo);
		panelCenter.add(panelThree);
		
		add(panelCenter, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnCancel) {
			this.dispose();
			Login lg = new Login(null);
		} 
		if(obj == btnAgree) {
			if(JOptionPane.showConfirmDialog(this, 
					"다 읽으셨습니까?",
					"이용약관 동의 안내",
					JOptionPane.YES_NO_OPTION
					) == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(this, "동의 되었습니다.", "이용약관 동의 안내", JOptionPane.INFORMATION_MESSAGE);
				joinFrame = new JoinFrame("회원가입");
				joinFrame.setLocationRelativeTo(null);
				JCheckBox ch = joinFrame.getAgreeCheck();
				ch.setSelected(true);
				this.dispose();
			}	

		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

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
