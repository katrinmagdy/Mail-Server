package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import java.awt.Choice;
import java.awt.ScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

public class Viewlist extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JList list;
	private JButton btnNewButton_2; //delete button
	private JButton btnNewButton_8; //edit button
	
	static App a = new App();
	static Folder folder = new Folder();
	static Filter filter = new Filter();
	static Sort sort =new Sort();
	
	Mail[] listofmails;
	String[] mails;
	int x=1;
	private JTextField Search1;
	private JTextField Filter1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Viewlist frame = new Viewlist(a,folder,filter,sort);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void close(){
		 WindowEvent winClosingEvent=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		 java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	 }

	/**
	 * Create the frame.
	 */
	public Viewlist(App a, Folder folder, Filter filter , Sort sort) {
		
		a.setViewingOptions(folder, filter, sort);
		
		
		
		listofmails = (Mail[]) a.listEmails(1) ;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 496);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    list = new JList();
		list.setVisibleRowCount(10);
		list.setBounds(57, 67, 409, 297);
		contentPane.add(list);
		
		mails = new String[listofmails.length];
		
		for(int i=0;i<mails.length;i++) {
			if(listofmails[i]!=null) {
				mails[i] = "Subject: "+listofmails[i].subject +",Sender: "+listofmails[i].sender+",Priority: "+listofmails[i].priority+",Date: "+listofmails[i].date;
			}
			else {
				mails[i]=null;
			}
		}
		
		list.setListData(mails);
		
		JButton btnNewButton = new JButton("View");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = list.getSelectedIndex();
				if(n==-1) {
					JOptionPane.showMessageDialog(null, "Please choose a mail to view");
				}
				else {
					if(listofmails[n]!=null) {
						SeeMessage s = new SeeMessage(listofmails[n]);
						s.setVisible(true);
						}
				}
				
			}
		});
		btnNewButton.setBounds(381, 399, 85, 21);
		contentPane.add(btnNewButton);
		
	    btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] n =list.getSelectedIndices();
				if(n.length==0) {
					JOptionPane.showMessageDialog(null, "Please choose a mail to delete");
				}
				else {
					DLinkedList d = new DLinkedList();
					for(int i=0;i<n.length;i++) {
						if(listofmails[n[i]]!=null) {
							d.add(listofmails[n[i]]);
						}
					}
					a.deleteEmails(d);
					if(a.mails.size()!=0) {
						listofmails= (Mail[]) a.listEmails(x);
						for(int i=0;i<mails.length;i++) {
							if(listofmails[i]!=null) {
								mails[i] = "Subject: "+listofmails[i].subject +",Sender: "+listofmails[i].sender+",Priority: "+listofmails[i].priority+",Date: "+listofmails[i].date;
							}
							else {
								mails[i]=null;
							}
						}
						list.setListData(mails);
					}
					else {
						JOptionPane.showMessageDialog(null, "No mails");
						close();
						UserFilesWindow u = new UserFilesWindow();
						u.setApp(a);
						u.show();
					}
				}
				
				
				
			}
		});
		btnNewButton_2.setBounds(286, 399, 85, 21);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Mails");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD, lblNewLabel.getFont().getSize() + 10f));
		lblNewLabel.setBounds(218, 24, 71, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_5 = new JButton("Next Page");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(x==a.maxpages) {
					JOptionPane.showMessageDialog(null, "Last Page");
				}
				else {
					x++; textField.setText(x+"");
					listofmails= (Mail[]) a.listEmails(x);
					for(int i=0;i<mails.length;i++) {
						if(listofmails[i]!=null) {
							mails[i] = "Subject: "+listofmails[i].subject +",Sender: "+listofmails[i].sender+",Priority: "+listofmails[i].priority+",Date: "+listofmails[i].date;
						}
						else {
							mails[i]=null;
						}
					}
					list.setListData(mails);
				}
			}
		});
		btnNewButton_5.setBounds(381, 374, 85, 21);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Prev Page");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(x>1) {
					x--;textField.setText(x+"");
					listofmails= (Mail[]) a.listEmails(x);
					for(int i=0;i<mails.length;i++) {
						if(listofmails[i]!=null) {
							mails[i] = "Subject: "+listofmails[i].subject +",Sender: "+listofmails[i].sender+",Priority: "+listofmails[i].priority+",Date: "+listofmails[i].date;
						}
						else {
							mails[i]=null;
						}
					}
					list.setListData(mails);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no page before it");
				}
			}
		});
		btnNewButton_6.setBounds(57, 374, 85, 21);
		contentPane.add(btnNewButton_6);
			
		JButton btnNewButton_7 = new JButton("Back");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFilesWindow u = new UserFilesWindow();
				a.mails.clear();
				close();
				u.setApp(a);
				u.show();
			}
		});
		btnNewButton_7.setBounds(476, 343, 85, 21);
		contentPane.add(btnNewButton_7);
				
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(202, 374, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("1");
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.mails.clear();
				a.setViewingOptions(folder, filter, sort);
				listofmails= (Mail[]) a.listEmails(x);
				for(int i=0;i<mails.length;i++) {
					if(listofmails[i]!=null) {
						mails[i] = "Subject: "+listofmails[i].subject +",Sender: "+listofmails[i].sender+",Priority: "+listofmails[i].priority+",Date: "+listofmails[i].date;
					}
					else {
						mails[i]=null;
					}
				}
				list.setListData(mails);
			}
		});
		btnNewButton_1.setBounds(476, 312, 85, 21);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_8 = new JButton("Edit");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = list.getSelectedIndex();
				if(listofmails[n]!=null) {
					EditDraft s = new EditDraft(listofmails[n]);
					s.setApp(a);
					close();
					s.setVisible(true);
					}
				
			}
		});
		btnNewButton_8.setBounds(57, 399, 85, 21);
		contentPane.add(btnNewButton_8);
		
		JComboBox Sort = new JComboBox();
		Sort.setModel(new DefaultComboBoxModel(new String[] {"None", "Subject", "Date", "Receiver", "Sender", "Attachment", "Body"}));
		Sort.setBounds(476, 159, 124, 21);
		contentPane.add(Sort);
		
		JComboBox Search = new JComboBox();
		Search.setModel(new DefaultComboBoxModel(new String[] {"None", "Subject", "Date", "Receiver", "Sender", "Attachment", "Body"}));
		Search.setBounds(476, 82, 124, 21);
		contentPane.add(Search);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(lblNewLabel_1.getFont().deriveFont(lblNewLabel_1.getFont().getSize() + 2f));
		lblNewLabel_1.setBounds(476, 63, 63, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sort");
		lblNewLabel_2.setFont(lblNewLabel_2.getFont().deriveFont(lblNewLabel_2.getFont().getSize() + 2f));
		lblNewLabel_2.setBounds(476, 142, 45, 15);
		contentPane.add(lblNewLabel_2);
		
		Search1 = new JTextField();
		Search1.setToolTipText("");
		Search1.setBounds(475, 113, 161, 19);
		contentPane.add(Search1);
		Search1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Filter");
		lblNewLabel_3.setFont(lblNewLabel_3.getFont().deriveFont(lblNewLabel_3.getFont().getSize() + 2f));
		lblNewLabel_3.setBounds(476, 190, 45, 21);
		contentPane.add(lblNewLabel_3);
		
		JComboBox Filter = new JComboBox();
		Filter.setModel(new DefaultComboBoxModel(new String[] {"None", "Sender", "Subject"}));
		Filter.setBounds(476, 212, 124, 21);
		contentPane.add(Filter);
		
		Filter1 = new JTextField();
		Filter1.setBounds(476, 243, 160, 19);
		contentPane.add(Filter1);
		Filter1.setColumns(10);
		
		JButton Apply = new JButton("Apply");
		Apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter.resetinfo();  sort.resetinfo();
				String searchaccto; String searchword;
				String sortaccto;
				String fileraccto; String filterword;
				
				if(!Search.getSelectedItem().equals("None")) {
					searchaccto = (String) Search.getSelectedItem();
					searchword=Search1.getText().trim();
					if(searchword.isBlank()) {
						JOptionPane.showMessageDialog(null, "Write the word you want to search for");
					}
					else {
						filter.searchAccTo=searchaccto;
						filter.searchword=searchword;
					}
				}
				
				if(!Sort.getSelectedItem().equals("None")) {
					sortaccto=(String) Sort.getSelectedItem();
					sortaccto=sortaccto.toLowerCase();
					sort.accto=sortaccto;
				}
				
				if(!Filter.getSelectedItem().equals("None")) {
					fileraccto=(String) Filter.getSelectedItem();
					if(fileraccto.equals("Subject")) {
						filterword=Filter1.getText().trim();
						if(filterword.isBlank()) {
							JOptionPane.showMessageDialog(null, "Write the subject you want to filter");
						}
						else {
							filter.filtersubject=filterword;
						}
					}
					else if(fileraccto.equals("Sender")) {
						filterword=Filter1.getText().trim();
						if(filterword.isBlank()) {
							JOptionPane.showMessageDialog(null, "Write the subject you want to filter");
						}
						else {
							filter.filtersender=filterword;
						}
					}
				}
				
				a.setViewingOptions(folder, filter, sort);
				if(a.maxpages==0) {
					JOptionPane.showMessageDialog(null, "No mails");
				}
				
				listofmails= (Mail[]) a.listEmails(x);
				for(int i=0;i<mails.length;i++) {
					if(listofmails[i]!=null) {
						mails[i] = "Subject: "+listofmails[i].subject +",Sender: "+listofmails[i].sender+",Priority: "+listofmails[i].priority+",Date: "+listofmails[i].date;
					}
					else {
						mails[i]=null;
					}
				}
				list.setListData(mails);
				
			}
		});
		Apply.setBounds(486, 270, 71, 21);
		contentPane.add(Apply);
		
		JButton btnNewButton_3 = new JButton("Reset");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter.resetinfo();
				sort.resetinfo();
				
                a.setViewingOptions(folder, filter, sort);
				
				listofmails= (Mail[]) a.listEmails(x);
				for(int i=0;i<mails.length;i++) {
					if(listofmails[i]!=null) {
						mails[i] = "Subject: "+listofmails[i].subject +",Sender: "+listofmails[i].sender+",Priority: "+listofmails[i].priority+",Date: "+listofmails[i].date;
					}
					else {
						mails[i]=null;
					}
				}
				list.setListData(mails);
			}
		});
		btnNewButton_3.setBounds(567, 270, 71, 21);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(57, 67, 409, 297);
		contentPane.add(scrollPane);
		btnNewButton_8.setVisible(false);
		
		if(a.maxpages==0) {
			JOptionPane.showMessageDialog(null, "No mails");
			close();
		}
		else {
			if(!folder.folder.equals("Trash")) {
				btnNewButton_2.show(true);
			}
			
			if(folder.folder.equals("Drafts")) {
				btnNewButton_8.show(true);;
			}
		}
		
	}
}