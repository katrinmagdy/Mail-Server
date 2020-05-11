package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;

import eg.edu.alexu.csd.datastructure.mailServer.DLinkedList.Dnode;

public class Filter implements IFilter {
	
	
	public String filtersubject;
	public String filtersender;
	public String searchAccTo;
	public String searchword;
	
	DLinkedList newSearchList=new DLinkedList();;//this is the list we will view
	
	public void resetinfo() {
		filtersubject=null;
		filtersender=null;
		searchAccTo=null;
	}
	
	public void filtermails(DLinkedList list) {
		if(filtersubject!=null) {
			for(int i=list.size()-1;i>=0;i--) {
				Mail s =  list.get(i);
				if(s!=null) {
					if(!s.subject.toLowerCase().contains(filtersubject.toLowerCase())) {
						list.remove(i);
					}
				}
				
			}
		}
		else if(filtersender!=null) {
			for(int i=(list.size()-1);i>=0;i--) {
				Mail s =  list.get(i);
				if(s!=null) {
					if(!(s.sender.equals(filtersender))) {
						list.remove(i);
					}
				}
				
			}
		}
	}
	
	
	
	public void SearchAccToDate(String date,DLinkedList list) {
		int counter=0;Dnode h=list.head.getNext();String arr[]=new String[list.size()];
		DLinkedList ToBeSorted=new DLinkedList();
		if(newSearchList.isEmpty()==false) {
			newSearchList.clear();
		}
		while(counter<list.size()) {
			ToBeSorted.add(h.getElement());
			h=h.getNext();
			counter++;
		}
		counter=0;h=ToBeSorted.head.getNext();
		while(counter<ToBeSorted.size()) {
			arr[counter]=h.getElement().date;
			counter++;h=h.getNext();
		}
		Sort s=new Sort();s.accto="dateAsc";
		s.SortTmpListForSearching(ToBeSorted);
		BinarySearchByStack b=new BinarySearchByStack();b.IterativeBinSearch(arr, date);
		if(b.getCount()>0) {
			int j=0;//loop in the new list
			h=ToBeSorted.head.getNext();
			while(j<ToBeSorted.size()) {
				for(int f=0;f<b.getCount();f++) {
					if(b.arrOfResults[f]==j) {
						newSearchList.add(h.getElement());
						break;
					}
				}
				j++;h=h.getNext();
			}
		}

	}
	
	public void SearchAccToSubject(String subject,DLinkedList list) {
		int counter=0;Dnode h=list.head.getNext();String arr[]=new String[list.size()];
		DLinkedList ToBeSorted=new DLinkedList();
		if(newSearchList.isEmpty()==false) {
			newSearchList.clear();
		}
		
		while(counter<list.size()) {
			ToBeSorted.add(h.getElement());
			h=h.getNext();
			counter++;
		}
		
		counter=0;h=ToBeSorted.head.getNext();
		while(counter<ToBeSorted.size()) {
			arr[counter]=h.getElement().subject;
			counter++;h=h.getNext();
		}
		
		Sort s=new Sort();s.accto="subject";
		s.SortTmpListForSearching(ToBeSorted);
		BinarySearchByStack b=new BinarySearchByStack();b.IterativeBinSearch(arr, subject);
		if(b.getCount()>0) {
			int j=0;//loop in the new list
			h=ToBeSorted.head.getNext();
			while(j<ToBeSorted.size()) {
				for(int f=0;f<b.getCount();f++) {
					if(b.arrOfResults[f]==j) {
						newSearchList.add(h.getElement());
						break;
					}
				}
				j++;h=h.getNext();
			}
		}

	}

