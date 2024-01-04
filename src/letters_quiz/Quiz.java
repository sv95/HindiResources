package letters_quiz;

import common.HindiChars;
import letters_quiz.quiz.QuestionWithAnswer;
import letters_quiz.quiz.WhatComesNextQuestionWithAnswer;
import letters_quiz.ui.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz {

    private final static int WRONG_OPTIONS_COUNT = 3;
    private static Window window;

    // Main method
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Cannot set LookAndFeel");
        }
        // Create a window
        window = new Window();
        window.createWindow();
        createQuestion(true);
    }

    public static void createQuestion(boolean isVowelQuestion) {
        // Create a question
        WhatComesNextQuestionWithAnswer question = new WhatComesNextQuestionWithAnswer(
                        isVowelQuestion? HindiChars.HindiCharacterType.VOWEL : HindiChars.HindiCharacterType.CONSONANT);
        window.addText(question.question);
        final List<String> wrongOptions = question.genWrongOptions(WRONG_OPTIONS_COUNT);
        final List<String> allOptions = new ArrayList<>();
        allOptions.add(question.answer);
        allOptions.addAll(wrongOptions);
        window.addButtons(allOptions, isVowelQuestion);
    }
}
