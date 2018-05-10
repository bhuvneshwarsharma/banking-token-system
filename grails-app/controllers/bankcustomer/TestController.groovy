package bankcustomer

class TestController {

    def testService

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
