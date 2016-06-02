import java.util.ArrayList;

/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Exam.
 * 
 * @author sparris
 */
public class Exam {
	/**
	 * Description of the property essays.
	 */
	public ArrayList<Essay> essays = new ArrayList<Essay>();

	/**
	 * Description of the property multipleChoices.
	 */
	public ArrayList<MultipleChoice> multipleChoices = new ArrayList<MultipleChoice>();

	/**
	 * Description of the property booleanQuestions.
	 */
	public ArrayList<BooleanQuestion> booleanQuestions = new ArrayList<BooleanQuestion>();

	/**
	 * Description of the property matchings.
	 */
	public ArrayList<Matching> matchings = new ArrayList<Matching>();

	/**
	 * Description of the property rankings.
	 */
	public ArrayList<Ranking> rankings = new ArrayList<Ranking>();

	/**
	 * Description of the property shortAnswers.
	 */
	public ArrayList<ShortAnswer> shortAnswers = new ArrayList<ShortAnswer>();

	// Start of user code (user defined attributes for Exam)

	// End of user code

	/**
	 * The constructor.
	 */
	public Exam() {
		// Start of user code constructor for Exam)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Exam)

	// End of user code
	/**
	 * Returns essays.
	 * @return essays 
	 */
	public ArrayList<Essay> getEssays() {
		return this.essays;
	}

	/**
	 * Returns multipleChoices.
	 * @return multipleChoices 
	 */
	public ArrayList<MultipleChoice> getMultipleChoices() {
		return this.multipleChoices;
	}

	/**
	 * Returns booleanQuestions.
	 * @return booleanQuestions 
	 */
	public ArrayList<BooleanQuestion> getBooleanQuestions() {
		return this.booleanQuestions;
	}

	/**
	 * Returns matchings.
	 * @return matchings 
	 */
	public ArrayList<Matching> getMatchings() {
		return this.matchings;
	}

	/**
	 * Returns rankings.
	 * @return rankings 
	 */
	public ArrayList<Ranking> getRankings() {
		return this.rankings;
	}

	/**
	 * Returns shortAnswers.
	 * @return shortAnswers 
	 */
	public ArrayList<ShortAnswer> getShortAnswers() {
		return this.shortAnswers;
	}

}
