package quizapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			Scanner input =new Scanner(System.in);
			
			while(true) {
				
			
				int choice;
				System.out.println("**********************MENÜ**********************");	
				System.out.print("1-)Quiz Başlat('1' e bas)\n2-)Quiz Sonuçları('2' ye bas)\n3-)Programdan Çıkış('3' e bas)\n");
				
				System.out.print("Seçiminiz: ");
				choice=input.nextInt();
				input.nextLine();
				
				if(choice==1) {
					List<Question>questions=new ArrayList<>();
					
					System.out.print("İsminizi giriniz: ");
					String name=input.nextLine();
					
					Student student=new Student(name);
					
					//Sorular
					List<String>options1=List.of("1995","2000","1990","1985");
					questions.add(new MultipleChoiceQuestion("Java hangi yılda çıkmıştır?",1,options1,0));
					
					questions.add(new TrueFalseQuestion("Java platform independent midir?",2,true));
			        
			        List<String>options2=List.of("if","for","switch","loop");
			        questions.add(new MultipleChoiceQuestion("Java'da döngü yapısı hangisidir?",2,options2,1));
			        
			        questions.add(new TrueFalseQuestion("Python statically typed bir dil midir?",5,false));
			        
			        List<String> options4 = List.of("JVM", "JDK", "JRE", "JIT");
			        questions.add(new MultipleChoiceQuestion("Java bytecode hangi yapı üzerinde çalışır?", 2, options4, 0));
	
			        List<String> options5 = List.of("int", "String", "double", "boolean");
			        questions.add(new MultipleChoiceQuestion("Hangisi primitive (ilkel) veri tipi değildir?", 2, options5, 1));
	
			        List<String> options6 = List.of("extends", "implements", "inherits", "instanceof");
			        questions.add(new MultipleChoiceQuestion("Bir sınıf interface'i hangi anahtar kelime ile uygular?", 2, options6, 1));
	
			        List<String> options7 = List.of("ArrayList", "HashMap", "HashSet", "LinkedList");
			        questions.add(new MultipleChoiceQuestion("Anahtar-değer (key-value) yapısı kullanan koleksiyon hangisidir?", 3, options7, 1));
	
			        List<String> options8 = List.of("Object", "String", "System", "Math");
			        questions.add(new MultipleChoiceQuestion("Java'da tüm sınıfların otomatik olarak türediği sınıf hangisidir?", 2, options8, 0));
	
			        List<String> options9 = List.of("public", "private", "protected", "static");
			        questions.add(new MultipleChoiceQuestion("Hangisi erişim belirleyici (access modifier) değildir?", 2, options9, 3));
	
			        List<String> options10 = List.of("try-catch", "if-else", "switch", "for");
			        questions.add(new MultipleChoiceQuestion("Exception yakalamak için kullanılan yapı hangisidir?", 2, options10, 0));
	
			        List<String> options11 = List.of("Heap", "Stack", "Method Area", "Register");
			        questions.add(new MultipleChoiceQuestion("Nesneler Java belleğinde nerede tutulur?", 3, options11, 0));
	
			        List<String> options12 = List.of("compile time", "runtime", "link time", "load time");
			        questions.add(new MultipleChoiceQuestion("Polymorphism genellikle hangi aşamada gerçekleşir?", 4, options12, 1));
			        
			        questions.add(new TrueFalseQuestion("Java platform bağımsız bir dildir.", 1, true));
	
			        questions.add(new TrueFalseQuestion("Java'da bir sınıf birden fazla sınıftan kalıtım alabilir.", 3, false));
	
			        questions.add(new TrueFalseQuestion("Interface içinde metotlar varsayılan olarak public'tir.", 2, true));
	
			        questions.add(new TrueFalseQuestion("Abstract sınıflardan nesne oluşturulabilir.", 2, false));
	
			        questions.add(new TrueFalseQuestion("Java'da String sınıfı immutable'dır.", 2, true));
	
			        questions.add(new TrueFalseQuestion("final anahtar kelimesi bir metodun override edilmesini engeller.", 3, true));
	
			        questions.add(new TrueFalseQuestion("Constructor'ların geri dönüş tipi vardır.", 2, false));
	
			        questions.add(new TrueFalseQuestion("Java'da garbage collector bellek yönetimini otomatik yapar.", 1, true));
	
			        questions.add(new TrueFalseQuestion("static metotlar nesne oluşturmadan çağrılabilir.", 1, true));
	
			        questions.add(new TrueFalseQuestion("Java'da her programda mutlaka interface kullanılmalıdır.", 1, false));
	
			        
			       Quiz quiz =new Quiz(questions,student,true);
			        
			        quiz.start(input);
			        quiz.calculateScore();
			        quiz.showResult();
			        quiz.saveResultToFile();
				}
				else if(choice==2) {
					Quiz.readResultsFromFile();
				}
				else if(choice==3) {
					System.out.print("Çıkış Yapıldı...");
					break;
				}
			}
			
		
		
		
//		
		
//        
//		
		

	}

}
