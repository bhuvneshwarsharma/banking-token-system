package bankcustomer

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

@Api(tags = ["ServiceCounter"], description = "APIs for service counter")
class ServiceCounterController {

    def serviceCounterService

    /**
     * This method will return list of service counters list
     * @return list
     */
    @ApiOperation(
            value = 'Get Service Counter List',
            nickname = 'serviceCounter',
            produces = 'application/json',
            consumes = 'application/json',
            httpMethod = 'GET'
    )
    def getServiceCounterList() {

        render serviceCounterService.getServiceCounterList()
    }

    /**
     * This method will return list of tokens for service counters
     * @return list
     */
    @ApiOperation(
            value = 'Get list of tokens for service counter',
            nickname = 'serviceCounter/{serviceType}/tokens',
            produces = 'application/json',
            consumes = 'application/json',
            httpMethod = 'GET'
    )
    @ApiImplicitParams([
            @ApiImplicitParam(name = "serviceType",
                    paramType = "path",
                    required = true,
                    value = "Service Type",
                    dataType = "string"
            )
    ])
    def getTokensForServiceCounter() {

        String serviceType = params.serviceType
        def tokens = serviceCounterService.getTokensForServiceCounter(serviceType)
        render tokens
    }
}
