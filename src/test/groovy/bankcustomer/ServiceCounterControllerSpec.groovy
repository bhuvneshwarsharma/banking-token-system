package bankcustomer

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ServiceCounterControllerSpec extends Specification implements ControllerUnitTest<ServiceCounterController> {

    def serviceCounterServiceMock
    def setup() {
        serviceCounterServiceMock = new Expando()
        serviceCounterServiceMock.getServiceCounterList = { ->

        }
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
