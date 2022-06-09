package frame.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import frame.db.DB;
import frame.main.MainFrame;

public class Record extends JFrame implements ActionListener, WindowListener{

	private JLabel lblDate;
	private JButton btnCheck, btnCancel, btnAdd, btnDel;
	private Vector<String> vecCombo;
	private JComboBox<String> comboEx;
	private JTextField tfWeight, tfDate, tfTime, tfSet;
	private Color skyblue;
	private JLabel lblKg, lblTimes, lblNum, lblName, lblWeight, lblTime, lblSet, lblNums, lblSets;
	private JTextField tfNum;
	private ArrayList<String> rcal;
	private String id, name;
	private DB db = new DB(null, null);
	
	public Vector<String> getVecCombo() {
		return vecCombo;
	}

	public Record(String title, String id, String name) {
		this.id = id;
		this.name = name;
		
		setTitle(title);
		setLocation(250, 150);
		setSize(510, 462);
		setLayout(new BorderLayout());
		setResizable(false);
		addWindowListener(this);
		skyblue = new Color(189, 215, 238);
		
		
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
		
		//현재 날짜 구하기 윤선호
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String formatedNow = now.format(formatter);
		
		tfDate = new JTextField(formatedNow);
		tfDate.setBounds(150, 22, 140, 30);
		tfDate.setBorder(BorderFactory.createEmptyBorder());
		tfDate.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 14));
		tfDate.setEnabled(false);	  // 날짜 텍스트필드 활성화 막음	
		//tfDate.setForeground(Color.WHITE);
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
	    btnAdd = new JButton("추가");
	    btnAdd.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
	    btnAdd.setContentAreaFilled(false);
	    btnAdd.setBorderPainted(false);
	    btnAdd.setBounds(340, 20, 65, 30);
	    btnAdd.setForeground(Color.WHITE);
	    btnAdd.addActionListener(this);
	    panel2.add(btnAdd);
	 		
	 	// 비밀번호 확인 취소 버튼 출력
	 	btnDel = new JButton("삭제");
	 	btnDel.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
	 	btnDel.setContentAreaFilled(false);
	 	btnDel.setBorderPainted(false);
	 	btnDel.setBounds(415, 20, 65, 30);
	 	btnDel.setForeground(Color.WHITE);
	 	btnDel.addActionListener(this);
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
		
//		ImageIcon imgPanelCenter = new ImageIcon("imges/centerBackImg.png");
//		JLabel lblPanelCenter = new JLabel(imgPanelCenter);
//		lblPanelCenter.setBounds(0, 0, 510, 62);
//		panelCenter.add(lblPanelCenter);
		
	}

	private void setSouth() {
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(null);
		panelSouth.setPreferredSize(new Dimension(0, 60));
		//panelSouth.setBackground(skyblue);
		
		// 운동 등록 버튼 출력
		btnCheck = new JButton("등록");
		btnCheck.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		btnCheck.setContentAreaFilled(false);
		btnCheck.setBorderPainted(false);
		btnCheck.setBounds(300, 10, 80, 40);
		btnCheck.setForeground(Color.WHITE);
		btnCheck.addActionListener(this);
		panelSouth.add(btnCheck);
				
		// 비밀번호 확인 취소 버튼 출력
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 13));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBounds(400, 10, 80, 40);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.addActionListener(this);
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


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnAdd) {
			ExAddFrame exadd = new ExAddFrame("운동 등록", this, id, name);
			exadd.setLocationRelativeTo(null); // 프레임 정가운데 출력
			
		}else if(obj == btnCancel) {
			this.dispose();
			MainFrame mf = new MainFrame(id);
			mf.setLocationRelativeTo(null); 
		}else if(obj == btnCheck) {
			if(comboEx.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "운동 기구를 선택해주세요.");
			} else {
			rcal = new ArrayList<String>();
			String ex_date = tfDate.getText();
			String ex_name = comboEx.getSelectedItem().toString();
			String ex_weight = tfWeight.getText();
			String ex_times = tfTime.getText();
			String ex_reps = tfNum.getText();
			String ex_set = tfSet.getText();
			
			rcal.add(ex_date);
			rcal.add(ex_name);
			rcal.add(ex_weight);
			rcal.add(ex_times);
			rcal.add(ex_reps);
			rcal.add(ex_set);
			
			DB db = new DB(null, null);
			db.EXInsert(id, name, ex_date, ex_name, ex_weight, ex_times, ex_reps, ex_set);
			MyRoutine mrt = new MyRoutine(id, name);
			mrt.setLocationRelativeTo(null); // 프레임 정가운데 출력
			// 전우진 기록지 보여주면서 해당 창 종료
			this.dispose();
			db.EXRefresh(mrt, id);
			}
		} else if(obj == btnDel) {
			if(comboEx.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "추가한 운동을 지워주세요.");
			} else {
				vecCombo.remove(comboEx.getSelectedIndex());
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