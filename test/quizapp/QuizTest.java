package quizapp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizTest {

    // -------------------------------------------------------------
    // 1) TÜM CEVAPLAR DOĞRU → Skor 30 olmalı
    // -------------------------------------------------------------
    @Test
    void testCalculateScore_allCorrect() {

        Student student = new Student("Test");

        List<String> opts = Arrays.asList("A", "B", "C", "D");
        Question q1 = new MultipleChoiceQuestion("S1", 1, opts, 0);   // doğru: A
        Question q2 = new TrueFalseQuestion("S2", 2, true);           // doğru: evet
        List<Question> questions = Arrays.asList(q1, q2);

        Quiz quiz = new Quiz(questions, student, false);

        // Sahte kullanıcı girişleri:
        // ENTER → A → evet
        String fakeInput = "\nA\nevet\n";

        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

            quiz.start();                // Cevapları userAnswers'a doldurur
            int score = quiz.calculateScore();

            assertEquals(30, score);
            assertEquals(30, student.getScore());

        } finally {
            System.setIn(originalIn);
        }
    }

    // -------------------------------------------------------------
    // 2) TÜM CEVAPLAR YANLIŞ → Skor 0 olmalı
    // -------------------------------------------------------------
    @Test
    void testCalculateScore_allWrong() {

        Student student = new Student("Test");

        List<String> opts = Arrays.asList("A", "B", "C", "D");
        Question q1 = new MultipleChoiceQuestion("S1", 1, opts, 0);   // doğru: A
        Question q2 = new TrueFalseQuestion("S2", 2, true);           // doğru: evet
        List<Question> questions = Arrays.asList(q1, q2);

        Quiz quiz = new Quiz(questions, student, false);

        // Sahte giriş:
        // ENTER → B → hayır
        String fakeInput = "\nB\nhayır\n";

        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

            quiz.start();
            int score = quiz.calculateScore();

            assertEquals(0, score);
            assertEquals(0, student.getScore());

        } finally {
            System.setIn(originalIn);
        }
    }

    // -------------------------------------------------------------
    // 3) showResult çıktısı doğru mu?
    // -------------------------------------------------------------
    @Test
    void testShowResult_printsCorrectMessage() {

        Student student = new Student("Test");

        List<String> opts = Arrays.asList("A", "B", "C", "D");
        Question q1 = new MultipleChoiceQuestion("S1", 1, opts, 0);
        Question q2 = new TrueFalseQuestion("S2", 2, true);
        List<Question> questions = Arrays.asList(q1, q2);

        Quiz quiz = new Quiz(questions, student, false);

        // Doğru cevaplar → skor 30
        String fakeInput = "\nA\nevet\n";

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));
            System.setOut(new PrintStream(baos));

            quiz.start();
            quiz.calculateScore();
            quiz.showResult();

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String output = baos.toString();

        assertTrue(output.contains("Test"));
        assertTrue(output.contains("30"));
    }
}
