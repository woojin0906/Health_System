package frame.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
// 메모창 전우진
//2022-05-19 윤선호 추가
public class MemoFrame extends JFrame implements ActionListener {

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
	
	public static int calYear;
	public static int calMonth;
	public static int calDayOfMon;
	
	private JLabel todayLab;
	private Calendarmain cm;
	private String btn_num;
	
	public MemoFrame(String title, Calendarmain cm) {
		setTitle(title);
		this.cm = cm;
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(250, 150);
		setSize(300, 300);
		setLayout(new BorderLayout());
		System.out.println(btn_num);
		setNorth();
		setCenter();
		setSouth();
		setVisible(true);
	}

	private void setNorth() {
		panelNorth = new JPanel();
		//lblDate = new JLabel("2022/5/15 (Today)");
		//panelNorth.add(lblDate);
		String num_day = btn_num;
		todayLab = new JLabel(today.get(Calendar.MONTH)+1+"/" + num_day + "/" + today.get(Calendar.YEAR));
		
		//today.get(Calendar.DAY_OF_MONTH) 
		
		//GregorianCalendar cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
		//calYear = today.get(Calendar.YEAR);
		//calMonth = today.get(Calendar.MONTH);
		//calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		
		//String dDayString = new String();
		
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
		btnClear.addActionListener(this);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		
		panelSouth.add(btnSave);
		panelSouth.add(btnClear);
		panelSouth.add(btnExit);
		
		add(panelSouth, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =  e.getSource();
		
		if(obj == btnClear) {
			taMemo.setText("");
		} 
		else if(obj == btnExit) {
			this.dispose();
		}
	}
}
