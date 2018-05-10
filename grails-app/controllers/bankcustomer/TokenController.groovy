package bankcustomer

class TokenController {

    def tokenService

    /**
     * This method will generate token for existing user
     * @return message
     */
    def generateToken() {

        def phoneNumber = request.JSON.phoneNumber
        def branchName = request.JSON.branchName
        boolean multiCounter = request.JSON.multi as Boolean

        def message = tokenService.generateToken(phoneNumber, branchName, multiCounter)
        render message
    }

    /**
     * This method will process token and will add to next
     * service counter queue if it needs
     * @return message
     */
    def processTokenOnCounter() {

        def counterName = request.JSON.counterName
        def branchName = request.JSON.branchName
        def message = tokenService.processToken(branchName, counterName)

        render message
    }
}
