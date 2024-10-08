/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.mobileredirectfrontend.controllers

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.http.Status
import play.api.test.Helpers._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.FakeRequest

class RedirectControllerSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  override def fakeApplication(): Application =
    new GuiceApplicationBuilder()
      .configure(
        "metrics.jvm"     -> false,
        "metrics.enabled" -> false
      )
      .build()

  private val controller = app.injector.instanceOf[RedirectController]

  "GET /ios/url" should {
    "return 303 and redirect correctly" in {
      val request = FakeRequest(GET, routes.RedirectController.iosRedirect("iosUrl?iosParam=param").url)
      val result  = await(controller.iosRedirect("iosUrl?iosParam=param")(request))
      result.header.status              shouldBe Status.SEE_OTHER
      result.header.headers("Location") shouldBe "hmrcapp://iv-result-continue?url=iosUrl%253FiosParam%253Dparam%253FiosParam%253Dparam"
    }
  }

  "GET /android/url" should {
    "return 303 and redirect correctly" in {
      val request = FakeRequest(GET, routes.RedirectController.androidRedirect("androidUrl?androidParam=param").url)
      val result  = await(controller.androidRedirect("androidUrl?androidParam=param")(request))
      result.header.status              shouldBe Status.SEE_OTHER
      result.header.headers("Location") shouldBe "hmrc://iv-result-continue?url=androidUrl%253FandroidParam%253Dparam%253FandroidParam%253Dparam"
    }
  }

  "GET /iv-success" should {
    "return 303 and redirect correctly" in {
      val request = FakeRequest(GET, routes.RedirectController.ivRedirect("iv-success").url)
      val result  = await(controller.ivRedirect("iv-success")(request))
      result.header.status              shouldBe Status.SEE_OTHER
      result.header.headers("Location") shouldBe "hmrc://app/iv-success"
    }
  }

  "GET /iv-failure" should {
    "return 303 and redirect correctly" in {
      val request = FakeRequest(GET, routes.RedirectController.ivRedirect("iv-failure").url)
      val result  = await(controller.ivRedirect("iv-failure")(request))
      result.header.status              shouldBe Status.SEE_OTHER
      result.header.headers("Location") shouldBe "hmrc://app/iv-failure"
    }
  }
}
