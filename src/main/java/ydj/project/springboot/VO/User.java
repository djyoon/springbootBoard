package ydj.project.springboot.VO;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.persistence.*;

/**
 * Created by djyoon on 2018-11-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqUse;

    @NonNull
    private String userId;
    @NonNull
    private String passWd;
    @NonNull
    private String email;
    private String gitId;
}

