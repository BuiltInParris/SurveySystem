import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Survey.
 * 
 * @author sparris
 */
public class Survey implements Serializable {
	
	public Survey()
	{
	}
	
	public void initalizeNewSurvey()
	{
		io.println("What is the name of the survey?");
		name = io.input(language);
		int answer = 1;

		while(answer == 1)
		{
			AskAdminQuestion();
			answer = menuPrompt("Would you like to add another question?\n1) Yes\n2) No", 1, 2);
		}
	}
	

	public void initalizeNewSurvey(String name)
	{
		int answer = 1;
		this.name = name;

		while(answer == 1)
		{
			AskAdminQuestion();
			answer = menuPrompt("Would you like to add another question?\n1) Yes\n2) No", 1, 2);
		}
		
	}
	
	/**
	 * Description of the property name.
	 */
	public String name = "";
	
	public int language = 1;
	/**
	 * Description of the property questions.
	 */
	public ArrayList<Question> questions = new ArrayList<Question>();

	/**
	 * Description of the property responsesArrays.
	 */
	public ArrayList<Response> responsesArray = new ArrayList<Response>();
	

	public static CustomIO io = CustomIO.getInstance();

	// Start of user code (user defined attributes for Survey)

	// End of user code

	/**
	 * Description of the method AddQuestion.
	 * @param question 
	 */
	public void AddQuestion(Question question) {
		// Start of user code for method AddQuestion
		// End of user code
	}

	/**
	 * Description of the method AskAdminBoolean.
	 */
	protected void AskAdminBoolean() {
		// Start of user code for method AskAdminBoolean
		// End of user code
		io.println("Enter the prompt for your True/False question:");
		String input = io.input(1);
		questions.add(new BooleanQuestion(input));		
	}

	/**
	 * Description of the method AskAdminEssay.
	 */
	protected void AskAdminEssay() {
		// Start of user code for method AskAdminEssay
		// End of user code
		io.println("Enter the prompt for your Essay question:");
		String input = io.input(1);
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

	/**
	 * Description of the method AskAdminShortAnswer.
	 */
	protected void AskAdminShortAnswer() {
		// Start of user code for method AskAdminShortAnswer
		// End of user code
		io.println("Enter the prompt for your Short Answer question:");
		String input = io.input(1);
		questions.add(new ShortAnswer(input));	
		
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

	/**
	 * Description of the method AskAdminRanking.
	 */
	protected void AskAdminRanking() {
		// Start of user code for method AskAdminRanking
		// End of user code
		io.println("Enter the prompt for your Ranking question:");
		String input = io.input(1);
		
		
		io.println("Enter the items you would like to rank (comma separated):");
		String items = io.input(1);
		ArrayList<String> ranks =  new ArrayList<String>(Arrays.asList(items.split(",")));
		
		questions.add(new Ranking(input, ranks));	
	}

	/**
	 * Description of the method AskAdminMatching.
	 */
	protected void AskAdminMatching() {
		// Start of user code for method AskAdminMatching
		// End of user code
		io.println("Enter the prompt for your Matching question:");
		String input = io.input(1);
		
		io.println("Enter the first set of items you would like to match (comma separated):");
		String items = io.input(1);
		ArrayList<String> itemsA = new ArrayList<String>(Arrays.asList(items.split(",")));
		
		io.println("Enter the second set items you would like to match (comma separated):");
		String items2 = io.input(1);
		ArrayList<String> itemsB = new ArrayList<String>(Arrays.asList(items2.split(",")));
		
		
		questions.add(new Matching(input, itemsA, itemsB));	
	}

	/**
	 * Description of the method AskAdminMC.
	 */
	protected void AskAdminMC() {
		// Start of user code for method AskAdminMC
		// End of user code
		io.println("Enter the prompt for your Multiple Choice question:");
		String input = io.input(1);
		
		io.println("Enter the choices (comma separated):");
		String items = io.input(1);
		ArrayList<String> choices =  new ArrayList<String>(Arrays.asList(items.split(",")));

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
		
		questions.add(new MultipleChoice(input, choices));	
		questions.get(questions.size() - 1).numAnswers = returnVal;
	}

	/**
	 * Description of the method Display.
	 */
	public void Display() {
		io.println(name);
		for(int i = 0; i < questions.size(); i++){
			io.println(Integer.toString(i + 1) + ") ");
			questions.get(i).Display();
		}
	}

	/**
	 * Description of the method Modify.
	 */
	public void Modify() {
		int looping = 2;
		while(looping != 0)
		{
			int questNum = menuPrompt("What question do you wish to modify? (1-" + (questions.size()) + ")", 1, questions.size());
			
			questions.get(questNum - 1).Modify();
			looping = menuPrompt("Modify another question? (1 for yes, 0 for no)", 0, 1);
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
		
		io.println("Thanks for taking this survey!");
	}

	/**
	 * Description of the method Tabulate.
	 */
	public void Tabulate() {
		io.println(name);
		for(int questnum = 0; questnum < questions.size(); questnum++)
		{
			ArrayList<ArrayList<String>> aggResps = new ArrayList<ArrayList<String>>();
			for(int respNum = 0; respNum < responsesArray.size(); respNum++)
			{
				aggResps.add(responsesArray.get(respNum).userAnswers.get(questnum));
			}
			questions.get(questnum).Tabulate(aggResps);
			io.println("\n");
			//responsesArray.get();
		}
	}

	/**
	 * Description of the method AskAdminQuestion.
	 */
	public void AskAdminQuestion() {
		// Start of user code for method AskAdminQuestion
		// End of user code
		String prompt = "1) Add a new T/F question\n"
		+ "2) Add a new multiple choice question\n"
		+ "3) Add a new short answer question\n"
		+ "4) Add a new essay question\n"
		+ "5) Add a new ranking question\n"
		+ "6) Add a new matching question";
		
		int answer = menuPrompt(prompt, 1, 6);
		
		if(answer == 1){
			AskAdminBoolean();
		} else if (answer == 2){
			AskAdminMC();
		} else if (answer == 3){
			AskAdminShortAnswer();
		} else if (answer == 4){
			AskAdminEssay();
		} else if (answer == 5){
			AskAdminRanking();
		} else if (answer == 6){
			AskAdminMatching();
		}
		
	}

	// Start of user code (user defined methods for Survey)

	// End of user code
	/**
	 * Returns name.
	 * @return name 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute name. 
	 * @param newName 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns questions.
	 * @return questions 
	 */
	public ArrayList<Question> getQuestions() {
		return this.questions;
	}

	/**
	 * Sets a value to attribute questions. 
	 * @param newQuestions 
	 */
	public void setQuestions(ArrayList<Question> newQuestions) {
		this.questions = newQuestions;
	}

}
