package bankcustomer

import bankcustomer.constant.EntityType
import bankcustomer.constant.ServiceType

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

        ServiceCounter s1 = new ServiceCounter(branch: branches[0], name: "D1", serviceType:ServiceType.DEPOSIT, counterType: EntityType.PREMIUM)
        s1.save()
        ServiceCounter s2 = new ServiceCounter(branch: branches[0], name: "D2", serviceType:ServiceType.DEPOSIT, counterType: EntityType.REGULAR)
        s2.save()
        ServiceCounter s3 = new ServiceCounter(branch: branches[0], name: "D3", serviceType:ServiceType.DEPOSIT, counterType: EntityType.REGULAR)
        s3.save()
        ServiceCounter s4 = new ServiceCounter(branch: branches[0], name: "W1", serviceType:ServiceType.WITHDRAW, counterType: EntityType.PREMIUM)
        s4.save()
        ServiceCounter s5 = new ServiceCounter(branch: branches[0], name: "W2", serviceType:ServiceType.WITHDRAW, counterType: EntityType.REGULAR)
        s5.save()
        ServiceCounter s6 = new ServiceCounter(branch: branches[0], name: "W3", serviceType:ServiceType.WITHDRAW, counterType: EntityType.REGULAR)
        s6.save()
        ServiceCounter s7 = new ServiceCounter(branch: branches[0], name: "A1", serviceType:ServiceType.ACCOUNT, counterType: EntityType.PREMIUM)
        s7.save()
        ServiceCounter s8 = new ServiceCounter(branch: branches[0], name: "A2", serviceType:ServiceType.ACCOUNT, counterType: EntityType.REGULAR)
        s8.save()
        ServiceCounter s9 = new ServiceCounter(branch: branches[0], name: "E1", serviceType:ServiceType.ENQUIERY, counterType: EntityType.REGULAR)
        s9.save()

        ServiceCounter s11 = new ServiceCounter(branch: branches[1], name: "A11", serviceType:ServiceType.ACCOUNT, counterType: EntityType.REGULAR)
        s11.save()
        ServiceCounter s12 = new ServiceCounter(branch: branches[1], name: "E11", serviceType:ServiceType.ENQUIERY, counterType: EntityType.REGULAR)
        s12.save()
        ServiceCounter s13 = new ServiceCounter(branch: branches[0], name: "D11", serviceType:ServiceType.DEPOSIT, counterType: EntityType.REGULAR)
        s13.save()
        ServiceCounter s14 = new ServiceCounter(branch: branches[0], name: "W11", serviceType:ServiceType.WITHDRAW, counterType: EntityType.REGULAR)
        s14.save()

        return true
    }

    def createBank() {
        Bank bank = new Bank(name: "ABC")
        bank
    }

    def createBankBranches(bank) {

        Branch branch1 = new Branch(branchName: "beghumpeth", bank: bank, ifscCode: "BANK001")
        Branch branch2 = new Branch(branchName: "bnjarahills", bank: bank, ifscCode: "BANK002")
        [branch1, branch2]
    }
}
