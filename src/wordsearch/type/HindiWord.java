package wordsearch.type;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HindiWord {

    private final String word;
    private final List<String> characters = new ArrayList<>();

    public HindiWord(String word) {
        this.word = word;
        Locale hindi = new Locale("hi", "IN");
        BreakIterator breaker = BreakIterator.getCharacterInstance(hindi);
        breaker.setText(word);
        int startOfChar = breaker.first();
        for (int endOfChar = breaker.next();
             endOfChar != BreakIterator.DONE;
             startOfChar = endOfChar, endOfChar = breaker.next()) {
            this.characters.add(word.substring(startOfChar,endOfChar));
        }
    }

    public int length() {
        return characters.size();
    }

    public List<String> getCharacters() {
        return characters;
    }

    public String charAt(int pos) {
        return characters.get(pos);
    }

    @Override
    public String toString() {
        return word;
    }
}
