package frame.main;

import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoFrame extends JFrame {

	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JLabel lblDate;
	private JTextArea taMemo;
	private JButton btnSave;
	private JButton btnClear;
	private JButton btnExit;
	private JScrollPane sp;

	Calendar today = Calendar.getInstance();
	private JLabel todayLab;
	
	public MemoFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(250, 150);
		setSize(300, 300);
		setLayout(new BorderLayout());
		
		setNorth();
		setCenter();
		setSouth();
		
		setVisible(true);
	}

	private void setNorth() {
		panelNorth = new JPanel();
		//lblDate = new JLabel("2022/5/15 (Today)");
		//panelNorth.add(lblDate);
		todayLab = new JLabel(today.get(Calendar.MONTH)+1+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR));
		panelNorth.add(todayLab);
		add(panelNorth, BorderLayout.NORTH);
	}

	private void setCenter() {
		panelCenter = new JPanel();
		taMemo = new JTextArea(12, 29);
		panelCenter.add(taMemo);
		
		sp = new JScrollPane(taMemo, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelCenter.add(sp);
		
		add(panelCenter, BorderLayout.CENTER);
	}

	private void setSouth() {
		panelSouth = new JPanel();
		btnSave = new JButton("Save");
		btnClear = new JButton("Clear");
		btnExit = new JButton("Exit");
		
		panelSouth.add(btnSave);
		panelSouth.add(btnClear);
		panelSouth.add(btnExit);
		
		add(panelSouth, BorderLayout.SOUTH);
	}
}
