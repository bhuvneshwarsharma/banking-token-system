package bankcustomer

import grails.converters.JSON

class ServiceCounterService {

    def getServiceCounterList() {

        def serviceCounters = bankcustomer.ServiceCounter.findAll()
        serviceCounters as JSON
    }

    def getTokensForServiceCounter(String name) {

        def customerTokens = bankcustomer.ServiceCounter.findByName(name)?.customerToken
        customerTokens? customerTokens as JSON : null
    }
}
