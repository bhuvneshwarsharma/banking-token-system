package bankcustomer

import grails.converters.JSON
import grails.validation.ValidationException
import org.grails.web.converters.exceptions.ConverterException

class CustomerController {

    def customerService

    /**
     * This method will return customer details for given phone number
     * @return customer data
     */
    def getCustomer() {

        def phoneNumber = params.phoneNumber
        def customer = customerService.getCustomer(phoneNumber)
        render customer? customer : "Customer does not exist with this phone number"
    }

    /**
     * This method will return list of customers
     * @return customerList
     */
    def getCustomerList() {

        def customerList = customerService.getCustomerList()
        render customerList
    }

    /**
     * This method will return create customer in db
     * @return message
     */
    def CreateCustomer() {

        try {

            def customer = new Customer(
                    phoneNumber: request.JSON.phoneNumber,
                    name: request.JSON.name,
                    address: request.JSON.address,
                    serviceType: EntityType.getEntityType(request.JSON.serviceType)
            )

            customerService.createCustomer(customer)
            render "Customer is created successfully"

        }  catch(ValidationException ve) {

            log.error(ve.getMessage())
            response.status = 500
            render ([error:"Constraints are failing for field's value of new customer"]) as JSON
        } catch(ConverterException ce) {

            log.error(ce.getMessage())
            response.status = 500
            render ([error:"Error while converting json data to create new customer"]) as JSON
        } catch(Exception e) {

            log.error(e.getMessage())
            response.status = 500
            render ([error:"Facing issue while creating customer"]) as JSON
        }
    }
}
