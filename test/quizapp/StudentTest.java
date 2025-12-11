package quizapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class StudentTest {

    @Test
    public void studentNameShouldBeSetCorrectly() {
        Student student = new Student("Atılım");
        assertEquals("Atılım", student.getName(), "Öğrenci adı doğru atanmadı.");
    }

    @Test
    public void defaultScoreShouldBeZero() {
        Student student = new Student("Atılım");
        assertEquals(0, student.getScore(), "Varsayılan puan 0 olmalı.");
    }

    @Test
    public void setScoreShouldUpdateTheValue() {
        Student student = new Student("Atılım");
        student.setScore(85);
        assertEquals(85, student.getScore(), "Puan güncellemesi doğru yapılmadı.");
    }

    @Test
    public void scoreShouldChangeWhenUpdatedAgain() {
        Student student = new Student("Atılım");
        student.setScore(50);
        student.setScore(95);
        assertEquals(95, student.getScore(), "Puan ikinci güncellemeden sonra doğru değil.");
    }
}

