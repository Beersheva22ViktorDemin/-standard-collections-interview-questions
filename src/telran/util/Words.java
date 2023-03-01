package telran.util;

import java.util.List;

public interface Words {
	/**
	 * adds word
	 * 
	 * @param word
	 * @return true if added, otherwise false if a word already exists (case
	 *         insensitive) words should be kept on source lower/upper case If the
	 *         word “Table” is added, the word “Table” should be kept but the word
	 *         “table” cannot be added
	 *
	 */
	public boolean addWord(String word);

	/**
	 * @param prefix
	 * @return list of words starting from a given prefix (case insensitive)
	 */
	public List<String> getWordsByPrefix(String prefix);
}