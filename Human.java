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

}