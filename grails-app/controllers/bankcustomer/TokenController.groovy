package bankcustomer

import grails.converters.JSON
import grails.validation.ValidationException
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.Example
import io.swagger.annotations.ExampleProperty
import org.grails.web.converters.exceptions.ConverterException

@Api(tags = ["Token"], description = "APIs for token generation and process")
class TokenController {

    def tokenService

    /**
     * This method will generate token for existing user
     * @return message
     */
    @ApiOperation(
            value = 'Generate new token',
            nickname = 'token',
            produces = 'application/json',
            consumes = 'application/json',
            httpMethod = 'POST'
    )
    @ApiResponses([
            @ApiResponse(code = 404,
                    message = 'Method Not Found'
            ),
            @ApiResponse(code = 500,
                    message = 'ValidationException / ConverterException'
            )
    ])
    @ApiImplicitParams([
            @ApiImplicitParam(name = "token",
                    paramType = "body",
                    required = true,
                    value = "Json data",
                    dataType = "string",
                    examples = @Example(@ExampleProperty(value = """
                    {\nphoneNumber: "74527529",\n\tbranchName: "beghumpeth",\n\tmulti: false\n}
                    """))
            )
    ])
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
    @ApiOperation(
            value = 'Process Token',
            nickname = 'users',
            produces = 'application/json',
            consumes = 'application/json',
            httpMethod = 'PUT'
    )
    @ApiResponses([
            @ApiResponse(code = 500,
                    message = 'ValidationException / ConverterException'
            )
    ])
    @ApiImplicitParams([
            @ApiImplicitParam(name = "token",
                    paramType = "body",
                    required = true,
                    value = "Json data",
                    dataType = "string",
                    examples = @Example(@ExampleProperty(value = """
                    {\ncounterName: "S1",\n\tbranchName: "beghumpeth"\n}
                    """))
            )
    ])
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
