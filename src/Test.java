import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Test.
 * 
 * @author sparris
 */
public class Test extends Survey implements Serializable {

	/**
	 * Description of the property cARs.
	 */
	public ArrayList<CAR> cARs = new ArrayList<CAR>();


	public Test()
	{
	}
	
	/*****************************************\
	| Walks admin through creating a new test.|
	\*****************************************/
	public void initializeNewTest()
	{
		cARs = new ArrayList<CAR>();
		questions = new ArrayList<Question>();
		
		io.println("What is the name of the test?");
		name = io.input(language);
		int answer = 1;

		while(answer == 1)
		{
			AskAdminQuestion();
			answer = menuPrompt("Would you like to add another question?\n1) Yes\n2) No", 1, 2);
		}
	}
	
	public void initializeNewTest(String name)
	{
		cARs = new ArrayList<CAR>();
		questions = new ArrayList<Question>();
		int answer = 1;
		this.name = name;
	
		while(answer == 1)
		{
			AskAdminQuestion();
			answer = menuPrompt("Would you like to add another question?\n1) Yes\n2) No", 1, 2);
		}
	}

	/**
	 * Description of the method AddQuestion.
	 * @param question 
	 * @param correctAnswers 
	 */
	public void AddQuestion(Question question, Object correctAnswers) {
		// Start of user code for method AddQuestion
		// End of user code
	}

	

	/*****************************************\
	| Displays all questions and their answers|
	\*****************************************/
	public void Display() {
		io.println(name);
		int correctAnswerIncr = 0;
		for(int i = 0; i < questions.size(); i++){
			io.println(Integer.toString(i + 1) + ") ");
			questions.get(i).Display();
			if((questions.get(i) instanceof ShortAnswer) || !(questions.get(i) instanceof Essay))
			{
				for(int j = 0; j < cARs.get(correctAnswerIncr).correctAnswers.size(); j++)
				{
					////misplaced line of code (variable name)
					io.println("The correct answer: " + cARs.get(correctAnswerIncr).correctAnswers.get(j));
				}
				correctAnswerIncr++;
			}
		}
	}



	/******************************************************\
	| Ask admin details of boolean question and add to test|
	\******************************************************/
	protected void AskAdminBoolean() {
		// Start of user code for method AskAdminBoolean
		// End of user code
		io.println("Enter the prompt for your True/False question:");
		String input = io.input(1);
		BooleanQuestion question = new BooleanQuestion(input);
		questions.add(question);
		String answerString = "Which is the correct answer:\n";
		for(int i = 0; i < question.choices.size(); i++)
		{
			answerString = answerString + (i + 1) + ") " + question.choices.get(i) + " ";
		}
		int answer = menuPrompt(answerString, 1, question.choices.size());
		
		String correctAnswer = question.choices.get(answer - 1);
		CAR newanswer = new CAR(correctAnswer);
		cARs.add(newanswer);
	}



