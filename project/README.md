# Exchange house management system

## What's the objective of this project?
To create a software for an exchange house that can be use to keep track of transactions and see real time exchange rates.

## Architecture
It's a Java EE web application.

**Technologies:**

*JBoss Wildfly 19.0.0* - for web server, I am also using for storage proposes the default datasource ExampleDS 
that runs an H2 database that uses only system memory (it will be deleted after server shutdown). Wildfly will
come with the java database connectivity driver for it, so no need to worry about it.

*Jakarta Persistence API* - `JPA is concerned with persistence, which loosely means any mechanism by which Java objects outlive the application process that created them. Not all Java objects need to be persisted, but most applications persist key business objects. The JPA specification lets you define which objects should be persisted, and how those objects should be persisted in your Java applications.`

*Dynamic Transfer Object* - When you are working directly with entities many wrong things can happen, so I am going to use the DTO concept when i need to do work with entities.

*Hibernate ORM*

*JavaServer Faces with PrimeFaces*


## What's inside so far?

### Entities:
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.abstractClass@2x.png" width="15" height="15"/> BasicEntity<br/>
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.class@2x.png" width="15" height="15"/> ExchangeRate<br/>
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.class@2x.png" width="15" height="15"/> Transaction<br/>

### Static meta model
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.abstractClass@2x.png" width="15" height="15"/> ExchangeRate_<br/>
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.abstractClass@2x.png" width="15" height="15"/> BasicEntity_<br/>

### EJBs:
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.class@2x.png" width="15" height="15"/> ExchangeRateEjb

### TOs:
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.class@2x.png" width="15" height="15"/> ExchangeRateTo<br/>
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.class@2x.png" width="15" height="15"/> TransactionTo<br/>

### Managed Beans:
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.class@2x.png" width="15" height="15"/> ExchangeRateBean

### Utils:
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.class@2x.png" width="15" height="15"/> App<br/>
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.abstractClass@2x.png" width="15" height="15"/> UtilDate<br/>
<img src="https://resources.jetbrains.com/help/img/idea/2019.3/Groovy.icons.groovy.abstractClass@2x.png" width="15" height="15"/> UtilToBuild<br/>