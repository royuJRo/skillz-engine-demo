package Exceptions;

public class AlreadyActedException extends Exception{
	private static final long serialVersionUID = 6797618588212070097L;
	
	public AlreadyActedException() {
		super("Exception : Player already acted with that character.");
	}

}
