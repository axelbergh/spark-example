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

## Job server
curl --form "jarFile=@target/scala-2.11/spark-example.jar" jobserver-rc-00.servers.despegar.it:9290/spark-scheduler/jars/spark-example

postman -> http://jobserver-rc-00.servers.despegar.it:9290/spark-scheduler/admin/jobs/spark-example
//TODO: agregar la conf del ejemplo en jobserver