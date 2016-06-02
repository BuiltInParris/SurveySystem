import java.io.Serializable;
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
 * Description of BooleanQuestion.
 * 
 * @author sparris
 */
public class BooleanQuestion extends MultipleChoice implements Serializable {

	public BooleanQuestion(String prompt)
	{
		this.prompt = prompt;
		this.choices.add("True");
		this.choices.add("False");
	}
	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.println(prompt);
		io.println("1) True\n2) False");
	}

	/**
	 * Description of the method Take.
	 */
	public ArrayList<String> Take() {
		Display();
		ArrayList<String> sample = new ArrayList<String>();
		sample.add(choices.get(menuPrompt("Enter your choice:", 1, choices.size()) - 1));
		return sample;
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
				String finalStr = "";
				for(int i = 0; i < qCorrectAnswers.correctAnswers.size(); i++)
				{
					finalStr = finalStr + (i + 1) + ") " + qCorrectAnswers.correctAnswers.get(i) + " ";
				}
	
				String answerString = "Which is the new correct answer:\n";
				for(int i = 0; i < choices.size(); i++)
				{
					answerString = answerString + (i + 1) + ") " + choices.get(i) + " ";
				}
				int answer = menuPrompt(answerString, 1, choices.size());
				
				String correctAnswer = choices.get(answer - 1);
				
				qCorrectAnswers.correctAnswers.set(0, correctAnswer);
			}
		}
		return qCorrectAnswers;
	}

	/**
	 * Description of the method MultipleChoice.
	 * @param prompt 
	 */
	public void MultipleChoice(String prompt) {
		// Start of user code for method MultipleChoice
		// End of user code
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
		keyList.addAll(choices);
		
		Set<String> unique = new HashSet<String>(keyList);
		io.println("\nTabulation:");
		for (String key : unique) {
		    System.out.println(key + ": " + Collections.frequency(combinedList, key));
		}
	}

	// Start of user code (user defined methods for BooleanQuestion)

	// End of user code

}
