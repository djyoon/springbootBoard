package ydj.project.springboot.VO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by djyoon on 2018-11-27.
 */
@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqAns;

    private String author;
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    private String regTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private LocalDate regDate = LocalDate.now();


    public Answer() {

    }

    public Answer(String author, String content, Question question ){
        this.author = author;
        this.content = content;
        this.question = question;
    }
}
