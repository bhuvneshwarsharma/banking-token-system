package bankcustomer


class CustomerToken {

    static hasMany = [serviceCounter: ServiceCounter]
    static belongsTo = [customer: Customer, serviceCounter: ServiceCounter]
    static mappedBy = [serviceCounter : 'customerToken']

    Integer tokenNumber
    Date currDate
    String status
    ServiceCounter nextServiceCounter

    static mapWith = "mongo"

    static constraints = {

        //unique: true,
        tokenNumber (required: true, nullable: false)
    }

    def beforeInsert() {

        currDate = new Date()
    }
}
