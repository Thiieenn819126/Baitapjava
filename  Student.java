package universityms;

public class Student {
private int StudentID;
private String name;
private int age;
private String email;
private double gpa;

public Student(int studentID, String name, int age, String email, double gpa) {
	this.StudentID = studentID;
	this.name = name;
	this.age = age;
	this.email = email;
	this.gpa = gpa;
}

public Student() {
	
}

public int getStudentID() {
	return StudentID;
}

public void setStudentID(int studentID) {
	StudentID = studentID;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public double getGpa() {
	return gpa;
}

public void setGpa(double gpa) {
	this.gpa = gpa;
}


}
