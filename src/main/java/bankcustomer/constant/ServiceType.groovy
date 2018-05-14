package bankcustomer.constant

enum ServiceType {

    ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    ENQUIERY

    static final Map serviceTypeMap = [
            'account' : ACCOUNT,
            'deposit' : DEPOSIT,
            'withdraw' : WITHDRAW,
            'enquiery' : ENQUIERY
    ]

    static String getserviceType(String serviceType) {

        def serviceTypeObj = serviceTypeMap.find { it.key == serviceType.toLowerCase() }?.value;
        return serviceTypeObj?.toString();
    }

    static ServiceType getService(String serviceType) {

        return serviceTypeMap.find { it.key == serviceType.toLowerCase() }?.value;
    }

}
