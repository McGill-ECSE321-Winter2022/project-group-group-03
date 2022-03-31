# Welcome to Group 03's Online Grocery Store

[![Java CI with Gradle](https://github.com/McGill-ECSE321-Winter2022/project-group-group-03/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/McGill-ECSE321-Winter2022/project-group-group-03/actions/workflows/main.yml)

## Project Scope
This is an online Grocery Store Software System made for Dr. Kanaan's Grocery Store, using the Agile methodology. 
This Software System allows customers to create an account which they can use to buy products from their favourite grocery store. 
Customers can buy products from the ease of their home by having it delivered to their address. However, an option for them to pick up their groceries from 
an in-store location is also available if they wish to do so. The system will be mainly managed by the owner Dr. Kanaan. He can hire and fire employees from this system, while also allowing him to manage his employee's schedules. 
He can also decide the business hours of the store as well as occasions of holidays. 

## Deploying the project as a Heroku application
### Backend 


 ##### Backend App Name:
grocerystore-backend-ise2022w 
##### prod-database:
- `spring.datasource.url`:jdbc:postgresql://ec2-3-91-127-228.compute-1.amazonaws.com:5432/daaah13ecc2e9c   
- `spring.datasource.username`:vdgjvdmjzreell   
- `spring.datasource.password`: 1766bd0bda93b6655add493bb145aa4dd24414a48a00e8466b9c95b9774f155c

##### test-database:
- `spring.datasource.url`:jdbc:postgresql://ec2-34-224-226-38.compute-1.amazonaws.com:5432/dt1ltla077q2v   
- `spring.datasource.username`:glxeofrnzvwwyi
- `spring.datasource.password`: 0e03c6d3baef79a1dc4fbbb5cee69313ce7d611681861983700930416cb53920


##### How to run this project
To run the project, clone this repository and run the java file `GroceryStoreBackendApplication.java`  \
locally as a  `Spring Boot Application`. In order to choose which database the application runs on, open `application.properties` and set `spring.profiles.default` =test or =prod depending on which one you want to use.

This will deploy the backend [here](http://localhost:8080/) locally.

Heroku hosts the backend [here](https://grocerystore-backend-ise2022w.herokuapp.com/) once deployed.

## Team Members 
| Name| Major|Team Role| Year |GitHub|
| :--- |:---: |:---: | :---:| :---:|
|Abhijeet Praveen | Computer Engineering| Project Manager|U2| [abhijeetpraveen](https://github.com/abhijeetpraveen)|
|Ari Arabian | Computer Engineering| Testing Engineer|U2|[AriA700](https://github.com/AriA700)|
|Edward Habelrih     | Computer Engineering| Testing Engineer|U2|[edwardhab](https://github.com/edwardhab)|
|Neel Faucher | Computer Engineering| Documentation Manager| U2|[NeelFaucher](https://github.com/NeelFaucher)|
|Rooshnie Velautham | Computer Engineering| Software Engineer| U2|[rooshnie23](https://github.com/rooshnie23)|
|Sébastien Cantin    | Computer Engineering | Software Engineer|U2 |[seb8stien1](https://github.com/seb8stien1)|

## Deliverable 1 

#### Tasks and Time Spent
| Name| Tasks|Time Spent (hrs)|
| :--- |--- |:---: |
|Abhijeet Praveen |<ul><li> Participated in finishing the class diagram<li> Participated in finishing state diagram<li> Setup initial JPA tags<li>Helped debug JPA tags errors<li>Completed use-case diagram separation into two<li>Started and continuously updated project README.md<li>Started project wiki and deliverable 1 report in the correct format<li>Helped write unit tests for the persistence layer<li>Helped debug errors during testing of persistence layer<li>Participated in formulating 15 most important functional and non-functional requirements<li>Completed use case specification for searching and purchasing items online<li>Helped clean up umple generated code<li>Assured backlog is being maintained<li>Assured checklist from deliverable instructions have been met</ul>|37| 
|Ari Arabian |<ul><li>Contributed ideas to the class diagram<li>Helped debug JPA tags<li>Setup the Spring-based backend<li>Setup the database, setup application.properties<li>Helped setup CRUD repositories<li>Helped write unit tests for the persistence layer<li>Completed use case specification for adding items to inventory<li>Helped clean up umple generated code</ul>| 37|
|Edward Habelrih|<ul><li>Contributed and participated in conceiving and building the class diagram<li>Setup all CRUD repositories<li>Setup persistence layer<li>Coded and mapped all unit tests for the persistence layer<li>Setup Spring-based backend<li>Setup application properties<li>Helped debug JPA tags<li>Completed the use case specification for viewing weekly schedules</ul>| 37|
|Neel Faucher |<ul><li>Completed use case specification for hiring and firing employees<li>Helped team members fix their use case specifications<li>Took meeting minutes for all 4 meetings<li>Helped setup repository<li>Wrote wiki for deliverable 1<li>Completed the UML class diagram<li>Helped with testing of persistence layer<li>Helped clean up umple generated code</ul>| 25|
|Rooshnie Velautham|<ul><li>Helped to design the UML class diagram and UML state diagram<li>Setup the initial JPA tags<li>Helped debug JPA tags errors<li>Completed use-case diagram<li>Project wiki and deliverable 1 report in the correct format<li>Added all the functional and non-functional requirements as issues<li>Distributed all the issues to members of the group<li>Assure that that the backlog is up-to-date<li>Helped debug errors during testing of persistence layer<li>Participated in formulating 15 most important functional and non-functional requirements<li>Completed use case scenario specification for a user creating an account<li>Helped clean up umple generated code<li>Wrote testing documentation on wiki and README.md<li>Made sure that everything from the correcting scheme is done neatly</ul>| 29|
|Sébastien Cantin|<ul><li>Designed most of the UML class diagram and UML state diagram<li>Wrote the rationale for the UML class diagram<li>Setup the Heroku database<li>Setup JPA tags<li>Helped debugging during testing of persistence layer<li>Setup initial issue on GitHub project view<li>Participated in formulating 15 most important functional and non-functional requirements<li>Started the initial use case diagram<li>Cleaned up umple generated code</ul>| 38|

### The deliverable 1 report can be found [here](https://github.com/McGill-ECSE321-Winter2022/project-group-group-03/wiki/Deliverable-1-Report)

## Deliverable 2 

#### Tasks and Time Spent
| Name| Tasks|Time Spent (hrs)|
| :--- |--- |:---: |
|Abhijeet Praveen |<ul><li>Started and continuously updated project README.md<li>Started project wiki and deliverable 2 report in the correct format<li>Helped other team members write unit tests for the service layer<li>Helped debug errors during testing of service layer<li>Helped update unit tests for the persistence layer<li>Helped debug errors during testing of persistence layer<li>Updated model classes in accordance with the service methods<li>Wrote the DTO classes for `BusinessHour` and `Item` <li>Wrote the service classes for `BusinessHour` and `Item`<li>Wrote the REST controller classes for `BusinessHour` and `Item`<li>Wrote the `TestService` classes for `BusinessHour` and `Item`<li>Assured checklist from deliverable instructions have been met<li>Assured backlog is being maintained<li>Added Gradle task for running integration tests<li>Ran Integration tests on Postman</ul>|77| 
|Ari Arabian |<ul><li>Helped other team members write unit tests for the service layer<li>Helped debug errors during testing of service layer<li>Helped update unit tests for the persistence layer<li>Helped debug errors during testing of persistence layer<li>Updated model classes in accordance with the service methods<li>Wrote the DTO classes for `DeliveryOrder` and `PickupOrder` <li>Wrote the service classes for `DeliveryOrder` and `PickupOrder`<li>Wrote the REST controller classes for `DeliveryOrder` and `PickupOrder`<li>Wrote the `TestService` classes for `DeliveryOrder` and `PickupOrder`<li>Assured checklist from deliverable instructions have been met<li>Ran Integration tests on Postman</ul>| 74|
|Edward Habelrih| <ul><li>Helped other team members write unit tests for the service layer<li>Helped debug errors during testing of service layer<li>Helped update unit tests for the persistence layer<li>Helped debug errors during testing of persistence layer<li>Updated model classes in accordance with the service methods<li>Wrote the DTO classes for `Customer` and `Store` <li>Wrote the service classes for `Customer` and `Store`<li>Wrote the REST controller classes for `Customer` and `Store`<li>Wrote the `TestService` classes for `Store` and `Customer`<li>Assured checklist from deliverable instructions have been met<li>Ran Integration tests on Postman</ul>|74|
|Neel Faucher |<ul><li>Helped other team members write unit tests for the service layer<li>Helped debug errors during testing of service layer<li>Helped update unit tests for the persistence layer<li>Helped debug errors during testing of persistence layer<li>Updated model classes in accordance with the service methods<li>Wrote the DTO classes for `WorkShift` and `PurchasedItem` <li>Wrote the service classes for `PurchasedItem`<li>Wrote the REST controller classes for `PurchasedItem` and `WorkShift`<li>Wrote the `TestService` classes for `PurchasedItem`<li>Assured checklist from deliverable instructions have been met<li>Ran Integration tests on Postman<li>Reported the Software Quality Assurance in the wiki<li>Reported the RESTful service endpoints in the wiki<li>Reported the Test Plan in the wiki</ul>| 72|
|Rooshnie Velautham|<ul><li>Helped other team members write unit tests for the service layer<li>Helped debug errors during testing of service layer<li>Helped update unit tests for the persistence layer<li>Helped debug errors during testing of persistence layer<li>Updated model classes in accordance with the service methods<li>Wrote the DTO classes for `Holiday`<li>Wrote the service classes for `Holiday` and `WorkShift` <li>Wrote the REST controller classes for `Holiday`<li>Wrote the `TestService` classes for `Holiday` and `WorkShift`<li>Assured checklist from deliverable instructions have been met<li>Ran Integration tests on Postman<li>Added the Test coverage report dependencies<li>Started and continuously updated project README.md<li>Started project wiki and deliverable 2 report in the correct format<li>Reported the Software Quality Assurance in the wiki<li>Reported the RESTful service endpoints in the wiki<li>Reported the Test Plan in the wiki</ul>| 73|
|Sébastien Cantin|<ul><li>Continuously updated project README.md<li>Helped other team members write unit tests for the service layer<li>Helped debug errors during testing of service layer<li>Helped update unit tests for the persistence layer<li>Helped debug errors during testing of persistence layer<li>Updated model classes in accordance with the service methods<li>Wrote the DTO classes for `Owner` and `Employee` <li>Wrote the service classes for `Owner` and `Employee`<li>Wrote the REST controller classes for `Owner` and `Employee`<li>Wrote the `TestService` classes for `Owner` and `Employee`<li>Assured checklist from deliverable instructions have been met<li>Assured backlog is being maintained<li>Fixed `application.properties`<li>Wrote `application-prod.properties`<li>Wrote `application-test.properties`<li>Added Gradle task for running integration tests<li>Ran Integration tests on Postman</ul>| 76|


### The deliverable 2 report can be found [here](https://github.com/McGill-ECSE321-Winter2022/project-group-group-03/wiki/Deliverable-2-Report)

## Deliverable 3 

### Tasks and Time Spent
| Name| Tasks|Time Spent (hrs)|
| :--- |--- |:---: |
|Abhijeet Praveen |<ul><li>Started README.md for Deliverable 3<li>Helped formulate Software Architecture</li></li><li>Setup Frontend with Vue.js</li><li>Completed login and signup pages on frontend</li></ul>|| 
|Ari Arabian |<ul></ul>||
|Edward Habelrih| <ul></ul>||
|Neel Faucher |<ul></ul>| |
|Rooshnie Velautham|<ul><li>Completed most of Software Architecture </li><li>Implemented Profile page in frontend</li></ul>| |
|Sébastien Cantin|<ul></ul>| |

### The deliverable 3 report can be found [here](https://github.com/McGill-ECSE321-Winter2022/project-group-group-03/wiki/Deliverable-3-Report)
