package com.wally.hiread.service.user;

import com.wally.hiread.model.user.Questionnaire;
import org.springframework.stereotype.Service;

/**
 * Created by eric on 01/09/2017.
 */
@Service
public class QuestionnaireService {

    /**
     * 计算调查问卷得分
     *
     * @param questionnaire
     * @return
     */
    public int calculateTotalScore(Questionnaire questionnaire) {
        int score = 0;

        String question1 = questionnaire.getQuestion1();
        score += calculateScoreFor4Answer(question1);

        String question2 = questionnaire.getQuestion2();
        score += calculateScoreFor6Answer(question2);

        String question3 = questionnaire.getQuestion3();
        score += calculateScoreFor6Answer(question3);

        String question4 = questionnaire.getQuestion4();
        score += calculateScoreFor6Answer(question4);

        String question5 = questionnaire.getQuestion5();
        if (question5 != null && !question5.isEmpty()) {
            String[] answer5Arr = question5.split(";");
            for (int i = 0; i < answer5Arr.length; i++) {
                if ("A".equals(answer5Arr[i])) {
                    score += 0;
                } else if ("B".equals(answer5Arr[i])) {
                    score += 2;
                } else if ("C".equals(answer5Arr[i])) {
                    score += 2;
                } else if ("D".equals(answer5Arr[i])) {
                    score += 2;
                } else {}
            }

        }

        String question6 = questionnaire.getQuestion6();
        if (question6 != null && !question6.isEmpty()) {
            String[] answer6Arr = question6.split(";");
            for (int i = 0; i < answer6Arr.length; i++) {
                if ("A".equals(answer6Arr[i])) {
                    score += 0;
                } else if ("B".equals(answer6Arr[i])) {
                    score += 1;
                } else if ("C".equals(answer6Arr[i])) {
                    score += 2;
                } else if ("D".equals(answer6Arr[i])) {
                    score += 4;
                } else {}
            }

        }

        return score;
    }

    /**
     * 计算有4个选项的题目的得分
     *
     * @param answer
     * @return
     */
    protected int calculateScoreFor4Answer(String answer) {
        int score = 0;

        switch (answer) {
            case "A":
                score += 1;
                break;
            case "B":
                score += 2;
                break;
            case "C":
                score += 3;
                break;
            case "D":
                score += 4;
                break;
            default:
                break;
        }

        return score;
    }

    /**
     * 计算有6个选项的题目的得分
     *
     * @param answer
     * @return
     */
    protected int calculateScoreFor6Answer(String answer) {
        int score = 0;

        switch (answer) {
            case "A":
                score += 0;
                break;
            case "B":
                score += 1;
                break;
            case "C":
                score += 2;
                break;
            case "D":
                score += 3;
                break;
            case "E":
                score += 4;
                break;
            case "F":
                score += 5;
                break;
            default:
                break;
        }

        return score;
    }
}
