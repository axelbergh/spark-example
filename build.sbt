organization := "com.despegar.p13n"

name := "spark-example"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers ++= Seq(
    "Mariot Chauvin" at "http://mchv.me/repository",
    "Codahale" at "http://repo.codahale.com",
    "Typesafe" at "http://repo.typesafe.com",
    "SnowPlow" at "http://maven.snplow.com/releases",
    "Maven Central Repo" at "http://search.maven.org"
    )

libraryDependencies ++= List(
        "org.scalatest" %% "scalatest" % "2.2.4" % "test",
        "org.apache.spark" %% "spark-core" % "1.6.1" withSources() withJavadoc(),
        "joda-time" % "joda-time" % "2.9.2" withSources() withJavadoc()
        )
        
EclipseKeys.withSource := true

test in assembly := {}

assemblyMergeStrategy in assembly := {
                case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
                case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
                case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
                case "application.conf"                            => MergeStrategy.concat
                case PathList("com", "esotericsoftware", xs @ _*)       => MergeStrategy.first
                case PathList("com", "google", "common", "base", xs @ _*)       => MergeStrategy.first
                case PathList("org", "apache", "commons", "beanutils", xs @ _*)       => MergeStrategy.first
                case PathList("org", "apache", "commons", "collections", xs @ _*)       => MergeStrategy.first
                case PathList("org", "apache", "hadoop", "yarn", xs @ _*)       => MergeStrategy.first
                case PathList("org", "apache", "spark", "unused", xs @ _*)       => MergeStrategy.first
                case x => (assemblyMergeStrategy in assembly).value(x)
}

assemblyJarName in assembly := "spark-example.jar"

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)
