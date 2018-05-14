package bankcustomer


class CustomerToken {

    static belongsTo = [customer: Customer]

    Integer tokenNumber
    Date currDate
    String status
    String serviceType
//    Customer customer

    static mapWith = "mongo"

    static constraints = {

        //unique: true,
        tokenNumber (required: true, nullable: false)
    }

    def beforeInsert() {

        currDate = new Date()
    }
}
