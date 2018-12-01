package ydj.project.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ydj.project.springboot.VO.User;

import java.util.List;

/**
 * Created by djyoon on 2018-11-21.
 */
public interface MainRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
}
