package bankcustomer

import grails.converters.JSON
import grails.validation.ValidationException
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.Example
import io.swagger.annotations.ExampleProperty
import org.grails.web.converters.exceptions.ConverterException

@Api(tags = ["Customer"], description = "APIs for Customer Operation")
class CustomerController {

    def customerService

    /**
     * This method will return customer details for given phone number
     * @return customer data
     */
    @ApiOperation(
            value = 'Get Customer Details',
            nickname = 'customer/{phoneNumber}',
            produces = 'application/json',
            consumes = 'application/json',
            httpMethod = 'GET'
    )
    @ApiResponses([
            @ApiResponse(code = 404,
                    message = 'Customer is not registered for this phoneNumber'
            ),
            @ApiResponse(code = 500,
                    message = 'Error while fetching customer details'
            )
    ])
    @ApiImplicitParams([
            @ApiImplicitParam(name = "phoneNumber",
                    paramType = "path",
                    required = true,
                    value = "Customer's Phone Number",
                    dataType = "string"
            )
    ])
    def getCustomer() {

        try{

            def phoneNumber = params.phoneNumber
            def customer = customerService.getCustomer(phoneNumber)

            if(!customer) {
                response.status = 404
                render ([error:"Customer is not registered for this phoneNumber"]) as JSON
            } else {
                render customer
            }

        } catch(Exception e) {

            log.error(e.getMessage())
            response.status = 500
            render ([error:"Error while fetching customer details"]) as JSON
        }
    }

    /**
     * This method will return list of customers
     * @return customerList
     */
    @ApiOperation(
            value = 'Get Customer List',
            nickname = 'customer',
            produces = 'application/json',
            consumes = 'application/json',
            httpMethod = 'GET'
    )
    @ApiResponses([
            @ApiResponse(code = 500,
                    message = 'Error while fetching users'
            ),
            @ApiResponse(code = 404,
                    message = 'Method Not Found'
            )
    ])
    def getCustomerList() {

        def customerList = customerService.getCustomerList()
        render customerList
    }

    /**
     * This method will return create customer in db
     * @return message
     */
    @ApiOperation(
            value = 'Add a new Custommer',
            nickname = 'customers',
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
            @ApiImplicitParam(name = "customer",
                    paramType = "body",
                    required = true,
                    value = "Json data of customer",
                    dataType = "string",
                    examples = @Example(@ExampleProperty(value = """
                    {\nuser: {\n\tname: "Bhuvi",\n\tphoneNumber: "36393464364",\n\taddress: "Hyderabad",\n\tserviceType: "premium"\n}\n}
                    """))
            )
    ])
    def CreateCustomer() {

        try {

            def customer = new Customer(
                    name: request.JSON.name,
                    phoneNumber: request.JSON.phoneNumber,
                    address: request.JSON.address,
                    serviceType: EntityType.getEntityType(request.JSON.serviceType)
            )

            customerService.createCustomer(customer)
            render "Customer is created successfully"

        }  catch(ValidationException ve) {

            log.error(ve.getMessage())
            response.status = 500
            render ([error:"Constraints are failing for field's value of new customer"]) as JSON
        } catch(ConverterException ce) {

            log.error(ce.getMessage())
            response.status = 500
            render ([error:"Error while converting json data to create new customer"]) as JSON
        } catch(Exception e) {

            log.error(e.getMessage())
            response.status = 500
            render ([error:"Facing issue while creating customer"]) as JSON
        }
    }
}
