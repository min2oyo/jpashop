package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 상품 저장
    public void save(Item item){
        if (item.getId() == null) {
            em.persist(item);   // 없을 경우 추가
        } else {
            em.merge(item); // 있을 경우 업데이트(?)
        }
    }

    // 모든 상품 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    // 특정 상품 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }



}
