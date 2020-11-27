package wordsearch;

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
        int start = breaker.first();
        for (int end = breaker.next();
             end != BreakIterator.DONE;
             start = end, end = breaker.next()) {
            this.characters.add(word.substring(start,end));
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
