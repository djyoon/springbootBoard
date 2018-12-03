package ydj.project.springboot.VO;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by djyoon on 2018-11-27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqQue;

    @NonNull
    private String author;
    @NonNull
    private String title;
    @Lob
    @NonNull
    private String content;

    @OneToMany(mappedBy = "question")
    @OrderBy("regTime DESC")
    private List<Answer> answers;

    private String regTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private LocalDate regDate = LocalDate.now();


}
