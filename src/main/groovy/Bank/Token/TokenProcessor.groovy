package Bank.Token

import bankcustomer.CustomerToken

/**
 * Created by bhuvneshwars on 8/5/18.
 */
class TokenProcessor {

    static Map<String, PriorityQueue<CustomerToken>> tokenTaskList= new HashMap<String, PriorityQueue<CustomerToken>>()

    /**
     * This method will add customer token to task queue
     * @param token
     */
    public static void addTokenToQueue(CustomerToken token) {

        String keyName = "${token.nextServiceCounter.branch.branchName}-${token.nextServiceCounter.name}"
        PriorityQueue queue = tokenTaskList.get(keyName)
        if(!queue) {
            queue = new PriorityQueue<CustomerToken>()
        }
        queue.add(token)
        tokenTaskList.put(keyName, queue)
    }

    /**
     * This method will process token and will remove from task queue
     * @param keyName
     * @return token
     */
    public static CustomerToken processToken(String keyName) {

        PriorityQueue<CustomerToken> queue = tokenTaskList.get(keyName)
        if(!queue || !queue.size())
            return null

        CustomerToken token = queue.poll()

        tokenTaskList.put(keyName, queue)
        return token
    }
}
