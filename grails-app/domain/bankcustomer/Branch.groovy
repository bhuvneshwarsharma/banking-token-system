package bankcustomer

import bankcustomer.ServiceCounter

class Branch {

    static hasMany = [serviceCounters: ServiceCounter]
    static belongsTo = [bank: Bank]

    String ifscCode
    String branchName
//    Set<ServiceCounter> serviceCounters

    static mapWith = "mongo"

    static constraints = {

        branchName (required: true, nullable: false, maxSize: 50)
        ifscCode (unique: true, required: true, nullable: false, maxSize: 20)
    }
}
