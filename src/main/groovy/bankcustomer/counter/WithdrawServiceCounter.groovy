package bankcustomer.counter;

import bankcustomer.CustomerToken

/**
 * Created by bhuvneshwars on 14/5/18.
 */
public class WithdrawServiceCounter implements IServiceCounter{

    Map<String, Queue<CustomerToken>> tokenTaskList= new HashMap<String, Queue<CustomerToken>>();
    static WithdrawServiceCounter withdrawServiceCounter = null;

    private WithdrawServiceCounter() { }

    public static WithdrawServiceCounter getInstance() {

        if(withdrawServiceCounter==null)
            withdrawServiceCounter = new WithdrawServiceCounter();

        return withdrawServiceCounter;
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