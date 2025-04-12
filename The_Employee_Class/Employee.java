package The_Employee_Class;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private int Salary;
	public Employee(int id, String firstName, String lastName, int salary) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		Salary = salary;
	}
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	
	public String getName() {
		return firstName+ lastName;
		
		
	}
	public int getAnnualSalary () {
		return Salary*12;
		
	
	}
	public int RaiseSalary(int percent) {
		return Salary+Salary*(percent/100);
	}
   public String toString() {
	   return "Employee[id= "+id +",name = "+ firstName + lastName+",salary= "+Salary+"]";
   }
}
