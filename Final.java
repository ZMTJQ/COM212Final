import java.util.Scanner;
import java.io.*;

public class Final implements java.io.Serializable{

	static CustomerBST customerDirectory = loadCustomerBST();
	static MovieBST movieDateDirectory = loadMovieBST();
	static MovieHash movieIDDirectory = loadMovieHash();
	static MovieHeap movieRTDirectory = loadMovieHeap();
	
	public static void main(String[] args){
		boolean correctID = false;
		Scanner in = new Scanner(System.in);
		while(correctID==false){
			System.out.print("Enter admin ID:");
			int inputID = in.nextInt();
			if(inputID==12345){
				System.out.println("Welcome Admin");
				correctID=true;
				welcomeScreen();
			}
			else{
				System.out.println("Incorrect ID");
			}
		}
	}
	public static void welcomeScreen(){
		Scanner in = new Scanner(System.in);
		boolean correctNum = false;
		while(correctNum==false){
			System.out.println("Enter: ");
			System.out.println("'1' for customer list");
			System.out.println("'2' for admin control of movies");
			System.out.println("'3' to view all movies");
			System.out.println("'4' for customer access");
			int inputNum = in.nextInt();
		
			if(inputNum==1){
				run1();
				correctNum=true;
			}
			else if(inputNum==2){
				run2();
				correctNum=true;
			}
			else if(inputNum==3){
				run3();
				correctNum=true;
			}
			else if(inputNum==4){
				run4();
				correctNum=true;
			}
			else{
				System.out.println("Not an avaliable option. Try again: ");
			}
		}
		
	}
	
	public static void run1(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter: ");
		System.out.println("'1' to add a new customer");
		System.out.println("'2' to delete a customer");
		System.out.println("'3' to access a customer");
		System.out.println("'4' to see every customer in the directory");
		System.out.println("'5' to return to the welcome screen");
		int inputNum = in.nextInt();
		
		if(inputNum==1){
			System.out.println("Enter name: ");	
			String inputName = in.next();
			System.out.println("Enter 4 digit credit card number: ");
			int inputCC= in.nextInt();
			System.out.println("Enter email address: ");	
			String inputEmail = in.next();
			CustomerNode customer = new CustomerNode(inputName, inputCC, inputEmail);
			customerDirectory.insert(customer);
			saveCustomerBST(customerDirectory);
			run1();
		}
		else if(inputNum==2){
			System.out.println("Enter 4 digit credit card number: ");
			int inputCC= in.nextInt();		
			customerDirectory.delete(customerDirectory.search(inputCC));
			saveCustomerBST(customerDirectory);
			run1();
		}
		else if(inputNum==3){
			System.out.println("Enter 4 digit credit card number: ");
			int inputCC= in.nextInt();	
			CustomerNode c = customerDirectory.search(inputCC);
			System.out.println(c.getName() + c.getCreditCard() + c.getEmail());	
			System.out.println("What would you like to do? ");	
			System.out.println("'1' access Wish List");
			System.out.println("'2' access Have Watched List");
			System.out.println("'3' change other customer data");
			System.out.println("'4' return to the previous screen");
			int inputNum1 = in.nextInt();
		
			if(inputNum1==1){
				System.out.println("What would you like to do? ");	
				System.out.println("'1' access first movie in Wish List");	
				System.out.println("'2' add to Wish List");
				System.out.println("'3' delete first movie in Wish List");
				int inputNum2 = in.nextInt();
				if(inputNum2==1){
					c.accessFrontWishList().getName();
					run1();
				}
				else if(inputNum2==2){
					System.out.println("Enter 5 digit movie ID: ");
					int inputID= in.nextInt();
					MovieNode node = movieIDDirectory.lookUp(inputID);
					if(node!=null){ 	
						c.addToWishList(movieIDDirectory.lookUp(inputID));
					}
					else{
						System.out.println("Movie doesn't exist");
					}
					saveCustomerBST(customerDirectory);
					run1();
				}
				else if(inputNum2==3){
					c.deleteWishList();
					saveCustomerBST(customerDirectory);
					run1();
				}
				
				else{
					System.out.println("Not an avaliable option. Try again: ");
				}
			}
			else if(inputNum1==2){
				System.out.println("What would you like to do? ");	
				System.out.println("'1' see entire Have Watched List");	
				System.out.println("'2' search for a movie in Have Watched List");
				System.out.println("'3' delete a movie in Have Watched List");
				int inputNum3 = in.nextInt();
				if(inputNum3==1){
					c.printHaveWatchedList();
					run1();
				}
				else if(inputNum3==2){
					System.out.println("Enter 5 digit movie ID: ");
					int ID1= in.nextInt();	
					if (c.searchHaveWatchedList(ID1)!=null){
						System.out.println(c.searchHaveWatchedList(ID1).getName());
					}
					else{
						System.out.println("Not in list");
					}
					run1();
				}
				else if(inputNum3==3){
					System.out.println("Enter 5 digit movie ID: ");
					int ID2= in.nextInt();	
					c.deleteHaveWatchedList(c.searchHaveWatchedList(ID2));
					saveCustomerBST(customerDirectory);
					run1();
				}
				
				else{
					System.out.println("Not an avaliable option. Try again: ");
				}
			}
			else if(inputNum1==3){
				System.out.println("What would you like to do? ");	
				System.out.println("'1' change name");	
				System.out.println("'2' change credit card");
				System.out.println("'3' change email");
				int inputNum4 = in.nextInt();
				if(inputNum4==1){
					System.out.println("Enter name: ");	
					String inputName1 = in.next();
					c.setName(inputName1);
					saveCustomerBST(customerDirectory);
					run1();
				}
				else if(inputNum4==2){
					System.out.println("Enter credit card: ");	
					int inputCC1 = in.nextInt();
					c.setCreditCard(inputCC1);
					saveCustomerBST(customerDirectory);
					run1();
				}
				else if(inputNum4==3){
					System.out.println("Enter email address: ");	
					String inputEmail1 = in.next();
					c.setEmail(inputEmail1);
					saveCustomerBST(customerDirectory);
					run1();
				}
				
				else{
					System.out.println("Not an avaliable option. Try again: ");
				}
			}
			else if(inputNum1==4){
				run1();
			}
			else{
				System.out.println("Not an avaliable option. Try again: ");
			}
		}
		else if(inputNum==4){
			customerDirectory.traverse();	
			run1();
		}
		else if(inputNum==5){
			welcomeScreen();
		}
		else{
			System.out.println("Not an avaliable option. Try again: ");
		}
	}
	
