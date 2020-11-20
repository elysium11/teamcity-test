package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ScriptBuildStep
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
        update<ScriptBuildStep>(0) {
            name = "set parameter"
            clearConditions()
            scriptContent = """
                echo "##teamcity[setParameter name='env.RUN_NEXT' value='true']"
                
                USE_CUSTOM_BUILD_FILE=false
                if [ -f "%teamcity.build.checkoutDir%/src/test/resources/test_payload_01.json"]; then
                    USE_CUSTOM_BUILD_FILE=true
                fi
                
                echo "##teamcity[setParameter name='USE_CUSTOM_BUILD_FILE' value='${'$'}{USE_CUSTOM_BUILD_FILE}']"
            """.trimIndent()
        }
        insert(1) {
            script {
                name = "test step"

                conditions {
                    equals("env.RUN_NEXT", "true")
                    equals("USE_CUSTOM_BUILD_FILE", "true")
                }
                scriptContent = """
                    echo "Hello from script"
                    ls -a
                """.trimIndent()
            }
        }
    }
}
