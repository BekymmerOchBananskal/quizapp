package quizapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Bir quiz oturumunu yöneten sınıf.
 *
 * Soruları gösterir, kullanıcının cevaplarını alır ve
 * {@link #calculateScore()} metodu ile öğrencinin puanını hesaplar.
 */
public class Quiz implements Gradable {

    private List<Question> questions;
    private Student student;
    private boolean shuffle;
    private List<String> userAnswers;

    public Quiz(List<Question> questions, Student student, boolean shuffle) {
        this.questions = questions;
        this.student = student;
        this.shuffle = shuffle;
        this.userAnswers = new ArrayList<>();
        

        if (shuffle) {
            Collections.shuffle(this.questions);
        }
    }

    /**
     * Quiz oturumunu başlatır 
     * Süre kontrolü her sorudan önce yapılır.
     */
    public void start() {
        Scanner input = new Scanner(System.in);

        System.out.println(student.getName() + " hazırsan ENTER’a bas ve quiz başlasın!");
        input.nextLine();
        System.out.println("Quiz başlıyor!\n");

        // 60 saniye
        long timeLimitMillis = 60000;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeLimitMillis;

        int number = 1;

        for (Question q : questions) {

            long remainingSeconds =
                    (endTime - System.currentTimeMillis()) / 1000;

            if (remainingSeconds <= 0) {
                System.out.println("\nSÜRE DOLDU! Quiz sona erdi.");
                
                break;
            }

            System.out.println("Kalan süre: " + remainingSeconds + " saniye");
            System.out.println(number + ". Soru: " + q.getText());

            if (q instanceof MultipleChoiceQuestion mcq) {
                List<String> opts = mcq.getOptions();
                char optionChar = 'A';
                for (String opt : opts) {
                    System.out.println(optionChar + ") " + opt);
                    optionChar++;
                }
            }

            if (q instanceof MultipleChoiceQuestion) {
                System.out.print("Cevabınız (Şık): ");
            } else {
                System.out.print("Cevabınız (Evet/Hayır): ");
            }

            String answer = input.nextLine();
            userAnswers.add(answer.trim());

            System.out.println("**************************************************************");
            number++;
        }
    }

    /**
     * Quiz sonuçlarını hesaplar.
     */
    @Override
    public int calculateScore() {
        int score = 0;
        int correct = 0;
        int wrong = 0;

        int n = Math.min(questions.size(), userAnswers.size());

        for (int i = 0; i < n; i++) {
            Question q = questions.get(i);
            String answer = userAnswers.get(i);

            if (q.checkAnswer(answer)) {
                score += 10 * q.getDifficulty();
                correct++;
            } else {
                wrong++;
            }
        }

        student.setScore(score);
        student.setCorrectCount(correct);
        student.setWrongCount(wrong);
        return score;
    }

    /**
     * Sonuçları yazdırır.
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
    public void saveResultToFile() {
    	String fileName="quizResult.txt";
    	DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = now.format(formatter);
    	try (FileWriter writer = new FileWriter(fileName, true)) {

            writer.write("******************** Quiz Sonucu ********************\n");
            writer.write("Tarih / Saat: " + dateTime + "\n");
            writer.write("Öğrenci Adı: " + student.getName() + "\n");
            writer.write("Doğru: " + student.getCorrectCount() + "\n");
            writer.write("Yanlış: " + student.getWrongCount() + "\n");
            writer.write("Quiz Puanı: " + student.getScore() + "\n");
            writer.write("*****************************************************\n\n");

        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }
			
    	
    	
    
}
