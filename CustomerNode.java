//Zach Quitkin, Evan Lyons, Arjun Premkumar
//5/9/2024
//A version of node class used to create/instantiate customers
//name, credit card, email, wishlist, have watched, left and right
//COM212 Final Project

import java.io.*;
public class CustomerNode implements java.io.Serializable{
	
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
		Queue wishList = new Queue();
		haveWatchedList = new List();
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
		if(wishList==null){
			wishList = new Queue();
			System.out.println("No movies in Wish List");
		}
		else{
			wishList.printQueue();
		}
	}
	
	public void printHaveWatchedList(){
		haveWatchedList.printList();
	}
	
	public MovieNode accessFrontWishList(){
		if(wishList ==null){
			wishList = new Queue();
			return null;
		}
		else{
			if(wishList.front()!=null){
				if(wishList.front().isAvailable() == true){
					return wishList.front();
				}
				else{
					System.out.println(wishList.front().getName() +" is unavailable. Loading next movie");
					wishList.dequeue();
					return accessFrontWishList();
				}
			}
			else{
				return null;
			}
		}
	}
	
	public MovieNode searchHaveWatchedList(int ID){
		if(haveWatchedList.searchReturn(ID) == null){
			return null;
		}
		else{
			return haveWatchedList.searchReturn(ID);
		}
	}
	
	public void addToWishList(MovieNode movie){
		if(movie!=null){
			if(wishList ==null){
				wishList = new Queue();
				wishList.enqueue(movie);
			}
			else{
				if(wishList.getN()<21){
					wishList.enqueue(movie);
				}
				else{
					System.out.println("Wish List is Full");
				}
			}
		}
		else{
			System.out.println("Can't add movie");
			}
	}
	public void addToHaveWatchedList(MovieNode movie){
		haveWatchedList.insert(movie);
	}
	
	public void deleteWishList(){
		if(wishList==null){
			wishList = new Queue();
			System.out.println("Wish List is empty");
		}
		else{
			wishList.dequeue();
		}
	}	
	
	public void deleteHaveWatchedList(MovieNode movie){
		if(movie!=null){
			if(haveWatchedList.searchReturn(movie.getID())==null){
				System.out.println("Movie not in have watched list");
			}
			else{
				haveWatchedList.searchRemove(movie.getID());
			}
		}
		else{
			System.out.println("Movie not in have watched list");
		}
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
