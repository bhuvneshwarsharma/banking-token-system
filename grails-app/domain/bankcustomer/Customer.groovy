package bankcustomer

class Customer {

    static hasMany = [customerToken: CustomerToken]

    String  name
    String  phoneNumber
    String  address
    String  serviceType

    static mapWith = "mongo"

    static constraints = {

        name (required: true, nullable: false, maxSize: 100)
        phoneNumber (unique: true, required: true, nullable: false, maxSize: 12)
        address (required: true, nullable: false, maxSize: 256)
        serviceType (required: true, nullable: false)
    }
}
