package bankcustomer

/**
 * This controller redirects to index gsp page
 */
class HomeController {

    def index() {

        redirect(controller: "apidoc", action: "getDocuments")
    }

}
