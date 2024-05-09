//Zach Quitkin, Evan Lyons, Arjun Premkumar
//5/9/2024
//Version of BST class used to store movies by date that admins edit
//and customers can view
//it holds movies (MovieNodes) and is how admins
//delete movies from the directory for good.
//COM212 Final Project

import java.io.*;
public class MovieBST implements java.io.Serializable{
	
    private MovieNode root;
	
    public MovieBST() {
        root = null; //empty BST
    }
    public boolean isEmptyTree() {  //to check if tree is empty
        return root == null;
    }
    
    public MovieNode search(int key) {
        return search2(root, key);  //calls search2 with root node to search for
    }
   
    private MovieNode search2(MovieNode node, int key) {  //to search for a key in BST (recursive method)
        if (node == null || node.getReleaseDate() == key) {  // checks if current node is null or matches target key
            return node;
        }
        if (key < node.getReleaseDate()) {  //if its less, it searches left subtree
            return search2(node.getLeft(), key); //returns left subtree
        }
        
        return search2(node.getRight(), key);  //recursively calls right child node and target key
    }
   
    //inserts node using recursion
	public void insert(MovieNode x){
		if (root == null){
			root = x;
		}
		else{
			insert2(root,x);
		}
	}
	
	//helper function for insert: finds appropriate spot
	private void insert2(MovieNode t, MovieNode p){
		if (p.getReleaseDate() < t.getReleaseDate()){
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
 
    
    public void delete(MovieNode node) {
        root = delete2(root, node.getReleaseDate());  //deletes the node from BST
    }
    
    
    private MovieNode delete2(MovieNode root, int key) {
        if (root == null) {
            return root;
            
        }
        
        if (key < root.getReleaseDate()) {
            root.setLeft(delete2(root.getLeft(), key)); //calls delete2 with left childnode
        } else if (key > root.getReleaseDate()) {
            root.setRight(delete2(root.getRight(), key)); //calls delete2 with right childnode
        } else {
		    if (root.getLeft() == null) { //if left is null, returns the right child
		        return root.getRight();
		    } else if (root.getRight() == null) { //if right is null, returns the left
		        return root.getLeft();
		    }
		    root.setReleaseDate(minValue(root.getRight()));
		    root.setRight(delete2(root.getRight(), root.getReleaseDate()));
		}
        return root;
    }
    
    
    private int minValue(MovieNode root) {  //private way to find minimum value in BST
        int minValue = root.getReleaseDate();  //initialising min value to key of current noede
        while (root.getLeft() != null) { //loops until left node is null
            minValue = root.getLeft().getReleaseDate();
            root = root.getLeft(); //updates current node to left child
        }
        return minValue;
    }
    
    
    public void traverse() {
        traverse2(root);
        System.out.println();
    }
    
    
    private void traverse2(MovieNode node) { //traverse with private recursion
        if (node != null) {		//loops until its null
            traverse2(node.getLeft());  //calls with left child node
            System.out.print("[" + node.getName() + "; Date: " + node.getReleaseDate() + "; ID: " + node.getID());
            System.out.print("; Rotten Tomatoes: " + node.getRottenTomatoes() + "; Status: ");
            if(node.isAvailable()==true){
        	    System.out.print("Available ]");
            }
            else{
            	    System.out.print("Unavailable ]");
            }
            System.out.println();
            traverse2(node.getRight()); //calls with right child node
        }
    }
    
    
    public void printTree() {
        printTree2(root);
        System.out.println();
    }
    
    private void printTree2(MovieNode root) { 
        if (root != null) {
            System.out.print(root.getReleaseDate() + " "); //prints key of current node
            if (root.getLeft() != null)		//checks if left child exists
                System.out.print("Left: " + root.getLeft().getReleaseDate() + " "); //prints left child 
            else
                System.out.print("Left: null "); 
            if (root.getRight() != null) //checks if right child exists
                System.out.println("Right: " + root.getRight().getReleaseDate() + " "); //prints right
            else
                System.out.println("Right: null ");
            printTree2(root.getLeft());
            printTree2     (root.getRight());   //recursively calls with left&right child node
        }
    }
}
