package bankcustomer

import Bank.Token.TokenProcessor

class TokenService {

    def customerService

    def generateToken(phoneNumber, branchName, multiCounter) {

        def customer = Customer.findByPhoneNumber(phoneNumber)
        if(customer) {

            def branch = Branch.findByBranchName(branchName)

            if(!branch) {
                return "Please provide valid branch name."
            }
            List<ServiceCounter> serviceCounters = ServiceCounter.findAllByBranchAndCounterType(branch, customer.serviceType)

            Long tokenNo = CustomerToken.createCriteria().get {projections {max "tokenNumber"}} as Long
            def token = new CustomerToken(tokenNumber: tokenNo+1, status: "CREATED", currDate: new Date())

            serviceCounters.sort {
                serviceCounter ->
                    serviceCounter.customerToken?.size()
            }
            if(multiCounter) {

                token.addToServiceCounter(serviceCounters.get(0))
                token.addToServiceCounter(serviceCounters.get(1))
            } else {

                token.addToServiceCounter(serviceCounters.get(0))
            }
            token.nextServiceCounter = serviceCounters.get(0)
            token.customer = customer

            token.save(failOnError: true)
            addTokenToQueue(token)

            return "Generated token successfully. Please go to service counter ${token.nextServiceCounter.name}"

        } else {

            return "Any custommer is not registered with this number, Please create new customer"
        }
    }

    def processToken(branchName, counterName) {

        CustomerToken token = TokenProcessor.processToken("${branchName}-${counterName}")
        if(!token){
            return "Service Counter ${counterName} of branch ${branchName} has no token in queue."
        }
        List<ServiceCounter> serviceCounters = new ArrayList<>(token.serviceCounter);
        ServiceCounter serviceCounter = null
        if(serviceCounters.size()>serviceCounters.indexOf(token.nextServiceCounter)+1)
            serviceCounter = serviceCounters.get(serviceCounters.indexOf(token.nextServiceCounter)+1)

        def message = "Token (${token.tokenNumber}) is processed at ${counterName} for customer ${token.customer.name}"
        if(serviceCounter) {
            token.nextServiceCounter = serviceCounter
            addTokenToQueue(token)
            message = "${message} and next counter is ${serviceCounter.name}"
            token.status = "IN PROGRESS"
        } else {
            token.nextServiceCounter = null
            token.status = "COMPLETED"
        }
        token.save()
        return message
    }

    def addTokenToQueue(CustomerToken token) {

        TokenProcessor.addTokenToQueue(token)
    }
}
