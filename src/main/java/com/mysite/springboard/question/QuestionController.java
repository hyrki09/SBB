package com.mysite.springboard.question;

import com.mysite.springboard.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor // 애너테이션의 생성자 방식
@Controller
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model) {

        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult){ // BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다.
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(),questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동

    }
}
