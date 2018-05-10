package bankcustomer

class ServiceCounterController {

    def serviceCounterService

    def serviceCounterList() {

        render serviceCounterService.getServiceCounterList()
    }

    def getTokensForServiceCounter() {

        String serviceCounterName = params.counterName
        render serviceCounterService.getTokensForServiceCounter(serviceCounterName)
    }
}
