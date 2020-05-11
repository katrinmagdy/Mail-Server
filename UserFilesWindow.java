package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserFilesWindow extends JFrame {

	private JPanel contentPane;
	public App a = new App();
	Folder folder = new Folder();
	Filter filter = new Filter();
	Sort sort = new Sort();

	/**
	 * Launch the application.
	 */
	static UserFilesWindow frame = new UserFilesWindow();
	

	/**
	 * Create the frame.
	 */
	public void close(){
		 WindowEvent winClosingEvent=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		 java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	 }
	public void setApp(App a) {
		   this.a=a;
		   
	   }
	
	public UserFilesWindow() {
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 271);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Inbox");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    		InboxFolder files=new InboxFolder();close();
	    		folder.folder="Inbox";
	    		files.setfolder(folder);
	    		files.setApp(a);
	    		files.show();
			}
		});
		btnNewButton_1.setFont(btnNewButton_1.getFont().deriveFont(btnNewButton_1.getFont().getStyle() | Font.BOLD));
		btnNewButton_1.setBounds(10, 82, 207, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Trash");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				folder.folder="Trash";
				Viewlist files=new Viewlist(a,folder,filter,sort);
	    		files.show();
			}
		});
		btnNewButton_2.setFont(btnNewButton_2.getFont().deriveFont(btnNewButton_2.getFont().getStyle() | Font.BOLD));
		btnNewButton_2.setBounds(337, 125, 207, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Drafts ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				folder.folder="Drafts";
				Viewlist files=new Viewlist(a,folder,filter,sort);
	    		files.show();
			}
		});
		btnNewButton_3.setFont(btnNewButton_3.getFont().deriveFont(btnNewButton_3.getFont().getStyle() | Font.BOLD));
		btnNewButton_3.setBounds(10, 125, 207, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Sent mails");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				folder.folder="sent";
				Viewlist files=new Viewlist(a,folder,filter,sort);
	    		files.show();
			}
		});
		btnNewButton_4.setFont(btnNewButton_4.getFont().deriveFont(btnNewButton_4.getFont().getStyle() | Font.BOLD));
		btnNewButton_4.setBounds(337, 82, 207, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("User Folder");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD, lblNewLabel.getFont().getSize() + 14f));
		lblNewLabel.setBounds(208, 10, 162, 45);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				test t=new test();close();
				t.setVisible(true);
			}
		});
		btnNewButton.setFont(btnNewButton.getFont().deriveFont(btnNewButton.getFont().getStyle() | Font.BOLD));
		btnNewButton.setBounds(491, 198, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_7 = new JButton("Compose new Mail");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendAMessage s = new SendAMessage();close();
				s.setApp(a);
				s.show();
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_7.setBounds(213, 158, 133, 21);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("User Information");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				UserInformation u=new UserInformation(a);u.setApp(a);u.show();
			}
		});
		btnNewButton_8.setFont(btnNewButton_8.getFont().deriveFont(btnNewButton_8.getFont().getStyle() | Font.BOLD, btnNewButton_8.getFont().getSize() + 2f));
		btnNewButton_8.setBounds(10, 198, 207, 23);
		contentPane.add(btnNewButton_8);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}