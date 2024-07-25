package repositories;

import error.OrderingSystemException;
import models.ErrorCode;
import models.Order;
import utils.ErrorUtil;

import java.util.HashMap;

public class OrderRepository {
    public HashMap<Integer, Order> orderHashMap = new HashMap<>();

    private OrderRepository orderRepository = null;

    public OrderRepository getInstance() {
        if(orderRepository == null) {
            orderRepository = new OrderRepository();
        }
        return orderRepository;
    }

    public void addOrder(int id, Order order){
        orderHashMap.put(id, order);
    }

    public void removeOrder(int id) {
        orderHashMap.remove(id);
    }

    public Order getOrder(int id) {
        if(orderHashMap.containsKey(id)) {
            return orderHashMap.get(id);
        }
        throw new OrderingSystemException(ErrorCode.ORDER_NOT_FOUND,
                ErrorUtil.errorCodeMessageHashMap.get(ErrorCode.ORDER_NOT_FOUND));
    }

    public boolean doesOrderExist(int id) {
        return orderHashMap.containsKey(id)? true : false;
    }
}
