package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repsository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest // 스프링을 컨테이너 안에서 테스트를 돌리는 것이다.
@Transactional //스프링 테스트가 끝나면 롤백이 된다. 테스트 클래스 한정에서
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("김태동");


        //when
        Long savedId = memberService.join(member);



        //then
        assertEquals(member,memberRepository.findOne(savedId));



    }

    @Test(expected = IllegalStateException.class)// 중복회원 예제
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2);



        //then
        fail("예외가 발생한다.");
    }

}