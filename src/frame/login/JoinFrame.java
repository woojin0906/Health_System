package frame.login;
// 202145022 전우진
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import frame.db.dbOpen;

public class JoinFrame extends JFrame implements MouseListener, ActionListener, WindowListener{
	
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JButton btnCancel;
	private JButton btnImg;
	private JButton btnNext;
	private JLabel lblLoginInfo;
	private JLabel lblAdmin;
	private JTextField tfId;
	private JPasswordField tfPassword;
	private JPasswordField tfPsCheck;
	private JTextField tfHint;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfAddress;
	private Vector<String> vecCombo;
	private JComboBox<String> comboHint;
	private JLabel lblJoin;
	private JCheckBox agreeCheck;
	private Color a;
	private AgreeFrame af;
	private Font mainFont;
	private Font subFont;
	private String path;
	private ImageIcon profileImg;
	private Login login;
	private JButton btnOver;
	private String joinImg;
	private dbOpen db;

	public JoinFrame(String title) {
		setTitle(title);
		setResizable(false); //창 크기 조절 불가능하게 만들기
		setLocation(250, 150);
		setSize(340, 550);
		setLayout(new BorderLayout());
		addWindowListener(this);
		
		mainFont = new Font("210 맨발의청춘 L", Font.BOLD, 15); // 서브 정보
	    subFont = new Font("210 맨발의청춘 L", Font.PLAIN, 13); 
	    
		setCenter();
		setSouth();
		
		setVisible(true);
	}

