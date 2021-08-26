package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;
	@Test
	public void 상품주문()
	{
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가","123-123"));
		em.persist(member);

		Item book = new Book();
		book.setName("시골 JPA");
		book.setPrice(10000);
		book.setStockQuantity(10);
		em.persist(book);

		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		Order getOrder = orderRepository.findOne(orderId);

		Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,getOrder.getStatus());
		Assert.assertEquals("주문한 상품은 종류 수가 정확해야한다. ", 1, getOrder.getOrderItems().size());
		Assert.assertEquals("주문 가격은 가격 * 수량이다.",10000 * orderCount,getOrder.getTotalPrice());
		Assert.assertEquals("주문 수량만큼 재고가 줄어야한다.", 8, book.getStockQuantity());
	}

	public void 주문취소()
	{

	}

	public void 상품주문_재고수량초과() throws Exception
	{

	}
}
