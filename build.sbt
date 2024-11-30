val scala3Version = "3.5.2"

val kyoVersion = "0.14.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "aoc2024",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    scalacOptions ++= Seq(
      "-Wvalue-discard",
      "-Wnonunit-statement",
      "-Wconf:msg=(discarded.*value|pure.*statement):error"
    ),
    libraryDependencies ++= Seq(
      "io.getkyo" %% "kyo-prelude" % kyoVersion,
      "io.getkyo" %% "kyo-core" % kyoVersion,
      "io.getkyo" %% "kyo-combinators" % kyoVersion
    ),
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
