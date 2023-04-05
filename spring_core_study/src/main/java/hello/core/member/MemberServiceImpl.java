package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 인터페이스만 가지고는 NPE이 나니 구현객체를 선택해줘야함.

    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해 memberRepository.save를 호출하면 위에서 선택해준 MemoryMemberRepository의 save를 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
