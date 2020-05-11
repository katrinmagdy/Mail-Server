package eg.edu.alexu.csd.datastructure.mailServer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.DropMode;

public class EditDraft extends JFrame {

	private JPanel contentPane;
	private JTextField To;
	private JTextField Subject;
	private JTextArea Message;
	private JComboBox Priority;
	private JComboBox comboBox;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	
	App a = new App();
	Folder folder = new Folder();
	Filter filter = new Filter();
	Sort sort = new Sort();
	
	private String s[]=null;
	private SLinkedList attach = new SLinkedList();
	static Mail mail;
	int count=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditDraft frame = new EditDraft(mail);
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
	public void setApp(App a) {
		   this.a=a;
		   
	   }


	/**
	 * Create the frame.
	 */
	public EditDraft(Mail m) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 671, 552);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	  
		attach=m.attach;
		
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Mail newmail = new Mail();
				
				newmail.sender=a.email;
				
				String to = To.getText();
				String[] receiverslist = to.split(",");
				Queue finalreceivers = new Queue();
				for(int i=0;i<receiverslist.length;i++) {
					finalreceivers.enqueue(receiverslist[i].trim());
				}
				newmail.listreceivers=finalreceivers;
				
				newmail.subject=Subject.getText().trim();
				
				newmail.content=Message.getText();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				String date = format.format( new Date()   );
			    newmail.date=date;
			    
			    newmail.priority=(String) Priority.getSelectedItem();
			    
			    newmail.attach=attach;
			   
			    
			    if(a.compose(newmail)) {
			    	newmail.sendmailtosentbox();
			    	newmail.sendmailtoinbox();
			    	JOptionPane.showMessageDialog(null, "Mail is sent");
			    	UserFilesWindow u = new UserFilesWindow();close();
					u.setApp(a);
					u.show();
			    	
			    }
			    else {
			    	
			    	JOptionPane.showMessageDialog(null, "Wrong Inputs :Please Read the Notes and check all data required is correct");
			    }
			    
			    m.deletemailfromdarft();
				
				
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setFont(btnNewButton.getFont().deriveFont(btnNewButton.getFont().getStyle() | Font.BOLD, btnNewButton.getFont().getSize() + 6f));
		btnNewButton.setBounds(20, 459, 107, 34);
		contentPane.add(btnNewButton);
		
		To = new JTextField();
		To.setBounds(137, 29, 508, 20);
		contentPane.add(To);
		To.setColumns(10);
		
		String[] receivers = new String[m.listreceivers.size()];
		for(int i=0;i<receivers.length;i++) {
			receivers[i]=(String) m.listreceivers.dequeue();
		}
		
		for(int i=0;i<receivers.length;i++) {
		 m.listreceivers.enqueue(receivers[i]);;
		}
		
		if(receivers.length!=0) {
			String t = receivers[0];
			for(int i=1;i<receivers.length;i++) {
				t=t+", "+receivers[i];
			}
			
			To.setText(t);
		}
		
		Subject = new JTextField();
		Subject.setBounds(137, 75, 508, 20);
		contentPane.add(Subject);
		Subject.setColumns(10);
		
		Subject.setText(m.subject);
		
	    Message = new JTextArea();
	    Message.setWrapStyleWord(true);
	    Message.setLineWrap(true);
		Message.setBounds(22, 208, 606, 20);
		contentPane.add(Message);
		
		Message.setText(m.content);
		
		JLabel lblNewJgoodiesTitle = new JLabel("To");
		lblNewJgoodiesTitle.setFont(lblNewJgoodiesTitle.getFont().deriveFont(lblNewJgoodiesTitle.getFont().getStyle() | Font.BOLD, lblNewJgoodiesTitle.getFont().getSize() + 2f));
		lblNewJgoodiesTitle.setBounds(10, 31, 66, 14);
		contentPane.add(lblNewJgoodiesTitle);
		
		JLabel lblNewJgoodiesTitle_1 = new JLabel("Subject");
		lblNewJgoodiesTitle_1.setFont(lblNewJgoodiesTitle_1.getFont().deriveFont(lblNewJgoodiesTitle_1.getFont().getStyle() | Font.BOLD, lblNewJgoodiesTitle_1.getFont().getSize() + 2f));
		lblNewJgoodiesTitle_1.setBounds(10, 76, 66, 14);
		contentPane.add(lblNewJgoodiesTitle_1);
		
		JLabel lblNewJgoodiesTitle_2 = new JLabel("Message");
		lblNewJgoodiesTitle_2.setFont(lblNewJgoodiesTitle_2.getFont().deriveFont(lblNewJgoodiesTitle_2.getFont().getStyle() | Font.BOLD, lblNewJgoodiesTitle_2.getFont().getSize() + 2f));
		lblNewJgoodiesTitle_2.setBounds(253, 155, 102, 25);
		contentPane.add(lblNewJgoodiesTitle_2);
		
		JButton btnNewButton_1 = new JButton("Attachment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				j.setMultiSelectionEnabled(true); 
				int r =j.showOpenDialog(null); 
				if (r == JFileChooser.APPROVE_OPTION) { 
	                File f[] = j.getSelectedFiles(); 
	                String s[]= new String[f.length];
	                String[] n=null;
	                
	                for(int i=0;i<f.length;i++) {
	            		 s[i] =f[i].getAbsolutePath();
	            		 s[i]=s[i].replaceAll("\\\\", "\\\\\\\\");
	            	}
	                
	                for(int i=0;i<s.length;i++) {
	                	attach.add(s[i]);
	                }
	                
	                for(int i=0;i<s.length;i++) {
	                	File f1 = new File(s[i]);
	                	s[i]=f1.getName();
	                }
	                
	                n = new String[attach.size()];
	                for(int i=0;i<attach.size();i++) {
	                	n[i] = (String) attach.get(i);
	                	File f1 = new File(n[i]);
	                	n[i]=f1.getName();
	                
	                }
	                
	                final DefaultComboBoxModel model = new DefaultComboBoxModel(n);
	                comboBox.setVisible(true);
	                comboBox.setModel(model);
	                btnNewButton_5.setVisible(true);
	                btnNewButton_6.setVisible(true);
	                
	            } 
				
				
			}
		});
		btnNewButton_1.setFont(btnNewButton_1.getFont().deriveFont(btnNewButton_1.getFont().getStyle() | Font.BOLD));
		btnNewButton_1.setBounds(0, 134, 179, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Contacts");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count=0;
				Contact c = new Contact();
				SLinkedList s =c.getcontacts(a.email);
				if(s.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No contacts yet");
				}
				else {
					JFrame j = new JFrame();
					j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					j.setBounds(100, 100, 378, 395);
					JPanel contentPane = new JPanel();
					contentPane.setBackground(Color.CYAN);
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					j.setContentPane(contentPane);
					contentPane.setLayout(null);
					
					JLabel lblNewLabel = new JLabel("Contacts");
					lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD, lblNewLabel.getFont().getSize() + 8f));
					lblNewLabel.setBounds(128, 10, 112, 32);
					contentPane.add(lblNewLabel);
					
					DefaultMutableTreeNode cont=new DefaultMutableTreeNode("Contacts");  
					for(int i=0;i<s.size();i++) {
						SLinkedList d = (SLinkedList) s.get(i);
						DefaultMutableTreeNode x=new DefaultMutableTreeNode((String)d.get(0)); 
						for(int k=1;k<d.size();k++) {
							DefaultMutableTreeNode y=new DefaultMutableTreeNode((String)d.get(k)); 
							x.add(y);
						}
						
						cont.add(x);
					}
					
					JTree tree = new JTree(cont);
					tree.setBounds(27, 67, 244, 179);
					contentPane.add(tree);
					
					JComboBox  comboBox = new JComboBox();
					comboBox.setBounds(84, 256, 156, 21);
					contentPane.add(comboBox);
					
					JButton btnNewButton = new JButton("Add");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							boolean found = false;
							for(int i=0;i<s.size();i++) {
								SLinkedList d = (SLinkedList) s.get(i);
								if(tree.getLastSelectedPathComponent().toString().equals(d.get(0))) {
									found=true;
								}
							}
							
							if(found==false&&!tree.getLastSelectedPathComponent().toString().equals("Contacts")) {
								comboBox.insertItemAt(tree.getLastSelectedPathComponent().toString(), count);
								count++;
							}
							else {
								JOptionPane.showMessageDialog(null, "You must choose an email not name");
							}
						
						
						}
					});
					btnNewButton.setBounds(281, 140, 58, 21);
					contentPane.add(btnNewButton);
					
					
					
					JButton btnNewButton_1 = new JButton("Delete");
					btnNewButton_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							int x =comboBox.getItemCount();
							String[] s = new String[x-1];
							int y=0;
							for(int i=0;i<x-1;i++) {
								if(!comboBox.getItemAt(y).equals(comboBox.getSelectedItem())) {
									s[i]=comboBox.getItemAt(y).toString();
								}
								else {
									y++;
									if(y<x) {
										s[i]=comboBox.getItemAt(y).toString();
									}
									
								}
								y++;
								
							}
							
							final DefaultComboBoxModel model = new DefaultComboBoxModel(s);
							comboBox.setModel(model);
							count--;
						}
					});
					btnNewButton_1.setBounds(250, 256, 89, 21);
					contentPane.add(btnNewButton_1);
					
					JLabel lblNewLabel_1 = new JLabel("Receivers");
					lblNewLabel_1.setFont(lblNewLabel_1.getFont().deriveFont(lblNewLabel_1.getFont().getSize() + 2f));
					lblNewLabel_1.setBounds(10, 257, 64, 17);
					contentPane.add(lblNewLabel_1);
					
					JScrollPane scrollPane = new JScrollPane(tree);
					scrollPane.setBounds(27, 67, 244, 179);
					contentPane.add(scrollPane);
					
					JButton btnNewButton_2 = new JButton("Add selected receivers ");
					btnNewButton_2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String t =null;
							if(comboBox.getItemCount()==0) {
								JOptionPane.showMessageDialog(null, "You haven`t selected any receivers");
							}
							else {
								t=comboBox.getItemAt(0).toString();
							}
							for(int i=1;i<comboBox.getItemCount();i++) {
								t=t+", "+comboBox.getItemAt(i).toString();
							}
							
							if(To.getText().isBlank()) {
								To.setText(t);
							}
							else {
								t=To.getText()+", "+t;
								To.setText(t);
							}
							j.setVisible(false);
							
						}
					});
					btnNewButton_2.setBounds(104, 299, 156, 21);
					contentPane.add(btnNewButton_2);
					
					j.setVisible(true);
				}
				
				
				
			}
		});
		btnNewButton_2.setFont(btnNewButton_2.getFont().deriveFont(btnNewButton_2.getFont().getStyle() | Font.BOLD, btnNewButton_2.getFont().getSize() + 1f));
		btnNewButton_2.setBounds(35, 28, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Draft");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send to draft
				
                Mail newmail = new Mail();
				
				newmail.sender=a.email;
				
				String to = To.getText();
				String[] receiverslist = to.split(",");
				Queue finalreceivers = new Queue();
				for(int i=0;i<receiverslist.length;i++) {
					finalreceivers.enqueue(receiverslist[i].trim());
				}
				newmail.listreceivers=finalreceivers;
				
				newmail.subject=Subject.getText().trim();
				
				newmail.content=Message.getText();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				String date = format.format( new Date()   );
			    newmail.date=date;
			    
			    newmail.priority=(String) Priority.getSelectedItem();
			    
			    newmail.attach=attach;
			    
			    newmail.sendmailtodraft();
			    
				UserFilesWindow u = new UserFilesWindow();close();
				u.setApp(a);
				u.show();
				
				m.deletemailfromdarft();
			}
		});
		btnNewButton_3.setFont(btnNewButton_3.getFont().deriveFont(btnNewButton_3.getFont().getStyle() | Font.BOLD, btnNewButton_3.getFont().getSize() + 6f));
		btnNewButton_3.setBounds(159, 459, 107, 34);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				folder.folder="Drafts";
				Viewlist u = new Viewlist(a,folder,filter,sort);close();
				u.show();
			}
		});
		btnNewButton_4.setFont(btnNewButton_4.getFont().deriveFont(btnNewButton_4.getFont().getStyle() | Font.BOLD, btnNewButton_4.getFont().getSize() + 7f));
		btnNewButton_4.setBounds(483, 459, 123, 32);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("Note: Enter \",\" between different receivers");
		lblNewLabel.setBounds(137, 52, 289, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Priority");
		lblNewLabel_1.setFont(lblNewLabel_1.getFont().deriveFont(lblNewLabel_1.getFont().getStyle() | Font.BOLD, lblNewLabel_1.getFont().getSize() + 2f));
		lblNewLabel_1.setBounds(10, 100, 66, 20);
		contentPane.add(lblNewLabel_1);
		
	    Priority = new JComboBox();
		Priority.setModel(new DefaultComboBoxModel(new String[] {"None", "Urgent/Important", "Not Urgent/Important", "Urgent/Not Important", "Not Urgent/Not Important"}));
		Priority.setBounds(137, 101, 168, 21);
		contentPane.add(Priority);
		
		Priority.setSelectedItem(m.priority);
		
		JLabel lblNewLabel_2 = new JLabel("Note: You Must Select a Priority");
		lblNewLabel_2.setBounds(318, 105, 244, 13);
		contentPane.add(lblNewLabel_2);
		
	    comboBox = new JComboBox();
	    comboBox.setToolTipText("");
		comboBox.setBounds(189, 135, 357, 21);
		contentPane.add(comboBox);
		comboBox.setVisible(false);
		
		String[] a = new String[m.attach.size()];
		for(int i=0;i<m.attach.size();i++) {
			File f = new File((String) m.attach.get(i));
			a[i]=f.getName();
			
		}
		final DefaultComboBoxModel model = new DefaultComboBoxModel(a);
		comboBox.setModel(model);
		
		if(!attach.isEmpty()) {
			comboBox.setVisible(true);
		}
		
	    btnNewButton_5 = new JButton("View");
		btnNewButton_5.setVisible(false);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String v = (String) comboBox.getSelectedItem();
				File f=null;
				for(int i=0;i<attach.size();i++) {
					f = new File((String)attach.get(i));
					if(v.equals(f.getName())) {
						break;
					}
				}
				Desktop desktop = Desktop.getDesktop();  
				try {
					desktop.open(f);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		btnNewButton_5.setBounds(556, 127, 85, 21);
		contentPane.add(btnNewButton_5);
		
		if(!attach.isEmpty()) {
			btnNewButton_5.setVisible(true);
		}
		
	    btnNewButton_6 = new JButton("Delete");
	    btnNewButton_6.setVisible(false);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String v = (String) comboBox.getSelectedItem();
				for(int i=0;i<attach.size();i++) {
					File f = new File((String)attach.get(i));
					if(v.equals(f.getName())) {
						attach.remove(i);
						break;
					}
				}
				if(attach.size()!=0) {
				String[] n = new String[attach.size()];
				for(int i=0;i<attach.size();i++) {
					File f2 = new File((String)attach.get(i));
					n[i]=f2.getName();
				}
				final DefaultComboBoxModel model = new DefaultComboBoxModel(n);
                comboBox.setVisible(true);
                comboBox.setModel(model);
				}
				else {
					comboBox.setVisible(false);
					btnNewButton_6.setVisible(false);
				    btnNewButton_5.setVisible(false);
				}
				
			}
		});
		btnNewButton_6.setBounds(556, 149, 85, 21);
		contentPane.add(btnNewButton_6);
		
		if(!attach.isEmpty()) {
			btnNewButton_6.setVisible(true);
		}
		
		JScrollPane scrollPane = new JScrollPane(Message);
		scrollPane.setBounds(20, 195, 608, 244);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_3 = new JLabel("Note: Back will not save changes ");
		lblNewLabel_3.setBounds(439, 492, 208, 13);
		contentPane.add(lblNewLabel_3);
		
	}
}
