import java.io.*;
public class MovieNode implements java.io.Serializable{

	private boolean available;
	private String name;
	private int ReleaseDate;
	private int UniqueIdCode;
	private int RottenTomatoes;
	private MovieNode left;
	private MovieNode right;

	public MovieNode(String name0,int releasedate0, int uniqueidcode0, int rottenTomatoes0) {
		available = true;
		name = name0;
		ReleaseDate = releasedate0%100000000;
		UniqueIdCode = uniqueidcode0%100000;
		RottenTomatoes = rottenTomatoes0;
		left = null;
		right = null;
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
	public MovieNode getRight(){
		return this.right;
	}
	public MovieNode getLeft(){
		return this.left;
	}

	

	
}



