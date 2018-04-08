lazy val commonSettings = Seq(
  organization := "cen.alpha",
  version := "0.1",
  scalaVersion := "2.12.5",
  scalacOptions ++= Seq(
    "-encoding", "UTF-8",
    "-target:jvm-1.8",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Xfuture",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Ywarn-unused"
  ),
  libraryDependencies ++= {
    val scalaTestVersion = "3.0.5"
    val scalaMeterVersion = "0.9"
    val spireVersion = "0.14.1"
    val dsiUtilsVersion = "2.4.2"
    Seq(
      "com.storm-enroute" %% "scalameter" % scalaMeterVersion % "it",
      "org.typelevel" %% "spire" % spireVersion % "test",
      "it.unimi.dsi" % "dsiutils" % dsiUtilsVersion % "test",
      "org.scalatest" %% "scalatest" % scalaTestVersion % "test,it"
    )
  }
)

lazy val random = (project in file(".")).
  settings(commonSettings).
  configs(IntegrationTest).
  settings(Defaults.itSettings).
  settings(
    name := "random"
  )
