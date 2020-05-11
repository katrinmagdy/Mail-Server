package eg.edu.alexu.csd.datastructure.mailServer;
//noter array must be sorted first
public class BinarySearchByStack {
StackMethods s=new StackMethods();StackMethods tmp=new StackMethods();int arrOfResults[]=new int[15000]; int count=0;


public String getMiddleElementOfStack(StackMethods ss) {
	int i=0;StackMethods st=new StackMethods();
	while(i<(ss.size()/2)) {
		st.push(ss.pop());i++;
	}String m=ss.peek().toString();
	while(st.size()>0) {
		ss.push(st.pop());
	}
	return m ;//middle element
}

public int getMiddleIndexOfStack(StackMethods ss) {
	int i=0;StackMethods st=new StackMethods();
	while(i<(ss.size()/2)) {
		st.push(ss.pop());i++;
	}int m=Integer.valueOf(ss.peek().toString());
	while(st.size()>0) {
		ss.push(st.pop());
	}
	return m ;//middle element
}



public void getRidOfFirstHalf(StackMethods ss,StackMethods ttt) {
	//x>arr[mid]
	StackMethods t=new StackMethods();StackMethods tt=new StackMethods();
	int i=0;
	while (i<(ss.size()/2)) {
		t.push(ss.pop());i++;tt.push(ttt.pop());
	}
	while(ss.size()>0) {
		ss.pop();ttt.pop();
	}
	while(t.size()>0) {
		ss.push(t.pop());ttt.push(tt.pop());
	}
	
}
public void getRidOfSecondHalf(StackMethods ss,StackMethods t) {
	//x<arr[mid]
	int i=0;
	while (i<(ss.size()/2)) {
		ss.pop();t.pop();
		i++;
	}
	
}


public void getRidOfmid(StackMethods ss) {
	//x<arr[mid]
	int i=0;StackMethods s=new StackMethods();
	while (i<(ss.size()/2)) {
		s.push(ss.pop());
		i++;
	}
	ss.pop();
	while(s.size()>0) {
		ss.push(s.pop());
	}
}

public void IterativeBinSearch(String []arr,String x) {//we want to find x
	QuickSortByStack q=new QuickSortByStack();q.IterativeQuickSort(arr);
	int i=0;String mid=new String();
	while(i<arr.length) {
		s.push(arr[i]);i++;
	}i=0;
	while(i<arr.length) {
		tmp.push(i);i++;//stack of indexes
	}
	BinarySearchByStack b=new BinarySearchByStack();
	while(s.isEmpty()==false) {
		mid=b.getMiddleElementOfStack(s);
		if(s.size()==1) {
			if(s.pop().toString().contains(x)) {
				arrOfResults[count]=Integer.valueOf(tmp.pop().toString());count++;
				break;
				//return true;
			}else {break;
			}
		}
		if(mid.contains(x)==true) {
			arrOfResults[count]=b.getMiddleIndexOfStack(tmp);count++;
			b.getRidOfmid(s);b.getRidOfmid(tmp);
		}
		else if(x.compareTo(mid)<0) {
			b.getRidOfSecondHalf(s,tmp);
		}else {
			b.getRidOfFirstHalf(s,tmp);
		}
	}
	
}

public int []getRes(){
	if(count==0) {
		return null;
	}
	int i=0;int arra[]=new int [count];
	while(i<count) {
		arra[i]=arrOfResults[i];i++;
	}return arra;
} 

public int getCount(){
	return count;
}


public void printResults() {
	int i=0;
	System.out.println();
	while(i<count) {System.out.print(arrOfResults[i]+" ");
		i++;
	}
}
}