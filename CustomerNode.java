//Zach Quitkin, 3/7/2024
//Program/class Node
//This class defines a node object discussed in class. 
//It has a name, SSN, and next pointer as instance variables

public class CustomerNode{
	
	private String name;
	private int creditCard;
	private String email;
	private Queue wishList;
	private List haveWatchedList;
	private CustomerNode left;
	private CustomerNode right;
	
	public CustomerNode(String name0, int creditCard0, String email0) {
		name = name0;
		creditCard = creditCard0%10000;
		email = email0;
		wishList = null;
		haveWatchedList = null;
		left = null;
		right = null;
	}
	
	//returns node's name
	public String getName() {
		return name;
	}
	
	//returns node's credit card number
	public int getCreditCard() {
		return creditCard;
	}
	
	//returns node's email
	public String getEmail() {
		return email;
	}
	
	public void printWishList(){
		wishList.printQueue();
	}
	
	public void printHaveWatchedList(){
		haveWatchedList.printList();
	}
	
	public MovieNode accessFrontWishList(){
		if(wishList.front().isAvailable() == true){
			return wishList.front();
		}
		else{
			System.out.println("Movie is unavailable");
			wishList.dequeue();
			return accessFrontWishList();
		}
	}
	
	public void searchHaveWatchedList(int ID){
		haveWatchedList.searchReturn(ID);
	}
	
	public void addToWishList(MovieNode movie){
		wishList.enqueue(movie);
	}
	
	public void addToHaveWatchedList(MovieNode movie){
		haveWatchedList.insert(movie);
	}
	
	public void deleteWishList(){
		wishList.dequeue();
	}	
	
	public void deleteHaveWatchedList(MovieNode movie){
		haveWatchedList.searchRemove(movie.getID());
	}	
	
	//sets node's name to new name
	public void setName(String name1) {
		name = name1;
	}

	//sets node's ssn to new ssn
	public void setCreditCard(int creditCard1){
		creditCard = creditCard1;
	} 
	
	//sets node's email
	public void setEmail(String email1) {
		email = email1;
	}
	
	//sets node's left pointer to new/next node
	public void setLeft(CustomerNode nextNode){
		left = nextNode;
	}
	
	//sets node's right pointer to new/next node
	public void setRight(CustomerNode nextNode){
		right = nextNode;
	}
	
	//returns the subsequent left node
	public CustomerNode getLeft(){
		return left;
	}
	
	//returns the subsequent right node
	public CustomerNode getRight(){
		return right;
	}
	
}
