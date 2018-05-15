package bankcustomer.constant

enum EntityType {

    PREMIUM,
    REGULAR

    static final Map entityTypeMap = [
            'premium' : PREMIUM,
            'regular' : REGULAR
    ]

    static String getEntityType(String entityType) {

        def entityTypeObj = entityTypeMap.find { it.key == entityType.toLowerCase() }?.value;
        return entityTypeObj?.toString();
    }

}
