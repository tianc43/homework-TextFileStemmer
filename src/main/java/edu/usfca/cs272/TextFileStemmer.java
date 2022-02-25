package edu.usfca.cs272;

import static java.nio.charset.StandardCharsets.UTF_8;
import static opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM.ENGLISH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.usfca.cs272.TextParser;

import opennlp.tools.stemmer.Stemmer;
import opennlp.tools.stemmer.snowball.SnowballStemmer;
import opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM;

/**
 * Utility class for parsing and stemming text and text files into collections
 * of stemmed words.
 *
 * @see TextParser
 *
 * @author CS 272 Software Development (University of San Francisco)
 * @version Spring 2022
 */
public class TextFileStemmer {
	/**
	 * Parses each line into cleaned and stemmed words.
	 *
	 * @param line the line of words to clean, split, and stem
	 * @param stemmer the stemmer to use
	 * @return a list of cleaned and stemmed words in parsed order
	 *
	 * @see Stemmer#stem(CharSequence)
	 * @see TextParser#parse(String)
	 */
	public static List<String> listStems(String line, Stemmer stemmer) {
		// TODO Fill in implementation
		try {
			List<String> list = new LinkedList<String>();
			String[] words = TextParser.parse(line); //clean+split
			for (String word : words) {
				list.add(stemmer.stem(word).toString());
			}
			return list;
		}catch (Exception e) {
			throw new UnsupportedOperationException("Not yet implemented.");
		}
	}

	/**
	 * Parses each line into cleaned and stemmed words using the default stemmer.
	 *
	 * @param line the line of words to parse and stem
	 * @return a list of cleaned and stemmed words in parsed order
	 *
	 * @see SnowballStemmer
	 * @see ALGORITHM#ENGLISH
	 * @see #listStems(String, Stemmer)
	 */
	public static List<String> listStems(String line) {
		return listStems(line, new SnowballStemmer(ENGLISH));
	}

