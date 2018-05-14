package bankcustomer

import bankcustomer.constant.EntityType
import grails.testing.services.ServiceUnitTest

class ServiceCounterServiceSpec extends  HelperSpec implements ServiceUnitTest<ServiceCounterService> {

    def setup() {
    }

    def cleanup() {
    }

    void "should return null without creating service counter list"() {

        assert bankcustomer.ServiceCounter.IServiceCounter.count() == 0
        given:
        def serviceCounters = service.getServiceCounterList()

        expect:
        assert bankcustomer.ServiceCounter.IServiceCounter.count() == 0
        assert serviceCounters.target.size() == 0
    }

    void "should return list of service counter list"() {

        assert bankcustomer.ServiceCounter.IServiceCounter.count() == 0
        given:
        createServiceCounterList()
        def serviceCounters = service.getServiceCounterList()

        expect:
        assert bankcustomer.ServiceCounter.IServiceCounter.count() == 2
        assert serviceCounters.target.size() == 2
        assert serviceCounters.target[0].name == "S1"
        assert serviceCounters.target[1].name == "S2"
    }

    void "should return list of tokens for service counter"() {

        assert CustomerToken.count() == 0
        given:
        createToken()
        def customerTokens = service.getTokensForServiceCounter("S1")

        expect:
        assert CustomerToken.count() == 1
        assert customerTokens.target.size() == 1
        assert customerTokens.target[0].tokenNumber == 1
    }

    void createServiceCounterList() {

        Bank bank = new Bank(name: "ABC")
        Branch branch = new Branch(branchName: "beghumpeth", bank: bank, ifscCode: "BANK001")

        ServiceCounter.IServiceCounter s1 = new ServiceCounter.IServiceCounter(branch: branch, name: "S1", counterType: EntityType.PREMIUM)
        s1.save()
        ServiceCounter.IServiceCounter s2 = new ServiceCounter.IServiceCounter(branch: branch, name: "S2", counterType: EntityType.PREMIUM)
        s2.save()

    }

    void createToken() {
        createServiceCounterList()
        List<ServiceCounter.IServiceCounter> serviceCounters = bankcustomer.ServiceCounter.IServiceCounter.findAll()

        def token = new CustomerToken(tokenNumber: 1, status: "CREATED", currDate: new Date())
        token.addToServiceCounter(serviceCounters.get(0))
        token.addToServiceCounter(serviceCounters.get(1))
        token.nextServiceCounter = serviceCounters.get(0)
        token.customer = createCustomer()
        token.save(failOnError: true)
    }

    def createCustomer() {
        def customer = new Customer(
                name: "Abdul",
                phoneNumber: "3952579267",
                address: "Hyderabad",
                serviceType: EntityType.getEntityType("regular")
        )
        customer.save(failOnError: true)
        return customer
    }
}
