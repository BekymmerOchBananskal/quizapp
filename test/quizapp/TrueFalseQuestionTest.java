package quizapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrueFalseQuestionTest {
	private TrueFalseQuestion createSampleQuestionTrue() {
		
		return new TrueFalseQuestion(
				"Türkiye'nin başkenti Ankaradır.",
				1,
				true
				
				
				
				);
	}
	private TrueFalseQuestion createSampleQuestionFalse() {
        return new TrueFalseQuestion(
                "2 + 2 = 5'tir.",
                2,
                false
        );
    }
	 @Test
	    void testCheckAnswer_forTrueQuestion() {
	        TrueFalseQuestion q = createSampleQuestionTrue(); // correctAnswer = true

	        // Doğru cevaplar
	        assertTrue(q.checkAnswer("evet"));
	        assertTrue(q.checkAnswer(" Evet "));
	        assertTrue(q.checkAnswer("EVET"));

	        // Yanlış cevaplar
	        assertFalse(q.checkAnswer("hayır"));
	        assertFalse(q.checkAnswer("HayIr "));
	        assertFalse(q.checkAnswer("random"));
	    }
	 @Test
	    void testCheckAnswer_forFalseQuestion() {
	        TrueFalseQuestion q = createSampleQuestionFalse(); // correctAnswer = false

	        // Doğru cevaplar (false soru için "hayır")
	        assertTrue(q.checkAnswer("hayır"));
	        assertTrue(q.checkAnswer(" Hayır "));
	        assertTrue(q.checkAnswer("HAYIR"));

	        // Yanlış cevaplar
	        assertFalse(q.checkAnswer("evet"));
	        assertFalse(q.checkAnswer(" EvEt "));
	    }
	 @Test
	    void testTrueFalseQuestion_constructor() {
	        TrueFalseQuestion q = new TrueFalseQuestion(
	                "Türkiye'nin başkenti Ankaradır.",
	                1,
	                true
	        );

	        // Question sınıfında getText / getDifficulty gibi metotların kontrolu.
	        
	        assertEquals("Türkiye'nin başkenti Ankaradır.", q.getText());
	        assertEquals(1, q.getDifficulty());

	        //checkAnswer metodu kontrol ediliyor.
	        assertTrue(q.checkAnswer("evet"));
	        assertFalse(q.checkAnswer("hayır"));
	    }

}
