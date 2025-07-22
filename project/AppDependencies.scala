import sbt._

object AppDependencies {

  private val bootstrapVersion    = "9.18.0"
  private val playFrontendVersion = "12.7.0"
  private val jsoupVersion        = "1.21.1"

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
