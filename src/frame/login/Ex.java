package frame.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ex extends JFrame{

	private Color skyblue;
	public Ex(String title) {
		setTitle(title);
		setLocation(250, 150);
		setSize(400, 250);
		setLayout(new BorderLayout());
		setResizable(false);
		
		skyblue = new Color(189, 215, 238);
		
	    setNorth();
	    setCenter();

		setVisible(true);
	}
	
	
	private void setNorth() {
		JPanel panelNorth = new JPanel(new BorderLayout());
		panelNorth.setLayout(null);
		panelNorth.setBackground(skyblue);
		panelNorth.setPreferredSize(new Dimension(370, 40));
		
		JButton btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		btnCancel.setBounds(0, 5, 60, 30);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		//btnCancel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		panelNorth.add(btnCancel, BorderLayout.WEST);
		
		// 비밀번호 확인 텍스트 필드 배경 이미지 출력
		ImageIcon imgEx = new ImageIcon("imges/ExBack.png");
		JLabel lblEx = new JLabel(imgEx);
		lblEx.setBounds(-5, 0, 400, 50);
		panelNorth.add(lblEx);
				
		add(panelNorth, BorderLayout.NORTH);
	}


	private void setCenter() {
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(null);
		panelCenter.setBackground(skyblue);
		
		JButton btnRecord = new JButton("운동 기록");
		btnRecord.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 17));
		btnRecord.setContentAreaFilled(false);
		btnRecord.setBorderPainted(false);
		//btnRecord.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnRecord.setBounds(25, 20, 140, 130);
		btnRecord.setForeground(Color.WHITE);
		panelCenter.add(btnRecord);
		
		JButton btnRead = new JButton("기록 열람");
		btnRead.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 17));
		btnRead.setContentAreaFilled(false);
		btnRead.setBorderPainted(false);
		//btnCancel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnRead.setBounds(220, 20, 140, 130);
		btnRead.setForeground(Color.WHITE);
		panelCenter.add(btnRead);
		
		// 비밀번호 확인 텍스트 필드 배경 이미지 출력
		ImageIcon imgBtn1 = new ImageIcon("imges/btnEx.png");
		JLabel lblBtn1 = new JLabel(imgBtn1);
		lblBtn1.setBounds(25, 20, 140, 130);
		panelCenter.add(lblBtn1);
				
		// 비밀번호 확인 텍스트 필드 배경 이미지 출력
		ImageIcon imgBtn2 = new ImageIcon("imges/btnEx.png");
		JLabel lblBtn2 = new JLabel(imgBtn2);
		lblBtn2.setBounds(220, 20, 140, 130);
		panelCenter.add(lblBtn2);
				
		// 비밀번호 확인 텍스트 필드 배경 이미지 출력
		ImageIcon imgEx = new ImageIcon("imges/ExBack.png");
		JLabel lblEx = new JLabel(imgEx);
		lblEx.setBounds(-5, 0, 400, 180);
		panelCenter.add(lblEx);
				
		add(panelCenter, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		Ex e = new Ex("ex");
	}
}