	private void setCenter() {
		panelCenter = new JPanel(new GridLayout(3, 1));
		a = new Color(189, 215, 238);
		
		// 회원가입 화면 첫번째 패널
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(a);
		
		// 회원가입 화면 취소 버튼
		btnCancel = new JButton("취소");
		btnCancel.setFont(subFont);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBounds(0, 5, 70, 30);
		
		btnCancel.addActionListener(this);
		
		panel1.add(btnCancel);
		
		// 회원가입 화면 제목 출력
		lblJoin = new JLabel("회원가입");
		lblJoin.setBounds(120, 15, 100, 20);
		lblJoin.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 20)); 
		panel1.add(lblJoin);
		
		// 회원가입 프로필 이미지 버튼
		profileImg = new ImageIcon("imges/Person.png");
		btnImg = new JButton(profileImg);
		btnImg.setContentAreaFilled(false);
		btnImg.setBorderPainted(false);
		btnImg.setBounds(110, 50, 100, 100);
		
		btnImg.addActionListener(this);
		
		panel1.add(btnImg);
		
		// 회원가입 화면 두번째 패널
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(a);
		
		// 회원가입 화면 로그인 정보 라벨 출력
		lblLoginInfo = new JLabel("로그인 정보");
		lblLoginInfo.setFont(mainFont);
		lblLoginInfo.setBounds(15, 0, 150, 35);
		panel2.add(lblLoginInfo);
		
		// 회원가입 화면 텍스트 필드(아이디) 출력
		tfId = new JTextField("아이디");
		tfId.setBounds(20, 38, 130, 29);
		tfId.setBorder(BorderFactory.createEmptyBorder());
		tfId.setFont(subFont);
		
		tfId.addMouseListener(this);
		
		panel2.add(tfId);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgId = new ImageIcon("imges/background_id.png");
		JLabel lblId = new JLabel(imgId);
		lblId.setBounds(13, 35, 148, 35);
		panel2.add(lblId);
				
		// 회원가입 화면 아이디 중복 확인 버튼 출력
		btnOver = new JButton("중복 확인");
		btnOver.setBounds(175, 38, 90, 29);
		btnOver.setContentAreaFilled(false);
		btnOver.setBorderPainted(false);
		btnOver.setForeground(Color.WHITE);
		btnOver.setFont(subFont);
		
		btnOver.addActionListener(this);
		
		panel2.add(btnOver);
		
		// 회원가입 화면 아이디 중복 배경 이미지 출력
		ImageIcon imgover = new ImageIcon("imges/overlap.png");
		JLabel lblover = new JLabel(imgover);
		lblover.setBounds(165, 35, 110, 35);
		panel2.add(lblover);
	
		// 회원가입 화면 텍스트 필드(비밀번호) 출력
		tfPassword = new JPasswordField("1111");
		tfPassword.setBounds(20, 78, 130, 29);
		tfPassword.setBorder(BorderFactory.createEmptyBorder());
		tfPassword.setFont(new Font("바탕체", 0, 14));

		tfPassword.addMouseListener(this);
		
		panel2.add(tfPassword);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgPw = new ImageIcon("imges/background_id.png");
		JLabel lblPw = new JLabel(imgPw);
		lblPw.setBounds(13, 75, 148, 35);
		panel2.add(lblPw);
		
		// 회원가입 화면 텍스트 필드(비밀번호 확인) 출력
		tfPsCheck = new JPasswordField("1111");
		tfPsCheck.setBounds(170, 78, 130, 29);
		tfPsCheck.setBorder(BorderFactory.createEmptyBorder());
		tfPsCheck.setFont(new Font("바탕체", 0, 14));

		tfPsCheck.addMouseListener(this);
		
		panel2.add(tfPsCheck);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgPwCh = new ImageIcon("imges/background_id.png");
		JLabel lblPwCh = new JLabel(imgPwCh);
		lblPwCh.setBounds(165, 75, 148, 35);
		panel2.add(lblPwCh);
		
		// 회원가입 화면 콤보박스 출력
		vecCombo = new Vector<String>();
		vecCombo.add("생일은?");
		vecCombo.add("번호 뒷자리는?");
		
		comboHint = new JComboBox<String>(vecCombo);
		comboHint.setBounds(20, 118, 85, 29);
		comboHint.setBorder(BorderFactory.createEmptyBorder(-2, -2, -2, -2));
		comboHint.setBackground(Color.WHITE);
		comboHint.setFont(subFont);
		panel2.add(comboHint);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgCombo = new ImageIcon("imges/background_combo.png");
		JLabel lblCombo = new JLabel(imgCombo);
		lblCombo.setBounds(11, 115, 100, 35);
		panel2.add(lblCombo);
		
		// 회원가입 화면 비밀번호 힌트 출력
		tfHint = new JTextField("힌트 답변");
		tfHint.setBounds(125, 119, 185, 30);
		tfHint.setBorder(BorderFactory.createEmptyBorder());
		tfHint.setFont(subFont);

		tfHint.addMouseListener(this);
		
		panel2.add(tfHint);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgHint = new ImageIcon("imges/background_hint.png");
		JLabel lblHint = new JLabel(imgHint);
		lblHint.setBounds(118, 115, 195, 35);
		panel2.add(lblHint);
		
		// 회원가입 화면 세번째 패널
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBackground(Color.WHITE);
		
		// 회원가입 화면 개인정보 라벨 출력
		lblAdmin = new JLabel("개인 정보");
		lblAdmin.setFont(mainFont);
		lblAdmin.setBounds(15, 0, 150, 35);
		panel3.add(lblAdmin);
		
		// 회원가입 화면 텍스트 필드(이름) 출력
		tfName = new JTextField("이름");
		tfName.setBounds(20, 38, 130, 29);
		tfName.setBorder(BorderFactory.createEmptyBorder());
		tfName.setFont(subFont);
		
		tfName.addMouseListener(this);
		
		panel3.add(tfName);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgName = new ImageIcon("imges/background_id.png");
		JLabel lblName = new JLabel(imgName);
		lblName.setBounds(13, 35, 150, 35);
		panel3.add(lblName);

		// 회원가입 화면 텍스트 필드(이름) 출력
		tfPhone = new JTextField("전화번호");
		tfPhone.setBounds(20, 78, 130, 29);
		tfPhone.setBorder(BorderFactory.createEmptyBorder());
		tfPhone.setFont(subFont);

		tfPhone.addMouseListener(this);
		
		panel3.add(tfPhone);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgPhone = new ImageIcon("imges/background_id.png");
		JLabel lblPhone = new JLabel(imgPhone);
		lblPhone.setBounds(13, 75, 150, 35);
		panel3.add(lblPhone);
		
		// 회원가입 화면 텍스트 필드(주소) 출력
		tfAddress = new JTextField("주소");
		tfAddress.setBounds(20, 119, 280, 29);
		tfAddress.setBorder(BorderFactory.createEmptyBorder());
		tfAddress.setFont(subFont);

		tfAddress.addMouseListener(this);
		
		panel3.add(tfAddress);
		
		// 회원가입 화면 텍스트 필드 배경 이미지 출력
		ImageIcon imgAddress = new ImageIcon("imges/background_address.png");
		JLabel lblAddress = new JLabel(imgAddress);
		lblAddress.setBounds(13, 115, 290, 35);
		panel3.add(lblAddress);
		
		panelCenter.add(panel1);
		panelCenter.add(panel2);
		panelCenter.add(panel3);
		
		add(panelCenter);
		
	}

	private void setSouth() {
		panelSouth = new JPanel();
		panelSouth.setLayout(null);
		panelSouth.setPreferredSize(new Dimension(340, 50));
		panelSouth.setBackground(Color.WHITE);
		
		// 회원가입 화면 체크박스 출력
		agreeCheck = new JCheckBox("약관에 동의합니다.(필수)");
		agreeCheck.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 13));
		agreeCheck.setBounds(5, 10, 170, 30);
		agreeCheck.setBackground(Color.WHITE);
		
		agreeCheck.addActionListener(this);
		
		panelSouth.add(agreeCheck);
	
		
		// 회원가입 화면 다음 버튼 이미지 출력
		ImageIcon buttonImg = new ImageIcon("imges/nextbutton.png");
		btnNext = new JButton(buttonImg);
		btnNext.setContentAreaFilled(false);
		btnNext.setBorderPainted(false);
		btnNext.setBounds(250, 0, 70, 50);
		btnNext.addActionListener(this);
		
		panelSouth.add(btnNext);
		add(panelSouth, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnCancel) {
			this.dispose();
		} else if(obj == agreeCheck) {
			if(agreeCheck.isSelected() == false) {
				JOptionPane.showMessageDialog(this, "약관을 읽어주세요.", "이용약관 동의 안내", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				af = new AgreeFrame("이용약관", this);
			}
		} else if(obj == btnNext) {
			String id = tfId.getText();
			char[] pw = tfPassword.getPassword();
			char[] pwch = tfPsCheck.getPassword();
			String hint = tfHint.getText();
			String name = tfName.getText();
			String phone = tfPhone.getText();
			String address = tfAddress.getText();
			
				if(id.equals("아이디") || pw.equals("비밀번호")
						|| pwch.equals("비밀번호 확인") || hint.equals("힌트 답변") 
						|| name.equals("이름") || phone.equals("전화번호")
						||address.equals("주소")
						
						|| id.equals("") || pw.equals("") 
						|| pwch.equals("") || hint.equals("") 
						|| name.equals("") || phone.equals("")
						|| address.equals("")) {
				 JOptionPane.showMessageDialog(this, "입력 정보를 다시 확인해주세요.");
				} else {
					char[] temp = pw;
					String resultpw = "";
					String resultpwch = "";
					
					for(char ch	: temp) {
						Character.toString(ch);
						resultpw += ""+ch+"";
					}
					
					temp = pwch;
					
					for(char ch	: temp) {
						Character.toString(ch);
						resultpwch += ""+ch+"";
					}
					
					if(resultpw.equals(resultpwch)) {
						if(resultpw.length() <= 8 && resultpw.length() >= 6 && resultpwch.length() <= 8 && resultpwch.length() >= 6) {
							
							// DB 연결
							db = new dbOpen();
							db.infoInsert(tfId.getText(), tfName.getText(), tfPhone.getText(), tfAddress.getText(), tfPassword.getText(), tfHint.getText());
							JOptionPane.showMessageDialog(this, "회원가입이 완료 되었습니다");
					this.dispose();
					} else {
						JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상 8자리 이하로 작성해주세요.");
					}
				} else {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				}
			}
			
		} else if (obj == btnImg) {
			JFileChooser fc = new JFileChooser();
			try {
				fc.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
				fc.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpeg"));
				fc.addChoosableFileFilter(new FileNameExtensionFilter("GIF", "gif"));
				
				int showOpenDialog = fc.showOpenDialog(this);
				
				path = fc.getSelectedFile().getPath();
				profileImg = new ImageIcon(path);
				
				// DB 연결
				btnImg.setText(fc.getSelectedFile().getAbsolutePath());
				
				File selfile = fc.getSelectedFile();
				String path = selfile.getAbsolutePath();
				
				File dirfile = new File(btnImg.getText());
				
				File srcfile = new File("imges/" + btnImg.getText() + ".jpg");
				
				Files.copy(dirfile.toPath(), srcfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				//joinImg = temp.imageUpdate(path);
				//System.out.println(joinImg);
				
				Image img = profileImg.getImage();
				Image changeImg = img.getScaledInstance(95, 102, Image.SCALE_SMOOTH);
				
				btnImg.setIcon(new ImageIcon(changeImg));
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "이미지를 다시 선택해주세요.");
			} 
			
		} else if(obj == btnOver) {
			String id = tfId.getText();
			if(id.length() >= 4) {
				db = new dbOpen();
				db.checkID(this, id, tfId);
//				JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다.");
			} else {
				JOptionPane.showMessageDialog(this, "아이디는 4자리 이상으로 작성해주세요.");
			} 
		}
	}


	public JTextField getTfId() {
		return tfId;
	}

	public JTextField getTfPassword() {
		return tfPassword;
	}

	public JTextField getTfHint() {
		return tfHint;
	}

	public JCheckBox getAgreeCheck() {
		return agreeCheck;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		Object obj = e.getSource();
			if(obj == tfId) {
				tfId.setText("");
			} else if(obj == tfPassword) {
				tfPassword.setText("");
			} else if(obj == tfPsCheck) {
				tfPsCheck.setText("");
			} else if(obj == tfHint) {
				tfHint.setText("");
			} else if(obj == tfName) {
				tfName.setText("");
			} else if(obj == tfPhone) {
				tfPhone.setText("");
			} else if(obj == tfAddress) {
				tfAddress.setText("");
			} 
		}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
		if(agreeCheck.isSelected() == false) {
			if(JOptionPane.showConfirmDialog(this, 
					"약관에 동의하시겠습니까?",
					"약관 안내",
					JOptionPane.YES_NO_OPTION
					) == JOptionPane.YES_OPTION) {
				af = new AgreeFrame("이용약관", this);
				agreeCheck.setSelected(true);
			} else {
				this.dispose();
			}
		} else {
			this.setVisible(true);
		}
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
