package bankcustomer

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

        } catch(Exception e) {
            render "Facing issue while creating customer : \n\n"+e.getMessage()
        }
    }
}
