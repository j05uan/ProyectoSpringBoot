package com.survey.survey.question.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionUpdateDTO {

    private String response_type;
    private String comment_question;
    private String question_text;

}
