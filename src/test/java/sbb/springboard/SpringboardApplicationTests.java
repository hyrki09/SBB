package sbb.springboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringboardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

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
		Assertions.assertEquals(2, all.size());

		Question question = all.get(0);
		Assertions.assertEquals("sbb가 무엇인가요?", question.getSubject());
	}


	@Test
	void testFindById(){
		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()) {
			Question question = oq.get();
//			Assertions.assertEquals("sbb가 무엇인가요?", question.getSubject());
			Assertions.assertEquals("수정된 제목", question.getSubject());
		}
	}

	@Test
	void testFindBySubject(){
		Question bySubject = this.questionRepository.findBySubject("오늘은 몇일 일까요?");

		Assertions.assertEquals(5, bySubject.getId());
	}

	@Test
	void testFindBySubjectAndContent(){
		Question bySubjectAndContent = this.questionRepository.findBySubjectAndContent("오늘은 몇일 일까요?", "바로 오늘은 근로자의 날 전날입니다!");


		Assertions.assertEquals(5, bySubjectAndContent.getId());
	}

	@Test
	void testFindBySubjectLike(){
		List<Question> bySubjectLike = this.questionRepository.findBySubjectLike("sbb%");

		Question question = bySubjectLike.get(0);


		Assertions.assertEquals("sbb가 무엇인가요?", question.getSubject());
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

		Assertions.assertEquals(5, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		Assertions.assertTrue(oq.isPresent());
		Question question = oq.get();
		this.questionRepository.delete(question);
		Assertions.assertEquals(4, this.questionRepository.count());

	}


}
