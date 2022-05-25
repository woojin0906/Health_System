package frame.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

public class Board2_PT extends JFrame implements ActionListener{
	
	
	private JPanel panelUP;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel lblTilte;
	private JTextArea ta;
	private JTextField tfsearch;
	private JButton btnsearch;
	private JButton btnWrite;
	private JScrollPane sp;
	private Color skyblue;

	public Board2_PT(String title) {
		skyblue = new Color(189, 215, 238);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 300);
		setSize(420, 600);
		setLayout(new BorderLayout());
		
		PanelUP();
		PanelDowm();
		
		setVisible(true);
	}

	private void PanelUP() {
		panelUP = new JPanel();
		panelUP.setLayout(new GridLayout(2,1));
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setPreferredSize(new DimensionUIResource(100,50));
		panel1.setBackground(skyblue);
		lblTilte = new JLabel("PT게시판");
		lblTilte.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 25));
		lblTilte.setLocation(20, 20);
		lblTilte.setSize(150, 30);
		panel1.add(lblTilte);
		
		btnWrite = new JButton(new ImageIcon("imges/pencil1.png"));
		//btnWrite.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
		btnWrite.setBorderPainted(false);
		btnWrite.setContentAreaFilled(false);
		btnWrite.setFocusPainted(false);
		btnWrite.setLocation(300, 20);
		btnWrite.setSize(130, 24);
		
		btnWrite.addActionListener(this);
		panel1.add(btnWrite);
		
		panelUP.add(panel1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setPreferredSize(new DimensionUIResource(100,50));
		panel2.setBackground(skyblue);
		
		tfsearch = new JTextField(30);
		tfsearch.setBounds(20, 10, 300, 25);
		tfsearch.setBorder(BorderFactory.createEmptyBorder());
		tfsearch.setFont(new Font("210 맨발의청춘 L", Font.PLAIN, 12));
		
		ImageIcon imgtfsearch = new ImageIcon("imges/textimage_edit.png");
		JLabel lbltfsearch = new JLabel(imgtfsearch);
		lbltfsearch.setBounds(8, 2, 330, 40);
		panel2.add(lbltfsearch);
		
		panel2.add(tfsearch);
		
		btnsearch = new JButton(new ImageIcon("imges/btnsearch2.png"));
		btnsearch.setBounds(325, 10, 70, 25);
		btnsearch.setBorderPainted(false);
		btnsearch.setContentAreaFilled(false);
		btnsearch.setFocusPainted(false);
		btnsearch.setFont(new Font("210 맨발의청춘 L", Font.BOLD, 10));
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
		ta.setEditable(false);
		add(sp,BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BoardWrite_PT bwpt = new BoardWrite_PT("글쓰기");
		
	}
}
