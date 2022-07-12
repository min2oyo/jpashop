package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA가 조회하는데 최적화. springframework로 import
@RequiredArgsConstructor    // final이 있는 필드만 가지고 생성자 만들어 줌(?)
public class MemberService {

    private final MemberRepository memberRepository;  // final하면 생성자 미입력값 오류 잡아줌?

    // 회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());   // 동시 등록을 방지하기 위해 DB에 unique 동록(?)
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다. ");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // ID로 조회
    public Member fineOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
