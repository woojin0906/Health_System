//작성자: 김지웅
package frame.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class StoreInfo extends JFrame {
	
	private Color mainColor;
	private Color subColor;
	private Font titleFont;
	private Font infoFont;
	private JLabel[] titleLbl;
	private JLabel[] infoLbl;

	public StoreInfo(){
		setTitle("센터 정보");
		setBounds(95, 100, 350, 500);
		setResizable(false); //창 크기 조절 불가능하게 만들기
		setLocationRelativeTo(null); //정가운데 출력
		setLayout(new BorderLayout());
		mainColor = new Color(189,215,238);
		subColor = new Color(189, 189, 189);
		titleFont = new Font("210 맨발의청춘 L", 0, 18);
		infoFont = new Font("210 맨발의청춘 L", 0, 16);
		
		setNorthPanel();
		setCenterPanel();
		
		setVisible(true);
	}
	
	private void setNorthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		southPanel.setPreferredSize(new Dimension(350, 140));
		
		JLabel imglbl = new JLabel(new ImageIcon("imges/InfoStoreImg.png"));
		imglbl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		southPanel.add(imglbl);
		
		add(southPanel, BorderLayout.NORTH);
	}
	
	private void setCenterPanel() {
		JPanel CenterPanel = new JPanel();
		CenterPanel.setLayout(new BorderLayout());
		add(CenterPanel, BorderLayout.CENTER);
		
		JPanel TitlePanel = new JPanel();
		TitlePanel.setPreferredSize(new Dimension(105, 370));
		TitlePanel.setBackground(mainColor);
		TitlePanel.setBorder(new LineBorder(subColor));
		CenterPanel.add(TitlePanel, BorderLayout.WEST);
		
		String[] titleText = {"센터이름", "영업시간", "센터위치", "문의하기"};
		titleLbl = new JLabel[4];
		
		for(int i = 0; i < titleText.length; i++) {
			titleLbl[i] = new JLabel(titleText[i]);
			titleLbl[i].setFont(titleFont);
			TitlePanel.add(titleLbl[i]);
			titleLbl[i].setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		}
		
		JPanel InfoPanel = new JPanel();
		InfoPanel.setBackground(Color.WHITE);
		InfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		InfoPanel.setBorder(new LineBorder(subColor));
		CenterPanel.add(InfoPanel, BorderLayout.CENTER);
		
		String[] infoText = {"건강해GYM", "월~일요일 00:00 ~ 24:00",
				"인천 미추홀구 인하로 777길 2층", "032-256-3652"};
		infoLbl = new JLabel[4];
		
		for(int i = 0; i < infoText.length; i++) {
			infoLbl[i] = new JLabel(infoText[i]);
			infoLbl[i].setFont(infoFont);
			InfoPanel.add(infoLbl[i]);
			infoLbl[i].setBorder(BorderFactory.createEmptyBorder(17, 5, 0, 0));
		}
		
		JLabel QR = new JLabel(new ImageIcon("imges/chatQr.jpg"));
		QR.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0));
		InfoPanel.add(QR);
	}

	public static void main(String[] args) {
		StoreInfo test = new StoreInfo();
	}
}
