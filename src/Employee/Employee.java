package Employee;

public class Employee {
	protected int id;
	protected String name;
	protected String designation;
	protected int salary;
	protected String email;
	
	public Employee() {
		
	}
	
	public Employee(int id) {
		this.id=id;
	}
	
	public Employee(int id, String name,String designation,int salary,String email) {
		this(name, designation, salary, email);
		this.id=id;
	}
	
	public Employee(String name,String designation,int salary,String email) {
		this.name=name;
		this.designation=designation;
		this.salary=salary;
		this.email=email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation=designation;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary=salary;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
}
