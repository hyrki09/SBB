package com.mysite.springboard.question;

import com.mysite.springboard.answer.AnswerForm;
import com.mysite.springboard.user.SiteUser;
import com.mysite.springboard.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor // 애너테이션의 생성자 방식
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;


    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model, @RequestParam(value="page", defaultValue = "0")int page) {

        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    // principal 객체를 사용하는 메서드에 @PreAuthorize("isAuthenticated()") 애너테이션을 사용해야 한다.
    // 로그인한 경우에만 실행
    // 로그아웃 상태에서 호출되면 로그인 페이지로 강제 이동
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,// BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다.
                                 Principal principal){
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteuser = userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(),questionForm.getContent(),siteuser);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,
                                 @PathVariable Integer id,
                                 Principal principal){

        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());

        return "question_form";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 @PathVariable Integer id,
                                 Principal principal){
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 업습니다.");
        }
        this.questionService.modify(question,questionForm.getSubject(), questionForm.getContent());

        return String.format("redirect:/question/detail/%s",id);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable Integer id) {
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:/";
    }


}
