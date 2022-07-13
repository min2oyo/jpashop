package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 저장
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 상품 수정(?)
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {   // 변경 감지로 데이터 변경
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    // 상품 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    // ID로 상품 조회
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
