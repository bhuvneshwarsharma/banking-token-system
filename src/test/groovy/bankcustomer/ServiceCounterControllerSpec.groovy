package bankcustomer

import bankcustomer.constant.EntityType
import grails.converters.JSON
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ServiceCounterControllerSpec extends Specification implements ControllerUnitTest<ServiceCounterController> {

    def serviceCounterServiceMock
    def setup() {

        serviceCounterServiceMock = new Expando()
        serviceCounterServiceMock.getServiceCounterList = { ->
            return getServiceCounterList()
        }
        controller.serviceCounterService = serviceCounterServiceMock
    }

    def cleanup() {
    }

    void "test getServiceCounterList"() {
        when:
        controller.getServiceCounterList()

        then:
        response.status  == 200
        response.contentType == "application/json;charset=UTF-8"
    }

    def getServiceCounterList() {

//        Bank bank = new Bank(name: "ABC")
//        Branch branch = new Branch(branchName: "beghumpeth", bank: bank, ifscCode: "BANK001")

        def s1 = ['branch': null, 'name': "S1", 'counterType': EntityType.PREMIUM]
        def s2 = ['branch': null, 'name': "S2", 'counterType': EntityType.REGULAR]

        def serviceCounters = [] as ArrayList
        serviceCounters.add(s1)
        serviceCounters.add(s2)

        return serviceCounters as JSON
    }
}
