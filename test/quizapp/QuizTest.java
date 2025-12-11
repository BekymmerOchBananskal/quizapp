package quizapp;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizTest {

    // Quiz içindeki private userAnswers alanına erişmek için yardımcı metot
    @SuppressWarnings("unchecked")
    private List<String> getUserAnswersList(Quiz quiz) throws Exception {
        Field uaField = Quiz.class.getDeclaredField("userAnswers");
        uaField.setAccessible(true);
        return (List<String>) uaField.get(quiz);
    }

    @Test
    void testCalculateScore_allCorrect() throws Exception {
        // 1) Test verilerini hazırla
        Student student = new Student("Test Öğrencisi");

        List<String> opts1 = Arrays.asList("A", "B", "C", "D");
        Question q1 = new MultipleChoiceQuestion("Soru1", 1, opts1, 0); // doğru: A

        Question q2 = new TrueFalseQuestion("Soru2", 2, true); // doğru: evet

        List<Question> questions = Arrays.asList(q1, q2);

        // Quiz oluştur (shuffle = false, userAnswers parametresi kullanılmıyor zaten)
        Quiz quiz = new Quiz(questions, student, false, new ArrayList<String>());

        // 2) Kullanıcının doğru cevaplarını userAnswers listesine ekle
        List<String> userAnswers = getUserAnswersList(quiz);
        userAnswers.add("A");     // q1 için doğru cevap
        userAnswers.add("evet");  // q2 için doğru cevap

        // 3) Puanı hesapla
        int score = quiz.calculateScore();

        // 4) Beklenen skor:
        // q1: difficulty 1 -> 10 * 1 = 10
        // q2: difficulty 2 -> 10 * 2 = 20
        // Toplam = 30
        assertEquals(30, score);
        assertEquals(30, student.getScore());
    }

    @Test
    void testCalculateScore_allWrong() throws Exception {
        Student student = new Student("Test Öğrencisi");

        List<String> opts1 = Arrays.asList("A", "B", "C", "D");
        Question q1 = new MultipleChoiceQuestion("Soru1", 1, opts1, 0); // doğru: A

        Question q2 = new TrueFalseQuestion("Soru2", 2, true); // doğru: evet

        List<Question> questions = Arrays.asList(q1, q2);

        Quiz quiz = new Quiz(questions, student, false, new ArrayList<String>());

        // Yanlış cevapları ekle
        List<String> userAnswers = getUserAnswersList(quiz);
        userAnswers.add("B");      // yanlış
        userAnswers.add("hayır");  // yanlış

        int score = quiz.calculateScore();

        assertEquals(0, score);
        assertEquals(0, student.getScore());
    }

    @Test
    void testShowResult_printsCorrectMessage() throws Exception {
        Student student = new Student("Test Öğrencisi");

        List<String> opts1 = Arrays.asList("A", "B", "C", "D");
        Question q1 = new MultipleChoiceQuestion("Soru1", 1, opts1, 0);
        List<Question> questions = Arrays.asList(q1);

        Quiz quiz = new Quiz(questions, student, false, new ArrayList<String>());

        // Doğru cevap ekleyelim
        List<String> userAnswers = getUserAnswersList(quiz);
        userAnswers.add("A");
        quiz.calculateScore(); // puanı hesapla (10 puan)

        // System.out çıktısını yakala
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream oldOut = System.out;
        System.setOut(new java.io.PrintStream(baos));

        quiz.showResult();

        System.setOut(oldOut); // System.out'u geri al

        String output = baos.toString();
        assertTrue(output.contains("Test Öğrencisi"));
        assertTrue(output.contains("10")); // 10 puan yazmasını bekliyoruz
    }
}

