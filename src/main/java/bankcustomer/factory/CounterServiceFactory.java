package bankcustomer.factory;

import bankcustomer.constant.ServiceType;
import bankcustomer.counter.*;

/**
 * Created by bhuvneshwars on 14/5/18.
 */
public class CounterServiceFactory {

    public static IServiceCounter getServiceCounterInstance(ServiceType serviceType) {

        IServiceCounter serviceCounterObj = null;

        switch (serviceType) {

            case ACCOUNT :
                serviceCounterObj = AccountServiceCounter.getInstance();
                break;

            case DEPOSIT :
                serviceCounterObj = DepositServiceCounter.getInstance();
                break;

            case WITHDRAW:
                serviceCounterObj = WithdrawServiceCounter.getInstance();
                break;

            case ENQUIERY:
                serviceCounterObj = EnquieryServiceCounter.getInstance();
                break;
        }

        return serviceCounterObj;
    }
}
