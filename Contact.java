package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

public class Contact implements IContact{
	public String email;
	public String password;public String userName;
	File file = new File("accounts//emails.txt");
	public boolean validateEmail() {
		if(email.isBlank()||password.isBlank()||userName.isBlank()) {
			return false;
		}
		else if(email.contains("\\")||email.contains("/")||email.contains(":")||email.contains("*")||email.contains("?")||email.contains("\"")||email.contains("<")||email.contains(">")||email.contains("|")) {
			return false;
		}
				
		try {
			File f = new File("accounts");
			f.mkdirs();
			file.createNewFile();
		} catch (IOException e2) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(null, "Something went wrong try again");
				return false;
			}
			
		}

	BufferedReader br ;
	try {
		br = new BufferedReader(new FileReader("accounts//emails.txt"));
		String line;
			while((line=br.readLine())!=null) {
				if(line.equals(email)==true) {
					br.close();
					return false;
				}
				br.readLine();br.readLine();//bec one line for password and one for user name
			}
		br.close();
	}catch(Exception e) {
		
	}
	try {
		
		FileOutputStream fos = new FileOutputStream("accounts//emails.txt",true);
		BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
		if(file.length()==0) {
			f.write(email);
			f.newLine();
			f.write(password);
			f.newLine();
			f.write(userName);
			f.close();
		}
		else {
			f.newLine();
			f.write(email);
			f.newLine();
			f.write(password);
			f.newLine();
			f.write(userName);
			f.close();
		}
		
		
	}catch(Exception e1) {
		
	}
	
	return true;
	}
	
	public SLinkedList getcontacts(String user) {
		SLinkedList c = new SLinkedList();
		String receiver;
		String name;
		
		BufferedReader br ;
		try {
			br = new BufferedReader(new FileReader("accounts//"+user+"//contacts.txt"));
			String line;
				while((line=br.readLine())!=null) {
					receiver=line;
					name=br.readLine();
					if(c.isEmpty()) {
						SLinkedList cc = new SLinkedList();
						cc.add(name);
						cc.add(receiver);
						c.add(cc);
					}
					else {
						boolean found = false;
						for(int i=0;i<c.size();i++) {
							SLinkedList x = (SLinkedList) c.get(i);
							if(x.get(0).equals(name)) {
								x.add(receiver);
								found=true;
							}
						}
						
						if(found==false) {
							SLinkedList cc = new SLinkedList();
							cc.add(name);
							cc.add(receiver);
							c.add(cc);
						}
					}
				}
			br.close();
		}catch(Exception e) {
			
		}
		
		
		return c;
	}
	

}