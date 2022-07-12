package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext // 스프링부트가 이 어노테이션에 있으면 아래 코드를 주입해 줌. 하지만 lombok 사용으로 생략
    private final EntityManager em;

    // 저장
    public void save(Member member) {
        em.persist(member);
//        return member.getId();  // void가 아니라 Long일 떄 사용. 커맨드와 쿼리 분리(?)
    }

    // 회원 전체 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)   // JPQL은 Entity를 대상으로 쓰임
                .getResultList();
    }

    // ID로 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 이름으로 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)  // Member.class: 조회 타임
                .setParameter("name", name)
                .getResultList();
    }

}
