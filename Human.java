public class Human{
    private String firstName;
    private String lastName;
    private String patronimic;
    private int age;
    private int id;

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPatronimic(){
        return patronimic;
    }
    public int getAge(){
        return age;
    }
    public int getId(){
        return id;
    }

    public Human(){
    } 

    public Human(String firstName,String lastName,String patronimic,int age,int id){
        this.firstName=firstName;
        this.lastName=lastName;
        this.patronimic=patronimic;
        this.age=age;
        this.id=id;
    } 

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setPatronimic(String patronimic){
        this.patronimic=patronimic;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setAge(int id){
        this.age=age;
    }

}