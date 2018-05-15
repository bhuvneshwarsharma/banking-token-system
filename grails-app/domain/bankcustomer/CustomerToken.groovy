package bankcustomer


class CustomerToken {

    static belongsTo = [customer: Customer]

    Integer tokenNumber
    Date currDate
    String status
    String serviceType

    static mapWith = "mongo"

    static constraints = {

        tokenNumber (unique: true, required: true, nullable: false)
    }

    def beforeInsert() {

        currDate = new Date()
    }
}
