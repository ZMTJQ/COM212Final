//Zach Quitkin, Evan Lyons, Arjun Premkumar
//5/9/2024
//A version of node class used to create/instantiate movies
//name, release date, ID code, rotten tomato score, 
//availablity boolean, left and right
//COM212 Final Project

import java.io.*;
public class MovieNode implements java.io.Serializable{

	private boolean available;
	private String name;
	private int ReleaseDate;
	private int UniqueIdCode;
	private int RottenTomatoes;
	private MovieNode left;
	private MovieNode right;
	private MovieNode next; //necessary for hash implementation

	public MovieNode(int ID, String name0,int releasedate0, int rottenTomatoes0) {
		available = true;
		name = name0;
		ReleaseDate = releasedate0%100000000;
		UniqueIdCode = ID;
		RottenTomatoes = rottenTomatoes0;
		left = null;
		right = null;
		next = null;
	}
	public boolean isAvailable(){
		return available;
	}
	public void setUavailable(){
		available = false;
	}
	public String getName() {
		return name;
	}

	public int getReleaseDate(){
		return ReleaseDate;
	}

	public int getID() {
		return UniqueIdCode;
	}

	public int getRottenTomatoes() {
		return RottenTomatoes;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setReleaseDate(int date){
		ReleaseDate=date;
	}
	public void setID(int id){
		UniqueIdCode=id;
	}
	public void setRottenTomatoes(int rt){
		RottenTomatoes=rt;
	}
	public void setLeft(MovieNode left){
		this.left = left;
	}
	public void setRight(MovieNode right){
		this.right = right;
	}

	public void setNext(MovieNode nextNode){
		this.next = nextNode;
	}
	public MovieNode getRight(){
		return this.right;
	}
	public MovieNode getLeft(){
		return this.left;
	}
	public MovieNode getNext(){
		return this.next;
	}

	

	
}



