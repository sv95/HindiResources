package letters_quiz.quiz;

import common.HindiChars.HindiCharacterType;

import java.util.ArrayList;
import java.util.List;

import static common.HindiChars.charTypeToChars;
import static common.HindiChars.charTypeToStrings;

public class WhatComesNextQuestionWithAnswer implements QuestionWithAnswer {
    public String question;
    public String answer;

    private int currentCharacterIndex;
    private HindiCharacterType hindiCharacterType;

    public WhatComesNextQuestionWithAnswer(HindiCharacterType hindiCharacterType) {
        if (!(hindiCharacterType==HindiCharacterType.VOWEL || hindiCharacterType==HindiCharacterType.CONSONANT)) {
            throw new IllegalArgumentException("hindiCharacterType must be VOWEL or CONSONANT");
        }
        this.hindiCharacterType = hindiCharacterType;

        // Generate question
        List<String> characters = charTypeToStrings.get(hindiCharacterType);
        currentCharacterIndex = (int) (Math.random() * characters.size());
        String currentCharacter = characters.get(currentCharacterIndex);
        this.question = String.format("Which letter comes after %s in the alphabet?", currentCharacter);
        System.out.println("question is " + this.question);

        // Generate answer
        int nextIndex = currentCharacterIndex + 1;
        if (nextIndex >= characters.size()) {
            this.answer = "None";
        } else {
            this.answer = String.valueOf(characters.get(nextIndex));
        }
    }

    public List<String> genWrongOptions(int numOfOptions) {
        List<String> letters = charTypeToStrings.get(hindiCharacterType);
        List<String> wrongOptions = new ArrayList<>();
        int generatedOptions = 0;
        while (generatedOptions < numOfOptions) {
            int randomIndex = (int) (Math.random() * letters.size());
            String randomLetter = letters.get(randomIndex);
            if (!(randomIndex==currentCharacterIndex || randomIndex==currentCharacterIndex+1)
                    && !wrongOptions.contains(randomLetter)) {
                wrongOptions.add(randomLetter);
                generatedOptions++;
            }
        }
        return wrongOptions;
    }
}
