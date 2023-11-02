package wordsearch.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HindiLetterGenerator {

    private final static double PROB_OF_VOWEL = 0.2;
    private final static double PROB_OF_HALF_LETTER = 0.1;
    private final static double PROB_OF_MATRA = 0.7;

    private static final char HALANT = '्';
    private final static List<Character> vowels = Arrays.asList(
        'अ', 'आ', 'इ', 'ई', 'उ', 'ऊ', 'ऋ', 'ए', 'ऐ', 'ओ', 'औ'
    );
    private final static List<Character> consonants = Arrays.asList(
        'क', 'ख', 'ग', 'घ', 'ङ',
        'च', 'छ', 'ज', 'झ', 'ञ',
        'ट', 'ठ', 'ड', 'ढ', 'ण',
        'त', 'थ', 'द', 'ध', 'न',
        'प', 'फ', 'ब', 'भ', 'म',
        'य', 'र', 'ल', 'व', 'श', 'ष', 'स', 'ह'
    );
    private final static List<Character> matrasAndVisarg = Arrays.asList(
        'ा', 'ि', 'ी', 'ु', 'ू', 'ृ',
        'े', 'ै', 'ो', 'ौ', 'ः'
    );

    private static final int numVowels = vowels.size();
    private static final int numConsonants = consonants.size();
    private static final int numMatras = matrasAndVisarg.size();

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
                matra = matrasAndVisarg.get(rng.nextInt(numMatras));
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
