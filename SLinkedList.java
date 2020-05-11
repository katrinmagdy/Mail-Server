package eg.edu.alexu.csd.datastructure.mailServer;


public class SLinkedList implements ILinkedList{
	public  class SNode{
        Object data;
        SNode next;
       }
	SNode head; // head of list 
    SNode tail;
    int size=0;
	public SLinkedList() { 
	     head=null; // head of list 
	     tail=null;
	     size=0;
	}
	
	public void add(int index, Object element) {
		SNode tmp=new SNode();
		SNode h=head;
		tmp.data=element;
				if(index==0&&index<=size) {
					tmp.next=head;
					h=null;
					head=tmp;
					size++;
				}
				else if(index==1&&index<=size) {
					tmp.next=head.next;
					head.next=tmp;
					size++;
				}
				else if(index>1&&index<=size){
					for (int i=0;i<index-1;i++) {
						h=h.next;
					}
					tmp.next=h.next;
					h.next=tmp;
					size++;
				}
				else {
					System.out.println("Invalid index");
				}
	}
	
	public void add(Object element) {
		SNode tmp=new SNode();
		tmp.data=element;
		if(size==0) {
			head=tail=tmp;
		}	
		else {
		tail.next=tmp;
		tail=tmp;
		tail.next=null;
		}
		size++;
	}
	
	public Object get(int index) {
		if(index<size&&index>=0) {
		SNode h=head;
		for(int i=0;i<index;i++) {
			h=h.next;
		}
		return h.data;
		}
		else {
			return null;
		}
	}
	
	public void set(int index, Object element) {
		SNode h=head;
		if(index==0&&size!=0) {
			head.data=element;
		}
		else if (index==1&&index<size) {
			head.next.data=element;
		}
		else if(index>1&&index<size) {
			for(int i=0;i<index;i++) {
				h=h.next;
			}
			h.data=element;
		}
		else {
			System.out.println("invalid index or the list may be empty");
		}
		
	}
	
	public void clear() {
		// head will refer t0 null and also tail 
		head=null;
		tail=null;
		size=0;
	}
	
	public boolean isEmpty() {
		if (size==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void remove(int index) {
		if(index==0&&size!=0) {
			head=head.next;
			size--;
		}
		else if(index==1&&index<size){
			head.next=head.next.next;
			size--;
		}
		else {
			if(index>1&&index<size) {
				SNode h=head;
				for(int i=0;i<index-1;i++) {
					h=h.next;
				}
				h.next=h.next.next;
				size--;
			}
			else {
				System.out.println("Invalid index");
			}
			
		}
	}
	
	public int size() {
		return size;
		//or we can use build in methods 
	}
	
	public ILinkedList sublist(int fromIndex, int toIndex) {
    
		SLinkedList n =new SLinkedList();
	    
	    for(int i=fromIndex;i<=toIndex;i++) {
	    	n.add(get(i));
	    }
	    return n;
	    
		}
	
	public boolean contains(Object o) {
		SNode h=head;
		SNode tmp=new SNode();
		tmp.data=o;
		int flag=0;
		while(h!=null) {
			if(h.data==tmp.data) {
				flag=1;
				break;
			}
			else {
			h=h.next;
			}
		}
		if(flag==0) {
		return false;
		}
		else {
			return true;
		}
	}

}
