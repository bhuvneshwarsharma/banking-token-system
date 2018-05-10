package bankcustomer

class Branch {

    static hasMany = [books: ServiceCounter]
    static belongsTo = [bank: Bank]

    String ifscCode
    String branchName

    static mapWith = "mongo"

    static constraints = {

        branchName (required: true, nullable: false, maxSize: 50)
        ifscCode (unique: true, required: true, nullable: false, maxSize: 20)
    }
}
