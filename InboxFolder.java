package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InboxFolder extends JFrame {

	private JPanel contentPane;
	App a = new App();
	Folder folder = new Folder();
	Filter filter = new Filter();
	Sort sort = new Sort();

	/**
	 * Launch the application.
	 */
	static InboxFolder frame = new InboxFolder();
 static void main(String[] args) {
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
   public void setfolder(Folder a) {
	   this.folder=a;
	   
   }
   public void setfilter(Filter a) {
	   this.filter=a;
	   
   }
   
   public InboxFolder() {
		setDefaultCloseOperation(InboxFolder.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Default");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				folder.DP="D";
				close();
				Viewlist files=new Viewlist(a,folder,filter,sort);
	    		//2a7aded 2el sort by default
	    		files.show();
	

			}
		});
		btnNewButton.setBounds(5, 5, 175, 209);
		btnNewButton.setBackground(Color.MAGENTA);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(btnNewButton.getFont().deriveFont(btnNewButton.getFont().getStyle() | Font.BOLD, btnNewButton.getFont().getSize() + 14f));
		
		JButton btnNewButton_1 = new JButton("Priority");
		btnNewButton_1.setBounds(180, 5, 192, 209);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//2a7aded 2el sort by priority
				folder.DP="P";
				close();
				Viewlist files=new Viewlist(a,folder,filter,sort);
	    		files.show();
				
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setFont(btnNewButton_1.getFont().deriveFont(btnNewButton_1.getFont().getStyle() | Font.BOLD, btnNewButton_1.getFont().getSize() + 14f));
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Back To User Folders");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFilesWindow  u=new UserFilesWindow();
				a.mails.clear();
				u.setApp(a);
				close();
				u.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setFont(btnNewButton_2.getFont().deriveFont(btnNewButton_2.getFont().getStyle() | Font.BOLD, btnNewButton_2.getFont().getSize() + 14f));
		btnNewButton_2.setBounds(372, 5, 312, 209);
		contentPane.add(btnNewButton_2);
	}
}