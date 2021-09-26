# connected-cars-consumer
 Consume Data From Kafka Topics
  
 
 # Kafka Setup
 
 Download the Kafka https://kafka.apache.org/downloads

# Start the Zookeeper-
\kafka\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties

# Start the Zookeeper-
\kafka\bin\windows>kafka-server-start.bat ..\..\config\server.properties

# Create a kafka topic
\kafka\bin\windows>
kafka-topics.bat --create --topic connected-cars-topic --zookeeper localhost:2181 --partitions 3 --replication-factor 1

# To Check the Kafka Published Messages
\kafka\bin\windows>kafka-console-consumer.bat --bootstrap-server LT167-Revanas.aditiconsulting.com:9092 --topic connected-cars-topic
