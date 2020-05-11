package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInformation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static App a=new App();
	/**
	 * Launch the application.
	 */
	public void setApp(App a) {
		   this.a=a;
		   
	   }
	public void close(){
		 WindowEvent winClosingEvent=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		 java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	 }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInformation frame = new UserInformation(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInformation(App a) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 346);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesTitle = new JLabel("User Information");
		lblNewJgoodiesTitle.setFont(lblNewJgoodiesTitle.getFont().deriveFont(lblNewJgoodiesTitle.getFont().getStyle() | Font.BOLD, lblNewJgoodiesTitle.getFont().getSize() + 7f));
		lblNewJgoodiesTitle.setBounds(225, 10, 178, 32);
		contentPane.add(lblNewJgoodiesTitle);
		
		JLabel lblNewJgoodiesTitle_1 = new JLabel("Email");
		lblNewJgoodiesTitle_1.setFont(lblNewJgoodiesTitle_1.getFont().deriveFont(lblNewJgoodiesTitle_1.getFont().getStyle() | Font.BOLD, lblNewJgoodiesTitle_1.getFont().getSize() + 3f));
		lblNewJgoodiesTitle_1.setBounds(10, 136, 88, 14);
		contentPane.add(lblNewJgoodiesTitle_1);
		
		JLabel lblNewJgoodiesTitle_2 = new JLabel("Password");
		lblNewJgoodiesTitle_2.setFont(lblNewJgoodiesTitle_2.getFont().deriveFont(lblNewJgoodiesTitle_2.getFont().getStyle() | Font.BOLD, lblNewJgoodiesTitle_2.getFont().getSize() + 3f));
		lblNewJgoodiesTitle_2.setBounds(10, 204, 88, 14);
		contentPane.add(lblNewJgoodiesTitle_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setEnabled(false);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(97, 133, 462, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(a.email);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(0, 0, 0));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setEnabled(false);
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField_1.setBounds(97, 201, 462, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(a.password);
		
		JButton btnNewButton_2 = new JButton("Back to User Folders");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				UserFilesWindow u=new UserFilesWindow();u.setApp(a);
				u.setVisible(true);
			}
		});
		btnNewButton_2.setFont(btnNewButton_2.getFont().deriveFont(btnNewButton_2.getFont().getStyle() | Font.BOLD, btnNewButton_2.getFont().getSize() + 5f));
		btnNewButton_2.setBounds(178, 257, 189, 42);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewJgoodiesTitle_3 = new JLabel("User Name");
		lblNewJgoodiesTitle_3.setFont(lblNewJgoodiesTitle_3.getFont().deriveFont(lblNewJgoodiesTitle_3.getFont().getStyle() | Font.BOLD, lblNewJgoodiesTitle_3.getFont().getSize() + 3f));
		lblNewJgoodiesTitle_3.setBounds(10, 75, 98, 20);
		contentPane.add(lblNewJgoodiesTitle_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setEnabled(false);
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		textField_2.setBounds(97, 75, 462, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(a.UserName);
	}

}