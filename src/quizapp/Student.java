package quizapp;

/**
 * Öğrenciyi temsil eden sınıf.
 * Öğrencinin adı ve aldığı toplam puan bilgisini taşır.
 */
public class Student {
	private String name;
	private int totalScore;
	private int correctCount;
	private int wrongCount;
	
	/**
	 * Öğrenciyi oluşturur.
	 * @param name öğrenci adı.
	 */
	public Student(String name) {
		this.name=name;
	}
	
	/**
	 * Öğrencinin adını döndürür.
	 * @return öğrenci adı
	 */
	public String getName() {
		return name;
	}
	
	public int getCorrectCount() {
		return correctCount;
	}
	public void setCorrectCount(int correctCount) {
		this.correctCount=correctCount;
	}
	
	public int getWrongCount() {
		return  wrongCount;
	}
	public void setWrongCount(int wrongCount) {
		this.wrongCount=wrongCount;
	}
	
	/**
	 * Öğrencinin puanını günceller.
	 * @param score yeni puan.
	 */
	public void setScore(int score) {
		this.totalScore=score;
	}
	
	/**
	 * Öğrencinin toplam puanını döndürür.
	 * @return toplam puan
	 */
	public int getScore() {
		return totalScore;
	}
}
