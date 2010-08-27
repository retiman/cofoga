import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) {
  override def artifactID            = "cofoga"
  override def dependencyPath        = "project" / "lib"
  override def managedDependencyPath = "project" / "lib_managed"
  override def mainScalaSourcePath   = "src"
  override def testScalaSourcePath   = "test"
  override def compileOptions        = Deprecation ::
                                       Optimize ::
                                       super.compileOptions.toList

  val scalaToolsRepository           = "Scala-tools Maven2 Repository" at
                                       "http://scala-tools.org/repo-releases"
  val lousycoderRepository           = "Lousycoder Maven2 Repository" at
                                       "http://maven.lousycoder.com"

  override def libraryDependencies = Set(
    "org.slf4j" % "slf4j-api" % "1.6.1",
    "ch.qos.logback" % "logback-classic" % "0.9.24",
    "commons-cli" % "commons-cli" % "1.1",
    "org.specs" %% "specs" % "1.6.5" % "test"
  )
}
