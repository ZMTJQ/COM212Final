//Zach Quitkin, Evan Lyons, Arjun Premkumar
//5/9/2024
//Version of Hash class used to store movies by ID
//which is how customers can access individual movies
//it holds movies (MovieNodes)
//COM212 Final Project

import java.io.*;
public class MovieHash implements java.io.Serializable{
	private MovieNode[] h = new MovieNode[257];
	private int n = 0;
	
	public int hash(int k){
		return k%257;
	}
	public boolean empty(){
		if(n==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void insert(MovieNode x){
		int temp = hash(x.getID());
		if(h[temp]==null){
			h[temp]=x;
			n++;
		}
		else{
			MovieNode head = h[temp];
	 		h[temp] = x;
	 		h[temp].setNext(head);
	 		n++;
	 	}
	}
	
	public void delete(int k){
		int num = hash(k);
		MovieNode temp = h[num];
		if(temp!=null){
			if(temp.getID()==k){
				h[num] = temp.getNext();
				n--;
			}
			else{
				while(temp.getNext()!=null){
					if(k==temp.getNext().getID()){
						MovieNode temp2 = temp.getNext();
						temp.setNext(temp2.getNext());
						temp2.setNext(null);
						n--;
					}
					else{
						temp = temp.getNext();
					}
				}
			}
		}
	}
	
	public void print(){
		System.out.println("Movies: "+n);
		for(int i = 0; i<257; i++){
			System.out.print(i+": ");
			MovieNode temp = h[i];
			while(temp!=null){
				System.out.print(temp.getID()+": ");
				temp = temp.getNext();
				
			}
				System.out.println(temp);
		}
	}
	
	public MovieNode lookUp(int k){
		int key = hash(k);
		MovieNode temp = h[key];
		while(temp!=null)
		{
			if(temp.getID()==k){
				return temp;
			}
			else{
				temp = temp.getNext();
			}
		}
		return null;
	}
	
}
