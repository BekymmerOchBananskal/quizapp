package quizapp;

/**
 * ğrenciyi temsil eden sınıf.
 * Öğrencinin adı ve aldığı toplam puan bilgisini taşır.
 */
public class Student {
	private String name;
	private int totalScore;
	
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
	public String getname() {
		return name;
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
