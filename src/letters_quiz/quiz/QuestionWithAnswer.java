package letters_quiz.quiz;

import java.util.List;

public interface QuestionWithAnswer {
    public String question = null;
    public String answer = null;

    public List<String> genWrongOptions(int numOfOptions);
}
