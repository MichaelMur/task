Taxi booking center application

This repository contains 2 modules:
- [booking-center](booking-center)
- [taxi-agent](taxi-agent)

Taxi agent is a small spring boot application simulating car behaviour like:
- register itself in booking center
- load orders
- take and complete orders

Booking center is a spring boot web application. 
It provides API for taxi agents and stores all data about orders, cars and statuses

As a building framework Maven is used, so to build all applications just run on root directory
```
mvn install
```

to start booking center:
- start docker compose to start PostgreSQL database
- go to [target](booking-center%2Ftarget) directory of booking-center module
- run ``` java -jar booking-center-1.0-SNAPSHOT.jar```
- it will start web application with API exposed to localhost:8080

to start taxi agent:
- go to [target](taxi-agent%2Ftarget) directory of taxi-agent module
- run ``` java -jar taxi-agent-1.0-SNAPSHOT.jar {driver_id} {driver_name}```
- it will start application and will try to connect to booking center


Here [BT-task](booking-center%2Fsrc%2Fmain%2Fresources%2FBT-task) all API calls examples are strorred