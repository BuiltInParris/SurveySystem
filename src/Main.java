import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/


// Start of user code (user defined imports)

// End of user code

/**
 * Description of Main.
 * 
 * @author sparris
 */
public class Main {
	//Language is used to determine the output/input in the entire program.
	public static int language = 1;
	public static String[] surveyMenuOptions = {"Create a new", "Display a", "Load a", "Save a", "Modify a", "Take a", "Tabulate a", "Quit"};
	public static String[] testMenuOptions = {"Create a new", "Display a", "Load a", "Save a", "Modify a", "Take a", "Grade a", "Tabulate a", "Quit"};
	//io object allowing big changes
	public static CustomIO io = CustomIO.getInstance();
	
	// Start of user code (user defined attributes for Main)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public static void main(String[] args) {
		Menu1();
	}
	
	/**
	 * First menu, gets language and branches between surveys and tests
	 */
	public static void Menu1() {
		
		//By default print initial instructions with text.
		io.println("Welcome to Exam System!");
		language = menuPrompt("Which would you like use?\n1) Text\n2) Voice", 1, 2);
		
		/*if(language == 2){
			io.println("Voice not implemented yet, using text.");
			language = 1;
		}*/

		io.language = language;

		int input = menuPrompt("Which would you like to operate with today?\n1) Survey\n2) Test", 1, 2);
		
		if(input == 1)
		{
			SurveyMenu();
		} else if(input == 2)
		{
			TestMenu();
		}
	}
	 
	/**
	 * Survey menu handles all the different types of commands related to a survey.
	 */
	public static void SurveyMenu() {
		String examName = "Survey";
		Survey userSurvey = new Survey();
		
		// Repeats until given the exit command.
		while(true) {
			//Present options
			String answerString = "What would you like to do?\n";
			
			for(int i = 0; i < surveyMenuOptions.length; i++)
			{
				answerString = answerString + (i + 1) + ") " + surveyMenuOptions[i] + " " + examName + "\n";
			}

			int input = menuPrompt(answerString, 1, surveyMenuOptions.length);
			
			// Use input to trigger action
			if(input == 1){ // Create survey
				userSurvey.initalizeNewSurvey();
			} else if(input == 2){ // Display survey
				if(!(userSurvey.name.equals("")))
					userSurvey.Display();
				else
					io.println("No survey selected!");
				
			} else if(input == 3){ // Load survey
				String filename = filenameQuery("surveys");
				userSurvey = LoadSurvey(filename);
				//print user
				for(int index = 0; index < userSurvey.responsesArray.size(); index++)
				{
					Response oneResponse = userSurvey.responsesArray.get(index);
					for(int i = 0; i < oneResponse.userAnswers.size(); i++)
					{
						for(int j = 0; j < oneResponse.userAnswers.get(i).size(); j++)
						{
							io.println(oneResponse.userAnswers.get(i).get(j));
						}
					}
				}
			} else if(input == 4){ // Save survey
				if(userSurvey.name.equals(""))
				{
					io.println("No Survey selected.");
				} else {
					String filename = userSurvey.name;
					SaveSurvey(filename, userSurvey);
				}
			} else if(input == 5){ // Modify survey
				String filename = filenameQuery("surveys");
				userSurvey = LoadSurvey(filename);
				userSurvey.Modify();
				
			} else if(input == 6){ // Take survey
				if(!userSurvey.name.equals(""))
				{
					userSurvey.Take();
					String filename = userSurvey.name;
					SaveSurvey(filename, userSurvey);
					/*//print user
					for(int index = 0; index < userSurvey.responsesArray.size(); index++)
					{
						Response oneResponse = userSurvey.responsesArray.get(index);
						for(int i = 0; i < oneResponse.userAnswers.size(); i++)
						{
							for(int j = 0; j < oneResponse.userAnswers.get(i).size(); j++)
							{
								io.println(oneResponse.userAnswers.get(i).get(j),language);
							}
						}
					}*/
				} else {
					io.println("No survey selected!");
				}
			} else if(input == 7){ // Tabulate survey
				if(userSurvey.name.equals(""))
				{
					io.println("No Survey selected.");
				} else {
					userSurvey.Tabulate();
				}
			} else if(input == 8){ // Exit survey
				 System.exit(0);
			}
		}
	}
	 
	/**
	 * This lists the files in a directory at a specified string, and allows the user to choose one of them.
	 * @return 
	 */
	public static String filenameQuery(String base_loc) {
		File folder = new File("./" + base_loc);
		File[] listOfFiles = folder.listFiles();
		String filename = "";

		//List files in directory
		String answerString = "Choose your filename:\n";
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				answerString = answerString + (i + 1) +  ") " + listOfFiles[i].getName() + "\n";
		    } else if (listOfFiles[i].isDirectory()) {
		    	answerString = answerString + "Directory " + listOfFiles[i].getName() + "\n";
		    }
		}
		
		int filename_input = menuPrompt(answerString, 1, listOfFiles.length);
	
		filename = listOfFiles[filename_input - 1].getName();
		
		return filename;
	}
	 
	/**
	 * Load survey with a filename from serialized object.
	 * @param filename 
	 */
	public static Survey LoadSurvey(String filename) {
		try
	    {
	      FileInputStream inputFileStream = new FileInputStream("surveys/" + filename);
	      ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
	      Survey survey = (Survey)objectInputStream.readObject();
	      objectInputStream.close();
	      inputFileStream.close();
	      return survey;
	    }
	    catch(ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch(IOException i)
	    {
	      i.printStackTrace();
	    }
	    
		return null;
	}
	 
	/**
	 * Save survey as serialized object.
	 * @param filename 
	 * @param userSurvey 
	 */
	public static void SaveSurvey(String filename, Survey userSurvey) {
		try
	    {
	        FileOutputStream fileOut = new FileOutputStream("surveys/" + filename);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(userSurvey);
	        out.close();
	        fileOut.close();
	        io.println("Serialized data is saved");
	     }catch(IOException i)
	     {
	         i.printStackTrace();
	     }
	}
	 
	/**
	 * TestMenu handles all the different types of commands related to a test.
	 */
	public static void TestMenu() {
		String examName = "Test";
		Test userTest = new Test();

		// Repeats until given the exit command.
		while(true) {
			
			// Present test options
			String answerString = "What would you like to do?\n";
			for(int i = 0; i < testMenuOptions.length; i++)
			{
				answerString = answerString + (i + 1) + ") " + testMenuOptions[i] + " " + examName + "\n";
			}

			int input = menuPrompt(answerString, 0, testMenuOptions.length);
			
			if(input == 1){ // Create test
				userTest.initializeNewTest();
			} else if(input == 2){ // Display test
				if(!userTest.name.equals(""))
					userTest.Display();
				else
					io.println("No Test selected!");
				
			} else if(input == 3){ // Load test
				String filename = filenameQuery("tests");
				//System.out.println(filename);
				userTest = LoadTest(filename);
				//print user responses
				/*for(int index = 0; index < userTest.responsesArray.size(); index++)
				{
					Response oneResponse = userTest.responsesArray.get(index);
					for(int i = 0; i < oneResponse.userAnswers.size(); i++)
					{
						for(int j = 0; j < oneResponse.userAnswers.get(i).size(); j++)
						{
							io.println(oneResponse.userAnswers.get(i).get(j),language);
						}
					}
				}*/
			} else if(input == 4){ // Save test
				if(userTest.name.equals(""))
				{
					io.println("No Test selected.");
				} else {
					String filename = userTest.name;
					SaveTest(filename, userTest);
				}
			} else if(input == 5){ // Modify test
				String filename = filenameQuery("tests");
				userTest = LoadTest(filename);
				userTest.Modify();
			} else if(input == 6){ // Take test
				if(userTest.name.equals(""))
				{
					io.println("No Test selected.");
				} else {
					userTest.Take();
					String filename = userTest.name;
					SaveTest(filename, userTest);
					userTest.Grade();
				}
				//io.println("Not implemented.");
			} else if(input == 7){ // Grade test
				if(userTest.name.equals(""))
				{
					io.println("No Test selected.");
				} else {
					userTest.Grade();
				}
			} else if(input == 8){ // Tabulate test
				if(userTest.name.equals(""))
				{
					io.println("No Test selected.");
				} else {
					userTest.Tabulate();
				}
				//io.println("Not implemented.");
			} else if(input == 9){ // Exit test
				System.exit(0);
			}
		}
	}
	 
	/**
	 * Load test from serialized object in tests folder.
	 * @param filename 
	 */
	public static Test LoadTest(String filename) {
		try
	    {
	      FileInputStream inputFileStream = new FileInputStream("tests/" + filename);
	      ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
	      Test test = (Test)objectInputStream.readObject();
	      objectInputStream.close();
	      inputFileStream.close();
	      return test;
	    }
	    catch(ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch(IOException i)
	    {
	      i.printStackTrace();
	    }
	    
		return null;
	}
	 
	/**
	 * Save test object to test folder as a serialized object.
	 * @param filename 
	 * @param  
	 * @param userTest 
	 */
	public static void SaveTest(String filename, Test userTest) {
		try
	    {
	        FileOutputStream fileOut = new FileOutputStream("tests/" + filename);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(userTest);
	        out.close();
	        fileOut.close();
	        io.println("Serialized data is saved");
	     }catch(IOException i)
	     {
	         i.printStackTrace();
	     }
	}
	 
	/**
	 * Returns language.
	 * @return language 
	 */
	public int getLanguage() {
		return this.language;
	}
	
	/**
	 * Sets a value to attribute language. 
	 * @param newLanguage 
	 */
	public void setLanguage(int newLanguage) {
	    this.language = newLanguage;
	}

	/**
	 * Returns io.
	 * @return io 
	 */
	public CustomIO getIo() {
		return this.io;
	}
	
	public static int menuPrompt(String prompt, int bot, int top)
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
	 * Sets a value to attribute io. 
	 * @param newIo 
	 */
	public void setIo(CustomIO newIo) {
	    this.io = newIo;
	}

}
