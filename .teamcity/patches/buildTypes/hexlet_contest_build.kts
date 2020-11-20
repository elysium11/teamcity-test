package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'hexlet_contest_build'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("hexlet_contest_build")) {
    expectSteps {
        script {
            name = "test step"
            scriptContent = """
                echo "Hello from script"
                                    ls -a
            """.trimIndent()
        }
    }
    steps {
        insert(1) {
            script {
                name = "set parameter"
                scriptContent = """echo "##teamcity[setParameter name='env.RUN_NEXT' value='true']""""
            }
        }
    }
}