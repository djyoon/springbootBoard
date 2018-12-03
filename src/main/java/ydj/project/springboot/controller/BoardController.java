package ydj.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ydj.project.springboot.VO.Answer;
import ydj.project.springboot.VO.Question;
import ydj.project.springboot.VO.User;
import ydj.project.springboot.repository.AnswerRepository;
import ydj.project.springboot.repository.BoardRepository;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by djyoon on 2018-11-27.
 */
@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    AnswerRepository answerRepository;

    @GetMapping("list")
    public String boardList(Model model,
                            @PageableDefault(size=15, sort="seqQue",direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("questions",boardRepository.findAll(pageable));
        model.addAttribute("totalPage",boardRepository.findAll(pageable).getTotalPages());
        model.addAttribute("curPage",boardRepository.findAll(pageable).getNumber());

        return "/board/list";
    }

    @PostMapping("insert")
    public String boardInsert(HttpSession session, Question question){

        question.setAuthor(((User)session.getAttribute("loginUser")).getUserId());
        boardRepository.save(question);

        return "redirect:/board/list";

    }

    @PostMapping("modify")
    public String boardModify(Question question){

        Question tempQuestion = boardRepository.getOne(question.getSeqQue());

        tempQuestion.setTitle(question.getTitle());
        tempQuestion.setContent(question.getContent());

        boardRepository.save(tempQuestion);

        return "redirect:/board/view/"+question.getSeqQue();

    }

    @PostMapping("delete")
    @ResponseBody
    public ResponseEntity boardDelete(Question question){

        boolean success = true;

        try{
            answerRepository.deleteAllByQuestion(question);
            boardRepository.delete(question);
        }catch(Exception e){
            success = false;
            e.printStackTrace();
        }finally {

            if(success){
                return new ResponseEntity("delete",HttpStatus.OK);
            } else {
                return new ResponseEntity("delete",HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @GetMapping("view/{seqQue}")
    public String boardView(@PathVariable Long seqQue, Model model, HttpSession session){

        boolean isAuthor = false;
        String loginUser = ((User)session.getAttribute("loginUser")).getUserId();

        Question question = boardRepository.findBySeqQue(seqQue);
        String author = question.getAuthor();

        if(loginUser.equals(author)){
            isAuthor = true;
        }

        model.addAttribute("question",question);
        model.addAttribute("isAuthor",isAuthor);

        return "/board/view";

    }

    @PostMapping("reply")
    @ResponseBody
    public ResponseEntity boardReply(@RequestParam Map<String, String> param, HttpSession session, Question question){

          String author = ((User)session.getAttribute("loginUser")).getUserId();
          Answer answer = new Answer(author,param.get("replyText"),question);
          boolean success = true;

            try{
                answerRepository.save(answer);
            }catch(Exception e){
                success = false;
                e.printStackTrace();
            }finally {

                if(success){
                    return new ResponseEntity("reply",HttpStatus.OK);
                } else {
                    return new ResponseEntity("reply",HttpStatus.INTERNAL_SERVER_ERROR);
                }

            }
    }
}
