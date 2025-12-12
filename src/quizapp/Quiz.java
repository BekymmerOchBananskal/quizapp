package quizapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * Bir quiz oturumunu yöneten sınıf.
 * 
 * Soruları gösterir, kullanıcının cevaplarını alır ve {@link #calculateScore()} metodu ile
 * öğrencinin puanını hesaplar.
 */
public class Quiz implements Gradable {
	private List<Question>questions;
	private Student student;
	private boolean timeEnabled;
	private boolean shuffle;
	private List<String>userAnswers;
	
	/**
	 * Yeni bir {@code Quiz} nesnesi oluşturur.
     *
     * @param questions   Quizde kullanılacak soru listesi.
     * @param student     Quizi çözecek öğrenci.
     * @param shuffle     Soruların karıştırılıp karıştırılmayacağını belirten bayrak.
     *                    {@code true} ise soru listesi rastgele karıştırılır.
     * @param userAnswers Öğrencinin cevaplarını tutmak için kullanılacak liste.
     *                    (Yapıcı içinde yeni bir liste başlatılır, dışarıdan gelen liste kullanılmaz.)
     */
	 
	public Quiz(List<Question>questions,Student student,boolean shuffle) {
		this.questions=questions;
		this.student=student;
		this.shuffle=shuffle;
		this.userAnswers=new ArrayList<String>();
		
		if(shuffle) {
			Collections.shuffle(this.questions);
		}
	}
	
	/**
	 *  Quiz oturumunu başlatır.
     * <p
     * Kullanıcıdan ENTER’a basarak quizi başlatması istenir ve ardından
     * tüm sorular sırayla gösterilir.
     * 
     * Çoktan seçmeli sorularda şıklar listelenir ve kullanıcıdan ilgili şıkkı
     *(örneğin "A" veya "B") yazması istenir.
     * Doğru/Yanlış (True/False) sorularında kullanıcıdan "Evet" veya "Hayır"
     * cevabı alınır.
     * 
     * Kullanıcının tüm cevapları {@code userAnswers} listesine eklenir.
     * 
     * Bu metot herhangi bir puan hesaplaması yapmaz;
     * puan hesaplamak için {@link #calculateScore()} metodu çağrılmalıdır.
	 */
	public void start() {
		Scanner input=new Scanner(System.in);
		System.out.println(student.getName()+" hazırsan ENTER’a bas ve quiz başlasın!");
		input.nextLine();
		System.out.println("Quiz başlıyor!\n");
		int number=1;
		
		for(Question q:questions) {
			System.out.println(number+".Soru: "+q.getText());
			
			if(q instanceof MultipleChoiceQuestion mcq) {
				List<String>opts=mcq.getOptions();
				char optionChar='A';
				for(String opt:opts) {
					System.out.println(optionChar+") "+opt);
					optionChar++;
				}
				System.out.println();
			}
			if(q instanceof MultipleChoiceQuestion) {
				System.out.print("Cevabınız(Şıkları yazınız): ");
				
			}
			else {
				System.out.print("Cevabınız(Evet/Hayır): ");
				
			}
			
			
			String answer=input.nextLine();
			userAnswers.add(answer.trim());
			System.out.println("**************************************************************");
			number++;
		}
	}
	
	/**Quiz sonuçlarını hesaplar ve öğrencinin puanını günceller.
     * Her soru için:
     * Cevap doğruysa, sorunun zorluk derecesi ile orantılı puan verilir:
     * {@code 10 * question.getDifficulty()}.
     * Cevap yanlışsa puan eklenmez.
     * @return Öğrencinin quiz sonunda ulaştığı toplam puan.
     */
	@Override
	public int calculateScore() {
		int score=0;
		int correct = 0;
	    int wrong = 0;
		
		for(int i=0;i<questions.size();i++) {
			Question q=questions.get(i);
			String answer=userAnswers.get(i);
			
			if(q.checkAnswer(answer)) {
				score+=10*q.getDifficulty();
				correct++;
			}
			else {
				wrong++;
			}
			
		}
		student.setScore(score);
		student.setCorrectCount(correct);
		student.setWrongCount(wrong);
        return score;
	}
	
	/**Öğrencinin quiz sonucunu konsola yazdırır.
     * Bu metot, öğrencinin adı ve toplam puanını aşağıdaki formatta gösterir:
     * [Öğrenci Adı] adlı öğrencinin toplam puanı: [Puan]
     * 
     */
	 public void showResult() {
		 System.out.println("*                      Quiz Sonucu                           *");
		 System.out.println("**************************************************************");
		 
		 System.out.printf("* Öğrenci Adı: %-45s *%n", student.getName());
		 System.out.printf("* Doğru: %-51d *%n", student.getCorrectCount());
		 System.out.printf("* Yanlış: %-50d *%n", student.getWrongCount());
		 System.out.printf("* Quiz Puanı:  %-45d *%n", student.getScore());
		 System.out.println("**************************************************************");
	}
}
