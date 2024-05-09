//Zach Quitkin, Evan Lyons, Arjun Premkumar
//5/9/2024
//Heap class used to store movies that admins edit
//it holds movies (MovieNodes) and is how admins
//make movies available(adding them) or unavailable(deleting them)
//COM212 Final Project

import java.io.*;
public class MovieHeap implements java.io.Serializable{
	
	private MovieNode[] h= new MovieNode[255];
	private int n = 0;
	
	public MovieHeap() {
		
	}
	
	//finds left child of current node
	private int left(int x){
		return ((2*x)+1);
	}
	
	//finds right child of current node
	private int right(int x){
		return ((2*x)+2);
	}
	
	//finds parent of current node
	private int parent(int x){
		return ((x-1)/2);
	}
	
	//returns the minimum node
	public MovieNode findMin(){
		if(n==0){
			return null;
		}
		else{
			return h[0];
		}
	}
	
	public boolean isFull(){
		if (n==255){
			return true;
		}
		else{
			return false;
		}
	}
	
	//swaps node positions at indices x and y
	public void swap(int x, int y){
		MovieNode temp = h[y];
		h[y] = h[x];
		h[x] = temp;
	}
	
	public int getN(){
		return n;
	}
	//inserts new node at the end, calls adjust to find its proper place
	public void insert(MovieNode x){
		if(n<255){
			h[n] = x;
			adjust(n);
			n++;
		}
		else {
			System.out.println("The heap is full");
		}
	}
	//node is less than parent, swap up until this is not the case
	private void adjust(int x){
		if(x>0){
			if (h[parent(x)].getRottenTomatoes() > h[x].getRottenTomatoes()){
				swap(parent(x),x);
				adjust(parent(x));
			}
		}
	}
	
	//deletes minimum node in the list by doing 3 things:
	//shortens length of list to n-1
	//swaps minimum node, at 0, with node at n
	//trickles node at minmum spot to proper spot
	public void deleteMin(){
		if(n!=0){
			n--;
			swap(0,(n));
			trickle(0);
		}
		else{
			System.out.println("The heap is empty");
		}
}
	
	private void trickle(int i){
		if(right(i)<n){
			if (h[left(i)].getRottenTomatoes() < h[i].getRottenTomatoes() || h[right(i)].getRottenTomatoes() < h[i].getRottenTomatoes()){
				if(h[left(i)].getRottenTomatoes() > h[right(i)].getRottenTomatoes()) {
					swap(right(i), i);
					trickle(right(i));
				
				}
				else{
					swap(left(i), i);
					trickle(left(i));
				}
			}
		}
	}
	
	//returns true if length of Heap is 0
	public boolean isEmptyHeap(){
		if (n==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	//prints the heap in array format
	public void traverse(){
		for(int i= 0; i<n; i++){
			System.out.print(h[i].getRottenTomatoes() + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	//prints contents of the heap as a tree, indicating left and right nodes
	public void printHeap() {
		printHeap2(0);
		System.out.println();
	}
	
	//prints helper function: does the printing
	private void printHeap2(int i) {
		if (i<n) {
			System.out.print(h[i].getRottenTomatoes() + " ");
			if (left(i) < n)
				System.out.print("Left: " + h[left(i)].getRottenTomatoes() + " ");
			else
				System.out.print("Left: null ");
			if (right(i) < n)
				System.out.println("Right: " + h[right(i)].getRottenTomatoes() + " ");
			else
				System.out.println("Right: null ");
				printHeap2(left(i));
				printHeap2(right(i));
		}
	}	

}
