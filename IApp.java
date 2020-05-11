package eg.edu.alexu.csd.datastructure.mailServer;

public interface IApp {
	
		public boolean signin(String email, String password);
		
		public boolean signup(IContact contact);
		
		public void setViewingOptions(IFolder folder, IFilter filter, ISort sort);
		
	    public IMail[] listEmails(int page);

	    public void deleteEmails(ILinkedList mails);

	    public void moveEmails(ILinkedList mails, IFolder des);

	    public boolean compose(IMail email);
		 

}
