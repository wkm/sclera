name := "Sclera"

organization := "com.wiktormacura"

//scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
  "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test",
  "org.scala-lang" % "scala-swing" % "2.9.1",
  "org.scala-lang" % "scala-compiler" % "2.9.1",
  "ch.qos.logback" % "logback-classic" % "0.9.30",
  "org.slf4j" % "slf4j-api" % "1.6.2"
)

//fork in run := true
