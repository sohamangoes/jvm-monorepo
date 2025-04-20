plugins {
    id("spring-app-conventions")
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation(project(":packages:libraries:hello-world"))

    testImplementation("io.projectreactor:reactor-test")
}