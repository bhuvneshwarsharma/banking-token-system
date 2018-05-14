package bankcustomer

class Bank {

    static hasMany = [branches: Branch]

    String name
//    Set<Branch> branches

    static mapWith = "mongo"

    static constraints = {

        name (required: true, nullable: false, maxSize: 100)
    }
}
