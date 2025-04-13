package universityms;

public class Learn {
private int LearnID;
private int StudentID;
private int ClassID;
public Learn(int learnID, int studentID, int classID) {
	LearnID = learnID;
	StudentID = studentID;
	ClassID = classID;
}
public Learn() {

}
public int getLearnID() {
	return LearnID;
}
public void setLearnID(int learnID) {
	LearnID = learnID;
}
public int getStudentID() {
	return StudentID;
}
public void setStudentID(int studentID) {
	StudentID = studentID;
}
public int getClassID() {
	return ClassID;
}
public void setClassID(int classID) {
	ClassID = classID;
}


}
