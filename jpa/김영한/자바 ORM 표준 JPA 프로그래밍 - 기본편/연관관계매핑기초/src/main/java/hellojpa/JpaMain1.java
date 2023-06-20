package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain1 {
    public static void main(String[] args) {
        // persistence.xml에 설정한 persistence-unit명 (==database 하나)
        //애플리케이션 로딩 시점에 딱 하나만 만들어야 함
        //db당 하나
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //transaction마다(고객의 요청이 올 떄 마다) 만듦, 쓰고버림
        //스레드간에 공유 절대 x
        //database connection 하나 받았다고 생각하면 된다
        EntityManager em = emf.createEntityManager();


        //transaction 얻어오기
        //jpa 모든 데이터 변경은 트랜잭션 안에서 해야함
        EntityTransaction tx = em.getTransaction();

        //transaction 시작
        tx.begin();
        try{
            //저장
            Team1 team = new Team1();
            team.setName("TeamA");
            em.persist(team);//영속상태가 되면 pk값이 세팅됨

            Member1 member = new  Member1();
            member.setUsername("member1");
            member.changeTeamProp(team);
            em.persist(member);

            //양방향 매핑시에는 양쪽 객체에 다 값을 세팅해주는 것이 맞다
            //근데 이런식으로 하려면 까먹으니까 member.setTeamProp에 team.getMembers().add(member)를 넣어줌
            // team.getMembers().add(member);


            //영속성 컨텍스트 말고 dbㅔㅇ서 가져오는 쿼리 보고싶다!
            // em.flush();//안 날린 쿼리 강제로 날림
            // em.clear();//영속성 컨텍스트 초기회ㅏ

            Team1 findTeam = em.find(Team1.class, team.getId());
            List<Member1> members = findTeam.getMembers();


            for(Member1 m : members){
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();

        }catch (Exception e){
            System.out.println("except");
            tx.rollback();
        }finally {
            //꼭 닫아줘야 한다
            em.close();//em은 내부적으로 db connection을 물고 동작
        }
        em.close();

        emf.close();

    }
}

