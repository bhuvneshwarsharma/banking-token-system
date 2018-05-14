package bankcustomer.counter;

import bankcustomer.CustomerToken;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by bhuvneshwars on 14/5/18.
 */
public class AccountServiceCounter implements IServiceCounter{

    Map<String, Queue<CustomerToken>> tokenTaskList= new HashMap<String, Queue<CustomerToken>>();
    static AccountServiceCounter accountServiceCounter = null;

    private AccountServiceCounter() { }

    public static AccountServiceCounter getInstance() {

        if(accountServiceCounter==null)
            accountServiceCounter = new AccountServiceCounter();

        return accountServiceCounter;
    }

    @Override
    public void addTokenToQueue(CustomerToken token) {

        String counterType = token.customer.getServiceType();
        Queue queue = tokenTaskList.get(counterType);
        if(queue == null) {
            queue = new PriorityQueue<CustomerToken>();
        }
        queue.add(token);
        tokenTaskList.put(counterType, queue);
    }

    @Override
    public CustomerToken processToken(String counterType) {

        Queue<CustomerToken> queue = tokenTaskList.get(counterType);
        if(queue == null)
            return null;

        CustomerToken token = queue.poll();

        tokenTaskList.put(counterType, queue);
        return token;
    }

    public void doSomething() {

    }
}
