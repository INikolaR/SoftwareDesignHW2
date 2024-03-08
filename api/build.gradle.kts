plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.springframework:spring-context:6.1.4")
    implementation("org.springframework:spring-webmvc:6.1.4")
}
