package telran.util;

import java.util.*;

public class WordsImpl implements Words {

	private Set<String> words = new HashSet<>();
	private Map<String, List<String>> prefixMap = new HashMap<>();

	@Override
	public boolean addWord(String word) {
		String lowerCaseWord = word.toLowerCase();
		if (words.contains(lowerCaseWord)) {
			return false;
		}
		words.add(lowerCaseWord);

		for (int i = 1; i <= lowerCaseWord.length(); i++) {
			String prefix = lowerCaseWord.substring(0, i);
			List<String> wordsWithPrefix = prefixMap.getOrDefault(prefix, new ArrayList<>());
			wordsWithPrefix.add(lowerCaseWord);
			prefixMap.put(prefix, wordsWithPrefix);
		}

		return true;
	}

	@Override
	public List<String> getWordsByPrefix(String prefix) {
		String lowerCasePrefix = prefix.toLowerCase();
		return prefixMap.getOrDefault(lowerCasePrefix, new ArrayList<>());
	}

}
