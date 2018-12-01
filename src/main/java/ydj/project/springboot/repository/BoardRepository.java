package ydj.project.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydj.project.springboot.VO.Question;

/**
 * Created by djyoon on 2018-11-27.
 */
public interface BoardRepository extends JpaRepository<Question, Long>  {

    Question findBySeqQue(Long seqQue);
}
