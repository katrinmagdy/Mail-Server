package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Mail implements IMail {
	public String subject;
	
	public String sender;
	
	public Queue listreceivers= new Queue();
	
	public String date;
	
	public String priority;
	
	public String content;
	
	public SLinkedList attach = new SLinkedList();
	
	
	
	public void sendmailtoinbox() {
		//to receivers
		while(listreceivers.size()!=0) {
			String r = (String) listreceivers.dequeue();
			
			File oo= new File("accounts\\"+r+"\\Inbox\\index file.txt");
	    	FileOutputStream fos;
			try {
				fos = new FileOutputStream(oo,true);
				OutputStreamWriter x =new OutputStreamWriter(fos);
				BufferedWriter f = new BufferedWriter(x);
				if(oo.length()==0) {
					f.write(subject);
					f.newLine();
					f.write(sender);
					f.newLine();
					f.write(date);
					f.newLine();
					f.write(priority);
					
					f.close();
					x.close();
					fos.close();
					
				}
				else {
					f.newLine();
					f.write(subject);
					f.newLine();
					f.write(sender);
					f.newLine();
					f.write(date);
					f.newLine();
					f.write(priority);
					
					f.close();
					x.close();
					fos.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			File re =new File("accounts\\"+r+"\\Inbox"+"\\"+sender+subject+date);
			File se =new File("accounts//"+sender+"//sent"+"//"+sender+subject+date);
			
			copyFolder(se,re);
			
			String adj = "accounts\\"+r+"\\Inbox"+"\\"+sender+subject+date+"//receivers.txt";
			File adj2 = new File(adj);
			try {
	    		fos = new FileOutputStream(adj2);
	    		OutputStreamWriter x =new OutputStreamWriter(fos);
	    		BufferedWriter f = new BufferedWriter(x);
	    			f.write(r);
	    			f.close();
	    			x.close();
	    			fos.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
			
			addreceivertocontact(r);
			
		}
	}
	
    public void sendmailtosentbox() {
		//to sender
    	String s = sender+subject+date;//mail name
    	String d = "accounts//"+sender+"//sent//"+s;
    	
    	File mail = new File(d);
    	mail.mkdirs();
    	
    	d=d+"//content.txt";
    	File contentfile = new File(d);
    	try {
			contentfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//write the content
    	try {
    		FileOutputStream fos = new FileOutputStream(contentfile,true);
    		OutputStreamWriter x =new OutputStreamWriter(fos);
    		BufferedWriter f = new BufferedWriter(x);
    			f.write(content);
    			f.close();
    			x.close();
    			fos.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//write list of receivers
    	d="accounts//"+sender+"//sent"+"//"+s+"//receivers.txt";
    	File receiversfile = new File(d);
    	try {
			receiversfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try {
    		FileOutputStream fos = new FileOutputStream(receiversfile,true);
    		OutputStreamWriter x =new OutputStreamWriter(fos);
    		BufferedWriter f = new BufferedWriter(x);
    			String[] r = new String[listreceivers.size()];
    			for(int i=0;i<r.length;i++) {
    				r[i]=(String) listreceivers.dequeue();
    			}
    			for(int i=0;i<r.length;i++) {
    				listreceivers.enqueue(r[i]);
    			}
    			f.write(r[0]);
    			for(int i=1;i<r.length;i++) {
    				f.newLine();
    				f.write(r[i]);
    			}
    			f.close();
    			x.close();
    			fos.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//attachments
    	d="accounts//"+sender+"//sent"+"//"+s+"//attachments";
    	File attachfile = new File(d);
    	attachfile.mkdirs();
    	
    	for(int i=0;i<attach.size();i++) {
    		File a = new File((String) attach.get(i));
    		File b = new File("accounts//"+sender+"//sent"+"//"+s+"//attachments"+"//"+a.getName());
    		copyFolder(a, b);
    		String ss =a.getName();
    		attach.set(i, ss);
    		
    	}
    	
    	d="accounts//"+sender+"//sent"+"//"+s+"//attachments//list.txt";
    	File attachlist = new File(d);
    	
    	try {
			attachlist.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
    		FileOutputStream fos = new FileOutputStream(attachlist,true);
    		OutputStreamWriter x =new OutputStreamWriter(fos);
    		BufferedWriter f = new BufferedWriter(x);
    			if(attach.size()!=0) {
    				String a = (String) attach.get(0);
        			f.write(a);
        			for(int i=1;i<attach.size();i++) {
        				a=(String) attach.get(i);
        				f.newLine();
        				f.write(a);
        			}
        			
    			}
    			f.close();
    			x.close();
    			fos.close();
    		   
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	String o ="accounts//"+sender+"//sent//index file.txt";
    	File oo= new File(o);
    	FileOutputStream fos;
		try {
			fos = new FileOutputStream(oo,true);
			OutputStreamWriter x =new OutputStreamWriter(fos);
			BufferedWriter f = new BufferedWriter(x);
			if(oo.length()==0) {
				f.write(subject);
				f.newLine();
				f.write(sender);
				f.newLine();
				f.write(date);
				f.newLine();
				f.write(priority);
				
				f.close();
				x.close();
				fos.close();
				
			}
			else {
				f.newLine();
				f.write(subject);
				f.newLine();
				f.write(sender);
				f.newLine();
				f.write(date);
				f.newLine();
				f.write(priority);
				
				f.close();
				x.close();
				fos.close();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void addreceivertocontact(String receiver) {
    	String Name=null;
    	BufferedReader br;
		try {
			br=new BufferedReader(new FileReader("accounts\\emails.txt"));
			String line;
			while((line=br.readLine())!=null) {
				if(line.equals(receiver)==true) {
					br.readLine();	
					Name=br.readLine();
					break;
					
				}else {
					br.readLine();
					br.readLine();
					continue;
				}
			}br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean found=false;
		
		try {
			br=new BufferedReader(new FileReader("accounts\\"+sender+"\\contacts.txt"));
			String line;
			while((line=br.readLine())!=null) {
				if(line.equals(receiver)==true) {
					found = true;
					break;
					
				}else {
					br.readLine();
					continue;
				}
			}br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(found==false) {
	    	String o ="accounts//"+sender+"//contacts.txt";
	    	File oo= new File(o);
	    	FileOutputStream fos;
			try {
				fos = new FileOutputStream(oo,true);
				OutputStreamWriter x =new OutputStreamWriter(fos);
				BufferedWriter f = new BufferedWriter(x);
				if(oo.length()==0) {
					f.write(receiver);
					f.newLine();
					f.write(Name);
					
					f.close();
					x.close();
					fos.close();
					
				}
				else {
					f.newLine();
					f.write(receiver);
					f.newLine();
					f.write(Name);
					
					f.close();
					x.close();
					fos.close();
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
    }
    
    
    public void sendmailtodraft() {
		if(subject.isBlank()) {
			subject=" ";
			
		}
		String s = sender+subject+date;
        String d = "accounts//"+sender+"//Drafts//"+s;
    	
    	File mail = new File(d);
    	mail.mkdirs();
    	
    	d=d+"//content.txt";
    	File contentfile = new File(d);
    	try {
			contentfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//write the content
    	try {
    		FileOutputStream fos = new FileOutputStream(contentfile,true);
    		OutputStreamWriter x =new OutputStreamWriter(fos);
    		BufferedWriter f = new BufferedWriter(x);
    			f.write(content);
    			f.close();
    			x.close();
    			fos.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//write list of receivers
    	d="accounts//"+sender+"//Drafts"+"//"+s+"//receivers.txt";
    	File receiversfile = new File(d);
    	try {
			receiversfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	if(listreceivers.size()!=0) {
    	try {
    		FileOutputStream fos = new FileOutputStream(receiversfile,true);
    		OutputStreamWriter x =new OutputStreamWriter(fos);
    		BufferedWriter f = new BufferedWriter(x);
    			String[] r = new String[listreceivers.size()];
    			for(int i=0;i<r.length;i++) {
    				r[i]=(String) listreceivers.dequeue();
    			}
    			for(int i=0;i<r.length;i++) {
    				listreceivers.enqueue(r[i]);
    			}
    			f.write(r[0]);
    			for(int i=1;i<r.length;i++) {
    				f.newLine();
    				f.write(r[i]);
    			}
    			f.close();
    			x.close();
    			fos.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	}
    	
    	//attachments
    	d="accounts//"+sender+"//Drafts"+"//"+s+"//attachments";
    	File attachfile = new File(d);
    	attachfile.mkdirs();
    	
    	for(int i=0;i<attach.size();i++) {
    		File a = new File((String) attach.get(i));
    		File b = new File("accounts//"+sender+"//Drafts"+"//"+s+"//attachments"+"//"+a.getName());
    		copyFolder(a, b);
    		String ss =a.getName();
    		attach.set(i, ss);
    		
    	}
    	
    	d="accounts//"+sender+"//Drafts"+"//"+s+"//attachments//list.txt";
    	File attachlist = new File(d);
    	
    	try {
			attachlist.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
    		FileOutputStream fos = new FileOutputStream(attachlist,true);
    		OutputStreamWriter x =new OutputStreamWriter(fos);
    		BufferedWriter f = new BufferedWriter(x);
    			if(attach.size()!=0) {
    				String a = (String) attach.get(0);
        			f.write(a);
        			for(int i=1;i<attach.size();i++) {
        				a=(String) attach.get(i);
        				f.newLine();
        				f.write(a);
        			}
        			
    			}
    			f.close();
    			x.close();
    			fos.close();
    		   
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	String o ="accounts//"+sender+"//Drafts//index file.txt";
    	File oo= new File(o);
    	FileOutputStream fos;
		try {
			fos = new FileOutputStream(oo,true);
			OutputStreamWriter x =new OutputStreamWriter(fos);
			BufferedWriter f = new BufferedWriter(x);
			if(oo.length()==0) {
				f.write(subject);
				f.newLine();
				f.write(sender);
				f.newLine();
				f.write(date);
				f.newLine();
				f.write(priority);
				
				f.close();
				x.close();
				fos.close();
				
			}
			else {
				f.newLine();
				f.write(subject);
				f.newLine();
				f.write(sender);
				f.newLine();
				f.write(date);
				f.newLine();
				f.write(priority);
				
				f.close();
				x.close();
				fos.close();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
    
    public void deletemailfromdarft() {
    	File f = new File("accounts//"+sender+"//Drafts"+"//"+sender+subject+date);
    	deleteFile(f);
    }
    
    public static void deleteFile(File element) {
	    if (element.isDirectory()) {
	        for (File sub : element.listFiles()) {
	            deleteFile(sub);
	        }
	    }
	    element.delete();
	}
    
    public static void copyFolder(File source, File destination){
        if (source.isDirectory()){
            if (!destination.exists()){
                destination.mkdirs();
            }

            String files[] = source.list();

            for (String file : files){
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);

                copyFolder(srcFile, destFile);
            }
        }
        else{
            InputStream in = null;
            OutputStream out = null;

            try{
                in = new FileInputStream(source);
                out = new FileOutputStream(destination);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0)
                {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.close();
            }
            catch (Exception e){
                try{
                    in.close();
                }
                catch (IOException e1){
                    e1.printStackTrace();
                }

                try{
                    out.close();
                }
                catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }

}
