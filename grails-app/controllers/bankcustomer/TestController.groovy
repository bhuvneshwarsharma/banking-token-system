package bankcustomer

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses

@Api(tags = ["Dummy Data"], description = "APIs to create dummy data")
class TestController {

    def testService

    @ApiOperation(
            value = 'Create dummy data',
            nickname = 'dummyData',
            produces = 'application/json',
            consumes = 'application/json',
            httpMethod = 'GET'
    )
    def createBankDummyData() {

        try{
            def created = testService.createBankDummyData()
            if(created) {
                render "Dummy data is created successfully"
            } else {
                render "Dummy data is already available in mongo db"
            }
        } catch(Exception e) {
            render "Unable to create dummy data"
        }
    }
}
