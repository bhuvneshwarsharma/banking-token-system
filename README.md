# Banking Token System
This service to manage customer tokens and to immprove customer experience.

## Setup

### Prerequisites

In order to get started you need the following:

1. JDK 7.0 or higher.
2. Grails 3.3.x+ (Use [SDKMAN](http://sdkman.io/install.html) to install grails.)
3. mongodb

### Setting up Database
Create db with name 'bank', user 'testUser' and password 'password'

When application is up then access url 'localhost:8080/bank/dummyData' which will create dummy data in mongo db like for Bank, Bank Branches, Service Counters .

### Setting up IntelliJ

- Import the project in your IntelliJ using 
    -  `Welcome Screen` > `Import Project` 
        - OR 
    - `File` > `New` > `Project from Existing Sources`
- Select the `banking-token-system` directory you cloned in the previous step 
- Click `OK` to launch the Wizard
- On the first screen, select `Import project from external model` and then select `Gradle`
- Click `Next`
- Point the `Gradle Project` option to `build.gradle`
- Click `Finish`


### APIs to use
'URL End Point' - 'Method Type' - 'Query Param' - 'Description'

'/dummyData' - 'GET' : It will create dummy data for application to work

'/customer/$phoneNumber'  - 'GET' - It will return customer details 

'/customer' - 'POST' - It will create customer in db. Need to send attributes like name,phoneNumber,address,serviceType

'/token' - 'POST' - it will generate token for customer if exist with given phone no. Attributes - phoneNumber,branchName,multi

'/token' - 'PUT' - It will process token. Attributes like counterName,branchName

'/serviceCounter' - 'GET' - Return all service acounters list

'/serviceCounter/$counterName/tokens' - 'GET' - return all tokens for service counter

