import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Final implements java.io.Serializable{

	static CustomerBST customerDirectory = loadCustomerBST();
	static MovieBST movieDateDirectory = loadMovieBST();
	static MovieHash movieIDDirectory = loadMovieHash();
	static MovieHeap movieRTDirectory = loadMovieHeap();
	static int IDCounter = loadID();
	
	public static void createID(MovieNode movie){
		movie.setID(IDCounter);
		IDCounter++;
		saveID(IDCounter);
	}
	
	public static void main(String[] args){
		run();
	}
	public static void run(){
		Scanner in = new Scanner(System.in);
		System.out.print("Enter '1' for Customer login or '2' for Admin login: ");
			int inputU = in.nextInt();
			try{
				if(inputU==1){
					customerLogin();
					
				}
				else if(inputU==2){
					adminLogin();
				}
			}
			catch(InputMismatchException e){
				System.out.println("Not an avaliable option.");
				run();
			}
	}
	public static void customerLogin(){
		Scanner in = new Scanner(System.in);
		System.out.println("'1' to create an account or '2' for customer login");
		int inputNum = in.nextInt();
		try{
			if(inputNum==1){
				System.out.println("Create an Account:");
				System.out.println("Enter username: ");	
				String inputName = in.next();
				System.out.println("Enter 4 digit credit card number: ");
				int inputCC= in.nextInt();
				System.out.println("Enter email address: ");	
				String inputEmail = in.next();
				CustomerNode customer = new CustomerNode(inputName, inputCC, inputEmail);
				customerDirectory.insert(customer);
				saveCustomerBST(customerDirectory);
				System.out.println("Hello "+customer.getName());
				customerScreen(customer);
			}
			
			else if(inputNum==2){
					
				System.out.print("Enter customer credit card:");
				int inputCCard = in.nextInt();
				CustomerNode User = customerDirectory.search(inputCCard);
					try{
						if(customerDirectory.search(inputCCard)!=null){
							customerScreen(User);
							System.out.println("Hello "+User.getName());
						}
						else{
							System.out.println("Incorrect credit card.");
							customerLogin();
						}
		
					}
					catch(InputMismatchException e){
						System.out.println("Incorrect credit card.");
						customerLogin();
					}
			}
		}
		catch(InputMismatchException e){
						System.out.println("Wrong input type. Restarting Log-in process...");
						customerLogin();
		}
	}
	public static void adminLogin(){
		boolean correctID = false;
		Scanner in = new Scanner(System.in);
		
		try{
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
		catch(InputMismatchException e){
				System.out.println("Incorrect ID");
				adminLogin();
		}
	}
	
	public static void customerScreen(CustomerNode customer1){
	Scanner in = new Scanner(System.in);
		try{
			System.out.println("Enter: ");
			System.out.println("'1' to view personal info");
			System.out.println("'2' to search and access a movie");
			System.out.println("'3' to view all movies");
			System.out.println("'4' to logout");
			int inputNum = in.nextInt();
			
				if(inputNum==1){
					customerRun1(customer1);
				}
				else if(inputNum==2){
					customerRun2(customer1);	
				}
				else if(inputNum==3){
					customerRun3(customer1);
				}
				else if(inputNum==4){
					System.out.println("logging out...");
					run();
				}
				else{
					System.out.println("Not an avaliable option. Try again: ");
				}
			}
		catch(InputMismatchException e){
				System.out.println("Choose one of the options");
				welcomeScreen();
		}		
		
	}
	
	public static void customerRun1(CustomerNode customer2){
		Scanner in = new Scanner(System.in);
		System.out.println("Info: ");
		System.out.println(customer2.getName());
		System.out.println(customer2.getCreditCard());
		System.out.println(customer2.getEmail());
		System.out.println();
		try{
			System.out.println("Enter: ");
			System.out.println("'1' access your wish list");
			System.out.println("'2' access your have watched list");
			System.out.println("'3' to access or search for a movie");
			System.out.println("'4' to see every movie in the directory");
			System.out.println("'5' to log out");
			int inputNum = in.nextInt();
			
			if(inputNum==1){
				System.out.println("Enter: ");
				System.out.println("'1' access first movie in wish list");
				System.out.println("'2' delete first movie in wish list");
				System.out.println("'3' add movie to wishList");
				System.out.println("'4' print entire wish list");
				System.out.println("'5' to return to prior screen");
				int inputNum1 = in.nextInt();
					if(inputNum1==1){
						if(customer2.accessFrontWishList()!=null){
							System.out.println(customer2.accessFrontWishList().getName());
							System.out.println("'1' delete first movie in Wish List");
							System.out.println("'2' return to customer menu");
							int inputNum12 = in.nextInt();
							if(inputNum12==1){
								System.out.println("'1' if movie was watched");
								System.out.println("'2' if not");
								int inputNums = in.nextInt();
								if(inputNums==1){
									customer2.addToHaveWatchedList(customer2.accessFrontWishList());
									saveCustomerBST(customerDirectory);
									customerRun1(customer2);	
								}
								customer2.deleteWishList();
								saveCustomerBST(customerDirectory);
								System.out.println("deleted.");
								customerRun1(customer2);
							}
							else if(inputNum12==2){	
								customerRun1(customer2);
							}
						}
						else{
							System.out.println("Wish List is Empty");
							customerRun1(customer2);
						}
					}
					else if(inputNum1==2){
						if(customer2.accessFrontWishList()!=null){
							System.out.println("'1' if movie was watched");
							System.out.println("'2' if not");
							int inputNum12 = in.nextInt();
							if(inputNum12==1){
								customer2.addToHaveWatchedList(customer2.accessFrontWishList());
								saveCustomerBST(customerDirectory);	
							}
							customer2.deleteWishList();
							saveCustomerBST(customerDirectory);
							System.out.println("deleted.");
							customerRun1(customer2);
							}
						
						else{
							System.out.println("Wish List is Empty");
							customerRun1(customer2);
						}
					}
					
					else if(inputNum1==3){
						System.out.println("Enter 5 digit movie ID: ");
						int inputID1= in.nextInt();
						MovieNode node = movieIDDirectory.lookUp(inputID1);
						if(node!=null){ 
							System.out.println(movieIDDirectory.lookUp(inputID1).getName());	
							customer2.addToWishList(movieIDDirectory.lookUp(inputID1));
							saveCustomerBST(customerDirectory);
							System.out.println("Movie Added");
						}
						else{
							System.out.println("Movie doesn't exist");
						}
						saveCustomerBST(customerDirectory);
						customerRun1(customer2);
					}
					
					else if(inputNum1==4){
						customer2.printWishList();
						customerRun1(customer2);
					}
					else if(inputNum1==5){
						customerRun1(customer2);
					}
					else{
						System.out.println("Not an avaliable option. Try again: ");
					}
			}
			else if(inputNum==2){
				System.out.println("Enter: ");
				System.out.println("'1' search for movie in have watched list");
				System.out.println("'2' delete a movie in have watched list");
				System.out.println("'3' print have watched list");
				System.out.println("'4' to return to prior screen");
				int inputNum1 = in.nextInt();
					if(inputNum1==1){
						System.out.println("Type 5 digit movie ID");
						int inputNext = in.nextInt();
						if(customer2.searchHaveWatchedList(inputNext)!=null){
							System.out.println(customer2.searchHaveWatchedList(inputNext).getName());
							customerRun1(customer2);
							}
						else{
							System.out.println("Moive is not in have watched");
							customerRun1(customer2);
						}
					}
					else if(inputNum1==2){
						System.out.println("Type 5 digit movie ID");
						int inputNext1 = in.nextInt();
						if(customer2.searchHaveWatchedList(inputNext1)!=null){
							customer2.deleteHaveWatchedList(customer2.searchHaveWatchedList(inputNext1));
							saveCustomerBST(customerDirectory);
							System.out.println("deleted");
							customerRun1(customer2);
							}
						else{
							System.out.println("Movie is not in have watched");
							customerRun1(customer2);
						}
	
					}
					
					else if(inputNum1==3){
						customer2.printHaveWatchedList();
						System.out.println();
						customerRun1(customer2);
					}
					
					else if(inputNum1==4){
						customerRun1(customer2);
					}
					else{
						System.out.println("Not an avaliable option. Try again: ");
					}
			}	
			
			else if(inputNum==3){
				customerRun2(customer2);
			}
			else if(inputNum==4){
				customerRun3(customer2);
			}
			else if(inputNum==5){
					System.out.println("logging out...");
					run();
				}
			else{
				System.out.println("Not an avaliable option. Try again: ");
			}
		}
		catch(InputMismatchException e){
				System.out.println("Choose one of the options");
				customerRun1(customer2);
		}
	}		
	
	public static void customerRun2(CustomerNode customer3){
		Scanner in = new Scanner(System.in);
		try{
			System.out.println("'1' to search");
			System.out.println("'2' to return to previous");
			int next = in.nextInt();
			if (next == 1){
				System.out.println("To search, enter movie ID: ");
				int movieID1 = in.nextInt();
				if(movieIDDirectory.lookUp(movieID1)!=null){
					System.out.println(movieIDDirectory.lookUp(movieID1).getName());
					System.out.println("'1' to add this to your wish list or '2' to return initial screen");
					int yesOrNo = in.nextInt();
					if(yesOrNo == 1){
						customer3.addToWishList(movieIDDirectory.lookUp(movieID1));
						saveCustomerBST(customerDirectory);
						customerRun2(customer3);
					}
					else if(yesOrNo == 2){
						customerScreen(customer3);
					}
					
					else{
						System.out.println("Choose one of the options");
					}
					
				}
				else{
					System.out.println("Movie not found");
					welcomeScreen();
				}
			}
			else if(next==2){
				customerScreen(customer3);
			}
			
			else{
				System.out.println("Choose one of the options");
			}
		}
		catch(InputMismatchException e){
			System.out.println("Wrong input type");
				customerRun2(customer3);	
		}
	}
	
	public static void customerRun3(CustomerNode customer4){
		Scanner in = new Scanner(System.in);
			try{
				System.out.println("'1' to see all movies");
				System.out.println("'2' to return to previous");
				int next1 = in.nextInt();
				if (next1 == 1){
					movieDateDirectory.traverse();
					System.out.println();
					System.out.println("'1' to add a movie to your wish list");
					System.out.println("'2' to return initial screen");
					int yesOrNo1 = in.nextInt();
					if(yesOrNo1 == 1){
						System.out.println("Enter movie ID: ");
						int movieID2 = in.nextInt();
						if(movieIDDirectory.lookUp(movieID2)!=null){
							customer4.addToWishList(movieIDDirectory.lookUp(movieID2));
							saveCustomerBST(customerDirectory);
							customerRun3(customer4);
						}
				
						else{
							System.out.println("Movie not found");
							customerRun3(customer4);
						}
						
					}
					else if(yesOrNo1 == 2){
						customerScreen(customer4);
					}
					
					else{
						System.out.println("Choose one of the options");
					}
				}
				else if (next1 == 2){
					customerScreen(customer4);
				}
				else{
				System.out.println("Choose one of the options");
				}
			}
		catch(InputMismatchException e){
			System.out.println("Wrong input type");
				customerRun3(customer4);	
		}
	}
	
	
	public static void welcomeScreen(){
		Scanner in = new Scanner(System.in);
		boolean correctNum = false;
		try{
			while(correctNum==false){
				System.out.println("Enter: ");
				System.out.println("'1' for customer list: (edit all customer information)");
				System.out.println("'2' for admin control of movies: (edit movie information)");
				System.out.println("'3' to view all movies: ");
				System.out.println("'4' for customer access: (Search movies by ID)");
				System.out.println("'5' to quit program");
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
				else if(inputNum==5){
					System.out.println("Closing program...");
					break;
				}
				else{
					System.out.println("Not an avaliable option. Try again: ");
				}
			}
		}
		catch(InputMismatchException e){
				System.out.println("Choose one of the options");
				welcomeScreen();
		}		
		
	}
	
	public static void run1(){
		Scanner in = new Scanner(System.in);
		try{
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
				System.out.println(customer.getName()+" has been added.");
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
						if(c.accessFrontWishList()!=null){
							System.out.println(c.accessFrontWishList().getName());
							System.out.println("'1' delete first movie in Wish List");
							System.out.println("'2' return to customer menu");
							int inputNum12 = in.nextInt();
							if(inputNum12==1){
								System.out.println("'1' if movie was watched");
								System.out.println("'2' if not");
								int inputNum14 = in.nextInt();
								if(inputNum14==1){
									c.addToHaveWatchedList(c.accessFrontWishList());	
								}
								c.deleteWishList();
								saveCustomerBST(customerDirectory);
								System.out.println("deleted.");
								run1();
							}
							else if(inputNum12==2){	
								run1();
							}
						}
						else{
							System.out.println("Wish List is Empty");
							run1();
						}
					}	
					else if(inputNum2==2){
						System.out.println("Enter 5 digit movie ID: ");
						int inputID= in.nextInt();
						MovieNode node = movieIDDirectory.lookUp(inputID);
						if(node!=null){ 
							System.out.println(movieIDDirectory.lookUp(inputID).getName());	
							c.addToWishList(movieIDDirectory.lookUp(inputID));
							System.out.println("Movie Added");
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
	
	catch(InputMismatchException e){
			System.out.println("Wrong input type. Returning to customer screen. ");
				run1();	
				}
		}
	public static void run2(){
		Scanner in = new Scanner(System.in);
		try{
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
				//System.out.println("Enter 5 digit id code ");	 //use attomatic id code
				//int inputID = in.nextInt();
				System.out.println("Enter rotten tomato score");
				int inputRT= in.nextInt();
				
				
				MovieNode movie = new MovieNode(IDCounter, inputName, inputRD, inputRT);
				createID(movie);
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
		catch(InputMismatchException e){
			System.out.println("Wrong input type. Returning to admin screen. ");
				run2();	
				}
		}
		
	public static void run3(){
		Scanner in = new Scanner(System.in);
		try{
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
				if(movieDateDirectory.search(RD).isAvailable() == false){
					movieIDDirectory.delete(movieDateDirectory.search(RD).getID());
					movieDateDirectory.delete(movieDateDirectory.search(RD));
					saveMovieBST(movieDateDirectory);
					saveMovieHash(movieIDDirectory);
				}
				else{
					System.out.println("You can't delete an available movie!");
					}
				run3();
			}
			else if(inputNum==3){
				welcomeScreen();
			}
			
			else{
				System.out.println("Not an avaliable option. Try again: ");
			}
		}
	catch(InputMismatchException e){
			System.out.println("Wrong input type. Returning to movie menu. ");
				run3();	
				}
		}
	public static void run4(){
		Scanner in = new Scanner(System.in);
		try{
			System.out.println("Enter movie ID: ");
			int movieID = in.nextInt();
			if(movieIDDirectory.lookUp(movieID)!=null){
				System.out.println(movieIDDirectory.lookUp(movieID).getName());
				welcomeScreen();
			}
			else{
				System.out.println("Movie not found");
				welcomeScreen();
			}
		}
		catch(InputMismatchException e){
			System.out.println("Choose one of the options");
				run4();	
				}
		}
	
	public static void saveID(int ID){
		try {
			FileOutputStream file = new FileOutputStream("ID.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeInt(ID);
			out.close();
			file.close();
        	} 
        	catch (IOException e) {
			e.printStackTrace();
        	}
   	}

	public static int loadID(){
		int ID;
        	try {
			FileInputStream file = new FileInputStream("ID.ser");
			ObjectInputStream in = new ObjectInputStream(file);
            		ID = (int) in.readInt();
           		in.close();
            		file.close();
        	} 
        	catch (Exception e) {
            		ID = 10012;
        	}
        	 return ID;
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
         
            	MovieNode movie1 = new MovieNode(10001, "Episode_IV:_A_New_Hope",19770525,96);
            	MovieNode movie2 = new MovieNode(10002, "Star_Wars_V:The Empire_Strikes_Back",19800521,97);
            	MovieNode movie3 = new MovieNode(10003, "The_Fast_and_The_Furious",20010622,74);
            	MovieNode movie4 = new MovieNode(10004, "2_Fast_2_Furious:_Tokyo_Drift",20030606,50);
            	MovieNode movie5 = new MovieNode(10005, "Fast_&_Furious",20090403,28);
            	MovieNode movie6 = new MovieNode(10006, "Fast_Five",20110429,78);
            	MovieNode movie7 = new MovieNode(10007, "Fast_&_Furious_6",20130524,71);
            	MovieNode movie8 = new MovieNode(10008, "Furious_7",20150403,81);
            	MovieNode movie9 = new MovieNode(10009, "The_Fate_Of_The_Furious",20170414,67);
            	MovieNode movie10 = new MovieNode(10010, "Hobbs_&_Shaw",20190802,66);
            	MovieNode movie11 = new MovieNode(10011, "F9_The_Fast_Saga",20210625,59);
            	
            	movie.insert(movie1);
            	movie.insert(movie2);
            	movie.insert(movie3);
            	movie.insert(movie4);
            	movie.insert(movie5);
            	movie.insert(movie6);
            	movie.insert(movie7);
            	movie.insert(movie8);
            	movie.insert(movie9);
            	movie.insert(movie10);
            	movie.insert(movie11);
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
            	
            	MovieNode movie1 = new MovieNode(10001, "Episode_IV:_A_New_Hope",19770525,96);
            	MovieNode movie2 = new MovieNode(10002, "Star_Wars_V:The Empire_Strikes_Back",19800521,97);
            	MovieNode movie3 = new MovieNode(10003, "The_Fast_and_The_Furious",20010622,74);
            	MovieNode movie4 = new MovieNode(10004, "2_Fast_2_Furious:_Tokyo_Drift",20030606,50);
            	MovieNode movie5 = new MovieNode(10005, "Fast_&_Furious",20090403,28);
            	MovieNode movie6 = new MovieNode(10006, "Fast_Five",20110429,78);
            	MovieNode movie7 = new MovieNode(10007, "Fast_&_Furious_6",20130524,71);
            	MovieNode movie8 = new MovieNode(10008, "Furious_7",20150403,81);
            	MovieNode movie9 = new MovieNode(10009, "The_Fate_Of_The_Furious",20170414,67);
            	MovieNode movie10 = new MovieNode(10010, "Hobbs_&_Shaw",20190802,66);
            	MovieNode movie11 = new MovieNode(10011, "F9_The_Fast_Saga",20210625,59);
            	
            	movie.insert(movie1);
            	movie.insert(movie2);
            	movie.insert(movie3);
            	movie.insert(movie4);
            	movie.insert(movie5);
            	movie.insert(movie6);
            	movie.insert(movie7);
            	movie.insert(movie8);
            	movie.insert(movie9);
            	movie.insert(movie10);
            	movie.insert(movie11);
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
            	
            	MovieNode movie1 = new MovieNode(10001, "Episode_IV:_A_New_Hope",19770525,96);
            	MovieNode movie2 = new MovieNode(10002, "Star_Wars_V:The Empire_Strikes_Back",19800521,97);
            	MovieNode movie3 = new MovieNode(10003, "The_Fast_and_The_Furious",20010622,74);
            	MovieNode movie4 = new MovieNode(10004, "2_Fast_2_Furious:_Tokyo_Drift",20030606,50);
            	MovieNode movie5 = new MovieNode(10005, "Fast_&_Furious",20090403,28);
            	MovieNode movie6 = new MovieNode(10006, "Fast_Five",20110429,78);
            	MovieNode movie7 = new MovieNode(10007, "Fast_&_Furious_6",20130524,71);
            	MovieNode movie8 = new MovieNode(10008, "Furious_7",20150403,81);
            	MovieNode movie9 = new MovieNode(10009, "The_Fate_Of_The_Furious",20170414,67);
            	MovieNode movie10 = new MovieNode(10010, "Hobbs_&_Shaw",20190802,66);
            	MovieNode movie11 = new MovieNode(10011, "F9_The_Fast_Saga",20210625,59);
            	
            	movie.insert(movie1);
            	movie.insert(movie2);
            	movie.insert(movie3);
            	movie.insert(movie4);
            	movie.insert(movie5);
            	movie.insert(movie6);
            	movie.insert(movie7);
            	movie.insert(movie8);
            	movie.insert(movie9);
            	movie.insert(movie10);
            	movie.insert(movie11);
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