	public static void run2(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter: ");
		System.out.println("'1' view lowest rated movie");
		System.out.println("'2' delete lowest rated movie");
		System.out.println("'3' add a new movie");
		System.out.println("'4' to return to the welcome screen");
		int inputNum = in.nextInt();
		
		if(inputNum==1){
			System.out.println(movieRTDirectory.findMin().getName());
			run2();
		}
		else if(inputNum==2){
			MovieNode temp = movieRTDirectory.findMin();
			movieRTDirectory.deleteMin();
			temp.setUavailable();
			saveMovieHeap(movieRTDirectory);
			saveMovieBST(movieDateDirectory);
			saveMovieHash(movieIDDirectory);
			run2();
		}
		else if(inputNum==3){
			System.out.println("Enter name: ");	
			String inputName = in.next();
			System.out.println("Enter release date ");
			int inputRD= in.nextInt();
			System.out.println("Enter 5 digit id code ");	
			int inputID = in.nextInt();
			System.out.println("Enter rotten tomato score");
			int inputRT= in.nextInt();
			MovieNode movie = new MovieNode(inputName, inputRD, inputID, inputRT);
			movieRTDirectory.insert(movie);
			movieIDDirectory.insert(movie);
			movieDateDirectory.insert(movie);
			saveMovieHeap(movieRTDirectory);
			saveMovieBST(movieDateDirectory);
			saveMovieHash(movieIDDirectory);
			run2();
		}
		else if(inputNum==4){
			welcomeScreen();
		}
		
		else{
			System.out.println("Not an avaliable option. Try again: ");
		}
	}
	
	public static void run3(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter: ");
		System.out.println("'1' print all movies in order of release date");
		System.out.println("'2' delete a movie");
		System.out.println("'3' to return to the welcome screen");
		int inputNum = in.nextInt();
		
		if(inputNum==1){
			movieDateDirectory.traverse();
			run3();
		}
		else if(inputNum==2){
			System.out.println("Enter release date: ");
			int RD= in.nextInt();	
			movieDateDirectory.delete(movieDateDirectory.search(RD));
			movieIDDirectory.delete(movieDateDirectory.search(RD).getID());
			saveMovieBST(movieDateDirectory);
			saveMovieHash(movieIDDirectory);
			run3();
		}
		else if(inputNum==3){
			welcomeScreen();
		}
		
		else{
			System.out.println("Not an avaliable option. Try again: ");
		}
	}
	
