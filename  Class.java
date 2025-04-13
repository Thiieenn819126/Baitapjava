package universityms;

public class Class {
private int ClassID;
private String description;
private int numberOfCreadits;

public Class() {
	
}

public Class(int classID, String description, int numberOfCreadits) {
	ClassID = classID;
	this.description = description;
	this.numberOfCreadits = numberOfCreadits;
}

public int getClassID() {
	return ClassID;
}

public void setClassID(int classID) {
	ClassID = classID;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getNumberOfCreadits() {
	return numberOfCreadits;
}

public void setNumberOfCreadits(int numberOfCreadits) {
	this.numberOfCreadits = numberOfCreadits;
}


}
