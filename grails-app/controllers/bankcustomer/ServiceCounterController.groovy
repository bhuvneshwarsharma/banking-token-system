package bankcustomer

class ServiceCounterController {

    def serviceCounterService

    /**
     * This method will return list of service counters list
     * @return list
     */
    def getServiceCounterList() {

        render serviceCounterService.getServiceCounterList()
    }

    /**
     * This method will return list of tokens for service counters
     * @return list
     */
    def getTokensForServiceCounter() {

        String serviceCounterName = params.counterName
        render serviceCounterService.getTokensForServiceCounter(serviceCounterName)
    }
}
