import java.io.Serializable;
import java.util.ArrayList;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of CAR.
 * 
 * @author sparris
 */
public class Response implements Serializable {
	/**
	 * Description of the property correctAnswers.
	 */
	public ArrayList<ArrayList<String>> userAnswers = new ArrayList<ArrayList<String>>();
	
	
	public Response()
	{
	}
	
	public Response(ArrayList<ArrayList<String>> answers)
	{
		userAnswers = answers;
	}

	// Start of user code (user defined attributes for CAR)

	// End of user code

	/**
	 * Description of the method CheckAnswers.
	 * @param indCAR 
	 * @return 
	 */
	public ArrayList<Integer> CheckAnswers(ArrayList<CAR> indCAR, ArrayList<Question> questions) {
		int grade = 0;
		int surveyNum = 0;
		ArrayList<Integer> grades = new ArrayList<Integer>();
		ArrayList<ArrayList<String>> questionsUserAnswers = userAnswers;
		//Iterate over each set of responses from the user by question
		int essayOffset = 0;
		for(int questNum = 0; questNum < questionsUserAnswers.size(); questNum++){
			if((questions.get(questNum) instanceof ShortAnswer) || !(questions.get(questNum) instanceof Essay))
			{
				boolean permAnswer = true;
				ArrayList<String> userAnswers = questionsUserAnswers.get(questNum);
				if(!(questions.get(questNum) instanceof Matching) && !(questions.get(questNum) instanceof Ranking))
				{
					// Iterate over each user response to that question
					for(int j = 0; j < userAnswers.size(); j++){
						boolean tempAnswer = false;
						String userAnswer = userAnswers.get(j);
						// Compare a part of a specific user response to all correct parts for that question
						for(int l = 0; l < indCAR.get(questNum - essayOffset).correctAnswers.size(); l++)
						{
							if((userAnswer.equals(indCAR.get(questNum - essayOffset).correctAnswers.get(l))))
							{
								tempAnswer = true;
								break;
							}
						}
						
						if(!tempAnswer)
						{
							permAnswer = false;
							break;
						}
					}
					if(permAnswer)
					{
						grade++;
					}
				} else {
					// Iterate over each user response to that question
					String userAnswer = "";
					for(int j = 0; j < userAnswers.size(); j++){
						userAnswer = userAnswer + userAnswers.get(j);
					}
					String correctAnswer = "";
					// Compare a part of a specific user response to all correct parts for that question
					for(int l = 0; l < indCAR.get(questNum - essayOffset).correctAnswers.size(); l++)
					{
						
						correctAnswer = correctAnswer + indCAR.get(questNum - essayOffset).correctAnswers.get(l);
					}

					if(userAnswer.equals(correctAnswer))
					{
						grade++;
					}
				}
			} else {
				essayOffset++;
			}
		}
		grades.add(grade);
		System.out.println("Warning: " + essayOffset + " essays ungraded.");
		return grades;
	}
}
