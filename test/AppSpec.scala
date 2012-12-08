import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.Logger
import play.api.libs.ws.WS

class AppSpec extends Specification {
  "root path" should {

    "return 200" in {
       running(TestServer(3333, 
          FakeApplication(
            additionalConfiguration = 
            Map("ws.followRedirects" -> "false")))){
         await(WS.url("http://localhost:3333").get).status must equalTo(OK)
       }
    }
  }
}

class HtmlUnitSpec extends Specification {
  "HTMLUNIT " should {

    "run in a browser" in {
      running(TestServer(3334), HTMLUNIT) { browser =>
    
        browser.goTo("http://localhost:3334")
        println(browser.title)
        browser.title must equalTo("Welcome to Play 2.0")

      }
    }
  }
}