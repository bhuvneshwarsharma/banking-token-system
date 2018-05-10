package Bank.Token

import bankcustomer.CustomerToken

/**
 * Created by bhuvneshwars on 8/5/18.
 */
class TokenProcessor {

    static Map<String, PriorityQueue<CustomerToken>> tokenTaskList= new HashMap<String, PriorityQueue<CustomerToken>>()

    public static void addTokenToQueue(CustomerToken token) {

        String keyName = "${token.nextServiceCounter.branch.branchName}-${token.nextServiceCounter.name}"
        PriorityQueue queue = tokenTaskList.get(keyName)
        if(!queue) {
            queue = new PriorityQueue<CustomerToken>()
        }
        queue.add(token)
        tokenTaskList.put(keyName, queue)
    }

    public static CustomerToken processToken(String keyName) {

        PriorityQueue<CustomerToken> queue = tokenTaskList.get(keyName)
        if(!queue || !queue.size())
            return null

        CustomerToken token = queue.poll()

        tokenTaskList.put(keyName, queue)
        return token
    }
}
