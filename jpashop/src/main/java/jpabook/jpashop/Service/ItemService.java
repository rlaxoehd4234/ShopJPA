package jpabook.jpashop.Service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repsository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void savedItem(Item item){
        itemRepository.save(item);
    }


    public List<Item> findItems(Long itemId) {
        return itemRepository.findAll();
    }


    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
