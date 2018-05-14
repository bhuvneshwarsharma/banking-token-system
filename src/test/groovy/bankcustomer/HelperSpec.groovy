package bankcustomer

import bankcustomer.ServiceCounter.IServiceCounter

/**
 * Created by bhuvneshwars on 10/5/18.
 */
import com.github.fakemongo.Fongo
import com.mongodb.MongoClient
import grails.test.mongodb.MongoSpec

class HelperSpec extends MongoSpec {

    List<Class> getDomainClasses() {
        [Bank, Branch, IServiceCounter, Customer, CustomerToken]
    }

    MongoClient createMongoClient() {
        return new Fongo(getClass().name).mongo
    }

}
