import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

//plane management class manage the seating booking of the plane
public class w2051998_PlaneManagement {
    public static int[][]  seats = { //2D array
            new int[14], new int[12],new int[12], new int[14] //This 2D array used to make plane seat structure
    };


    public static void main(String[] args) {

        Tickets[][] tickets= new Tickets[4][14]; //This 2D array for ticket

        System.out.println("\n<<<<Welcome to Plane Management System!!>>>>");
        System.out.println("\n");
        System.out.println("""
                    1   2   3   4   5   6   7   8   9   10  11  12  13  14
                A   O   O   O   O   O   O   O   O   O   O   O   O   O   O
                B   O   O   O   O   O   O   O   O   O   O   O   O
                C   O   O   O   O   O   O   O   O   O   O   O   O
                D   O   O   O   O   O   O   O   O   O   O   O   O   O   O
                
                Price of seats according to the column:
                1-5 = £200
                6-9 = £150
                9-14= £180
                """);


        boolean terminate=false;
  do {
      int menuSelection = menu();
      switch (menuSelection) { //here i use switch case to select the options
          case 0:
              terminate = true;
              System.out.println("Thank you for choosing us.....Contact our team for more details"); //this for terminate the whole program
              break;
          case 1:
              buy_seat(seats, tickets);
              break;
          case 2:
              cancel_seat(tickets);
              break;
          case 3:
              find_first_available();
              break;
          case 4:
              show_seating_plan();
              break;
          case 5:
              print_ticket_info(tickets);
              break;
          case 6:
              search_ticket(tickets);
              break;
          default:
              System.out.println("Invalid option. Please try again."); //If user input invalid data this message will be thrown.

      }
  }while(!terminate);
    }

    private static int menu() { //this menu() prints menu option for us
        Scanner scanner = new Scanner(System.in);
        int menuSelection;
        do {
            System.out.println("\n");
            System.out.println("""
                    *************************************************
                    *                Menu Option                    *
                    *************************************************
                                    
                        1) Buy a seat
                        2) Cancel a seat
                        3) Find first seat available
                        4) Show seating plan
                        5) Print tickets information and total sales
                        6) Search tickets
                        0) Quit
                        
                    **************************************************
                    """);
            System.out.println("Select your option (0-6): ");
            while (!scanner.hasNextInt()) { // here check if input is not an integer
                System.out.println("Incorrect input. Please enter a number (0-6): ");
                scanner.next(); // Consume the invalid input
            }
            menuSelection = scanner.nextInt();
            if (menuSelection < 0 || menuSelection > 6) {
                System.out.println("Incorrect input. Please enter a number between 0 and 6.");
            }
        } while (menuSelection < 0 || menuSelection > 6);
        return menuSelection;
    }
    //here this buy_seat option is used to book a seat and ask user to input the other details that need to book a seat
    private static void buy_seat(int[][]  seats, Tickets[][] tickets){
        {Scanner scanner= new Scanner (System.in);
        try {
                 //Prompt to ask user for row letter (A-D)
                System.out.println("Enter the row letter (A-D)");
                char rowLetter = scanner.next().toUpperCase().charAt(0);//here we use char to get an alphabet., and also convert in to uppercase
                int row = rowLetter - 'A';/* Here we are subtract A,Reason is in ascii A=65 so when we have an input row letter A,B,C,D
        that shows 65 to array, but we created only 4 array so that when we subtract the A  we got A-A=0 in index 0 is the
        so we can access the  1st array */

            //prompt to ask user for seat number
            System.out.println("Enter the seat number : ");
            int seatNo = scanner.nextInt();

            scanner.nextLine();
            if (seats[row][seatNo - 1] == 0) {//this condition checking whether seat is booked or not.

                seats[row][seatNo - 1] = 1;

                String name = "";
                while (name.trim().isEmpty()) {
                    System.out.println("Enter passenger's name: ");
                    name = scanner.nextLine();
                    if (name.trim().isEmpty()) {//if user skip to enter the name when book the ticket, program will not allow to next step.
                        System.out.println("Passenger's Name cannot be empty.");

                    }
                }

                String surname = "";
                while (surname.trim().isEmpty()) {
                    System.out.println("Enter passenger's surname: ");
                    surname = scanner.nextLine();
                    if (surname.trim().isEmpty()) {//if user skip to enter the Surname when book the ticket, program will not allow to next step.
                        System.out.println("Passenger's Surname cannot be empty.");
                    }
                }

                String email = "";
                while (email.trim().isEmpty()) {
                    System.out.println("Enter passenger's Email id: ");
                    email = scanner.nextLine();
                    if (email.trim().isEmpty()) {//if user skip to enter the email when book the ticket, program will not allow to book ticket.
                        System.out.println("Email cannot be empty.");
                    }
                }

                Person newPerson = new Person(name,surname,email);//here creating new object with user details

                //Here calculating the price based on seat number
                int price;
                if (seatNo <= 5  ){
                    price= 200;
                }else if (seatNo <= 9){
                    price = 150;
                }else{
                    price=180;
                }

                //Here create new Tickets object and store in tickets array
                Tickets newTickets = new Tickets( rowLetter, seatNo ,price, newPerson);
                tickets[row][seatNo - 1] = newTickets;
                //to save ticket details
                newTickets.save();

                System.out.println("Your Ticket is booked. Thank you...");
            } else {
                System.out.println("Sorry this ticket is already booked...Try another seat to book");
            }
        }catch (InputMismatchException|ArrayIndexOutOfBoundsException e){
            System.out.println("Your selection is invalid. Please try again");
        }

    }
}

