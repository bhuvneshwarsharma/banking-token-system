package bankcustomer

import Bank.Token.TokenProcessor
import bankcustomer.constant.ServiceType

class TokenService {

    def customerService

    def generateToken(phoneNumber, serviceType) {

        def customer = Customer.findByPhoneNumber(phoneNumber)
        if(customer) {

            Long tokenNo = CustomerToken.createCriteria().get {projections {max "tokenNumber"}} as Long
            def token = new CustomerToken(tokenNumber: tokenNo+1, serviceType: ServiceType.getserviceType(serviceType), status: "CREATED", currDate: new Date())

            token.customer = customer

            token.save(failOnError: true)
            TokenProcessor.addTokenToQueue(token)

            return "Generated token successfully. Please go to service counter ${token.nextServiceCounter.name}"

        } else {

            return "Any custommer is not registered with this number, Please create new customer"
        }
    }

    def processToken(counterName) {

        ServiceCounter counter = ServiceCounter.findByName(counterName)
        if(counter) {
            return "Counter ${counter.name} does not exist, Please provide valid name"
        }
        CustomerToken token = TokenProcessor.processToken(counter)

        def message = "Token (${token.tokenNumber}) is processed at ${counterName} for customer ${token.customer.name}"

        if(token.serviceType.equalsIgnoreCase(ServiceType.ACCOUNT)) {
            token.serviceType = ServiceType.ENQUIERY
            token.status = "IN PROGRESS"
            token.save(failOnError: true)

            message = "${message} and next counter is ${ServiceType.ENQUIERY}"

            addTokenToQueue(token)
        } else{

            token.status = "COMPLETED"
            token.save(failOnError: true)
        }

        return message
    }

}
