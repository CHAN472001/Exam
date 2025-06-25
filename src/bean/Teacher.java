package bean;

public class Teacher implements java.io.Serializable{
	private String id;

	private String password;
	public String name;
	public String school;


	public String getSchool(){
		return school;
	}
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public void setId(String id){
		this.id=id;
	}

	public void setPassword(String password){
		this.password=password;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setSchool(String school) {
        this.school = school;
    }
}