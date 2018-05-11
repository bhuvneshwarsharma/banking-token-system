package bankcustomer

import grails.converters.JSON

class CustomerService {

    def getCustomer(def phoneNumber) {

        def customer = Customer.findByPhoneNumber(phoneNumber)
        customer? customer as JSON : null
    }

    def getCustomerList() {

        def customerList = Customer.findAll()
        customerList as JSON
    }

    def createCustomer(def customer) {

        customer.save(failOnError: true)
    }
}
