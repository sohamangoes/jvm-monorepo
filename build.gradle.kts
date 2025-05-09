plugins { id("com.diffplug.spotless") version ("6.25.0") }

repositories { gradlePluginPortal() }

spotless {
  protobuf { buf() }

  shell {
    target("**/*.sh", "**/*.bash")
    shfmt("3.10.0")
  }

  json {
    target("**/*.json")
    targetExclude("**/build/**")
    endWithNewline()
    gson().sortByKeys()
  }

  yaml {
    target("**/*.yaml", "**/*.yml")
    targetExclude("**/build/**")
    endWithNewline()
    trimTrailingWhitespace()
    jackson().yamlFeature("LITERAL_BLOCK_STYLE", true)
  }

  java {
    target("**/*.java", "**/*.java")
    targetExclude("**/build/**")
    googleJavaFormat()
    formatAnnotations()
    removeUnusedImports()
  }

  kotlin {
    target("**/*.kt")
    targetExclude("**/build/**")
    ktfmt().googleStyle()
  }

  kotlinGradle {
    target("**/*.gradle.kts")
    targetExclude("**/build/**")
    ktfmt().googleStyle()
  }

  sql {
    target("**/*.sql")
    targetExclude("**/build/**")
    prettier()
  }
}

tasks.register<Copy>("setupGitHooks") {
  from(rootDir.resolve(".scripts/git-hooks"))
  into(rootDir.resolve(".git/hooks"))

  outputs.upToDateWhen {
    val hooksDir = rootDir.resolve(".git/hooks")
    if (!hooksDir.exists() || hooksDir.listFiles()?.isEmpty() == true) {
      return@upToDateWhen false
    }

    var scriptsDir = rootDir.resolve(".scripts/git-hooks")
    val scripts = scriptsDir.listFiles() ?: return@upToDateWhen false
    val hooks = hooksDir.listFiles() ?: return@upToDateWhen false

    scripts.all { script ->
      val correspondingHook = hooks.find { it.name == script.name }
      correspondingHook != null && correspondingHook.lastModified() >= script.lastModified()
    }
  }

  onlyIf { !state.upToDate }
  doFirst { println("🛠️ setting up git hooks..") }
  doLast { println("✅ git hooks setup successfully!") }
}
