package bankcustomer

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class TokenControllerSpec extends Specification implements ControllerUnitTest<TokenController> {

    def tokenServiceMock
    def setup() {
        tokenServiceMock = new Expando()
        tokenServiceMock.generateToken { phoneNumber, branchName, multiCounter ->
            return "Token is created successfully"
        }
        tokenServiceMock.processToken { branchName, counterName ->
            return "Token is processed successfully"
        }
        controller.tokenService = tokenServiceMock
    }

    def cleanup() {
    }

    void "test generateToken"() {

        when:
        controller.generateToken()
        then:
        response.status  == 200
        response.text == "Token is created successfully"
    }

    void "test processTokenOnCounter"() {

        when:
        controller.processTokenOnCounter()
        then:
        response.status  == 200
        response.text == "Token is processed successfully"
    }
}
