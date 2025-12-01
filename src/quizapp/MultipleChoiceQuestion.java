package quizapp;
import java.util.List;

/**
 * Çoktan seçmeli soruları temsil eden sınıf.
 * Soru metni,soru zorluk seviyesi,şıklar ve doğru şık indeksini içerir.
 */
public class MultipleChoiceQuestion extends Question {
	private List<String>options;
	private int correctIndex;
	
	/**
	 * Çoktan seçmeli soru oluşturur.
	 * @param text soru metni.
	 * @param difficulty soru zorluk seviyesi.
	 * @param options şıklar listesi(A,B,C,D)
	 * @param correctIndex doğru şık indeksi.
	 */
	public MultipleChoiceQuestion(String text,int difficulty,List<String> options,int correctIndex){
		super(text,difficulty);
		this.options=options;
		this.correctIndex=correctIndex;
	}
	
	/**
	 * Soru şıklarını döndürür.
	 * @return soru şıkları.
	 */
	public List<String> getOptions(){
		return options;
	}
	
	/**
	 *Kullanıcıdan alınan cevabın çoktan seçmeli soru için doğru olup olmadığını kontrol eder.
	 *Bu metot, kullanıcının girdiği cevabı(A,B,C veya D)temizleyip
	 *büyük harfe dönüştürür.Ardından bu harfi 0-3 arası bir indeks değeriyle eşleştirir ve sorunun doğru cevabını
	 *belirten{@code correctIndex} ile karşılaştırır.
	 *Geçersiz giriş yapılması durumunda(örneğin A-D dışı bir değer veya null),
	 *metot bir hata fırlatmak yerine güvenli şekile{@code false} döndürür.
	 *@param answer kullanıcının soru için girdiği cevap(A,B,C veya D harflerinden biri)
	 *@return cevap doğruysa{@code true},yanlış veya geçersiz ise{@code false}
	 */
	
	@Override
	public boolean checkAnswer(String answer) {
		try {
			// Kullanıcı harf girer(A,B,C,D)
			int choice=-1;
			answer=answer.trim().toUpperCase();
			
			
			if(answer.equals("A")) {
				choice=0;
			}
			else if(answer.equals("B")) {
				choice=1;
			}
			else if(answer.equals("C")) {
				choice=2;
			}
			else if(answer.equals("D")) {
				choice=3;
			}
			return choice==correctIndex;
		}
		catch(Exception e) {
			return false;
		}
	}
	

}
