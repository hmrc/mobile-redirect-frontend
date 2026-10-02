import sbt._

object AppDependencies {

  private val bootstrapVersion    = "10.8.0"
  private val playFrontendVersion = "13.15.0"
  private val jsoupVersion        = "1.23.2"

  val compile = Seq(
    "uk.gov.hmrc" %% "bootstrap-frontend-play-30" % bootstrapVersion,
    "uk.gov.hmrc" %% "play-frontend-hmrc-play-30" % playFrontendVersion
  )

  val test = Seq(
    "uk.gov.hmrc" %% "bootstrap-test-play-30" % bootstrapVersion % Test,
    "org.jsoup"   % "jsoup"                   % jsoupVersion     % Test
  )

  val it = Seq.empty
}
