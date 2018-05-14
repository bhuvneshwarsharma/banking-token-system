package bankcustomer

class ServiceCounter {

    static belongsTo = [branch: Branch]

    String name
    String serviceType  // Which service provides like deposit, withdraw, enquiery etc.
    String counterType  // Which customer type serve like premium, regular
//    Branch branch

    static mapWith = "mongo"

    static constraints = {

        name (unique: true, required: true, nullable: false, maxSize: 100)
        serviceType (required: true, nullable: false, maxSize: 100)
        counterType (required: true, nullable: false, maxSize: 100)
    }
}
