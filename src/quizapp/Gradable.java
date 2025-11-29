package quizapp;

/**
 * Bir quzin puan hesaplama özelliğini temseil eden arayüz.
* Bu arayüzü uygulayan sınıflar puan hesaplıyabilir.
*/
 
public interface Gradable {
	
	/**
	 * Öğrenciniin aldığı toplam puanı hesaplar.
	 * @return int toplam puan
	 * 
	 */
	int calculateScore();
}
