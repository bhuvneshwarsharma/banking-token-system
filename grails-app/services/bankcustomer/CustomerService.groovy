package bankcustomer

import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    def getCustomer(def phoneNumber) {

        def customer = Customer.findByPhoneNumber(phoneNumber)
        customer? customer as JSON : null
    }

    def createCustomer(def customer) {

        customer.save()
    }
}
