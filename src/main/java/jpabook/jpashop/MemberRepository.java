package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링부트가 이 어노테이션에 있으면 아래 코드를 주입해 줌
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();  // 커맨드와 쿼리 분리(?)
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
