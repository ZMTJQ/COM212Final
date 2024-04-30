import java.util.Scanner;

public class Final{
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
	
	static CustomerBST customerDirectory = new  CustomerBST();
	static MovieBST movieDateDirectory = new  MovieBST();
	static MovieHash movieIDDirectory = new  MovieHash();
	static MovieHeap movieRTDirectory = new  MovieHeap();
	
	public static void run1(){
		int i = 0;
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
			run1();
		}
		else if(inputNum==2){
			System.out.println("Enter 4 digit credit card number: ");
			int inputCC= in.nextInt();		
			customerDirectory.delete(customerDirectory.search(inputCC));
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
					c.addToWishList(movieIDDirectory.lookUp(inputID));
					run1();
				}
				else if(inputNum2==3){
					c.deleteWishList();
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
					run1();
				}
				else if(inputNum4==2){
					System.out.println("Enter credit card: ");	
					int inputCC1 = in.nextInt();
					c.setCreditCard(inputCC1);
					run1();
				}
				else if(inputNum4==3){
					System.out.println("Enter email address: ");	
					String inputEmail1 = in.next();
					c.setEmail(inputEmail1);
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
		
	}
	
	public static void run3(){
		
	}
	
	public static void run4(){
		
	}
}
