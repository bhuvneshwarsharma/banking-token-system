package bankcustomer

import grails.testing.services.ServiceUnitTest

class CustomerServiceSpec extends HelperSpec implements ServiceUnitTest<CustomerService>{

    def setup() {
    }

    def cleanup() {
    }

    void "Should create new customer"() {

        assert Customer.count() == 0

        given:
        service.createCustomer(customerObj("5436373838"))

        expect:
            assert Customer.count() == 1
    }

    void "Should get customer information"() {

        given:
        service.createCustomer(customerObj("7878777766"))
        def customer = service.getCustomer("7878777766")

        expect:
        assert customer.target.phoneNumber == "7878777766"
        assert customer.target.name == "Abdul"
        assert customer.target.serviceType == "REGULAR"
    }

    void "Should return null if customer does not exist"() {

        given:
        def customer = service.getCustomer("56247141")

        expect:
        assert customer == null
    }

    def customerObj(phoneNumber) {
        def customer = new Customer(
                name: "Abdul",
                phoneNumber: phoneNumber,
                address: "Hyderabad",
                serviceType: EntityType.getEntityType("regular")
        )
        return customer
    }
}
