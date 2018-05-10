package bankcustomer

class TokenController {

    def tokenService

    def generateToken() {

        def phoneNumber = params.phoneNumber
        def branchName = params.branchName
        boolean multiCounter = params.multi as Boolean

        def message = tokenService.generateToken(phoneNumber, branchName, multiCounter)
        render message
    }

    def processTokenOnCounter() {

        def counterName = params.counterName
        def branchName = params.branchName
        def message = tokenService.processToken(branchName, counterName)

        render message
    }
}