	public void SearchAccToBody(String body,DLinkedList list) {
		int counter=0;Dnode h=list.head.getNext();String arr[]=new String[list.size()];
		DLinkedList ToBeSorted=new DLinkedList();
		if(newSearchList.isEmpty()==false) {
			newSearchList.clear();
		}
		while(counter<list.size()) {
			ToBeSorted.add(h.getElement());
			h=h.getNext();
			counter++;
		}
		counter=0;h=ToBeSorted.head.getNext();
		while(counter<ToBeSorted.size()) {
			arr[counter]=h.getElement().content;
			counter++;h=h.getNext();
		}
		Sort s=new Sort();s.accto="body";
		s.SortTmpListForSearching(ToBeSorted);
		BinarySearchByStack b=new BinarySearchByStack();b.IterativeBinSearch(arr, body);
		if(b.getCount()>0) {
			int j=0;//loop in the new list
			h=ToBeSorted.head.getNext();
			while(j<ToBeSorted.size()) {
				for(int f=0;f<b.getCount();f++) {
					if(b.arrOfResults[f]==j) {
						newSearchList.add(h.getElement());
						break;
					}
				}
				j++;h=h.getNext();
			}
		}

	}

	public void SearchAccToSender(String sender,DLinkedList list) {

		int counter=0;Dnode h=list.head.getNext();String arr[]=new String[list.size()];
		DLinkedList ToBeSorted=new DLinkedList();
		if(newSearchList.isEmpty()==false) {
			newSearchList.clear();
		}
		while(counter<list.size()) {
			ToBeSorted.add(h.getElement());
			h=h.getNext();
			counter++;
		}
		counter=0;h=ToBeSorted.head.getNext();
		while(counter<ToBeSorted.size()) {
			arr[counter]=h.getElement().sender;
			counter++;h=h.getNext();
		}
		Sort s=new Sort();s.accto="sender";
		s.SortTmpListForSearching(ToBeSorted);
		BinarySearchByStack b=new BinarySearchByStack();b.IterativeBinSearch(arr, sender);
		if(b.getCount()>0) {
			int j=0;//loop in the new list
			h=ToBeSorted.head.getNext();
			while(j<ToBeSorted.size()) {
				for(int f=0;f<b.getCount();f++) {
					if(b.arrOfResults[f]==j) {
						newSearchList.add(h.getElement());
						break;
					}
				}
				j++;h=h.getNext();
			}
		}
		
	}

	public void SearchAccToReciever(String reciever,DLinkedList list) {
		int counter=0;Dnode h=list.head.getNext();
		if(newSearchList.isEmpty()==false) {
			newSearchList.clear();
		}
		
		String arr[];
		while(counter<list.size()) {
			if(h.getElement().listreceivers.size()>0) {
				//search in list of recievers 
				/*if(h.getElement().listreceivers.searchForCertainElement(reciever)==true) {
					newSearchList.add(h.getElement());
				}*/
				arr=new String[h.getElement().listreceivers.size];
				h.getElement().listreceivers.putElementsOfQueueInArr(arr);
				QuickSortByStack q=new QuickSortByStack();
				q.IterativeQuickSort(arr);BinarySearchByStack b=new BinarySearchByStack();
				b.IterativeBinSearch(arr,reciever);
				if(b.getCount()>0) {
					newSearchList.add(h.getElement());
				}
			}
			h=h.getNext();
			counter++;
		}
		
	}

	
	public void SearchAccToAttachment(String attachment,DLinkedList list) {
		if(newSearchList.isEmpty()==false) {
			newSearchList.clear();
		}
	int counter=0;int i=0;String[] arr;
	Dnode h=list.head.getNext();
	while(counter<list.size()) {
		if(h.getElement().attach.size()>0) {
			arr=new String[h.getElement().attach.size()];i=0;
			while(i<h.getElement().attach.size()) {
				String s=h.getElement().attach.get(i).toString();
				File f=new File(s);
				arr[i]=f.getName();
				i++;
			}
			BinarySearchByStack b=new BinarySearchByStack();
			b.IterativeBinSearch(arr, attachment);
			if(b.getCount()>0) {
				//we will put this mail in the list
				newSearchList.add(h.getElement());
			}
		}
		h=h.getNext();
		counter++;
	}
	
	}
	
	public DLinkedList getSearchList() {
		return newSearchList;
	}
	
}