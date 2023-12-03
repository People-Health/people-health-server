plugins {
    id("java")
}

group = "com.peoplehealth.server"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    val jettyVersion = "9.4.51.v20230217"

    // websocket
    implementation("javax.websocket:javax.websocket-api:1.1")

    // jetty server to handle websockets
    implementation("org.eclipse.jetty:jetty-server:$jettyVersion")
    implementation("org.eclipse.jetty:jetty-servlet:$jettyVersion")
    implementation("org.eclipse.jetty.websocket:javax-websocket-server-impl:$jettyVersion")

    // lombok to handle data
    "org.projectlombok:lombok:1.18.30".let {
        implementation(it)
        annotationProcessor(it)
    }
}
