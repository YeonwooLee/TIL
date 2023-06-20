package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
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
