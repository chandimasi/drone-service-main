
## Drones Service  API
## Run the application
Run DroneServiceMainApplication class or
mvn spring-boot:run

## Limitation
Drone can be carried only one medication

## Usage
#### Registering a drone 
\<IP:PORT\>/drones

```sh
curl -X POST http://localhost:8080/drones 
    -H "Content-Type: application/json" 
    -d '{   "serialNumber": "sss900",
            "model":"Cruiserweight",
            "weightLimit":690,
            "batteryCapacity":100,
            "state":"IDLE"}' 
   
```
----


#### Loading a drone with medication items
\<IP:PORT\>/drones/{droneId}/load/{medicationId}

```sh
curl -X GET http://localhost:8080/drones/100/load/1
   
```
----

#### Checking loaded medication items for a given drone
\<IP:PORT\>/drones/{droneId}/medicationItems

```sh
curl -X GET http://localhost:8080/drones/100/medicationItems
   
```
----


#### Checking available drones for loading
\<IP:PORT\>/availabledrones

```sh
curl -X GET http://localhost:8080/availabledrones
   
```
----


#### check drone battery level for a given drone
\<IP:PORT\>/batterylevel/{droneId}

```sh
curl -X GET http://localhost:8080/batterylevel/100
   
```
----
 **END** 


