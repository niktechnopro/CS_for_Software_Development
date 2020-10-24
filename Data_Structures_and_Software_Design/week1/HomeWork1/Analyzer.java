import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		/* IMPLEMENT THIS METHOD! */
		List<Sentence> sentences = new ArrayList<>(); 
		try {
//			BufferedReader reviews = new BufferedReader(new FileReader(filename));
			File reviews = new File(filename);
	        Scanner scnr = new Scanner(reviews);
	        while(scnr.hasNextLine()){
	            String line = scnr.nextLine();
	            //split line on score and text
	            int score = Integer.parseInt(line.substring(0, 2).trim());//converting to int
	            String text = line.substring(2).trim();
//	            System.out.println("Score: " + score + " text: " + text);
	            if(score >= -2 && score <= 2) {
	            	sentences.add(new Sentence(score, text));
	            }
	        }
	        scnr.close();//close scanner
		}catch(Exception ex) {
			System.out.println("Ooops: " + ex.getMessage());
		}
		return sentences; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
//		System.out.println(sentences);
		/* IMPLEMENT THIS METHOD! */
		Set<Word> words = new HashSet<>();
		List<Word> wordsList = new ArrayList<>();	//use list to manipulate information
		
		if (sentences == null || sentences.isEmpty()) {//if the list is empty, method returns an empty set.
		    return words;	
		}
		
		for(Sentence sentence : sentences) {
//		    System.out.println("Sentence examined " + sentence.getText());
		    if (sentence != null) {
		    	String[] tokens = sentence.getText().toLowerCase().split("[\\p{Punct}\\s]+");//regex to split line by punctuation and white space
		    	for (String token : tokens) {
//		    		System.out.println("token: " + token);
		    		if(token.matches("[a-zA-Z0-9]+")) {
		    			Word word = new Word(token);
//		    			int index = wordsList.indexOf(word);//if the word doesn't exist it'll show as -1 
		    			if (wordsList.contains(word)) {//word is already in the list
//		    				System.out.println("already in the list: " + word.getText());
//		    				System.out.println("This word exists " + token + ". Score increased by " + sentence.getScore());
		    				wordsList.get(wordsList.indexOf(word)).increaseTotal(sentence.getScore());
		    			} else {//new word	
		    				word.increaseTotal(sentence.getScore());
		    				wordsList.add(word);
////				    	System.out.println(token + " added for the score of " + sentence.getScore());
		    			}
		    		}
		    	}
		    }
		}
		
		words = new HashSet<Word> (wordsList);
		
		//test - for the same text - object is the same
//		ArrayList<String> e = new ArrayList<>();
//		String ex1 = "test1";
//		String ex2 = "test1";
//		String ex3 = "test1";
//		e.add(ex1);
//		e.add(ex2);
//		e.add(ex3);
//		for (String f : e) {
//			Word word = new Word(f);
//			System.out.println(word);
//		}
		//end of test
		return words;
	}
		
		
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
//		System.out.println(words);
		/* IMPLEMENT THIS METHOD! */
		Map<String, Double> myMap = new HashMap<>();
		if(words == null || words.isEmpty()) return myMap;
		for(Word w : words) {
//			System.out.println(w);
			myMap.put(w.getText(), w.calculateScore());
		}
		return myMap; // this line is here only so this code will compile if you don't modify it
	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		/* IMPLEMENT THIS METHOD! */
		System.out.println("Sentence: " + sentence + wordScores);
		if(wordScores == null || wordScores.isEmpty() || sentence == null || sentence.length() < 1 ) return 0;
		sentence.toLowerCase();//converts all words to lower case
		String[] words = sentence.split("\\s+");//split on empty spaces
		int howManyWords = words.length;
		double sc = 0.0;
		for(String word : words) {
			if(!word.trim().equals("") && word.trim().matches("^[a-zA-Z]*$")) {//regex ensures that word starts with letter only
				if(wordScores.containsKey(word.trim())) {
//					System.out.println("for key: " + word.trim() + wordScores.get(word.trim()));
					sc += wordScores.get(word.trim());
				}else {
//					System.out.println("no such key");
					sc += 0.0;
				}
			}
		}
		
		return sc/howManyWords; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		System.out.println("enetered sentence: " + sentence);
//		List<Sentence> sentences = Analyzer.readFile(filename);
//		Set<Word> words = Analyzer.allWords(sentences);
//		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		List<Sentence> sentences = Analyzer.readFile("reviews");//step 1
		Set<Word> words = Analyzer.allWords(sentences);//step 2
		Map<String, Double> wordScores = Analyzer.calculateScores(words);//step 3 
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
