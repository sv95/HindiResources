package common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HindiChars {

    // enum to represent char type
    public enum HindiCharacterType {
        VOWEL, CONSONANT, MATRA, VISARG, HALANT
    }

    public static final char HALANT = '्';
    public final static List<Character> vowels = Arrays.asList(
            'अ', 'आ', 'इ', 'ई', 'उ', 'ऊ', 'ऋ', 'ए', 'ऐ', 'ओ', 'औ'
    );
    public final static List<String> vowelsWithAnusvarAndVisarg = Arrays.asList(
            "अ", "आ", "इ", "ई", "उ", "ऊ", "ऋ", "ए", "ऐ", "ओ", "औ", "अं", "अः"
    );
    public final static List<Character> consonants = Arrays.asList(
            'क', 'ख', 'ग', 'घ', 'ङ',
            'च', 'छ', 'ज', 'झ', 'ञ',
            'ट', 'ठ', 'ड', 'ढ', 'ण',
            'त', 'थ', 'द', 'ध', 'न',
            'प', 'फ', 'ब', 'भ', 'म',
            'य', 'र', 'ल', 'व',
            'श', 'ष', 'स', 'ह'
    );
    public final static List<String> consonantsPlusCompoundConsonants = Arrays.asList(
            "क", "ख", "ग", "घ", "ङ",
            "च", "छ", "ज", "झ", "ञ",
            "ट", "ठ", "ड", "ढ", "ण",
            "त", "थ", "द", "ध", "न",
            "प", "फ", "ब", "भ", "म",
            "य", "र", "ल", "व",
            "श", "ष", "स", "ह",
            "क्ष", "त्र", "ज्ञ"
    );
    public final static List<Character> matras = Arrays.asList(
            'ा', 'ि', 'ी', 'ु', 'ू', 'ृ',
            'े', 'ै', 'ो', 'ौ', 'ं', 'ः'
    );

    // Map from HindiCharType enum values to the corresponding list of characters
    public final static Map<HindiCharacterType, List<Character>> charTypeToChars = Map.of(
            HindiCharacterType.VOWEL, vowels,
            HindiCharacterType.CONSONANT, consonants,
            HindiCharacterType.MATRA, matras,
            HindiCharacterType.VISARG, List.of('ः'),
            HindiCharacterType.HALANT, List.of('्')
    );

    public final static Map<HindiCharacterType, List<String>> charTypeToStrings = Map.of(
            HindiCharacterType.VOWEL, vowelsWithAnusvarAndVisarg,
            HindiCharacterType.CONSONANT, consonantsPlusCompoundConsonants
    );
}
