package bankcustomer

import grails.converters.JSON
import grails.validation.ValidationException
import org.grails.web.converters.exceptions.ConverterException

class TokenController {

    def tokenService

    /**
     * This method will generate token for existing user
     * @return message
     */
    def generateToken() {

        try {

            def phoneNumber = request.JSON.phoneNumber
            def branchName = request.JSON.branchName
            boolean multiCounter = request.JSON.multi as Boolean

            def message = tokenService.generateToken(phoneNumber, branchName, multiCounter)
            render message

        } catch(ValidationException ve) {

            log.error(ve.getMessage())
            response.status = 500
            render ([error:"Constraints are failing for field's value of new token"]) as JSON
        } catch(ConverterException ce) {

            log.error(ce.getMessage())
            response.status = 500
            render ([error:"Error while converting json data to create new token"]) as JSON
        } catch(Exception e) {

            log.error(e.getMessage())
            response.status = 500
            render ([error:"Facing issue while creating new token"]) as JSON
        }
    }

    /**
     * This method will process token and will add to next
     * service counter queue if it needs
     * @return message
     */
        def processTokenOnCounter() {

        try {

            def counterName = request.JSON.counterName
            def branchName = request.JSON.branchName
            def message = tokenService.processToken(branchName, counterName)

            render message

        } catch(ValidationException ve) {

            log.error(ve.getMessage())
            response.status = 500
            render ([error:"Constraints are failing for field's value"]) as JSON
        } catch(ConverterException ce) {

            log.error(ce.getMessage())
            response.status = 500
            render ([error:"Error while converting json data"]) as JSON
        } catch(Exception e) {

            log.error(e.getMessage())
            response.status = 500
            render ([error:"Facing issue while processing token"]) as JSON
        }
    }
}
