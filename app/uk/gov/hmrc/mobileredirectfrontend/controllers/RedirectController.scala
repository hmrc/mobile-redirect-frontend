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

import uk.gov.hmrc.play.bootstrap.frontend.controller.FrontendBaseController
import play.api.mvc.{Action, AnyContent, MessagesControllerComponents}
import uk.gov.hmrc.mobileredirectfrontend.config.AppConfig

import java.net.URLEncoder
import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class RedirectController @Inject() (
  val controllerComponents: MessagesControllerComponents,
  configuration:            AppConfig)
    extends FrontendBaseController {

  def iosRedirect(url: String): Action[AnyContent] = Action.async { implicit request =>
    val fullUrl = s"$url?${request.rawQueryString}"
    Future.successful(
      Redirect(
        configuration.iosAppDeeplinkUrl,
        Map(
          "url" -> collection.immutable.Seq(URLEncoder.encode(fullUrl, "UTF-8"))
        )
      )
    )
  }

  def androidRedirect(url: String): Action[AnyContent] = Action.async { implicit request =>
    val fullUrl = s"$url?${request.rawQueryString}"
    Future.successful(
      Redirect(
        configuration.androidAppDeeplinkUrl,
        Map(
          "url" -> collection.immutable.Seq(URLEncoder.encode(fullUrl, "UTF-8"))
        )
      )
    )
  }

}
