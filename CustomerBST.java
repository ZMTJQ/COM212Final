//Zach Quitkin, Evan Lyons, Arjun Premkumar
//5/9/2024
//Version of BST class used to store customers by credit card
//Admins can access this and the individual customers within it
//when new customers create accounts, they're added to this
//returning customers log in data (credit card) is stored here
//it holds customers (CustomerNodes) 
//COM212 Final Project

import java.io.*;
public class CustomerBST implements java.io.Serializable{
	
	private CustomerNode root = null;
	
	public CustomerBST(){
		
	}
	
	//inserts node using recursion
	public void insert(CustomerNode x){
		if (root == null){
			root = x;
		}
		else{
			insert2(root,x);
		}
	}
	
	//helper function for insert: finds appropriate spot
	private void insert2(CustomerNode t, CustomerNode p){
		if (p.getCreditCard() < t.getCreditCard()){
			if (t.getLeft() == null){
				t.setLeft(p);
			}
			else{
				insert2(t.getLeft(), p);
			}
		}
		else{
			if (t.getRight() == null){
				t.setRight(p);
			}
			else{ 
				insert2(t.getRight(), p);
				}
			}				
	}
	
	//deletes node using recursion
	public void delete(CustomerNode x){
		if(root!=null){
			if(root==x){
				root = deleteRoot(x);
			}
			else{
				delete2(root,x);
			}
		}
	}
	
	//delete's helper function #1: evaluates what to do at each case
	public CustomerNode deleteRoot(CustomerNode x){
		if (x.getRight() == null && x.getLeft()==null){  //if node is leaf
			return null;
		}
		else if(x.getRight() == null && x.getLeft()!=null){ //if node has left child
			CustomerNode temp = x;
			x =x.getLeft();
			temp.setLeft(null);
			return x;
		}
		else if(x.getRight() != null && x.getLeft()==null){  //if node has right child
			CustomerNode temp = x;
			x =x.getRight();
			temp.setRight(null);
			return x;
		}
		else{						  //if node has two children
			CustomerNode temp = successor(x);
			delete(temp);
			temp.setLeft(x.getLeft());
			temp.setRight(x.getRight());
			x.setLeft(null);
			x.setRight(null);
			return temp;	
		}
	}
	
	//delete's helper function #2: finds pointer in the list so node gets properly deleted
	public void delete2(CustomerNode x, CustomerNode y){
		if (x.getLeft()!=null && x.getCreditCard() > y.getCreditCard()){
			if(x.getLeft().getCreditCard() == y.getCreditCard()){
				x.setLeft(deleteRoot(y));
			}
			else{
				delete2(x.getLeft(),y);
			}
		}
		else if (x.getRight()!=null && x.getCreditCard() < y.getCreditCard()){
			if(x.getRight().getCreditCard() == y.getCreditCard()){
				x.setRight(deleteRoot(y));
			}
			else{
				delete2(x.getRight(),y);
			}
		}
	}
	
	//delete's helper function #3: finds appropriate successor
	private CustomerNode successor(CustomerNode p){
		CustomerNode temp = p.getRight();
		while (temp.getLeft()!=null){
			temp = temp.getLeft();
		}
		return temp;
	}
	
	//searches and returns node with desired key
	public CustomerNode search(int key){
		return searchr(root, key);
	}
	
	//search helper function: actually does the searching
	private CustomerNode searchr(CustomerNode x, int k){
		if(x==null){
			return null;
		}
		else if(k == x.getCreditCard()){
			return x;
		}
		else if(k < x.getCreditCard()){
			return searchr(x.getLeft(),k);
		}
		else{
			return searchr(x.getRight(),k);
		}
	}
	
	//traverses through the list, prints keys in order
	public void traverse(){
		traverser(root);
		System.out.println();
	}
	
	//traverse helper function: does the traversing in order left - right
	private void traverser(CustomerNode x){
		if (x!=null){
			traverser(x.getLeft());
			System.out.println(x.getName() +": " + x.getCreditCard() + " ");
			traverser(x.getRight());
		}
	}
	
	//prints contents of the tree, indicating left and right nodes
	public void printTree() {
		printTree2(root);
		System.out.println();
	}
	
	//prints helper function: does the printing
	private void printTree2(CustomerNode tree) {
		if (tree != null) {
			System.out.print(tree.getCreditCard() + " ");
			if (tree.getLeft() != null)
				System.out.print("Left: " + tree.getLeft().getCreditCard() + " ");
			else
				System.out.print("Left: null ");
			if (tree.getRight() != null)
				System.out.println("Right: " + tree.getRight().getCreditCard() + " ");
			else
				System.out.println("Right: null ");
				printTree2(tree.getLeft());
				printTree2(tree.getRight());
		}
	}	
	
	//checks if tree is empty -- returns true if root is null
	public boolean isEmptyTree(){
		if (root==null){
			return true;
		}
		else{
			return false;
		}
	}
	
}
