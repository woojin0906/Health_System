package frame.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

public class Record extends JFrame {

	private JLabel lblDate;
	private JButton btnAdd;
	private JButton btnCancel;
	private Vector<String> vecCombo;
	private JComboBox<String> comboEx;
	private JTextField tfWeight;
	private JTextField tfTime;
	private JTextField tfSet;
	private JButton btn;
	private JButton btnDel;
	private JTextField tfDate;
	private Color skyblue;
	private JLabel lblKg;
	private JLabel lblTimes;
	private JLabel lblNum;
	private JLabel lblName;
	private JLabel lblWeight;
	private JLabel lblTime;
	private JLabel lblSet;
	private JTextField tfNum;
	private JLabel lblNums;
	private JLabel lblSets;

	public Record(String title) {
		setTitle(title);
		setLocation(250, 150);
		setSize(510, 462);
		setLayout(new BorderLayout());
		setResizable(false);
		skyblue = new Color(189, 215, 238);
		setBackground(skyblue);
		
	    setCenter();
	    setSouth();

		setVisible(true);
	}
	
	private void setCenter() {
		JPanel panelCenter = new JPanel(new GridLayout(6, 1));
	
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		
		lblDate = new JLabel("현재 날짜");
		lblDate.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblDate.setBounds(30, 25, 120, 30);
		panel1.add(lblDate);
		
		tfDate = new JTextField();
		tfDate.setBounds(150, 22, 140, 30);
		tfDate.setBorder(BorderFactory.createEmptyBorder());
		tfDate.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panel1.add(tfDate);
		panelCenter.add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		
		lblName = new JLabel("운동 이름");
		lblName.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblName.setBounds(30, 20, 120, 30);
		panel2.add(lblName);
		
		vecCombo = new Vector<String>();
	    vecCombo.add("기구를 선택해주세요");
	      
	    comboEx = new JComboBox<String>(vecCombo);
	    comboEx.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
	    comboEx.setBorder(BorderFactory.createEmptyBorder(-2, -2, -2, -2));
	    comboEx.setBounds(150, 20, 170, 30);
	    comboEx.setBackground(Color.white);
	    panel2.add(comboEx);
	    
	    // 비밀번호 확인 취소 버튼 출력
	    btn = new JButton("추가");
	    btn.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
	    btn.setContentAreaFilled(false);
	    btn.setBorderPainted(false);
		//btnAdd.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	    btn.setBounds(340, 20, 65, 30);
	    btn.setForeground(Color.WHITE);
	    panel2.add(btn);
	 		
	 	// 비밀번호 확인 취소 버튼 출력
	 	btnDel = new JButton("삭제");
	 	btnDel.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
	 	btnDel.setContentAreaFilled(false);
	 	btnDel.setBorderPainted(false);
		//btnAdd.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	 	btnDel.setBounds(415, 20, 65, 30);
	 	btnDel.setForeground(Color.WHITE);
	 	panel2.add(btnDel);
		panelCenter.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		
		lblWeight = new JLabel("무게");
		lblWeight.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblWeight.setBounds(30, 20, 120, 30);
		panel3.add(lblWeight);
		
		tfWeight = new JTextField();
		tfWeight.setBounds(150, 19, 140, 30);
		tfWeight.setBorder(BorderFactory.createEmptyBorder());
		tfWeight.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panel3.add(tfWeight);
		
		lblKg = new JLabel("kg");
		lblKg.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblKg.setBounds(300, 20, 50, 30);
		panel3.add(lblKg);
		panelCenter.add(panel3);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		
		lblTime = new JLabel("운동 시간");
		lblTime.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblTime.setBounds(30, 20, 120, 30);
		panel4.add(lblTime);
		
		tfTime = new JTextField();
		tfTime.setBounds(150, 19, 140, 30);
		tfTime.setBorder(BorderFactory.createEmptyBorder());
		tfTime.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panel4.add(tfTime);
		
		lblTimes = new JLabel("times");
		lblTimes.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblTimes.setBounds(300, 20, 50, 30);
		panel4.add(lblTimes);
		panelCenter.add(panel4);
		
		JPanel panel5 = new JPanel();
		panel5.setLayout(null);
		
		lblNum = new JLabel("횟수");
		lblNum.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblNum.setBounds(30, 20, 120, 30);
		panel5.add(lblNum);
		
		tfNum = new JTextField();
		tfNum.setBounds(150, 16, 140, 30);
		tfNum.setBorder(BorderFactory.createEmptyBorder());
		tfNum.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panel5.add(tfNum);
		
		lblNums = new JLabel("reps");
		lblNums.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblNums.setBounds(300, 20, 50, 30);
		panel5.add(lblNums);
		panelCenter.add(panel5);
		
		JPanel panel6 = new JPanel();
		panel6.setLayout(null);
		
		lblSet = new JLabel("세트 수");
		lblSet.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblSet.setBounds(30, 20, 120, 30);
		panel6.add(lblSet);
		
		tfSet = new JTextField();
		tfSet.setBounds(150, 16, 140, 30);
		tfSet.setBorder(BorderFactory.createEmptyBorder());
		tfSet.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		panel6.add(tfSet);
		
		lblSets = new JLabel("set");
		lblSets.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 15));
		lblSets.setBounds(300, 20, 50, 30);
		panel6.add(lblSets);
		panelCenter.add(panel6);
		
		add(panelCenter, BorderLayout.CENTER);
		
		
		// 비밀번호 확인 텍스트 필드 배경 이미지 출력
		ImageIcon imgAdd = new ImageIcon("imges/btnCancel.png");
		JLabel lblAdd = new JLabel(imgAdd);
		lblAdd.setBounds(340, 20, 65, 30);
		panel2.add(lblAdd);
				
		// 비밀번호 확인 텍스트 필드 배경 이미지 출력
		ImageIcon imgCancel = new ImageIcon("imges/btnCancel.png");
		JLabel lblCancel = new JLabel(imgCancel);
		lblCancel.setBounds(415, 20, 65, 30);
		panel2.add(lblCancel);
				
		
		ImageIcon imgDate = new ImageIcon("imges/record_img.png");
		JLabel lblDateImg = new JLabel(imgDate);
		lblDateImg.setBounds(145, 20, 150, 35);
		panel1.add(lblDateImg);
		
		ImageIcon imgCombo = new ImageIcon("imges/record_combo.png");
		JLabel lblCombo = new JLabel(imgCombo);
		lblCombo.setBounds(144, 16, 180, 35);
		panel2.add(lblCombo);
		
		ImageIcon imgWeight = new ImageIcon("imges/record_img.png");
		JLabel lblWeightImg = new JLabel(imgWeight);
		lblWeightImg.setBounds(145, 16, 150, 35);
		panel3.add(lblWeightImg);
		
		ImageIcon imgTime = new ImageIcon("imges/record_img.png");
		JLabel lblTimeImg = new JLabel(imgTime);
		lblTimeImg.setBounds(145, 16, 150, 35);
		panel4.add(lblTimeImg);
		
		ImageIcon imgNum = new ImageIcon("imges/record_img.png");
		JLabel lbNumImg = new JLabel(imgNum);
		lbNumImg.setBounds(145, 14, 150, 35);
		panel5.add(lbNumImg);
		
		ImageIcon imgSet = new ImageIcon("imges/record_img.png");
		JLabel lblSetImg = new JLabel(imgSet);
		lblSetImg.setBounds(145, 14, 150, 35);
		panel6.add(lblSetImg);
		
		ImageIcon imgPanel1 = new ImageIcon("imges/centerBackImg.png");
		JLabel lblPanel1 = new JLabel(imgPanel1);
		lblPanel1.setBounds(0, 0, 510, 62);
		panel1.add(lblPanel1);
		
		ImageIcon imgPanel2 = new ImageIcon("imges/centerBackImg.png");
		JLabel lblPanel2 = new JLabel(imgPanel2);
		lblPanel2.setBounds(0, 0, 510, 62);
		panel2.add(lblPanel2);
		
		ImageIcon imgPanel3 = new ImageIcon("imges/centerBackImg.png");
		JLabel lblPanel3 = new JLabel(imgPanel3);
		lblPanel3.setBounds(0, 0, 510, 62);
		panel3.add(lblPanel3);
		
		ImageIcon imgPanel4 = new ImageIcon("imges/centerBackImg.png");
		JLabel lblPanel4 = new JLabel(imgPanel4);
		lblPanel4.setBounds(0, 0, 510, 62);
		panel4.add(lblPanel4);
		
		ImageIcon imgPanel5 = new ImageIcon("imges/centerBackImg.png");
		JLabel lblPanel5 = new JLabel(imgPanel5);
		lblPanel5.setBounds(0, 0, 510, 62);
		panel5.add(lblPanel5);
		
		ImageIcon imgPanel6 = new ImageIcon("imges/centerBackImg.png");
		JLabel lblPanel6 = new JLabel(imgPanel6);
		lblPanel6.setBounds(0, 0, 510, 62);
		panel6.add(lblPanel6);
		
