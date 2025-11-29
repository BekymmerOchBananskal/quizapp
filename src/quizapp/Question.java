package quizapp;

/**
 * Tüm soru türlerinin ortak özelliklerini barındıran soyut sınıf.
 * Her soru metin içerir ve doğru cevabı kontrol eden bir metoda sahiptir.
 */
public abstract class Question {
	private String text;
	private int difficulty;
	
	/**
	 * Soru nesnesi oluşturur.
	 * @param text sorunun metni.
	 * @param difficulty sorunun zorluk seviyesi.
	 */
	public Question(String text,int difficulty) {
		this.text=text;
		this.difficulty=difficulty;
	}
	
	/**
	 * Sorunun metnini döndürür.
	 * @return soru metni
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Sorunun zorluk seviyesini döndürür.
	 * @return soru zorlukseviyesi.
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Kullanıcının verdiği cevabın doğru olup olmadığını kontrol eder.
	 * @param answer kullanıcının girdiği cevap.
	 * @return doğruysa true,yanlışsa false döndürür.
	 */
	public abstract boolean checkAnswer(String Answer);
}
