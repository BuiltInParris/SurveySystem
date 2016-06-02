import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Essay.
 * 
 * @author sparris
 */
public class Essay extends Question {
	/**
	 * Description of the property graded.
	 */
	public boolean graded = false;


	public Essay(){
		
	}
	
	public Essay(String prompt){
		this.prompt = prompt;
	}

	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.println(prompt);
	}

	/**
	 * Description of the method Take.
	 * @return 
	 */
	public ArrayList<String> Take() {
		Display();
		io.println("Please enter " + numAnswers + " answer(s):");
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 1; i < numAnswers + 1; i++)
		{
			io.println(i + ")");
			String input = io.input(language);
			answers.add(input);
		}
		return answers;
	}

	/**
	 * Description of the method Modify.
	 */
	public void Modify() {
		ModifyPrompt();
		String prompt = "How many answers would you like:";
		io.println(prompt);
		int returnVal = -2;
		try {
			returnVal = Integer.parseInt(io.input(language));
		} catch(NumberFormatException e){
			returnVal = -2;
		}
		while(!(returnVal >= 1))
		{
			io.println("Invalid input. Try again.");
			io.println(prompt);
			try {
				returnVal = Integer.parseInt(io.input(language));
			} catch(NumberFormatException e){
				returnVal = 0;
			}
		}
	}

	/**
	 * Description of the method Tabulate.
	 */
	public void Tabulate(ArrayList<ArrayList<String>> aggResp) {
		Display();
		ArrayList<String> combinedList = new ArrayList<String>();
		io.println("\nReplies:");
		for(int i = 0; i < aggResp.size(); i++){
			for(int j = 0; j < aggResp.get(i).size(); j++){
				io.println(aggResp.get(i).get(j));
				combinedList.add(aggResp.get(i).get(j));
			}
		}
		ArrayList<String>keyList = new ArrayList<String>(combinedList);
		
		Set<String> unique = new HashSet<String>(keyList);
		io.println("\nTabulation:");
		for (String key : unique) {
		    System.out.println(key + ": " + Collections.frequency(combinedList, key));
		}
	}

	// Start of user code (user defined methods for Essay)

	// End of user code
	/**
	 * Returns graded.
	 * @return graded 
	 */
	public boolean getGraded() {
		return this.graded;
	}

	/**
	 * Sets a value to attribute graded. 
	 * @param newGraded 
	 */
	public void setGraded(boolean newGraded) {
		this.graded = newGraded;
	}

}
