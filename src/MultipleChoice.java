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
 * Description of MultipleChoice.
 * 
 * @author sparris
 */
public class MultipleChoice extends Question implements Serializable {
	/**
	 * Description of the property choices.
	 */
	public ArrayList<String> choices = new ArrayList<String>();

	// Start of user code (user defined attributes for MultipleChoice)

	// End of user code
	public MultipleChoice(){
	}
	
	public MultipleChoice(String prompt, ArrayList<String> newChoices){
		this.prompt = prompt;
		this.choices = newChoices;
	}

	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.println(prompt);
		String finalStr = "";
		for(int i = 0; i < choices.size(); i++)
		{
			//String alphabet = Character.toString((char) (i + 65));
			finalStr = finalStr + (i + 1) + ") " + choices.get(i) + " ";
		}
		io.println(finalStr);
	}

	/**
	 * Description of the method Take.
	 * @return 
	 */
	public ArrayList<String> Take() {
		Display();
		boolean validInput = true;
		io.println("Please give " + numAnswers + " answer(s): (comma separated):");
		
		String unsplitAnswer = io.input(language);
		ArrayList<String> splitAnswers =  new ArrayList<String>(Arrays.asList(unsplitAnswer.split(",")));
		ArrayList<Integer> integerAnswers =  new ArrayList<Integer>();
		
		for(int i = 0; i < splitAnswers.size(); i++)
		{
			int answer = 0;
			try {
				answer = Integer.parseInt(splitAnswers.get(i));
			} catch(NumberFormatException e){
				answer = 0;
			}
			if(answer < 1 || answer > choices.size())
			{
				validInput = false;
				break;
			}
			integerAnswers.add(answer - 1);
		}
		
		while(!validInput)
		{
			validInput = true;
			io.println("Invalid response.");
			Display();
			io.println("Please give " + numAnswers + " answer(s): (comma separated):");
			unsplitAnswer = io.input(language);
			splitAnswers =  new ArrayList<String>(Arrays.asList(unsplitAnswer.split(",")));
			integerAnswers =  new ArrayList<Integer>();
			
			if(splitAnswers.size() == numAnswers)
			{
				for(int i = 0; i < splitAnswers.size(); i++)
				{
					int answer = 0; 
					try {
						answer = Integer.parseInt(splitAnswers.get(i));
					} catch(NumberFormatException e){
						answer = 0;
					}
					if(answer < 1 || answer > choices.size())
					{
						validInput = false;
						break;
					}
					integerAnswers.add(answer - 1);
				}
			} else {
				validInput = false;
			}
		}
		
		ArrayList<String> userAnswers = new ArrayList<String>();
		
		for(int i = 0; i < integerAnswers.size(); i++)
		{
			userAnswers.add(choices.get(integerAnswers.get(i)));
		}
		return userAnswers;
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
				for(int i = 0; i < choices.size(); i++)
				{
					//String alphabet = Character.toString((char) (i + 65));
					finalStr = finalStr + (i + 1) + ") " + choices.get(i) + " ";
				}
				
				int choiceNum = menuPrompt("Which choice would you like to modify?\n" + finalStr, 1, choices.size());
				
				io.println("Enter the new choice:");
				String item = io.input(1);
				
				this.choices.set(choiceNum - 1, item);
			}
		}
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
				for(int i = 0; i < choices.size(); i++)
				{
					//String alphabet = Character.toString((char) (i + 65));
					finalStr = finalStr + (i + 1) + ") " + choices.get(i) + " ";
				}
				
				int choiceNum = menuPrompt("Which choice would you like to modify?\n" + finalStr, 1, choices.size());
				
				io.println("Enter the new choice:");
				String item = io.input(1);
				
				this.choices.set(choiceNum - 1, item);
			}
		}
		
		change = 1;
		while(change == 1)
		{
			change = menuPrompt("Do you wish to modify the correct answer? (1 for yes, 0 for no)", 0, 1);
			
			if(change == 1)
			{
				boolean validInput = true;
				io.println("Which are the correct answer(s) (comma separated):");
				for(int i = 0; i < choices.size(); i++)
				{
					io.println((i + 1) + ") " + choices.get(i));
				}
				String unsplitAnswer = io.input(language);
				ArrayList<String> splitAnswers =  new ArrayList<String>(Arrays.asList(unsplitAnswer.split(",")));
				ArrayList<Integer> integerAnswers =  new ArrayList<Integer>();
				
				for(int i = 0; i < splitAnswers.size(); i++)
				{
					int answer = 0;
					try {
						answer = Integer.parseInt(splitAnswers.get(i));
					} catch(NumberFormatException e){
						answer = 0;
					}
					if(answer < 1 || answer > choices.size())
					{
						validInput = false;
						break;
					}
					integerAnswers.add(answer - 1);
				}
				
				while(!validInput)
				{
					validInput = true;
					io.println("Invalid response.");
					io.println("Which are the correct answer(s) (comma separated):");
					for(int i = 0; i < choices.size(); i++)
					{
						io.println((i + 1) + ") " + choices.get(i));
					}
					unsplitAnswer = io.input(language);
					splitAnswers =  new ArrayList<String>(Arrays.asList(unsplitAnswer.split(",")));
					integerAnswers =  new ArrayList<Integer>();
					
					for(int i = 0; i < splitAnswers.size(); i++)
					{
						int answer = 0; 
						try {
							answer = Integer.parseInt(splitAnswers.get(i));
						} catch(NumberFormatException e){
							answer = 0;
						}
						if(answer < 1 || answer > choices.size())
						{
							validInput = false;
							break;
						}
						integerAnswers.add(answer - 1);
					}
				}
				ArrayList<String> correctAnswers = new ArrayList<String>();
				
				for(int i = 0; i < integerAnswers.size(); i++)
				{
					correctAnswers.add(choices.get(integerAnswers.get(i)));
				}
				qCorrectAnswers.correctAnswers = correctAnswers;
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
		ArrayList<String>keyList = new ArrayList<String>(combinedList);
		keyList.addAll(choices);
		
		Set<String> unique = new HashSet<String>(keyList);
		io.println("\nTabulation:");
		for (String key : unique) {
		    System.out.println(key + ": " + Collections.frequency(combinedList, key));
		}
	}

	// Start of user code (user defined methods for MultipleChoice)

	// End of user code
	/**
	 * Returns choices.
	 * @return choices 
	 */
	public List<String> getChoices() {
		return this.choices;
	}

	/**
	 * Sets a value to attribute choices. 
	 * @param newChoices 
	 */
	public void setChoices(ArrayList<String> newChoices) {
		this.choices = newChoices;
	}

}
