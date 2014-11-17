name := "scalar"

version := "0.1"

scalaVersion := "2.11.4"

sbtVersion := "0.13.5"

libraryDependencies ++= List(
  "com.h2database"              % "h2"                    % "1.3.173"     % "test",
  "org.hamcrest"                % "hamcrest-all"          % "1.3"         % "test",
  "org.scalacheck"             %% "scalacheck"            % "1.11.3"      % "test",
  "junit"                       % "junit"                 % "4.7"         % "test",
  "org.specs2"                 %% "specs2"                % "2.4"         % "test",
  "org.mockito"                 % "mockito-all"           % "1.9.0"       % "test",
  "com.novocode"                % "junit-interface"       % "0.7"         % "test->default"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.6",
  "-encoding", "UTF-8"
)

resolvers ++= Seq(
  "Typesafe Repository"       at        "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype Snapshots"        at        "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases"         at        "http://oss.sonatype.org/content/repositories/releases",
  "Spray Repo"                at        "http://repo.spray.io",
  "Spray Nightlies"           at        "http://nightlies.spray.io"
)

parallelExecution in Test := false

testOptions in Test += Tests.Argument(TestFrameworks.Specs2, "junitxml", "console")
