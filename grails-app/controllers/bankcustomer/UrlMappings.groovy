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

        "/dummyData"(controller: 'test') {
            action = [
                    GET: "createBankDummyData"
            ]
        }

        "/customer"(controller: 'customer') {
            action = [
                    GET: "getCustomer",
                    POST: "CreateCustomer"
            ]
        }

        "/token/generate"(controller: 'token') {
            action = [
                    GET: "generateToken"
            ]
        }
        "/token/process"(controller: 'token') {
            action = [
                    GET: "processTokenOnCounter"
            ]
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
