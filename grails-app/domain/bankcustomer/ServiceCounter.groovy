package bankcustomer

class ServiceCounter {

    static belongsTo = [branch: Branch]
    static hasMany = [customerToken: CustomerToken]
    static mappedBy = [customerToken: 'serviceCounter']

    String name
    String counterType

    static mapWith = "mongo"

    static constraints = {

        name (required: true, nullable: false, maxSize: 100)
        counterType (required: true, nullable: false, maxSize: 100)
    }
}
