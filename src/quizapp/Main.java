package quizapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		List<Question>questions=new ArrayList<>();
		
		System.out.print("İsminizi giriniz: ");
		String name=input.nextLine();
		
		Student student=new Student(name);
		
		List<String>options1=List.of("1995","2000","1990","1985");
		questions.add(new MultipleChoiceQuestion("Java hangi yılda çıkmıştır?",1,options1,0));
		
		questions.add(new TrueFalseQuestion("Java platform independent midir?",2,true));
        
        List<String>options2=List.of("if","for","switch","loop");
        questions.add(new MultipleChoiceQuestion("Java'da döngü yapısı hangisidir?",2,options2,1));
        
        questions.add(new TrueFalseQuestion("Python statically typed bir dil midir?",5,false));
        
        Quiz quiz=new Quiz(questions,student,true);
        
        quiz.start();
        quiz.calculateScore();
        quiz.showResult();
        quiz.saveResultToFile();
        quiz.readResultsFromFile();
		
		

	}

}
