package ydj.project.springboot.VO;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by djyoon on 2018-11-27.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqAns;

    @NonNull
    private String author;
    @Lob
    @NonNull
    private String content;

    @ManyToOne
    @JoinColumn(name="question_id")
    @NonNull
    private Question question;

    private String regTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private LocalDate regDate = LocalDate.now();
/*
    public Answer(String author, String replyText, Question question) {
        this.author = author;
        this.content = replyText;
        this.question = question;
    }*/
}