	/**
	 * Reads a file line by line, parses each line into cleaned and stemmed words
	 * using the default stemmer.
	 *
	 * @param input the input file to parse and stem
	 * @return a list of stems from file in parsed order
	 * @throws IOException if unable to read or parse file
	 *
	 * @see SnowballStemmer
	 * @see ALGORITHM#ENGLISH
	 * @see StandardCharsets#UTF_8
	 * @see #listStems(String, Stemmer)
	 */
	public static List<String> listStems(Path input) throws IOException {
		// TODO Fill in implementation
		List<String> list = new LinkedList<String>();
		String line = "";
		try(Scanner in = new Scanner(input, "UTF-8")){
			while (in.hasNextLine()) {
				line = in.nextLine();
				list.addAll(TextFileStemmer.listStems(line));
			}
			return list;
		}catch (IOException e) {
			throw new IOException("IO error.");
		}catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}catch (Exception e) {
			throw new UnsupportedOperationException("Not yet implemented.");
		}
	}
	/**
	 * Parses the line into unique, sorted, cleaned, and stemmed words.
	 *
	 * @param line the line of words to parse and stem
	 * @param stemmer the stemmer to use
	 * @return a sorted set of unique cleaned and stemmed words
	 *
	 * @see Stemmer#stem(CharSequence)
	 * @see TextParser#parse(String)
	 */
	public static Set<String> uniqueStems(String line, Stemmer stemmer) {
		// TODO Fill in implementation
		try {
			Set<String> set = new TreeSet<String>();
			List<String> list = TextFileStemmer.listStems(line, stemmer);
			for (String word : list) {
				set.add(word);
			}
			return set;
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not yet implemented.");
		}
	}

	/**
	 * Parses the line into unique, sorted, cleaned, and stemmed words using the
	 * default stemmer.
	 *
	 * @param line the line of words to parse and stem
	 * @return a sorted set of unique cleaned and stemmed words
	 *
	 * @see SnowballStemmer
	 * @see ALGORITHM#ENGLISH
	 * @see #uniqueStems(String, Stemmer)
	 */
	public static Set<String> uniqueStems(String line) {
		return uniqueStems(line, new SnowballStemmer(ENGLISH));
	}

	/**
	 * Reads a file line by line, parses each line into unique, sorted, cleaned,
	 * and stemmed words using the default stemmer.
	 *
	 * @param input the input file to parse and stem
	 * @return a sorted set of unique cleaned and stemmed words from file
	 * @throws IOException if unable to read or parse file
	 *
	 * @see SnowballStemmer
	 * @see ALGORITHM#ENGLISH
	 * @see StandardCharsets#UTF_8
	 * @see #uniqueStems(String, Stemmer)
	 */
	
	public static Set<String> uniqueStems(Path input) throws IOException {
		// TODO Fill in implementation
		Set<String> set = new TreeSet<String>();
		String line = "";
		try(Scanner in = new Scanner(input, "UTF-8")){
			while (in.hasNextLine()) {
				line = in.nextLine();
				set.addAll(TextFileStemmer.uniqueStems(line));
			}
			return set;
		}catch (IOException e) {
			throw new IOException("IO error.");
		}catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}catch (Exception e) {
			throw new UnsupportedOperationException("Not yet implemented.");
		}
	}

	/**
	 * Reads a file line by line, parses each line into unique, sorted, cleaned,
	 * and stemmed words using the default stemmer, and adds the set of unique
	 * sorted stems to a list per line in the file.
	 *
	 * @param input the input file to parse and stem
	 * @return a list where each item is the sets of unique sorted stems parsed
	 *   from a single line of the input file
	 * @throws IOException if unable to read or parse file
	 *
	 * @see SnowballStemmer
	 * @see ALGORITHM#ENGLISH
	 * @see StandardCharsets#UTF_8
	 * @see #uniqueStems(String, Stemmer)
	 */

	public static List<Set<String>> listUniqueStems(Path input) throws IOException {
		// TODO Fill in implementation
		List<Set<String>> list = new LinkedList<Set<String>>();
		String line = "";
		try(Scanner in = new Scanner(input, "UTF-8")){
			while (in.hasNextLine()) {
				TreeSet<String> set = new TreeSet<String>();
				line = in.nextLine();
				set.addAll(TextFileStemmer.uniqueStems(line));
				list.add(set);
			}
			return list;
		}catch (IOException e) {
			throw new IOException("IO error.");
		}catch (NullPointerException npe) {
			throw new NullPointerException(npe.getMessage());
		}catch (Exception e) {
			throw new UnsupportedOperationException("Not yet implemented.");
		}
	}

	/**
	 * Demonstrates this class.
	 *
	 * @param args unused
	 * @throws IOException if an I/O error occurs
	 */
	public static void main(String[] args) throws IOException {
		// demonstrates how to use stemmer
		Stemmer stemmer = new SnowballStemmer(ENGLISH);
		String demo = "practise";
		String stem = stemmer.stem(demo).toString();
		System.out.println("____STEMMER DEMO____");
		System.out.println("Word: " + demo + ", Stem: " + stem);
		System.out.println();

		// demonstrates how to use list/uniqueStems methods
		String text = """
				practic practical practice practiced practicer practices
				practicing practis practisants practise practised practiser
				practisers practises practising practitioner practitioners
				""";

		System.out.println("____STEMMING TEXT____");
		System.out.println("Original: \n" + text);
		System.out.println("  List: " + listStems(text));
		System.out.println("Unique: " + uniqueStems(text));
		System.out.println();

		Path base = Path.of("src", "test", "resources", "stemmer");
		Path file = base.resolve("input.txt");
		String input = Files.readString(base.resolve("symbols.txt"), UTF_8);

		System.out.println("____STEMMING FILE____");
		System.out.println("Original:\n" + input);

		System.out.println("       List: " + listStems(file));
		System.out.println("     Unique: " + uniqueStems(file));
		System.out.println("List Unique: " + listUniqueStems(file));
	}
}
