package bankcustomer

class UrlMappings {

    static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }

        "/"(controller: 'home')
        "/index"(controller: 'home')

        "/serviceCounter"(controller: 'serviceCounter') {
            action = [
                    GET: "getServiceCounterList"
            ]
        }

        "/serviceCounter/$counterName/tokens"(controller: 'serviceCounter') {
            action = [
                    GET: "getTokensForServiceCounter"
            ]
        }

        "/dummyData"(controller: 'test') {
            action = [
                    GET: "createBankDummyData"
            ]
        }

        "/customer/$phoneNumber"(controller: 'customer') {
            action = [
                    GET: "getCustomer"
            ]
        }

        "/customer"(controller: 'customer') {
            action = [
                    GET: "getCustomerList",
                    POST: "CreateCustomer"
            ]
        }

        "/token"(controller: 'token') {
            action = [
                    POST: "generateToken",
                    PUT: "processTokenOnCounter"
            ]
        }

        "/apidoc/$action?/$id?"(controller: "apiDoc", action: "getDocuments")

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
