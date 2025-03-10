import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Tickets {
    //attributes
    private char rowLetter;// Stores the row letter of the ticket

    private int seatNo;// Stores the seat number of the ticket

    private  int price;// Stores the price of the ticket

    private Person person; // Stores the person associated with the ticket

    //Constructor

    public Tickets(char rowLetter, int seat, int price, Person person){

        this.rowLetter = rowLetter;// Initialize rowLetter attribute
        this.seatNo = seat;// Initialize seatNo attribute
        this.price = price;// Initialize price attribute
        this.person = person;// Initialize person attribute
    }

    public Tickets(char rowLetter, int seatNo, int price) {
    }

    //getters
    public char getRowLetter(){
        return this.rowLetter;
    }// Return the row letter of the ticket

    public int getSeatNo(){
        return this.seatNo;
    }// Return the seatNo of the ticket
    public int getPrice(){
        return this.price;
    }// Return the price of the ticket
    public Person getPerson(){
        return this.person;
    }// Return the person of the ticket
    //setters


    public void setRowLetter(char rowLetter) {
        this.rowLetter = rowLetter;
    } //set the row letter of the ticket

    public void setSeatNo(int seatNO) {
        this.seatNo = seatNO;
    }// set the seat number of the ticket

    public void setPrice(int price) {
        this.price = price;
    }// set the price of the ticket

    public void setPerson(Person person) {
        this.person = person;
    } //set the person of the ticket

     //Print method

    public  void TicketDetails(){ //By this  going to disply ticket information
        System.out.println("Passenger Ticket Information " +
                "\n\tRow: " + getRowLetter()+
                "\n\tSeat: " + getSeatNo() +
                "\n\tPrice: £" + getPrice() );
        person.personDetail();
    }
    public void save() {//this method is used to save the ticket information that enterd  by user
        String fileName = "Booking Information"+File.separator +getRowLetter() +""+getSeatNo() + ".txt";//here I define the path and file name
        try {
            String folderPath = "Booking Information";
            File folder = new File(folderPath);

            // here i check if folder doesn't exist, then create it
            if (!folder.exists()) {
                folder.mkdirs();
            }


            FileWriter file = new FileWriter(fileName); //Here i createed a FileWriter object
            file.write("-------------------------------------------------\n");
            file.write("\tTicket information of seat booked\n");
            file.write("-------------------------------------------------\n");
            file.write("\n");
            file.write("\tRow: " + getRowLetter() + "\n"+ "\tSeat Number: " + getSeatNo() +"\n");
            file.write("\tPrice: £" + getPrice() +"\n");
            file.write("\n-------------Passenger's Information-------------\n");
            file.write("\n");
            file.write("\tName of Passenger: " + person.getName()+ "\n");
            file.write("\tPassenger's Surname: " + person.getSurname() + "\n");
            file.write("\tPassenger's E-mail Id: " + person.getEmail() + "\n");

            file.close();// Close the FileWriter object


        } catch (IOException e) {
            System.out.println("Error occurred");
        }
    }



}





