//		ImageIcon imgCenter = new ImageIcon("imges/centerBackImg.png");
//		JLabel lblCenter = new JLabel(imgCenter);
//		lblCenter.setBounds(0, 0, 510, 1);
//		panelCenter.add(lblCenter);
		
	}

	private void setSouth() {
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(null);
		panelSouth.setPreferredSize(new Dimension(0, 60));
		
		// 비밀번호 확인 취소 버튼 출력
		btnAdd = new JButton("등록");
		btnAdd.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorderPainted(false);
		//btnAdd.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnAdd.setBounds(300, 10, 80, 40);
		btnAdd.setForeground(Color.WHITE);
		panelSouth.add(btnAdd);
				
		// 비밀번호 확인 취소 버튼 출력
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		//btnCancel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnCancel.setBounds(400, 10, 80, 40);
		btnCancel.setForeground(Color.WHITE);
		panelSouth.add(btnCancel);
		
		ImageIcon imgSouthAdd = new ImageIcon("imges/btnSouth.png");
		JLabel lblSouthAdd = new JLabel(imgSouthAdd);
		lblSouthAdd.setBounds(300, 10, 80, 40);
		panelSouth.add(lblSouthAdd);
		
		ImageIcon imgSouthCanel = new ImageIcon("imges/btnSouth.png");
		JLabel lblSouthCancel = new JLabel(imgSouthCanel);
		lblSouthCancel.setBounds(400, 10, 80, 40);
		panelSouth.add(lblSouthCancel);
		
		ImageIcon imgSouth = new ImageIcon("imges/southBackImg.png");
		JLabel lblSouth = new JLabel(imgSouth);
		lblSouth.setBounds(0, 0, 510, 90);
		panelSouth.add(lblSouth);
		
		add(panelSouth, BorderLayout.SOUTH);
		
		
	}

	public static void main(String[] args) {
		Record rd = new Record("기록");
	}

}
