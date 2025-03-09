plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.7.5"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
}

group = "com.tavillecode"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    //maven("https://repo.codemc.io/repository/maven-snapshots")
}

dependencies {
    implementation("fr.mrmicky:fastboard:2.1.3")
    implementation("org.json:json:20210307")
    implementation("fr.skytasul:glowingentities:1.4")
    //implementation("net.wesjd:anvilgui:1.10.4-SNAPSHOT")
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
    //compileOnly(files("C:/Users/19074/Downloads/BetterHud-bukkit-1.11.4.jar"))
    compileOnly(files("D:/魔法世界2/plugins/ItemStorage-1.0-SNAPSHOT.jar"))
    //compileOnly(files("C:/Users/19074/Desktop/ChatHead-0.0.5.jar"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.tavillecode"
            artifactId = "centaurus"
            version = "1.0"

            from(components["java"])
        }
    }
}

tasks.publishToMavenLocal {
    dependsOn(tasks.reobfJar)
}

tasks.assemble {
    dependsOn(tasks.reobfJar)
}