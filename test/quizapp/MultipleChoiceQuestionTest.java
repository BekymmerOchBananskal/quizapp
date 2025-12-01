package quizapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class MultipleChoiceQuestionTest {

    private MultipleChoiceQuestion createSampleQuestion() {
        // Doğru cevap index 1 olsun (yani "B")
        return new MultipleChoiceQuestion(
                "2 + 2 kaçtır?",
                1, // difficulty
                Arrays.asList("3", "4", "5", "6"),
                1  // correctIndex -> "4"
        );
    }

    @Test
    public void correctAnswerShouldReturnTrue() {
        MultipleChoiceQuestion q = createSampleQuestion();

        boolean result = q.checkAnswer("B");

        assertTrue(result);
    }

    @Test
    public void wrongAnswerShouldReturnFalse() {
        MultipleChoiceQuestion q = createSampleQuestion();

        boolean result = q.checkAnswer("A");

        assertFalse(result);
    }

    @Test
    public void lowercaseAnswerShouldStillWork() {
        MultipleChoiceQuestion q = createSampleQuestion();

        boolean result = q.checkAnswer("b");  // küçük harf

        assertTrue(result);
    }

    @Test
    public void invalidLetterShouldReturnFalse() {
        MultipleChoiceQuestion q = createSampleQuestion();

        boolean result = q.checkAnswer("X");  // A–D dışında

        assertFalse(result);
    }

    @Test
    public void nullAnswerShouldReturnFalse() {
        MultipleChoiceQuestion q = createSampleQuestion();

        boolean result = q.checkAnswer(null);

        assertFalse(result);
    }

    @Test
    public void questionTextAndDifficultyShouldBeSetCorrectly() {
        MultipleChoiceQuestion q = createSampleQuestion();

        assertEquals("2 + 2 kaçtır?", q.getText());
        assertEquals(1, q.getDifficulty());
    }
}
