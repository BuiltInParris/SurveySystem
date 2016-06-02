import java.io.Serializable;
import java.util.ArrayList;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Question.
 * 
 * @author sparris
 */
public class Question implements Serializable {
	/**
	 * Description of the property prompt.
	 */
	public String prompt = "";
	
	public int language = 1;
	
	public int numAnswers = 1;
	
	public static CustomIO io = CustomIO.getInstance();

	/**
	 * Description of the property responses.
	 */
	public String responses = "";

	/**
	 * Description of the property numResponsesRequired.
	 */
	public int numResponsesRequired = 0;

	// Start of user code (user defined attributes for Question)

	// End of user code

	/**
	 * Description of the method SetPrompt.
	 * @param text 
	 */
	public void SetPrompt(String text) {
		// Start of user code for method SetPrompt
		// End of user code
	}

	/**
	 * Description of the method SetChoices.
	 */
	public void SetChoices() {
		// Start of user code for method SetChoices
		// End of user code
	}

	/**
	 * Description of the method Display.
	 */
	public void Display() {
		// Start of user code for method Display
		// End of user code
	}

	/**
	 * Description of the method Take.
	 * @return 
	 */
	public ArrayList<String> Take() {
		// Start of user code for method Take
		// End of user code
		return new ArrayList<String>();
	}

	/**
	 * Description of the method Modify.
	 */
	public void Modify() {
		// Start of user code for method Modify
		// End of user code
	}
	
	public CAR Modify(CAR obj) {
		return obj;
		// Start of user code for method Modify
		// End of user code
	}

	public void ModifyPrompt() {
		int looping = menuPrompt("Do you wish to modify the prompt? (1 for yes, 0 for no)", 0, 1);
		
		if(looping == 1)
		{
			io.println("Enter the prompt:");
			String input = io.input(1);
			this.prompt = input;
		}
	}
	
	public int menuPrompt(String prompt, int bot, int top)
	{
		io.println(prompt);
		int returnVal = -2;
		try {
			returnVal = Integer.parseInt(io.input(language));
		} catch(NumberFormatException e){
			returnVal = -2;
		}
		while(!(returnVal >= bot && returnVal <= top))
		{
			io.println("Invalid input. Try again.");
			io.println(prompt);
			try {
				returnVal = Integer.parseInt(io.input(language));
			} catch(NumberFormatException e){
				returnVal = 0;
			}
		}
		return returnVal;
	}
	
	/**
	 * Description of the method Tabulate.
	 */
	public void Tabulate(ArrayList<ArrayList<String>> aggResp) {
		// Start of user code for method Tabulate
		// End of user code
	}

	// Start of user code (user defined methods for Question)

	// End of user code
	/**
	 * Returns prompt.
	 * @return prompt 
	 */
	public String getPrompt() {
		return this.prompt;
	}

	/**
	 * Sets a value to attribute prompt. 
	 * @param newPrompt 
	 */
	public void setPrompt(String newPrompt) {
		this.prompt = newPrompt;
	}

	/**
	 * Returns responses.
	 * @return responses 
	 */
	public String getResponses() {
		return this.responses;
	}

	/**
	 * Sets a value to attribute responses. 
	 * @param newResponses 
	 */
	public void setResponses(String newResponses) {
		this.responses = newResponses;
	}

	/**
	 * Returns numResponsesRequired.
	 * @return numResponsesRequired 
	 */
	public int getNumResponsesRequired() {
		return this.numResponsesRequired;
	}

	/**
	 * Sets a value to attribute numResponsesRequired. 
	 * @param newNumResponsesRequired 
	 */
	public void setNumResponsesRequired(int newNumResponsesRequired) {
		this.numResponsesRequired = newNumResponsesRequired;
	}

}
