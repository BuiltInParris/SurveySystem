import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of ShortAnswer.
 * 
 * @author sparris
 */
public class ShortAnswer extends Essay implements Serializable {
	/**
	 * Description of the property limit.
	 */
	private int limit = 0;

	public ShortAnswer(){
		
	}
	
	public ShortAnswer(String prompt){
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
	}
	
	public CAR Modify(CAR qCorrectAnswers) {
		ModifyPrompt();
		int change = 1;
		while(change == 1)
		{
			change = menuPrompt("Do you wish to modify a correct answer? (1 for yes, 0 for no)", 0, 1);
			
			if(change == 1)
			{
				io.println("Enter the correct answer(s) (comma separated):");
				String unsplitAnswer = io.input(language);
				ArrayList<String> splitAnswers =  new ArrayList<String>(Arrays.asList(unsplitAnswer.split(",")));
				
				qCorrectAnswers.correctAnswers = splitAnswers;
			}
		}
		this.numAnswers = qCorrectAnswers.correctAnswers.size();
		return qCorrectAnswers;
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
		
		Set<String> unique = new HashSet<String>(combinedList);
		io.println("\nTabulation:");
		for (String key : unique) {
		    System.out.println(key + ": " + Collections.frequency(combinedList, key));
		}
	}

	// Start of user code (user defined methods for ShortAnswer)

	// End of user code
	/**
	 * Returns limit.
	 * @return limit 
	 */
	public int getLimit() {
		return this.limit;
	}

	/**
	 * Sets a value to attribute limit. 
	 * @param newLimit 
	 */
	public void setLimit(int newLimit) {
		this.limit = newLimit;
	}

}
