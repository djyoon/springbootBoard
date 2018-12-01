package ydj.project.springboot.VO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by djyoon on 2018-11-20.
 */
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqUse;

    private String userId;

    private String passWd;

    private String email;

    private String gitId;
}