	public static void run4(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter movie ID: ");
		int movieID = in.nextInt();
		System.out.println(movieIDDirectory.lookUp(movieID).getName());
		welcomeScreen();
	}
	
	public static void saveMovieBST(MovieBST movie){
		try {
			FileOutputStream file = new FileOutputStream("movieBST.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(movie);
			out.close();
			file.close();
        	} 
        	catch (IOException e) {
			e.printStackTrace();
        	}
   	}

	public static MovieBST loadMovieBST(){
		MovieBST movie;
        	try {
			FileInputStream file = new FileInputStream("movieBST.ser");
			ObjectInputStream in = new ObjectInputStream(file);
            		movie = (MovieBST) in.readObject();
           		in.close();
            		file.close();
        	} 
        	catch (Exception e) {
            	movie = new MovieBST();
        	}
        return movie;
    	}
    	
    	public static void saveMovieHeap (MovieHeap movie){
		try {
			FileOutputStream file = new FileOutputStream("movieHeap.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(movie);
			out.close();
			file.close();
        	} 
        	catch (IOException e) {
			e.printStackTrace();
        	}
   	}

	public static MovieHeap loadMovieHeap(){
		MovieHeap movie;
        	try {
			FileInputStream file = new FileInputStream("movieHeap.ser");
			ObjectInputStream in = new ObjectInputStream(file);
            		movie = (MovieHeap) in.readObject();
           		in.close();
            		file.close();
        	} 
        	catch (Exception e) {
            	movie = new MovieHeap();
        	}
        return movie;
    	}
    	
    	public static void saveMovieHash (MovieHash movie){
		try {
			FileOutputStream file = new FileOutputStream("movieHash.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(movie);
			out.close();
			file.close();
        	} 
        	catch (IOException e) {
			e.printStackTrace();
        	}
   	}

	public static MovieHash loadMovieHash(){
		MovieHash movie;
        	try {
			FileInputStream file = new FileInputStream("movieHash.ser");
			ObjectInputStream in = new ObjectInputStream(file);
            		movie = (MovieHash) in.readObject();
           		in.close();
            		file.close();
        	} 
        	catch (Exception e) {
            	movie = new MovieHash();
        	}
        return movie;
    	}
    	
    	public static void saveCustomerBST (CustomerBST movie){
		try {
			FileOutputStream file = new FileOutputStream("customerBST.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(movie);
			out.close();
			file.close();
        	} 
        	catch (IOException e) {
			e.printStackTrace();
        	}
   	}

	public static CustomerBST loadCustomerBST(){
		CustomerBST movie;
        	try {
			FileInputStream file = new FileInputStream("customerBST.ser");
			ObjectInputStream in = new ObjectInputStream(file);
            		movie = (CustomerBST) in.readObject();
           		in.close();
            		file.close();
        	} 
        	catch (Exception e) {
            	movie = new CustomerBST();
			CustomerNode customer1 = new CustomerNode( "Zach_Qutikin", 1234, "zq@cc.edu");
            	CustomerNode customer2 = new CustomerNode( "Evan_Lyons", 2345, "el@cc.edu");
            	CustomerNode customer3 = new CustomerNode( "Arjun_Premkumar", 3456, "ap@cc.edu");
            	CustomerNode customer4 = new CustomerNode( "Derin_Gezgin", 4567, "dg@cc.edu");
            	CustomerNode customer5 = new CustomerNode( "Dimitris_Seremitis", 5678, "ds@cc.edu");
            	CustomerNode customer6 = new CustomerNode( "Nick_Essery", 6789, "ne@cc.edu");
            	CustomerNode customer7 = new CustomerNode( "Johnny_Andreasen", 7890, "ja@cc.edu");
            	movie.insert(customer1);
            	movie.insert(customer2);
            	movie.insert(customer3);
            	movie.insert(customer4);
            	movie.insert(customer5);
            	movie.insert(customer6);
            	movie.insert(customer7);
            	
			
        	}
        return movie;
    	}
    	
    	    	
}
