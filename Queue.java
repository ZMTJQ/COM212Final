//Zach Quitkin 3/30
//Program & class QueueA
//This class implements a queue using an array
//It has an array of length 100, int front, and int n (counter) as instance variables
//A user can perform the following methods on a queue
//front, dequeue, enqueue, isEmpty
import java.io.*;
public class Queue implements java.io.Serializable{
	
	private MovieNode[] q= new MovieNode[20];
	private int front = 0;
	private int n = 0;
	
	public Queue() {
		
	}
	
	//returns node at front of the queue
	public MovieNode front(){
		if (isEmpty() == true){
			return null;
		}
		else {
			return q[front];
		}
	}
	
	//removes and returns node at the front of the queue
	public MovieNode dequeue(){
		int temp = front;
		front = (front+1)%20;
		n = n - 1;
		return q[temp];
	}
	
	//adds node to end of the queue
	public void enqueue(MovieNode x){
		int tail = (front + n)%20;
		q[tail] = x;
		n = n+1;
	}
	
	//returns true if queue is empty
	public boolean isEmpty(){
		if (n == 0){
			return true;
		}
		else {
			return false;
		}
	}
	
	//prints contents of the queue in order 1st-last
	public void printQueue() {
		int tail = (front + n) % 20;
		System.out.println(front);
		System.out.println(tail);
		if (front <= tail)
			for(int i = front; i < tail; i++) 
			System.out.println(q[i].getName());
		else {
			for(int i = front; i < 20; i++) 
				System.out.println(q[i].getName());          
			for(int i = 0; i < tail; i++) 
				System.out.println(q[i].getName());
		}          
    }

}
