package ydj.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ydj.project.springboot.VO.Answer;
import ydj.project.springboot.VO.Question;
import ydj.project.springboot.VO.User;
import ydj.project.springboot.repository.AnswerRepository;
import ydj.project.springboot.repository.BoardRepository;
import ydj.project.springboot.repository.MainRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by djyoon on 2019-06-15.
 */

@Service
public class MainService implements UserDetailsService{

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private MainRepository mainRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page pageFindAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Question findBySeqQueBoard(Long seqQue) {
        return boardRepository.findBySeqQue(seqQue);
    }

    public void saveBoard(Question question) {
        boardRepository.save(question);
    }

    public Question getOneBoard(Long seqQue) {
        return boardRepository.getOne(seqQue);
    }

    public void deleteBoard(Question question) {
        boardRepository.delete(question);
    }

    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public void deleteAllByQuestion(Question question) {
        answerRepository.deleteAllByQuestion(question);
    }

    public User findByUserId(String userId) {
       return mainRepository.findByUserId(userId);
    }

    public User saveMain(User user) {
        user.setPassWd(passwordEncoder.encode(user.getPassWd()));
        System.out.println(user.getPassWd());
        mainRepository.save(user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = mainRepository.findByUserId(s);
        System.out.println(s);
        System.out.println(user);
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassWd(), getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @PostConstruct
    public void init(){
        User djyoon = mainRepository.findByUserId("djyoon");
        if(djyoon == null){
            User user = new User();
            user.setUserId("djyoon");
            user.setPassWd("djyoon");
            User newUser = saveMain(user);
            System.out.println(newUser);
        }
    }
}
