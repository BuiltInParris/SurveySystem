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
 * Description of Ranking.
 * 
 * @author sparris
 */
public class Ranking extends Matching implements Serializable {
	
	public Ranking(){
		
	}
	
	public Ranking(String prompt, ArrayList<String>choicesA){
		this.prompt = prompt;
		this.choicesA = choicesA;
		for(int i = 1; i < choicesA.size() + 1; i++)
		{
			choicesB.add(Integer.toString(i));
		}
		this.numAnswers = choicesA.size();
	}

	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.println(prompt + " (1-" + choicesB.size() + ")");
		for(int i = 0; i < choicesA.size(); i++)
		{
			io.println(choicesA.get(i));
		}
	}

	/**
	 * Description of the method Take.
	 * @return 
	 */
	public ArrayList<String> Take() {
		Display();
		ArrayList<String>itemsB = new ArrayList<String>(choicesB);
		ArrayList<String>correctAnswers = new ArrayList<String>();
		for(int j = 0; j < choicesA.size(); j++)
		{
			String answerString = "Which is the correct match for " + choicesA.get(j) + ":\n";
			for(int i = 0; i < itemsB.size(); i++)
			{
				answerString = answerString + (i + 1) + ") " + itemsB.get(i) + "\n";
			}
			int answer = menuPrompt(answerString, 1, itemsB.size());

			correctAnswers.add(itemsB.get(answer - 1));
			itemsB.remove(answer - 1);
		}
		return correctAnswers;
	}

	/**
	 * Description of the method Modify.
	 */
	public void Modify() {
		ModifyPrompt();
		int change = 1;
		while(change == 1)
		{
			change = menuPrompt("Would you like to change your choices? (1 for yes, 0 for no)", 0, 1);
			
			if(change == 1)
			{
				String finalStr = "";
				for(int i = 0; i < choicesA.size(); i++)
				{
					//String alphabet = Character.toString((char) (i + 65));
					finalStr = finalStr + (i + 1) + ") " + choicesA.get(i) + " ";
				}
				
				int choiceNum = menuPrompt("Which choice would you like to modify?\n" + finalStr, 1, choicesA.size());
				
				io.println("Enter the new choice:");
				String item = io.input(1);
				
				this.choicesA.set(choiceNum - 1, item);
			}
		}
		this.numAnswers = choicesA.size();
	}
	
	public CAR Modify(CAR qCorrectAnswers) {
		ModifyPrompt();
		int change = 1;
		while(change == 1)
		{
			change = menuPrompt("Would you like to change your choices? (1 for yes, 0 for no)", 0, 1);
			
			if(change == 1)
			{
				String finalStr = "";
				for(int i = 0; i < choicesA.size(); i++)
				{
					//String alphabet = Character.toString((char) (i + 65));
					finalStr = finalStr + (i + 1) + ") " + choicesA.get(i) + " ";
				}
				
				int choiceNum = menuPrompt("Which choice would you like to modify?\n" + finalStr, 1, choicesA.size());
				
				io.println("Enter the new choice:");
				String item = io.input(1);
				
				this.choicesA.set(choiceNum - 1, item);
			}
		}
		
		change = 1;
		while(change == 1)
		{
			change = menuPrompt("Would you like to change your correct answers? (1 for yes, 0 for no)", 0, 1);

			ArrayList<String> correctAnswers = new ArrayList<String>();
			
			if(change == 1)
			{
				ArrayList<String> copyChoicesB = new ArrayList<String>();
				copyChoicesB = choicesB;
				for(int j = 0; j < choicesA.size(); j++)
				{
					String answerString = "Which is the correct match for " + choicesA.get(j) + ":\n";
					for(int i = 0; i < copyChoicesB.size(); i++)
					{
						answerString = answerString + (i + 1) + ") " + copyChoicesB.get(i) + "\n";
					}
					int answer = menuPrompt(answerString, 1, copyChoicesB.size());
		
					correctAnswers.add(copyChoicesB.get(answer - 1));
					copyChoicesB.remove(answer - 1);
				}
				qCorrectAnswers.correctAnswers = correctAnswers;
			}
		}
		this.numAnswers = choicesA.size();
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
			String combined = "";
			for(int j = 0; j < aggResp.get(i).size(); j++){
				combined = combined + aggResp.get(i).get(j) + "\n";
				//combinedList.add(aggResp.get(i).get(j));
			}
			io.println(combined);
			combinedList.add(combined);
		}
		//ArrayList<String>keyList = new ArrayList<String>(combinedList);
		//keyList.addAll(choicesB);
		
		Set<String> unique = new HashSet<String>(combinedList);
		io.println("Tabulation:");
		for (String key : unique) {
			System.out.println(Collections.frequency(combinedList, key) + ") ");
		    System.out.println(key + "\n");
		}
	}

	// Start of user code (user defined methods for Ranking)

	// End of user code

}
