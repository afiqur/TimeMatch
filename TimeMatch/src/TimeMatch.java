import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeMatch extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel Clock;
	private JLabel lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeMatch frame = new TimeMatch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;

	/**
	 * Create the frame.
	 */
	public TimeMatch() {
		initialize();
		showTime();
		clock();
		showMessage();
	}

	public void showMessage() {
		Thread showMessage = new Thread() {
			public void run() {
				try {

					for (;;) {
						if (textField.getText().equals(Clock.getText())) {

							JOptionPane.showMessageDialog(null, "Matched");
						} else {

						}
						sleep(59000);
					}

				}

				catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};

		showMessage.start();
	}

	public void showTime() {
		try {
			String query = "select * from TimeM";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				textField.setText(rs.getString("CurrentTime"));
			}
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int hours = 0, minutes = 0;
	String timeString = "";

	Thread t = null;

	public void clock2() {
		t = new Thread(this);
		t.start();
	}

	public void printTime() {
		Clock.setText(timeString);
	}

	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {

					for (;;)

					// while(true)

					{

						Calendar cal = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
						Date date = cal.getTime();
						String timeString = formatter.format(date);
						Clock.setText(timeString);

						// Calendar cal = new GregorianCalendar();
						// int AM_PM = cal.get(Calendar.AM_PM);
						// int minute = cal.get(Calendar.MINUTE);
						// int hour = cal.get(Calendar.HOUR_OF_DAY);
						//
						// Clock.setText(""+hour+":"+minute+" "+AM_PM+"");

						// sleep(1000);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		clock.start();

	}

	public void initialize() {
		conn = SQLiteConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(70, 81, 182, 70);
		contentPane.add(textField);
		textField.setColumns(10);

		Clock = new JLabel("");
		Clock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Clock.setBounds(70, 24, 182, 46);
		contentPane.add(Clock);

		lbl = new JLabel("New label");
		lbl.setBounds(191, 179, 112, 36);
		contentPane.add(lbl);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
