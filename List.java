//Zach Quitkin, Evan Lyons, Arjun Premkumar
//5/9/2024
//Linked List class used for Have-Watched List in CustomerNode
//COM212 Final Project

import java.io.*;
public class List implements java.io.Serializable{
	
	private MovieNode head = null;
	private int n = 0;
	
	public List () {
		
	}
	
	//returns length of list
	public int length() {
		return n;
	}
	
	//returns true if list is empty
	public boolean isEmptyList() {
		if (head ==null) {
			return true;
		}
		else {
			return false;
		}
	}

	//searches for a node and returns its key
	public MovieNode searchReturn(int ID) {
		MovieNode temp = head;
		if (head == null){
			return null;
		}
		else {
			while (temp!=null) {
				if (temp.getID() == ID) {
					return temp; 
				}
				
				else {
					temp = temp.getRight(); 
				}
			}
			return null;
		}
	}
		
	//searches for a node, removes it, and returns its key
	public MovieNode searchRemove(int key){
		MovieNode temp = head;
		if (head == null){
			return null;
		}
		
		else if (head.getID() == key){
			head = head.getRight();
			n--;
			return temp;
		}
		
		else {
			while (temp!=null) {
				if (temp.getRight().getID() == key) {
					MovieNode tempNext = temp.getRight();
					temp.setRight(tempNext.getRight());
					tempNext.setRight(null);
					n--;
					return temp; 
					}
				else {
					temp = temp.getRight(); 
				}
			}
			return null;
		}
	}
	
	//inserts new node x at the front of the list
	public void insert(MovieNode x) {
		if (head==null){
			head = x;
			n++;
		}
		
		else {
			MovieNode temp = head;
			head = x;
			head.setRight(temp);
			n++;
		}
	}
	
	//prints the contents of the list in order first to last
	public void printList(){
		if (head != null){
			//System.out.println(n);
			MovieNode temp = head;
			while (temp!=null) {
				System.out.println(temp.getName());
				temp = temp.getRight();
			}
		}
		
		else {
			//System.out.println(n);
		}
	}
	
}
