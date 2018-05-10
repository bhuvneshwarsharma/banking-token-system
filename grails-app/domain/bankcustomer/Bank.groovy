package bankcustomer

class Bank {

    static hasMany = [branch: Branch]

    String name

    static mapWith = "mongo"

    static constraints = {

        name (required: true, nullable: false, maxSize: 100)
    }
}
