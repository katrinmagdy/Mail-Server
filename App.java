package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class App implements IApp{
	public String email;
	public String password;public String UserName;
	public DLinkedList mails = new DLinkedList() ;
	public int maxpages;
	public String fol;
	public DLinkedList extra = new DLinkedList() ;
	
	public  boolean signin(String email, String password) {
		//lets look at the folder which contains emails,passwords
				BufferedReader br;
				try {
					br=new BufferedReader(new FileReader("accounts\\emails.txt"));
					String line;
					while((line=br.readLine())!=null) {
						if(line.equals(email)==true) {
							line=br.readLine();	
							if(line.equals(password)==true) {
								line = br.readLine();
								br.close();
								this.email=email;
								this.password=password;
								this.UserName=line;
								return true;
							}
							else {
								br.close();
								return false;
							}
						}else {
							br.readLine();
							br.readLine();
							continue;
						}
					}br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;//email is not found
		
	}
	
	public boolean signup(IContact contact) {
		 Contact cc = (Contact) contact;
		 this.email=cc.email;
		 this.UserName=cc.userName;
		 this.password=cc.password;
		if(cc.validateEmail()) {
			File acc = new File("accounts");
			acc.mkdir();
			 
			String ss = "accounts//"+cc.email;
			 File file = new File(ss);
			 file.mkdir();
			 
			 ss = "accounts//"+cc.email+"//Inbox";
			 File inbox = new File(ss);
			 inbox.mkdirs();
			 
			 ss = "accounts//"+cc.email+"//Inbox//index file.txt";
			 File indexinbox = new File(ss);
			 try {
				indexinbox.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 ss ="accounts//"+ cc.email+"//sent";
			 File sent = new File(ss);
			 sent.mkdirs();
			 
			 ss = "accounts//"+cc.email+"//sent//index file.txt";
			 File indexsent = new File(ss);
			 try {
				indexsent.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 ss = "accounts//"+cc.email+"//Drafts";
			 File draft = new File(ss);
			 draft.mkdirs();
			 
			 ss ="accounts//"+ cc.email+"//Drafts//index file.txt";
			 File indexdraft = new File(ss);
			 try {
				indexdraft.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 ss ="accounts//"+ cc.email+"//Trash";
			 File trash = new File(ss);
			 trash.mkdirs();
			 
			 ss ="accounts//"+ cc.email+"//Trash//index file.txt";
			 File indextrash = new File(ss);
			 try {
				indextrash.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 ss = "accounts//"+cc.email+"//contacts.txt";
			 File cont = new File(ss);
			 try {
				cont.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			 
			 return true;
		}
		else {
			return false;
		}
		
	}
	
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		this.mails.clear();
		
		Folder f = (Folder) folder;
		Filter fil= (Filter) filter;
	    Sort s = (Sort) sort;
		 
		if(f.folder.equals("Trash")) {
			f.trashmails(email, mails);
			fol=f.folder;
		}
		else {
			f.foldermails(email,mails);
		    fol=f.folder;
		    
		}
		
		fil.filtermails(mails);
		
		
		if(fil.searchAccTo!=null) {
			
			if(fil.searchAccTo.equals("Subject")) {
				fil.SearchAccToSubject(fil.searchword,mails);
			}
			else if(fil.searchAccTo.equals("Date")) {
				fil.SearchAccToDate(fil.searchword,mails);
			}
			else if(fil.searchAccTo.equals("Receiver")) {
				fil.SearchAccToReciever(fil.searchword,mails);
			}
			else if(fil.searchAccTo.equals("Sender")) {
				fil.SearchAccToSender(fil.searchword,mails);
			}
			else if(fil.searchAccTo.equals("Attachment")) {
				fil.SearchAccToAttachment(fil.searchword,mails);
			}
			else if(fil.searchAccTo.equals("Body")) {
				fil.SearchAccToBody(fil.searchword,mails);
			}
			mails.clear();
			for(int i=0;i<fil.newSearchList.size();i++) {
				mails.add(fil.newSearchList.get(i));
			}
			
		}
		
		
		//search first
		
	   
	    
	    if(s.accto!=null) {
	    	 String ext = s.accto;
	    	if(s.accto.equals("date")) {
	    		s.accto="dateDesc";
		    }
	    	s.accto="dateDesc";
	    	s.SortTmpListForSearching(mails);
	    	
	    	s.accto=ext;
	    	s.SortTmpListForSearching(mails);
	    	
	    }
	    else {
	    	s.accto="dateDesc";
	    	s.SortTmpListForSearching(mails);
	    	
	    	if(f.folder.equals("Inbox")) {
				if(f.DP.equals("D")) {
					s.accto="dateDesc";
					s.SortTmpListForSearching(mails);
				}
				else if(f.DP.equals("P")) {
					s.SortAccToPriority(mails);
				}
			}
	    }
	    
	    
        
        if(mails.size()%10==0) {
        	this.maxpages=mails.size()/10;
        }
        else {
        	this.maxpages=(mails.size()+10)/10;
        }
       
		
	}
	
	public IMail[] listEmails(int page) {
    	Mail[] m = new Mail[10];
    	int j=0;
    	for(int i=0;i<10;i++) {
    		m[i]=null;
    	}
    	for(int i=(0+(page-1)*10);i<(page*10);i++) {
    		if(i!=mails.size()) {
    			Mail ex = (Mail) mails.get(i);
    			m[j] = new Mail();
    			m[j]= ex;
    			
    			j++;
    		}
    		else {
    			break;
    		}
    	}
    	return m;
    	
    }

    public void deleteEmails(ILinkedList mails) {
    	 DLinkedList m = (DLinkedList) mails;
    	 Folder fold = new Folder();
    	 
    	 fold.folder=this.fol;
    	 
    	 fold.foldermails(email, extra);
    	 
    	for(int i=0;i<m.size();i++) {
    		Mail x =(Mail) m.get(i);
    		 for(int j=(this.extra.size()-1);j>=0;j--) {
    			 Mail y = (Mail) this.extra.get(j);
    			 if((x.subject.equals(y.subject))&&(x.date.equals(y.date))&&(x.priority.equals(y.priority))&&(x.sender.equals(y.sender))) {
    				 this.extra.remove(j);
    				 break;
    			 }
    		 }
    	 }
    	
    	 Folder f1 = new Folder();
    	 f1.folder="Trash";
    	 
    	 moveEmails( mails, f1);
    	 
    	 
    	 
    	 if(this.extra.size()!=0) {
    		 String o ="accounts//"+this.email+"//"+fol+"//index file.txt";
 	    	File oo= new File(o);
 	    	FileOutputStream fos;
 			try {
 				fos = new FileOutputStream(oo);
 				BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
 				Mail y = (Mail) this.extra.get(0);
 	
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
 			
 			for(int i=1;i<this.extra.size();i++) {
 				try {
 					fos = new FileOutputStream(oo,true);
 					BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
 					Mail y = (Mail) this.extra.get(i);
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
 			
 			this.extra.clear();
    	 }
    	 else {
    		 String o ="accounts//"+this.email+"//"+fol+"//index file.txt";
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

    public void moveEmails(ILinkedList mails, IFolder des) {
    	Folder f1 =(Folder) des;
    	DLinkedList m = (DLinkedList) mails;
    	if(f1.folder.equals("Trash")) {
    		for(int i=0;i<m.size();i++) {
       		 for(int j=(this.mails.size()-1);j>=0;j--) {
       				 if(this.mails.get(j).equals(m.get(i))) {
       					 Mail x = (Mail) m.get(i);
       					 File from = new File("accounts//"+this.email+"//"+fol+"//"+x.sender+x.subject+x.date);
       					 File to = new File("accounts//"+this.email+"//Trash"+"//"+x.sender+x.subject+x.date);
       					 copyFolder(from, to);
       					 deleteFile(from);
       					 String o ="accounts//"+this.email+"//Trash//index file.txt";
       				    	File oo= new File(o);
       				    	FileOutputStream fos;
       						try {
       							fos = new FileOutputStream(oo,true);
       							BufferedWriter f = new BufferedWriter(new OutputStreamWriter(fos));
       							if(oo.length()==0) {
       								f.write(x.subject);
       								f.newLine();
       								f.write(x.sender);
       								f.newLine();
       								f.write(x.date);
       								f.newLine();
       								f.write(x.priority);
       								
       								f.close();
       								
       							}
       							else {
       								f.newLine();
       								f.write(x.subject);
       								f.newLine();
       								f.write(x.sender);
       								f.newLine();
       								f.write(x.date);
       								f.newLine();
       								f.write(x.priority);
       								f.close();
       								
       							}
       						} catch (Exception e) {
       							// TODO Auto-generated catch block
       							e.printStackTrace();
       						}
       					
           				 this.mails.remove(j);
           				 break;
           			 }
       		 }
       	  }
    	}
    	
    }

    public boolean compose(IMail email) {
    	Mail mail = (Mail) email;
    	//check priority
    	if(mail.priority=="None") {
    		return false;
    	}
    	
    	//check receivers list
    	String[] receivers = new String[mail.listreceivers.size()];
    	
    	if(receivers.length==0) {
    		return false;
    	}
    	
    	for(int i=0;i<receivers.length;i++) {
    		receivers[i]=(String) mail.listreceivers.dequeue();
    		
    	}
    	for(int i=0;i<receivers.length;i++) {
    		 mail.listreceivers.enqueue(receivers[i]);
    	}
    	for(int i=0;i<receivers.length;i++) {
    		BufferedReader br;
    		boolean found=false;
    		try {
    			br=new BufferedReader(new FileReader("accounts\\emails.txt"));
    			String line; 
    			while((line=br.readLine())!=null) {
    				if(line.equals(receivers[i])==true) {
    					found=true;
    					break;
    				}
    				br.readLine();
    				br.readLine();
    			}
    			br.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		if(found==false) {
    			return false;
    		}
    	}
    	
    	for(int i=0 ;i<receivers.length;i++) {
    		for(int j=i+1;j<receivers.length;j++) {
    			if(receivers[i].equals(receivers[j])) {
    				return false;
    			}
    		}
    	}
    	
    	//check subject
    	if(mail.subject.isBlank()) {
    		return false;
    	}
    	
    	//check attachments
    	
    	for(int i=0;i<mail.attach.size();i++) {
    		File file = new File((String) mail.attach.get(i));
    		if(!file.exists()) {
    			return false;
    		}
    	}
    	
    	return true;
    		
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