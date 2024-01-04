package letters_quiz.ui;

import letters_quiz.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Window {

    JFrame jframe;
    JPanel jpanel;
    JLabel questionLabel;

    private void refresh() {
        jframe.revalidate();
        jframe.repaint();
    }

    // Create a GUI window
    public void createWindow() {
        // Create a frame
        jframe = new JFrame("Hindi Letters Quiz");

        // Create a panel
        jpanel = new JPanel();
        jpanel.setBorder(new EmptyBorder(32, 32, 32, 32));
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));

        // Add panel to frame
        jframe.setContentPane(jpanel);

        // Set the size of the frame
        jframe.setSize(300, 300);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.setVisible(true);
    }

    // Add text and 4 clickable buttons below the text
    public void addText(String text) {
        // Create a label
        questionLabel = new JLabel(text);
        questionLabel.setFont(new Font("Century Schoolbook L", Font.PLAIN, 32));
        // Add label to panel
        jpanel.add(questionLabel);
        refresh();
    }

    // Add 4 clickable buttons
    public void addButtons(List<String> options, boolean isVowelQuestion) {
        JPanel buttonPane = new JPanel(new GridLayout(-1, 1));
        buttonPane.setBorder(new EmptyBorder(32, 32, 32, 32));
        int addedButtons = 0;
        for (String option : options) {
            JButton button = new JButton(option);
            button.setFont(new Font("Century Schoolbook L", Font.PLAIN, 32));
            final Color buttonColourOnClick = addedButtons==0?Color.GREEN:Color.RED;
            button.addActionListener(e -> {
                button.setBackground(buttonColourOnClick);
                refresh();
            });
            buttonPane.add(button, (int)(Math.random() * ++addedButtons));
        }
        jframe.add(buttonPane);
        jframe.pack();
        addNextButton(isVowelQuestion);
        addLetterChangeButton(isVowelQuestion);
        refresh();
    }

    // Add next button at the bottom
    public void addNextButton(boolean isVowelQuestion) {
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 32));
        nextButton.addActionListener(e -> {
            jframe.getContentPane().removeAll();
            refresh();
            Quiz.createQuestion(isVowelQuestion);
        });
        jframe.add(nextButton);
        jframe.pack();
        refresh();
    }

    // Add letter change button at the bottom
    public void addLetterChangeButton(boolean isVowelQuestion) {
        JButton letterChangeButton = new JButton("Change to " + (isVowelQuestion?"Consonant":"Vowel") + "s");
        letterChangeButton.setFont(new Font("Century Schoolbook L", Font.PLAIN, 32));
        letterChangeButton.addActionListener(e -> {
            jframe.getContentPane().removeAll();
            refresh();
            Quiz.createQuestion(!isVowelQuestion);
        });
        jframe.add(letterChangeButton);
        jframe.pack();
        refresh();
    }
}
