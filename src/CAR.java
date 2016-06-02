import java.io.Serializable;
import java.util.ArrayList;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of CAR.
 * 
 * @author sparris
 */
public class CAR implements Serializable {
	/**
	 * Description of the property correctAnswers.
	 */
	public ArrayList<String> correctAnswers = new ArrayList<String>();
	
	
	public CAR()
	{
	}
	
	public CAR(String correctAnswer)
	{
		correctAnswers.add(correctAnswer);
	}
	
	public CAR(ArrayList<String> newCorrectAnswers)
	{
		correctAnswers = newCorrectAnswers;
	}

	// Start of user code (user defined attributes for CAR)

	// End of user code

	/**
	 * Description of the method CheckAnswers.
	 * @param indResponse 
	 * @return 
	 */
	public ArrayList<Integer> CheckAnswers(Response indResponse) {
		return new ArrayList<Integer>();
	}

	// Start of user code (user defined methods for CAR)

	// End of user code
	/**
	 * Returns correctAnswers.
	 * @return correctAnswers 
	 */
	public ArrayList<String> getCorrectAnswers() {
		return this.correctAnswers;
	}

	/**
	 * Sets a value to attribute correctAnswers. 
	 * @param newCorrectAnswers 
	 */
	public void setCorrectAnswers(ArrayList<String> newCorrectAnswers) {
		this.correctAnswers = newCorrectAnswers;
	}

}
