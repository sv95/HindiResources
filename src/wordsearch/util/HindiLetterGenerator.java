package wordsearch.util;

import java.util.Random;

import static common.HindiChars.*;

public class HindiLetterGenerator {

    private final static double PROB_OF_VOWEL = 0.2;
    private final static double PROB_OF_HALF_LETTER = 0.1;
    private final static double PROB_OF_MATRA = 0.7;

    private static final int numVowels = vowels.size();
    private static final int numConsonants = consonants.size();
    private static final int numMatras = matras.size();

    private static final Random rng = new Random();

    public static String generate() {
        // Check if we're generating a vowel
        // If so, we don't need to bother with half letters and matras
        if (rng.nextDouble() < PROB_OF_VOWEL) {
            return String.valueOf(vowels.get(rng.nextInt(numVowels)));
        } else {
            String halfLetter = "";
            char matra = 0;
            if (rng.nextDouble() < PROB_OF_HALF_LETTER) {
                halfLetter = "" + consonants.get(rng.nextInt(numConsonants)) + HALANT;
            }
            char fullLetter = consonants.get(rng.nextInt(numConsonants));
            if (rng.nextDouble() < PROB_OF_MATRA) {
                matra = matras.get(rng.nextInt(numMatras));
            }
            return "" + halfLetter + fullLetter + matra;
        }

/*        // Generate half letter if needed
        rng.nextDouble();



        HindiWord hindiLoremIpsum = new HindiWord("क");
        try {
            Scanner s = new Scanner(new File("resources/hindi_lorem_ipsum.txt"));
            while (s.hasNext()) {
                hindiLoremIpsum = new HindiWord(s.next());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find Hindi Lorem Ipsum so defaulting to \"क\".");
        }
        return hindiLoremIpsum.charAt(rng.nextInt(hindiLoremIpsum.length()));
 */
    }
}
