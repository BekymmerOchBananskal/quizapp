package quizapp;

/**
 * Evet/Hayır(True/False) sorularını temsil eden sınıf.
 */
public class TrueFalseQuestion extends Question{
	
	private boolean correctAnswer;
	
	/**
	 * @param text soru metni.
	 * @param difficulty soru zorluk seviyesi.
	 * @param correctAnswer doğru cevap(True/false).
	 */
	public TrueFalseQuestion(String text,int difficulty,boolean correctAnswer) {
		super(text,difficulty);
		this.correctAnswer=correctAnswer;	
		}
	
	/**
	 *Kullanıcının cevabının doğruluğunu kontrol eder.
	 *@param answer kullanıcının cevabı("evet/hayır).
	 *@return doğruysa true
	 */
	@Override
	public boolean checkAnswer(String answer) {
		answer=answer.trim().toLowerCase();
		boolean user=answer.equals("evet");
		return user==correctAnswer;
	}
	
}
