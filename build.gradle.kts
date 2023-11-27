plugins {
  kotlin("jvm") version "1.7.21"
  application
}

application {
  mainClass.set("MainKt")
}

group = "org.myco.library"
version = "1.1.4"

repositories {
  mavenCentral()
}

object DependencyVersions {
  const val coroutines = "1.6.4"
  const val http4k = "4.34.0.3"
  const val jackson = "2.14.0"
  const val mockk = "1.13.2"
  const val logback = "1.2.11"
  const val postgres = "42.6.0"
  const val hibernate = "6.2.7.Final"
  const val mockito = "5.4.0"
  const val mockitoInline = "5.2.0"
  const val mockitoJupiter = "5.4.0"
}

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation(kotlin("test"))

  implementation(platform("org.http4k:http4k-bom:${DependencyVersions.http4k}"))
  implementation("org.http4k:http4k-core")
  implementation("org.http4k:http4k-server-netty")
  implementation("org.http4k:http4k-client-websocket")
  implementation("org.http4k:http4k-format-jackson")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersions.coroutines}")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${DependencyVersions.jackson}")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${DependencyVersions.jackson}")
  implementation("ch.qos.logback:logback-classic:${DependencyVersions.logback}")
  testImplementation("io.mockk:mockk:${DependencyVersions.mockk}")

  implementation("org.postgresql:postgresql:${DependencyVersions.postgres}")
  implementation("org.hibernate:hibernate-core:${DependencyVersions.hibernate}")
  implementation("org.mockito:mockito-core:${DependencyVersions.mockito}")
  implementation("org.mockito:mockito-inline:${DependencyVersions.mockitoInline}")
  implementation("org.mockito:mockito-junit-jupiter:${DependencyVersions.mockitoJupiter}")
  testImplementation("org.mockito:mockito-core:${DependencyVersions.mockito}")

}

tasks.test {
  useJUnitPlatform()
}
