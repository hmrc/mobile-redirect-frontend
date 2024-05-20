import play.sbt.PlayImport.PlayKeys.playDefaultPort
import uk.gov.hmrc.DefaultBuildSettings

ThisBuild / majorVersion := 0
ThisBuild / scalaVersion := "2.13.12"

lazy val microservice = Project("mobile-redirect-frontend", file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    // https://www.scala-lang.org/2021/01/12/configuring-and-suppressing-warnings.html
    // suppress warnings in generated routes files
    scalacOptions += "-Wconf:src=routes/.*:s",
    scalacOptions += "-Wconf:cat=unused-imports&src=html/.*:s",
    pipelineStages := Seq(gzip),
    playDefaultPort := 8266
  )
  .settings(resolvers += Resolver.jcenterRepo)
  .settings(CodeCoverageSettings.settings: _*)

lazy val it = project
  .enablePlugins(PlayScala)
  .dependsOn(microservice % "test->test")
  .settings(Defaults.itSettings)
  .settings(libraryDependencies ++= AppDependencies.it)
