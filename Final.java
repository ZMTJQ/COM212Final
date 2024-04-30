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
			}
			else{
				System.out.println("Incorrect ID");
			}
		}
		boolean correctNum = false;
		while(correctNum==false){
			System.out.println("Enter: ");
			System.out.println("'1' for cutomer list");
			System.out.println("'2' for admin control of movies");
			System.out.println("'3' to view all movies");
			System.out.println("'4' for customer access");
			int inputNum = in.nextInt();
		
			if(inputNum==1){
				correctNum=true;
			}
			else if(inputNum==2){
				correctNum=true;
			}
			else if(inputNum==3){
				correctNum=true;
			}
			else if(inputNum==4){
				correctNum=true;
			}
			else{
				System.out.println("Not an avaliable option. Try again: ");
			}
		}
		
	}
}
