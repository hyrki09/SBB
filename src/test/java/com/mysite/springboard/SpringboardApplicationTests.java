package com.mysite.springboard;

import com.mysite.springboard.answer.Answer;
import com.mysite.springboard.answer.AnswerRepository;
import com.mysite.springboard.answer.AnswerService;
import com.mysite.springboard.question.Question;
import com.mysite.springboard.question.QuestionRepository;
import com.mysite.springboard.question.QuestionService;
import com.mysite.springboard.user.SiteUser;
import com.mysite.springboard.user.UserService;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class SpringboardApplicationTests {

//	@Autowired
//	private QuestionRepository questionRepository;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserService userService;

	private final Logger LOGGER = LoggerFactory.getLogger(SpringboardApplicationTests.class);
	@Test
	void testJpa() {
		Question question = questionService.getQuestion(311);

		SiteUser siteUser = new SiteUser();
//		Page<Answer> list = answerService.getList(question,1);
//		Page<Answer> list1 = answerService.getList(question, 1);
//		List<Answer> allByQuestion = answerRepository.findAllByQuestion(question);

		SiteUser user = userService.getUser("testkim4");
		int cnt = 1;

		for (int i = 0; i <50; i++) {
//			LOGGER.info("answer{} : {}",cnt,answer.getContent() );
//			cnt++;
			answerService.create(question,"answerTest" +i, user);
		}

//		assertEquals(5, list1.stream().count());
	}


/*

//	Save 테스트
	@Test
	void contextLoads() {
		Question q1 = new Question();
		q1.setSubject("오늘은 몇일 일까요?");
		q1.setContent("바로 오늘은 근로자의 날 전날입니다!");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);


//		Question q2 = new Question();
//		q2.setSubject("스프링 부트 모델 질문입니다.");
//		q2.setContent("어떻게 하면 잘해질까요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);

	}

	@Test
	void testFindAll(){
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question question = all.get(0);
		assertEquals("sbb가 무엇인가요?", question.getSubject());
	}


	@Test
	void testFindById(){
		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()) {
			Question question = oq.get();
//			Assertions.assertEquals("sbb가 무엇인가요?", question.getSubject());
			assertEquals("수정된 제목", question.getSubject());
		}
	}

	@Test
	void testFindBySubject(){
		Question bySubject = this.questionRepository.findBySubject("오늘은 몇일 일까요?");

		assertEquals(5, bySubject.getId());
	}

	@Test
	void testFindBySubjectAndContent(){
		Question bySubjectAndContent = this.questionRepository.findBySubjectAndContent("오늘은 몇일 일까요?", "바로 오늘은 근로자의 날 전날입니다!");


		assertEquals(5, bySubjectAndContent.getId());
	}

	@Test
	void testFindBySubjectLike(){
		List<Question> bySubjectLike = this.questionRepository.findBySubjectLike("sbb%");

		Question question = bySubjectLike.get(0);


		assertEquals("sbb가 무엇인가요?", question.getSubject());
	}

	@Test
	void testModify(){
		Optional<Question> oq = this.questionRepository.findById(1);
		Assertions.assertTrue(oq.isPresent());
		Question question = oq.get();
		question.setSubject("수정된 제목");
		this.questionRepository.save(question);
	}

	@Test
	void testDelete(){

		assertEquals(5, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		Assertions.assertTrue(oq.isPresent());
		Question question = oq.get();
		this.questionRepository.delete(question);
		assertEquals(4, this.questionRepository.count());

	}

	@Test
	void testAnswerSave(){

		Optional<Question> question = this.questionRepository.findById(2);
		assertTrue(question.isPresent());
		Question q = question.get();
//		List<Answer> answerList = q.getAnswerList();
		String content = q.getContent();

		System.out.println("answerList = " + content);

		Answer answer = new Answer();
		answer.setContent("네 자동으로 생성됩니다.");
		answer.setQuestion(q);
		answer.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(answer);
	}

	@Test
	void testAnswerFindById(){
		Optional<Answer> answer = this.answerRepository.findById(1);
		assertTrue(answer.isPresent());
		Answer a = answer.get();
		assertEquals(2, a.getQuestion().getId());
	}

	@Transactional
	@Test
	void testAnswerQuestionFindById(){
		Optional<Question> questionRepositoryById = this.questionRepository.findById(2);
		assertTrue(questionRepositoryById.isPresent());
		Question question = questionRepositoryById.get();

		List<Answer> answerList = question.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());


	}

*/

}
