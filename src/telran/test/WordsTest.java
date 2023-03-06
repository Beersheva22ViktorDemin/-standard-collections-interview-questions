package telran.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Words;
import telran.util.WordsTreeImpl;


class WordsTest {

	String words[]= {"abcdef","ab123","aaa","ab","ablmn","abbbb",
			"a", "ABd","bbb", "B12", "*/"};
	String wordsStartB[] = {"B12", "bbb"};
	String wordsStartAB[] = {"ab","ab123","abbbb","abcdef","ABd","ablmn"};
	String wordsStartABC[] = {"abcdef"};
	String wordsStartAsteric[] = {"*/"};
	Words elasticSearch;
		@BeforeEach
		void setUp() throws Exception {
			elasticSearch = new WordsTreeImpl();
			for(String word: words) {
				elasticSearch.addWord(word);
			}


		}

		@Test
		void test() {
			assertArrayEquals(wordsStartABC,
					elasticSearch.getWordsByPrefix("abc").toArray(String[]::new));
			assertArrayEquals(wordsStartB, elasticSearch.getWordsByPrefix("B").toArray(String[]::new));
			assertArrayEquals(wordsStartAB, elasticSearch.getWordsByPrefix("ab").toArray(String[]::new));
			assertArrayEquals(wordsStartAsteric, elasticSearch.getWordsByPrefix("*").toArray(String[]::new));

		}


}