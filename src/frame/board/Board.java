package frame.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Board extends JFrame{
	
	
	private JPanel panelUP;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel lblTilte;
	private JTextArea ta;
	private JTextField tfsearch;
	private JButton btnsearch;
	private JButton btnWrite;
	private Color skyblue;
	private JScrollPane sp;

	public Board(String title) {
		skyblue = new Color(189, 215, 238);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(380, 600);
		setLayout(new BorderLayout());
		
		PanelUP();
		PanelDowm();
		
		setVisible(true);
	}

	private void PanelUP() {
		panelUP = new JPanel();
		panelUP.setLayout(new GridLayout(2,1));
		
		panel1 = new JPanel();
		panel1.setBackground(skyblue);
		panel1.setLayout(null);
		lblTilte = new JLabel("자유게시판");
		lblTilte.setFont(new Font("굴림", Font.BOLD, 20));
		lblTilte.setLocation(20, 10);
		lblTilte.setSize(150, 30);
		panel1.add(lblTilte);
		
		btnWrite = new JButton("글쓰기");
		btnWrite.setLocation(280, 10);
		btnWrite.setSize(80, 30);
		panel1.add(btnWrite);
		
		panelUP.add(panel1);
		
		panel2 = new JPanel();
		panel2.setBackground(skyblue);
		tfsearch = new JTextField(30);
		panel2.add(tfsearch);
		
		btnsearch = new JButton("검색");
		panel2.add(btnsearch);
		
		panelUP.add(panel2);
		
		add(panelUP,BorderLayout.NORTH);
		
		
	}

	private void PanelDowm() {
		ta = new JTextArea(40,40);
		sp = new JScrollPane(ta, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ta.setLineWrap(true);
		//ta.setEditable(false);
		add(sp,BorderLayout.CENTER);
		
	}
}
