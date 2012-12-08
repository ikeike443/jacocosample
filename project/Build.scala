import sbt._
import Keys._
import PlayProject._
import de.johoop.jacoco4sbt.JacocoPlugin._
import de.johoop.jacoco4sbt.XMLReport
import de.johoop.jacoco4sbt.HTMLReport

object ApplicationBuild extends Build {

  val appName = "jacocotest"
  val appVersion = "1.0-SNAPSHOT"

  lazy val s = Defaults.defaultSettings ++ Seq(jacoco.settings: _*) 

  val appDependencies = Seq( // Add your project dependencies here,
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA, settings = s).settings(
    // Add your own project settings here
    // for jacoco
    parallelExecution in test := false,

    jacoco.reportFormats in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8")),
    jacoco.outputDirectory in jacoco.Config := new File("target/coverage/"),
    jacoco.excludes in jacoco.Config :=
      Seq(
        "views.html.*",
        "Routes*", 
        "*Reverse*", 
        "views**", 
        "routes*" )
  )

}
