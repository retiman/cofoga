import sbt._

class SimpolProject(info: ProjectInfo) extends DefaultProject(info) {
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

  override def libraryDependencies = Set(
    "commons-logging" % "commons-logging" % "1.1.1",
    "commons-cli" % "commons-cli" % "1.1",
    "org.specs" % "specs" % "1.4.1",
    "log4j" % "log4j" % "1.2.13"
  )
}
