package bankcustomer.counter;

import bankcustomer.CustomerToken;

/**
 * Created by bhuvneshwars on 14/5/18.
 */
public interface IServiceCounter {

    public void addTokenToQueue(CustomerToken token);

    public CustomerToken processToken(String counterType);

    public void doSomething();
}
