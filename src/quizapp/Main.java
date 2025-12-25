package quizapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            int choice = -1;

            // Menü seçimi güvenli şekilde alınır
            while (true) {
                System.out.println("********************************MENÜ********************************");
                System.out.print(
                        "1-)Quiz Başlat('1' e bas)\n" +
                        "2-)Quiz Sonuçları('2' ye bas)\n" +
                        "3-)Programdan Çıkış('3' e bas)\n" +
                        "********************************************************************\n"
                );

                System.out.print("Seçiminiz: ");

                if (!input.hasNextInt()) {
                    System.out.println("Lütfen sadece sayı giriniz!");
                    input.nextLine(); // hatalı girdiyi temizle
                    continue;
                }

                choice = input.nextInt();
                input.nextLine(); // enter temizle

                if (choice < 1 || choice > 3) {
                    System.out.println("Geçersiz seçim! 1 ile 3 arasında bir değer giriniz.");
                    continue;
                }

                break; // geçerli seçim
            }

            if (choice == 1) {

                // İsim boş girilirse tekrar sor
                System.out.print("İsminizi giriniz: ");
                String name = input.nextLine();

                while (name.trim().isEmpty()) {
                    System.out.print("Lütfen geçerli bir isim giriniz: ");
                    name = input.nextLine();
                }

                Student student = new Student(name);

                // Soruları dosyadan oku
                List<Question> questions = loadQuestionsFromFile("questions.txt");

                if (questions.isEmpty()) {
                    System.out.println("Soru bulunamadı! questions.txt boş olabilir veya format hatalı olabilir.");
                    continue;
                }

                Quiz quiz = new Quiz(questions, student, true);

                quiz.start(input);
                quiz.calculateScore();
                quiz.showResult();
                quiz.saveResultToFile();

            } else if (choice == 2) {

                Quiz.readResultsFromFile();

            } else if (choice == 3) {

                System.out.print("Çıkış Yapıldı...");
                break;
            }
        }

        input.close();
    }

    /**
     * questions.txt dosyasından soruları okur.
     * Format:
     * MC;soruMetni;zorluk;A;B;C;D;dogruIndex
     * TF;soruMetni;zorluk;true/false
     */
    private static List<Question> loadQuestionsFromFile(String fileName) {

        List<Question> questions = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName), "UTF-8")) {

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine().trim();

                // boş satır / yorum satırı
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split(";");
                String type = parts[0].trim();

                // MC;soruMetni;zorluk;A;B;C;D;dogruIndex
                if (type.equalsIgnoreCase("MC")) {

                    if (parts.length != 8) {
                        System.out.println("MC format hatası (8 parça olmalı): " + line);
                        continue;
                    }

                    String text = parts[1].trim();
                    int difficulty = Integer.parseInt(parts[2].trim());

                    List<String> options = List.of(
                            parts[3].trim(),
                            parts[4].trim(),
                            parts[5].trim(),
                            parts[6].trim()
                    );

                    int correctIndex = Integer.parseInt(parts[7].trim());

                    questions.add(new MultipleChoiceQuestion(text, difficulty, options, correctIndex));
                }

                // TF;soruMetni;zorluk;true/false
                else if (type.equalsIgnoreCase("TF")) {

                    if (parts.length != 4) {
                        System.out.println("TF format hatası (4 parça olmalı): " + line);
                        continue;
                    }

                    String text = parts[1].trim();
                    int difficulty = Integer.parseInt(parts[2].trim());
                    boolean correctAnswer = Boolean.parseBoolean(parts[3].trim());

                    questions.add(new TrueFalseQuestion(text, difficulty, correctAnswer));
                }

                else {
                    System.out.println("Bilinmeyen soru tipi: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + fileName);
        } catch (Exception e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }

        return questions;
    }
}