	/**************************************************************\
	| Ask admin details of multiple choice question and add to test|
	\**************************************************************/
	protected void AskAdminMC() {
		// Start of user code for method AskAdminMC
		// End of user code
		io.println("Enter the prompt for your Multiple Choice question:");
		String input = io.input(1);
		
		io.println("Enter the choices (comma separated):");
		String items = io.input(1);
		ArrayList<String> choices =  new ArrayList<String>(Arrays.asList(items.split(",")));
		MultipleChoice question = new MultipleChoice(input, choices);
		
		questions.add(question);

		boolean validInput = true;
		io.println("Which are the correct answer(s) (comma separated):");
		for(int i = 0; i < question.choices.size(); i++)
		{
			io.println((i + 1) + ") " + question.choices.get(i));
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
			for(int i = 0; i < question.choices.size(); i++)
			{
				io.println((i + 1) + ") " + question.choices.get(i));
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
				if(answer < 1 || answer > question.choices.size())
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
			correctAnswers.add(question.choices.get(integerAnswers.get(i)));
		}
		
		questions.get(questions.size() - 1).numAnswers = correctAnswers.size();
		CAR answer = new CAR(correctAnswers);
		cARs.add(answer);
	}



	/***********************************************************\
	| Ask admin details of short answer question and add to test|
	\***********************************************************/
	protected void AskAdminShortAnswer() {
		// Start of user code for method AskAdminShortAnswer
		// End of user code
		io.println("Enter the prompt for your Short Answer question:");
		String input = io.input(language);
		questions.add(new ShortAnswer(input));	
		io.println("Enter the correct answer(s) (comma separated):");
		String unsplitAnswer = io.input(language);
		ArrayList<String> splitAnswers =  new ArrayList<String>(Arrays.asList(unsplitAnswer.split(",")));
		questions.get(questions.size() - 1).numAnswers = splitAnswers.size();
		CAR answer = new CAR(splitAnswers);
		cARs.add(answer);
	}



	/******************************************************\
	| Ask admin details of essay question and add to test  |
	\******************************************************/
	protected void AskAdminEssay() {
		// Start of user code for method AskAdminEssay
		// End of user code
		io.println("Enter the prompt for your Essay question:");
		String input = io.input(language);
		questions.add(new Essay(input));
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
		questions.get(questions.size() - 1).numAnswers = returnVal;
	}


	/******************************************************\
	| Ask admin details of matching question and add to test|
	\******************************************************/
	protected void AskAdminMatching() {
		// Start of user code for method AskAdminMatching
		// End of user code
		io.println("Enter the prompt for your Matching question:");
		String input = io.input(language);
		
		io.println("Enter the first set of items you would like to match (comma separated):");
		String items = io.input(language);
		ArrayList<String> itemsA = new ArrayList<String>(Arrays.asList(items.split(",")));
		
		io.println("Enter the second set items you would like to match (comma separated):");
		String items2 = io.input(language);
		ArrayList<String> itemsB = new ArrayList<String>(Arrays.asList(items2.split(",")));
		Matching question = new Matching(input, itemsA, itemsB);
		questions.add(question);
		
		ArrayList<String> correctAnswers = new ArrayList<String>(); //misplaced line of code
		ArrayList<String> itemsBCopy = new ArrayList<String>(itemsB);
		
		
		for(int j = 0; j < itemsA.size(); j++)
		{
			String answerString = "Which is the correct match for " + itemsA.get(j) + ":\n";
			for(int i = 0; i < itemsBCopy.size(); i++)
			{
				answerString = answerString + (i + 1) + ") " + itemsBCopy.get(i) + "\n";
			}
			int answer = menuPrompt(answerString, 1, itemsBCopy.size());

			correctAnswers.add(itemsBCopy.get(answer - 1));
			itemsBCopy.remove(answer - 1);
		}

		questions.get(questions.size() - 1).numAnswers = correctAnswers.size();
		CAR correctanswer = new CAR(correctAnswers); //misplaced line of code
		cARs.add(correctanswer); //misplaced line of code
	}


	/******************************************************\
	| Ask admin details of ranking question and add to test|
	\******************************************************/
	protected void AskAdminRanking() {
		io.println("Enter the prompt for your Ranking question:");
		String input = io.input(language);
		
		ArrayList<String> correctAnswers = new ArrayList<String>();
		
		
		io.println("Enter the items you would like to rank (comma separated):");
		String items = io.input(language);
		ArrayList<String> itemsA =  new ArrayList<String>(Arrays.asList(items.split(",")));
		ArrayList<String> itemsB =  new ArrayList<String>();
		

		for(int i = 1; i < itemsA.size() + 1; i++)
		{
			itemsB.add(Integer.toString(i));
		}
		
		questions.add(new Ranking(input, itemsA));
		
		for(int j = 0; j < itemsA.size(); j++)
		{
			String answerString = "Which is the correct match for " + itemsA.get(j) + ":\n";
			for(int i = 0; i < itemsB.size(); i++)
			{
				answerString = answerString + (i + 1) + ") " + itemsB.get(i) + "\n";
			}
			int answer = menuPrompt(answerString, 1, itemsB.size());

			correctAnswers.add(itemsB.get(answer - 1));
			itemsB.remove(answer - 1);
		}

		questions.get(questions.size() - 1).numAnswers = correctAnswers.size();
		CAR correctanswer = new CAR(correctAnswers);
		cARs.add(correctanswer);
	}

	/**
	 * Description of the method Modify.
	 */
	public void Modify() {
		int looping = 2;
		
		while(looping != 0)
		{
			int questNum = menuPrompt("What question do you wish to modify? (1-" + (questions.size()) + ")", 1, questions.size());
			if((questions.get(questNum-1) instanceof ShortAnswer) || !(questions.get(questNum-1) instanceof Essay)){
				int essayOffset = 0;
				for(int i = 0; i < questNum; i++){
					if(!(questions.get(i) instanceof ShortAnswer) && (questions.get(i) instanceof Essay))
					{
						essayOffset++;
					}
				}
				CAR qCorrectAnswers = cARs.get(questNum-essayOffset);
				qCorrectAnswers = questions.get(questNum - 1).Modify(qCorrectAnswers);
				cARs.set(questNum - essayOffset, qCorrectAnswers);
				questions.get(questNum - 1).numAnswers = cARs.get(questNum-essayOffset).correctAnswers.size();
			} else {
				questions.get(questNum - 1).Modify();
			}
			/* else if((modifiedQuestion instanceof ShortAnswer) || !(modifiedQuestion instanceof Essay || modifiedQuestion instanceof BooleanQuestion)){
			}
				int change = 1;
				while(change == 1)
				{
					change = menuPrompt("Do you wish to modify the correct answer(s)? (1 for yes, 0 for no)", 0, 1);
					
					if(change == 1)
					{
						String finalStr = "";
						for(int i = 0; i < qCorrectAnswers.correctAnswers.size(); i++)
						{
							//String alphabet = Character.toString((char) (i + 65));
							finalStr = finalStr + (i + 1) + ") " + qCorrectAnswers.correctAnswers.get(i) + " ";
						}
						
						int choiceNum = menuPrompt("Which choice would you like to modify?\n" + finalStr, 1, qCorrectAnswers.correctAnswers.size());
						
						io.println("Enter the new answer:");
						String item = io.input(1);
						
						qCorrectAnswers.correctAnswers.set(choiceNum - 1, item);
						cARs.set(questNum-1, qCorrectAnswers);
					}
				}
			}*/
			
			looping = menuPrompt("Modify another question? (1 for yes, 0 for no)", 0, 1);
		}
	}

	/**
	 * Description of the method Grade.
	 */
	public void Grade() {
		for(int i = 0; i < responsesArray.size(); i++)
		{
			double totalPoints = 0;
			Response indResponse = responsesArray.get(i);
			ArrayList<Integer> grades = indResponse.CheckAnswers(cARs, questions);
			totalPoints = grades.get(0);
			int size = questions.size();
			totalPoints = totalPoints/size * 100;
			double rounded = Math.round(totalPoints * 100.0) / 100.0;
			io.println("Final grade for user test " + (i+1) + ": " + rounded + "%");
		}
	}

	/**
	 * Description of the method Take.
	 */
	public void Take() {
		io.println(name);
		ArrayList<ArrayList<String>> userAnswers = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < questions.size(); i++){
			io.println((i + 1) + ")");
			userAnswers.add(questions.get(i).Take());
		}
		
		Response newResponse = new Response(userAnswers);
		responsesArray.add(newResponse);
		io.println("Thanks for taking this test!");
	}

	/**
	 * Returns cARs.
	 * @return cARs 
	 */
	public ArrayList<CAR> getCARs() {
		return this.cARs;
	}

}
