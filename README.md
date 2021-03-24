# Wenance challenge
### How to run the App locally
- First download **Docker** and **Docker Compose** (if you don't have them already).
- You will also need **Gradle** in order to compile.
- In the root folder, execute the command `docker-compose up -d` (-d to run in detach mode) in order to have the Mongodb running.
- If you want to run the App with an IDE such as Intellij, a simple 'Run' should suffice.
- If you want to run it through the terminal, in the root folder execute the command
    - `gradle bootRun` or `./gradlew bootRun` if you are on Mac or Linux
    - `gradlew.bat` if you are on Windows
    
### How to operate with the App
- Go to contract folder and there you will find a swagger file in which you will see the endpoints available, and the parameters needed.

### Design of the Challenge
- Developed in Kotlin using Spring Boot as the framework.
- Spring Scheduler to make the recurrent calls to the BuenBit API.
- DDD (Domain Driven Design) was the choice for the project structure and design.
- The persistence layer uses MongoDB.
- Each of the operations that were requested is a use case, and communicates through the different layers by using gateways and repositories.
    - The first operation is Bitcoin related.
    - The second operation, to get the average price, you can send in the query params the crypto currency to find as there seemed to be no constraint about this.
    - The last operation returns a paginated response of all the crypto currencies saved.
