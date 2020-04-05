# Exchange house management system

## What's the objective of this project?
To create a software for an exchange house that can be use to keep track of transactions and see real time exchange rates.

## Architecture
It's a Java EE web application.

**Technologies:**

*JBoss Wildfly 19.0.0* - for web server, i am also using for storage proposes the default datasource ExampleDS 
that runs an H2 database that uses only system memory (it will be deleted after server shutdown). Wildfly will
come with the java database connectivity driver for it, so no need to worry about it.

*Jakarta Persistence API* - `JPA is concerned with persistence, which loosely means any mechanism by which Java objects outlive the application process that created them. Not all Java objects need to be persisted, but most applications persist key business objects. The JPA specification lets you define which objects should be persisted, and how those objects should be persisted in your Java applications.`

*Hibernate ORM*

*JavaServer Faces with PrimeFaces*


## What's inside so far?

### Entities:
*ExchangeRate, Transaction**

### EJBs:
*ExchangeRateEjb*

### DTOs:
*ExchangeRateTo, TransactionTo*

### Managed Beans:
*ExchangeRateBean*

### Utils:
*App, UtilDate, UtilToBuild*