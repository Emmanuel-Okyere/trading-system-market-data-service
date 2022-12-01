##### server port = 8082

## API Endpoints

### Market Data Processing Service
| METHOD | URL        | DESCRIPTION |
|--------|------------|-------------|
| GET    | /api/v1/md | get order   |

### How to set up and run Kafka
1. Download kafka from https://kafka.apache.org/downloads
    1. Start the ZooKeeper service
       #### Windows:
            .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

       #### Mac:
           bin/zookeeper-server-start.sh config/zookeeper.properties
    2. Start the Kafka broker service
       #### Windows:
              ./bin/kafka-server-start.bat ./config/server.properties

       #### Mac:
              bin/kafka-server-start.sh config/server.properties