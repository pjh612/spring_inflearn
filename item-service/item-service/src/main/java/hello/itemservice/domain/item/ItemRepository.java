package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ItemRepository {
    private static final HashMap<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item)
    {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id)
    {
        return store.get(id);
    }

    public List<Item> findAll()
    {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam)
    {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore()
    {
        store.clear();
    }

}