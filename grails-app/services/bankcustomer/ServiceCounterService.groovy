package bankcustomer

import grails.converters.JSON

class ServiceCounterService {

    def getServiceCounterList() {

        def serviceCounters = ServiceCounter.findAll()
        serviceCounters as JSON
    }

    def getTokensForServiceCounter(String name) {

        def customerTokens = ServiceCounter.findByName(name)?.customerToken
        customerTokens? customerTokens as JSON : null
    }
}
