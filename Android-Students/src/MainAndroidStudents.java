import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.org.mozilla.javascript.tools.idswitch.FileBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;


/**
 * Classe de l'application android élèves
 * @author pact33
 *
 */
public class MainAndroidStudents {
	private int masID;
	static final Logger logger = LogManager.getLogger(MainAndroidStudents.class);
	private HashSet<String> dictionnary = new HashSet<String>();
	private final static String DICTIONNARY_PATH = "data/dictionnary.txt";
	
	public static void main(String[] args) {	
		logger.trace("Module Application Android Eleves");		
	}
	
	/**
	 * Constructor of MainAndroidStudents
	 * @param id id of the Student
	 */
	public MainAndroidStudents(int id) {
		this.masID = id;
		this.initDictionnary();
	}
	
	
	/** Initializes dictionnary with content in the 
	 * file contained in the variable DICTIONNARY_PATH
	 */
	public void initDictionnary() {
		String line;
		InputStream fis;
		try {
			fis = new FileInputStream(DICTIONNARY_PATH);
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
		        dictionnary.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * True if the word is in the dictionnary, false if not
	 * @param word the word to be checked
	 * @return True if the word is in the dictionnary, false if not
	 */
	public boolean isCorrect(String word) {
		return this.dictionnary.contains(word);
	}
	
	/* *********** CODE A CORRIGER *********** */
	public Set<String> closestWords(String word) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (String entry : this.dictionnary) {
			int distance = MainAndroidStudents.levenshteinDistance(word, word.length(),
					entry, entry.length());
			if (hm.size() < 10) {
				hm.put(entry, distance);
			}
			else {
				String maxString = null;
				int distanceToRemove = -1;
				for (String elem : hm.keySet()) {
					if (hm.get(elem) > distanceToRemove) {
						distanceToRemove = hm.get(elem);
						maxString = elem;
					}
				}
				if (distanceToRemove > distance) {
					hm.remove(maxString);
					hm.put(entry, distance);
				}
			}
		}
		return hm.keySet();
	}
	
	public static int levenshteinDistance(String s1, int s1Length, String s2, int s2Length) {
		int cost;
		
		if (s1Length == 0) return s2Length;
		if (s2Length == 0) return s1Length;
		
		if (s1.charAt(s1Length - 1) == s2.charAt(s2Length - 1))
			cost = 0;
		else
			cost  = 1;
		
		return Math.min(levenshteinDistance(s1, s1Length - 1, s2, s2Length) + 1,
				Math.min(levenshteinDistance(s1, s1Length - 1, s2, s2Length - 1) + 1,
				levenshteinDistance(s1, s1Length - 1, s2, s2Length - 1)) + cost);
	}
	
}

