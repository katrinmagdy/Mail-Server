package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;

import javax.swing.JOptionPane;

import eg.edu.alexu.csd.datastructure.mailServer.DLinkedList.Dnode;

public class Sort implements ISort {
public String accto;


public void swap(DLinkedList d,Dnode a,Dnode b) {
	Mail tmp;
	tmp=a.getElement();
	a.setElement(b.getElement());b.setElement(tmp);
}

public void resetinfo() {
	accto=null;
}



public void SortTmpListForSearching(DLinkedList l) {
	if(accto.contentEquals("dateAsc")) {
		if(l.size()>0) {
		String arrOfDates[]=new String[l.size()];int counter =0;
		Dnode v=l.head.getNext();
		while(counter<l.size()) {
			arrOfDates[counter]=v.getElement().date;
			counter++;
			v=v.getNext();
		}
		QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfDates);int i=0;

		//loop for sorting list
		v=l.head.getNext();i=0;int x=0;counter=0;
		while(counter<l.size()) {
			if(v.getElement().date.contentEquals(arrOfDates[i])==false) {
				x=counter;Dnode y=v;
				while(x<l.size()) {
					if(y.getElement().date.contentEquals(arrOfDates[i])==false) {
						x++;y=y.getNext();
					}else {
						break;
					}
				}
				i++;counter++;
				swap(l,y,v);v=v.getNext();
			}else {
				i++;counter++;v=v.getNext();
			}
		}

		}
		
		
	}
	else if(accto.contentEquals("dateDesc")) {
		if(l.size()>0) {
		String arrOfDates[]=new String[l.size()];int counter =0;
		Dnode v=l.head.getNext();
		while(counter<l.size()) {
			arrOfDates[counter]=v.getElement().date;
			counter++;
			v=v.getNext();
		}
		QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfDates);int i=0;

		//loop for sorting list
		v=l.head.getNext();i=arrOfDates.length-1;int x=0;counter=0;
		while(counter<l.size()) {
			if(v.getElement().date.contentEquals(arrOfDates[i])==false) {
				x=counter;Dnode y=v;
				while(x<l.size()) {
					if(y.getElement().date.contentEquals(arrOfDates[i])==false) {
						x++;y=y.getNext();
					}else {
						break;
					}
				}
				i--;counter++;
				swap(l,y,v);v=v.getNext();
			}else {
				i--;counter++;v=v.getNext();
			}
		}

		}
		
		
	}
	else if(accto.contentEquals("subject")) {
		
		if(l.size()>0) {
			String arrOfSubjects[]=new String[l.size()];int counter =0;
			Dnode v=l.head.getNext();
			while(counter<l.size()) {
				arrOfSubjects[counter]=v.getElement().subject;
				counter++;
				v=v.getNext();
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfSubjects);int i=0;
			
			//loop for sorting list
			v=l.head.getNext();i=0;int x=0;counter=0;
			while(counter<l.size()) {
				if(v.getElement().subject.contentEquals(arrOfSubjects[i])==false) {
					x=counter;Dnode y=v;
					while(x<l.size()) {
						if(y.getElement().subject.contentEquals(arrOfSubjects[i])==false) {
							x++;y=y.getNext();
						}else {
							break;
						}
					}
					i++;counter++;
					swap(l,y,v);v=v.getNext();
				}else {
					i++;counter++;v=v.getNext();
				}
			}
			
			
			}
		
	}else if(accto.contentEquals("sender")) {
		
		if(l.size()>0) {
			String arrOfSenders[]=new String[l.size()];int counter =0;
			
			Dnode v=l.head.getNext();
			while(counter<l.size()) {
				arrOfSenders[counter]=v.getElement().sender;
				counter++;
				v=v.getNext();
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfSenders);int i=0;
			
			//loop for sorting list
			v=l.head.getNext();i=0;int x=0;counter=0;
			while(counter<l.size()) {
				if(v.getElement().sender.contentEquals(arrOfSenders[i])==false) {
					x=counter;Dnode y=v;
					while(x<l.size()) {
						if(y.getElement().sender.contentEquals(arrOfSenders[i])==false) {
							x++;y=y.getNext();
						}else {
							break;
						}
					}
					i++;counter++;
					swap(l,y,v);v=v.getNext();
				}else {
					i++;counter++;v=v.getNext();
				}
			}
			counter=0;Dnode h=l.head.getNext();
			}
		
	}else if(accto.contentEquals("reciever")) {
		
		if(l.size()>0) {
			String arrOfRecievers[]=new String[l.size()];int count=0;
			int counter=0;Dnode h=l.head.getNext();
			while(counter<l.size()){
				arrOfRecievers[count]= String.valueOf(h.getElement().listreceivers.GetFirstElement());
				count++;
				h=h.getNext();
				counter++;
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfRecievers);
			Dnode v=l.head.getNext();int i=0;int x=0;counter=0;
			while(counter<l.size()) {String s= String.valueOf(v.getElement().listreceivers.GetFirstElement());
				if(s.contentEquals(arrOfRecievers[i])==false) {
					x=counter;Dnode y=v;
					while(x<l.size()) {
						String ss= String.valueOf(y.getElement().listreceivers.GetFirstElement());
						if(ss.contentEquals(arrOfRecievers[i])==false) {
							x++;y=y.getNext();
						}else {
							break;
						}
					}
					i++;counter++;
					swap(l,y,v);
					v=v.getNext();
				}else {
					i++;counter++;v=v.getNext();
				}
			}
			counter=0;Dnode f=l.head.getNext();
			while(counter<l.size()) {
				System.out.print(f.getElement().listreceivers.GetFirstElement()+"  ");counter++;
				f=f.getNext();
			}
			}
			
		
	}else if(accto.contentEquals("body")) {
		
		if(l.size()>0) {
			String arrOfBodies[]=new String[l.size()];int counter =0;
			Dnode v=l.head.getNext();
			while(counter<l.size()) {
				arrOfBodies[counter]=v.getElement().content;
				counter++;
				v=v.getNext();
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfBodies);int i=0;
			
			//loop for sorting list
			v=l.head.getNext();i=0;int x=0;counter=0;
			while(counter<l.size()) {
				if(v.getElement().content.contentEquals(arrOfBodies[i])==false) {
					x=counter;Dnode y=v;
					while(x<l.size()) {
						if(y.getElement().content.contentEquals(arrOfBodies[i])==false) {
							x++;y=y.getNext();
						}else {
							break;
						}
					}
					i++;counter++;
					swap(l,y,v);v=v.getNext();
				}else {
					i++;counter++;v=v.getNext();
				}
			}
			counter=0;Dnode h=l.head.getNext();
			
			}
		
	}else if(accto.contentEquals("attachment")) {
		
		if(l.size()>0) {
			Dnode d=l.head.getNext();String arrOfAttachments[];//first we want to know number of emails containing attachments
			int counter=0;int countEmailsContainingAttachments=0;int count=0;
			DLinkedList ListOfEmailsWithoutAttachments=new DLinkedList();
			DLinkedList ListOfEmailsWithAttachments=new DLinkedList();
			while(counter<l.size()) {
				if(d.getElement().attach.size()>0) {
					countEmailsContainingAttachments++;
				}
				d=d.getNext();counter++;
			}
			if(countEmailsContainingAttachments>0) {
			arrOfAttachments=new String[countEmailsContainingAttachments];
			//if there are some Emails don't contain attachments we will put it at last
			counter=0;d=l.head.getNext();int y=l.size();int index;
			while(counter<y) {index=counter;
				if(d.getElement().attach.size()>0) {
					ListOfEmailsWithAttachments.add(d.getElement());
					String s=d.getElement().attach.get(0).toString();
					File f=new File(s);
					arrOfAttachments[count]=f.getName();count++;
				}else {
					ListOfEmailsWithoutAttachments.add(d.getElement());
				}
				counter++;d=d.getNext();
			}
			QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arrOfAttachments);
			counter=0;int i=0;
			//if(ListOfEmailsWithAttachments.size()>0)
			d=ListOfEmailsWithAttachments.head.getNext();
			while(counter<ListOfEmailsWithAttachments.size()&&d!=null) {
				String ss=d.getElement().attach.get(0).toString();
				File ff=new File(ss);
				if(ff.getName().contentEquals(arrOfAttachments[i])==true) {
					i++;
					counter++;d=d.getNext();
				}else {
					Dnode dd=d;int c=counter;
					while(c<ListOfEmailsWithAttachments.size()&&dd!=null) {
						String str=dd.getElement().attach.get(0).toString();
						File myFile=new File(str);
						if(myFile.getName().contentEquals(arrOfAttachments[i])==true) {
							break;
						}
						c++;dd=dd.getNext();
					}
					swap(ListOfEmailsWithAttachments, dd, d);d=d.getNext();i++;d=d.getNext();counter++;
				}
			}count=0;
			while(count<ListOfEmailsWithoutAttachments.size()) {
				ListOfEmailsWithAttachments.add(ListOfEmailsWithoutAttachments.get(count));count++;
			}
			counter=0;d=ListOfEmailsWithAttachments.head.getNext();
			l.clear();
			while(counter<ListOfEmailsWithAttachments.size()) {
				l.add(d.getElement());d=d.getNext();
				counter++;
			}
			}else {
				//there isn't any email containing attachments
				//we willnot change order of Emails 
				count=0;Dnode r=ListOfEmailsWithoutAttachments.head.getNext();
				while(count<ListOfEmailsWithoutAttachments.size()) {
					l.add(r.getElement());
					count++;r=r.getNext();
				}
			}
		}
		
		
	}
}
public void SortAccToPriority(DLinkedList list) {
	//put elements in priority queue
	Dnode d=list.head.getNext();int key;
	int counter=0;Priority_Queue p=new Priority_Queue();
	while(counter<list.size()) {//"Urgent/Important", "Not Urgent/Important", "Urgent/Not Important", "Not Urgent/Not Important"
		if(d.getElement().priority.contentEquals("Urgent/Important")) {
			key=1;
		}else if(d.getElement().priority.contentEquals("Not Urgent/Important")) {
			key=2;
		}else if(d.getElement().priority.contentEquals("Urgent/Not Important")) {
			key=3;
		}else {//if(d.getElement().priority.contentEquals("Not Urgent/Not Important")) 
			key=4;
		}
		p.insert(d.getElement(), key);
		counter++;d=d.getNext();
	}
	if(p.isEmpty()==false) {
		list.clear();
		while(p.size()>0) {
		list.add((Mail)p.removeMin());
		}
	}	
	
 }
}
