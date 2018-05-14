package Bank.Token

import bankcustomer.CustomerToken
import bankcustomer.ServiceCounter
import bankcustomer.constant.ServiceType
import bankcustomer.counter.IServiceCounter
import bankcustomer.factory.CounterServiceFactory

/**
 * Created by bhuvneshwars on 8/5/18.
 */
class TokenProcessor {

    /**
     * This method will add customer token to task queue
     * @param token
     */
    public static void addTokenToQueue(CustomerToken token) {

        IServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService(token.serviceType))
        serviceCounter.addTokenToQueue(token)
    }

    /**
     * This method will process token and will remove from task queue
     * @param keyName
     * @return token
     */
    public static CustomerToken processToken(ServiceCounter counter) {

        IServiceCounter serviceCounter = CounterServiceFactory.getServiceCounterInstance(ServiceType.getService(counter.serviceType))
        def token = serviceCounter.processToken(counter.counterType)
        return token
    }
}
