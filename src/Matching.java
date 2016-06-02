import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Matching.
 * 
 * @author sparris
 */
public class Matching extends Question implements Serializable {
	/**
	 * Description of the property choicesA/B.
	 */
	public ArrayList<String> choicesA = new ArrayList<String>();
	public ArrayList<String> choicesB = new ArrayList<String>();
	
	public Matching(){
		
	}
	
	public Matching(String prompt, ArrayList<String>choicesA, ArrayList<String>choicesB){
		this.prompt = prompt;
		this.choicesA =  choicesA;
		this.choicesB =  choicesB;
		numAnswers = choicesA.size();
	}

	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.println(prompt);
		for(int i = 0; i < choicesA.size(); i++)
		{
			io.println(choicesA.get(i) + " " + choicesB.get(i));
		}
	}

	/**
	 * Description of the method Take.
	 * @return 
	 */
	public ArrayList<String> Take() {
		Display();
		ArrayList<String> correctAnswers = new ArrayList<String>(); //misplaced line of code
		ArrayList<String> itemsBCopy = new ArrayList<String>(choicesB);
		
		
		for(int j = 0; j < choicesA.size(); j++)
		{
			String answerString = "Which is the correct match for " + choicesA.get(j) + ":\n";
			for(int i = 0; i < itemsBCopy.size(); i++)
			{
				answerString = answerString + (i + 1) + ") " + itemsBCopy.get(i) + "\n";
			}
			int answer = menuPrompt(answerString, 1, itemsBCopy.size());

			correctAnswers.add(itemsBCopy.get(answer - 1));
			itemsBCopy.remove(answer - 1);
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
			change = menuPrompt("Would you like to change the first set of choices? (1 for yes, 0 for no)", 0, 1);
			
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
			change = menuPrompt("Would you like to change the second set of choices? (1 for yes, 0 for no)", 0, 1);
			
			if(change == 1)
			{
				String finalStr = "";
				for(int i = 0; i < choicesB.size(); i++)
				{
					//String alphabet = Character.toString((char) (i + 65));
					finalStr = finalStr + (i + 1) + ") " + choicesB.get(i) + " ";
				}
				
				int choiceNum = menuPrompt("Which choice would you like to modify?\n" + finalStr, 1, choicesB.size());
				
				io.println("Enter the new choice:");
				String item = io.input(1);
				
				this.choicesB.set(choiceNum - 1, item);
			}
		}
		numAnswers = choicesA.size();
	}
	
	public CAR Modify(CAR qCorrectAnswers) {
		ModifyPrompt();
		
		int change = 1;
		while(change == 1)
		{
			change = menuPrompt("Would you like to change the first set of choices? (1 for yes, 0 for no)", 0, 1);
			
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
			change = menuPrompt("Would you like to change the second set of choices? (1 for yes, 0 for no)", 0, 1);
			
			if(change == 1)
			{
				String finalStr = "";
				for(int i = 0; i < choicesB.size(); i++)
				{
					//String alphabet = Character.toString((char) (i + 65));
					finalStr = finalStr + (i + 1) + ") " + choicesB.get(i) + " ";
				}
				
				int choiceNum = menuPrompt("Which choice would you like to modify?\n" + finalStr, 1, choicesB.size());
				
				io.println("Enter the new choice:");
				String item = io.input(1);
				
				this.choicesB.set(choiceNum - 1, item);
			}
		}
		
		ArrayList<String> correctAnswers = new ArrayList<String>(); //misplaced line of code
		ArrayList<String> choicesBCopy = new ArrayList<String>(choicesB);
		
		for(int j = 0; j < choicesA.size(); j++)
		{
			String answerString = "Which is the correct match for " + choicesA.get(j) + ":\n";
			for(int i = 0; i < choicesBCopy.size(); i++)
			{
				answerString = answerString + (i + 1) + ") " + choicesBCopy.get(i) + "\n";
			}
			int answer = menuPrompt(answerString, 1, choicesBCopy.size());

			correctAnswers.add(choicesBCopy.get(answer - 1));
			choicesBCopy.remove(answer - 1);
		}
		this.numAnswers = choicesA.size();
		qCorrectAnswers.correctAnswers = correctAnswers;
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
		    System.out.println(Collections.frequency(combinedList, key) + ") " + key);
		}
	}

	/**
	 * Description of the method Matching.
	 */
	public void Matching() {
		// Start of user code for method Matchin
		// End of user code
	}

	// Start of user code (user defined methods for Matching)

	// End of user code
	/**
	 * Returns choicesA.
	 * @return choicesA 
	 */
	public List<String> getChoicesA() {
		return this.choicesA;
	}

	/**
	 * Sets a value to attribute choicesA. 
	 * @param newChoicesA 
	 */
	public void setChoicesA(ArrayList<String> newChoicesA) {
		this.choicesA = newChoicesA;
	}

	/**
	 * Returns choicesB.
	 * @return choicesB 
	 */
	public List<String> getChoicesB() {
		return this.choicesB;
	}

	/**
	 * Sets a value to attribute choicesB. 
	 * @param newChoicesB 
	 */
	public void setChoicesB(ArrayList<String> newChoicesB) {
		this.choicesB = newChoicesB;
	}

}
