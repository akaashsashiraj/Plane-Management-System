public class Person {
    //attributes
    private String name;//here stores passenger's name

    private  String surname;//here stores passenger's surname

    private String email;//here stores passenger's email


    //Constructor
    public Person(String name, String surname, String email){
        this.name= name;// Initialize name attribute
        this.surname= surname;// Initialize surname attribute
        this.email= email;// Initialize email attribute
    }

    //getters
    public String getName(){
        return this.name;//return passenger's name

    }

    public String getSurname(){
        return this.surname;//return passenger's surname

    }

    public String getEmail(){
        return this.email;//return passenger's email

    }
    //setter

    public void setName(String name){
        this.name = name;// Set the person's name

    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setEmail(String email){
        this.email = email;

    }

    //print method
    public void personDetail(){//By this  going to disply person's detail
        System.out.println( "\nPassenger's detail" +
                "\n\tName: " + getName() +
        "\n\tSurname: " + getSurname()+
                "\n\tEmail:" + getEmail()+ "\n");
    }
}

