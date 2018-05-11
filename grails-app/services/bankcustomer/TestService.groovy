package bankcustomer

class TestService {

    def createBankDummyData() {

        def created = createBankServiceCounters()
    }

    def createBankServiceCounters() {

        def banks = Bank.findAll()
        if(banks.size()>0) {
            return false
        }

        def bank = createBank()
        def branches = createBankBranches(bank)

        ServiceCounter s1 = new ServiceCounter(branch: branches[0], name: "S1", counterType: EntityType.PREMIUM)
        s1.save()
        ServiceCounter s2 = new ServiceCounter(branch: branches[0], name: "S2", counterType: EntityType.PREMIUM)
        s2.save()
        ServiceCounter s3 = new ServiceCounter(branch: branches[0], name: "S3", counterType: EntityType.REGULAR)
        s3.save()
        ServiceCounter s4 = new ServiceCounter(branch: branches[0], name: "S4", counterType: EntityType.REGULAR)
        s4.save()
        ServiceCounter s5 = new ServiceCounter(branch: branches[0], name: "S5", counterType: EntityType.REGULAR)
        s5.save()

        ServiceCounter s11 = new ServiceCounter(branch: branches[1], name: "S11", counterType: EntityType.REGULAR)
        s11.save()
        ServiceCounter s12 = new ServiceCounter(branch: branches[1], name: "S12", counterType: EntityType.REGULAR)
        s12.save()

        return true
    }

    def createBank() {
        Bank bank = new Bank(name: "ABC")
//        bank.save()
        bank
    }

    def createBankBranches(bank) {

        Branch branch1 = new Branch(branchName: "beghumpeth", bank: bank, ifscCode: "BANK001")
        Branch branch2 = new Branch(branchName: "bnjarahills", bank: bank, ifscCode: "BANK002")
//        branch1.save()
//        branch2.save()

        [branch1, branch2]
    }
}
