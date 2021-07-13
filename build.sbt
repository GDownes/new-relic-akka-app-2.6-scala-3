lazy val akkaHttpVersion = "10.2.4"
lazy val akkaVersion    = "2.6.14"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.example",
      scalaVersion    := "3.0.0"
    )),
    name := "Akka App 2.6",
    libraryDependencies ++= Seq(
      ("com.typesafe.akka"       %%  "akka-http"                 % akkaHttpVersion).cross(CrossVersion.for3Use2_13),
      ("com.typesafe.akka"       %%  "akka-http-spray-json"      % akkaHttpVersion).cross(CrossVersion.for3Use2_13),
      ("com.typesafe.akka"       %%  "akka-actor-typed"          % akkaVersion).cross(CrossVersion.for3Use2_13),
      ("com.typesafe.akka"       %%  "akka-stream"               % akkaVersion).cross(CrossVersion.for3Use2_13),
      ("com.typesafe.akka"       %%  "akka-remote"               % akkaVersion).cross(CrossVersion.for3Use2_13),
      "ch.qos.logback"           %   "logback-classic"           % "1.2.3",
      "io.netty"                 %   "netty"                     % "3.10.6.Final",
      "com.newrelic.agent.java"  %   "newrelic-api"              % "3.43.0" from()
    )
  )
