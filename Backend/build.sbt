name := "Backend"

version := "1.0"

lazy val `backend` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalacOptions += "-Ypartial-unification"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  evolutions,
  jdbc ,
  ehcache ,
  ws ,
  specs2 % Test ,
  guice,
  filters,
  // https://mvnrepository.com/artifact/postgresql/postgresql
  "org.postgresql" % "postgresql" % "42.1.3",
  "com.typesafe.play" %% "play-slick" % "3.0.1",
  "com.typesafe.slick" %% "slick" % "3.2.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % "test",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.typesafe.play" %% "play-json-joda" % "2.6.0-RC1",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0",
  "org.pac4j" %% "play-pac4j" % "6.0.0-RC1",
  "org.joda" % "joda-convert" % "1.7",
  "org.typelevel" %% "cats-core" % "1.2.0"


)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