    private static void cancel_seat(Tickets[][] tickets){
        {Scanner scanner= new Scanner (System.in);
        try {
            System.out.println("Enter the row letter (A-D)");
            char rowLetter = scanner.next().toUpperCase().charAt(0);
            int row = rowLetter - 'A';

            System.out.println("Enter the seat number : ");
            int seatNo = scanner.nextInt();
            if (seats[row][seatNo - 1] == 1) {//this condition checking whether seat is booked or not.

                seats[row][seatNo - 1] = 0;
                System.out.println("Your booking is cancelled");

                tickets[row][seatNo - 1]= null;//this used to remove ticket from tickets array

                //here deleting the data which are entered when buy_seat
                String fileName = rowLetter + "" + seatNo +".txt";
                File delefile = new File(fileName);
                delefile.delete();


            } else {
                System.out.println("This seat is not booked yet");
            }
        }catch (InputMismatchException| ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid input. Please try again");
        }
        }


    }

    private static void find_first_available(){
        boolean available=false; //here this used to check if any available seat is found
        for(int row=0; row< seats.length; row++){//here this iterates over rows
            for(int seatNO=0; seatNO<seats[row].length;seatNO++ ){//here this it iterates over seats in each row
                if(seats[row][seatNO]==0){
                    char rowLetter =(char) (row +'A'); //here I convert row index to corresponding letter (A-D)
                    //Print first available seat information
                    System.out.println("First available seats: row= " + rowLetter +"\tSeat Number= "+ (seatNO + 1) );
                    available = true;
                    break;
                }
            }
            if (available){//Exit outer loop once an available seat is found
                break;
            }
            if (!available){ // If no available seat is found after iteration
                System.out.println("All Tickets are booked....");
            }
        }


    }

    private static void show_seating_plan(){
        char rowLabel = 'A'; // Start with row label A
        for(int i = 0; i < seats.length; i++){
            System.out.print(rowLabel++ + ""); //print row label for the row and move to next row
                for (int seat : seats[i]) { //here i used enhanced loop
                    if (seat == 0) {
                        System.out.print(" 0 ");// Print '0' to represent an available seat
                    } else {
                        System.out.print(" X ");// Print 'X' to represent a booked seat
                    }
                }
                System.out.println();// Move to the next line after printing seats for a row
        }
    }



    private static void print_ticket_info(Tickets[][] tickets){

        int totalprice = 0;//here initializing the total price variable
        for(int row=0; row< tickets.length; row++) {// Iterate over each row in the tickets array
            for (int seatNO = 0; seatNO < seats[row].length; seatNO++) {
                if (tickets[row][seatNO] != null){
                    tickets[row][seatNO].TicketDetails();// Print ticket details for the booked seat
                    totalprice = totalprice + tickets[row][seatNO].getPrice();// Add the ticket price to the total price

                }
            }
        }
        System.out.println("\n");
        System.out.println("Total price is : £" + totalprice);//here Printing the total price
    }

    private static void search_ticket(Tickets[][] tickets) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the row letter (A-D)");
            char rowLetter = scanner.next().toUpperCase().charAt(0);
            int row = rowLetter - 'A';
            System.out.println("Enter the seat number : ");
            int seatNo = scanner.nextInt();

            if (tickets[row][seatNo - 1] != null) {// Check if a ticket is booked for the specified seat
                tickets[row][seatNo - 1].TicketDetails();// Print ticket details for the booked seat
            } else {
                System.out.println("This seat is not booked. You can book this seat");
            }
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) { // Catch exceptions for invalid input
            System.out.println("Invalid input. Please try again");
            scanner.nextLine();

        }
    }

}