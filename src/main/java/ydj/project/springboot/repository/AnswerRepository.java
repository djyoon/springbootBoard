package ydj.project.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ydj.project.springboot.VO.Answer;
import ydj.project.springboot.VO.Question;

/**
 * Created by djyoon on 2018-11-30.
 */
public interface  AnswerRepository extends JpaRepository<Answer, Long> {

    @Transactional
    void deleteAllByQuestion(Question question);


}
