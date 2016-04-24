# spark-example
Simple spark example

# Ejemplos

## Word count package
sbt package

scripts/runWordCountJob.sh

http://localhost:4040

## Word count fat jar
sbt assembly

scripts/runFatjar.sh

## Streaming
scripts/streamingExample.sh

## Job server
curl --form "jarFile=@target/scala-2.11/spark-example.jar" [jobserverurl]/spark-scheduler/jars/spark-example

postman -> http://[jobserverurl]/spark-scheduler/admin/jobs/spark-example
//TODO: agregar la conf del ejemplo en jobserver