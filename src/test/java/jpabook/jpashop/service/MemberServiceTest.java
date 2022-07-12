package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)  // junit을 할 때 스프링이랑 엮어서 실행
@SpringBootTest // 스프링부트를 띄운 상태로 test 함. 없으면 Autowired가 다 실패함. 스프링 컨테이너 안에서 test
@Transactional  // test가 끝나면 rollback 해버림
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)    // Transactional 무력화. DB에 저장됨
    public void 회원가입() throws Exception {

        // Given
        Member member = new Member();
        member.setName("kim");

        // When
        Long savedId = memberService.join(member);

        // Then
        em.flush(); // member가 쿼리로 DB에 반영. 그러나 Transactional로 rollback 됨
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {

        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // When
        memberService.join(member1);
        try {
            memberService.join(member2);    // 에외가 발생해야 한다!!
        } catch (IllegalStateException e) {
            return;
        }

        // Then
        fail("예외가 발생해야 한다. ");  // 코드가 돌다가 여기에 오면 안 됨
    }

}
