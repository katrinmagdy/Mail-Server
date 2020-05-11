package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Folder implements IFolder{
	
	public String folder;
	public String DP;
	
	public void foldermails(String user,DLinkedList list) {
		String open = "accounts//"+user+"//"+folder+"//index file.txt";
		
		BufferedReader br ;
		try {
			br = new BufferedReader(new FileReader(open));
			String line;
			while((line=br.readLine())!=null) {
				Mail m = new Mail();
				m.subject=line;//subject
				line=br.readLine();//sender
				m.sender=line;
				line=br.readLine();//date
				m.date=line;
				line=br.readLine();//priority
				m.priority=line;
				
				File f = new File("accounts//"+user+"//"+folder+"//"+m.sender+m.subject+m.date);
				
				if( f.exists()) {
					
					String mopen = "accounts//"+user+"//"+folder+"//"+m.sender+m.subject+m.date+"//receivers.txt";
					BufferedReader br2  ;
					try {
						FileReader x = new FileReader(mopen);
						br2 = new BufferedReader(x);
						String fr;
						while((fr=br2.readLine())!=null) {
							m.listreceivers.enqueue(fr);
						}
						br2.close();
						x.close();
						
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try
			        {
			           String content = new String ( Files.readAllBytes( Paths.get("accounts//"+user+"//"+folder+"//"+m.sender+m.subject+m.date+"//content.txt") ) );
			           m.content=content;
			           
			        } 
			        catch (IOException e) 
			        {
			            e.printStackTrace();
			        }
					
					try {
						FileReader x =new FileReader("accounts//"+user+"//"+folder+"//"+m.sender+m.subject+m.date+"//attachments//list.txt");
						br2 = new BufferedReader(x);
						String fr;
						while((fr=br2.readLine())!=null) {
							String element = "accounts//"+user+"//"+folder+"//"+m.sender+m.subject+m.date+"//attachments//"+fr;
							m.attach.add(element);
						}
						br2.close();
						x.close();
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					list.add(m);
					
				}
				
				
			}
		br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void trashmails(String user,DLinkedList list) {
		String open = "accounts//"+user+"//Trash"+"//index file.txt";
		
		BufferedReader br ;
		try {
			br = new BufferedReader(new FileReader(open));
			String line;
			while((line=br.readLine())!=null) {
				Mail m = new Mail();
				m.subject=line;//subject
				line=br.readLine();//sender
				m.sender=line;
				line=br.readLine();//date
				m.date=line;
				line=br.readLine();//priority
				m.priority=line;
				
				File f = new File("accounts//"+user+"//Trash"+"//"+m.sender+m.subject+m.date);
				long diff = System.currentTimeMillis() - f.lastModified();
				
				if( diff<(30 * 24 * 60 * 60 * 1000.0)) {
					
					String mopen = "accounts//"+user+"//Trash"+"//"+m.sender+m.subject+m.date+"//receivers.txt";
					BufferedReader br2  ;
					try {
						FileReader x = new FileReader(mopen);
						br2 = new BufferedReader(x);
						String fr;
						while((fr=br2.readLine())!=null) {
							m.listreceivers.enqueue(fr);
						}
						br2.close();
						x.close();
						
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try
			        {
			           String content = new String ( Files.readAllBytes( Paths.get("accounts//"+user+"//Trash"+"//"+m.sender+m.subject+m.date+"//content.txt") ) );
			           m.content=content;
			           
			        } 
			        catch (IOException e) 
			        {
			            e.printStackTrace();
			        }
					
					try {
						FileReader x =new FileReader("accounts//"+user+"//Trash"+"//"+m.sender+m.subject+m.date+"//attachments//list.txt");
						br2 = new BufferedReader(x);
						String fr;
						while((fr=br2.readLine())!=null) {
							String element = "accounts//"+user+"//Trash"+"//"+m.sender+m.subject+m.date+"//attachments//"+fr;
							m.attach.add(element);
						}
						br2.close();
						x.close();
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					list.add(m);
					
				}
				else {
					deleteFile(f);
				}
				
				
			}
		br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(list.size()!=0) {
   		
	    	File oo= new File(open);
	    	FileOutputStream fos;
			try {
				fos = new FileOutputStream(oo);
				BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
				Mail y = (Mail) list.get(0);
	
					f.write(y.subject);
					f.newLine();
					f.write(y.sender);
					f.newLine();
					f.write(y.date);
					f.newLine();
					f.write(y.priority);
					f.close();
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=1;i<list.size();i++) {
				try {
					fos = new FileOutputStream(oo,true);
					BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
					Mail y = (Mail) list.get(i);
						f.newLine();
						f.write(y.subject);
						f.newLine();
						f.write(y.sender);
						f.newLine();
						f.write(y.date);
						f.newLine();
						f.write(y.priority);
						f.close();
						
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
   	 else {
   		 String o ="accounts//"+user+"//Trash"+"//index file.txt";
 	    	File oo= new File(o);
 	    	FileOutputStream fos;
   		 try {
					fos = new FileOutputStream(oo);
					BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
						f.write("");
						f.close();
						
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	 }
   	 
		
	}
	
	public static void deleteFile(File element) {
	    if (element.isDirectory()) {
	        for (File sub : element.listFiles()) {
	            deleteFile(sub);
	        }
	    }
	    element.delete();
	}
	
	
}
