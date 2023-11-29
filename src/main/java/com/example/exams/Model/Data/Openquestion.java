package com.example.exams.Model.Data;

import com.example.exams.Model.Data.db.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Openquestion {
    private Integer questionid;
    private String content;
    private Integer score;
    private Integer subjectsubject_subjectid;
}
