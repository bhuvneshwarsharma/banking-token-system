package bankcustomer

import bankcustomer.constant.ServiceType
import grails.converters.JSON

class ServiceCounterService {

    def getServiceCounterList() {

        def serviceCounters = bankcustomer.ServiceCounter.findAll()
        serviceCounters as JSON
    }

    def getTokensForServiceCounter(String serviceType) {

        String serviceTypeS = ServiceType.getserviceType(serviceType)
        if(!serviceTypeS)return "Bank does not support service ${serviceType}"
        def customerTokens = CustomerToken.findByServiceType(serviceTypeS)
        customerTokens? customerTokens as JSON : "No tokens found found for service ${serviceType}"
    }
}
