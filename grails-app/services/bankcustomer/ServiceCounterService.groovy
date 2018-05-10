package bankcustomer

import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional
class ServiceCounterService {

    def getServiceCounterList() {

        def serviceCounters = ServiceCounter.findAll()
        serviceCounters as JSON
    }

    def getTokensForServiceCounter(String name) {

        def customerTokens = CustomerToken.findByServiceCounter(name)
        customerTokens as JSON
    }
}
