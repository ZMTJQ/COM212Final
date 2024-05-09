import java.io.*;
public class MovieHash implements java.io.Serializable{
	private MovieNode[] h = new MovieNode[251];
	private int n = 0;
	
	public int hash(int k){
		return k%251;
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
	 		h[temp].setRight(head);
	 		n++;
	 	}
	}
	
	public void delete(int k){
		int num = hash(k);
		MovieNode temp = h[num];
		if(temp!=null){
			if(temp.getID()==k){
				h[num] = temp.getRight();
				n--;
		
			}
		
			else{
				while(temp.getRight()!=null){
					if(k==temp.getRight().getID()){
						MovieNode temp2 = temp.getRight();
						temp.setRight(temp2.getRight());
						temp2.setRight(null);
						n--;
					}
					else{
						temp = temp.getRight();
					}
			
				}
		
			}
		
		}
	}
	
	public void print(){
	System.out.println("Movies: "+n);
	for(int i = 0; i<251; i++){
		System.out.print(i+": ");
		MovieNode temp = h[i];
		while(temp!=null){
			System.out.print(temp.getID()+": ");
			temp = temp.getRight();
			
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
				temp = temp.getRight();
			}
		}
		return null;
		
	}
	
	
}
