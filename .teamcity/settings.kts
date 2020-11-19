import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.1"

project {
    description = "Contains all other projects"

    vcsRoot(GitVcsRoot {
        id("hextlet-contest")
        name = "Hexlet Contest"
        url = "git@github.com:elysium11/hexlet-contest.git"
        branch = "refs/heads/master"
        branchSpec = "+:*"
        agentCleanPolicy = GitVcsRoot.AgentCleanPolicy.ALWAYS
        authMethod = uploadKey {
            uploadKey = "teamcity.rsa"
        }
    })

    subProject {
        id("hexlet-contest")
        name("Hexlet Contest")
        buildTypesOrder(listOf(buildType {
            id("hexlet-contest-build")
            name  = "Hexlet Contest Build"
            
            vcs {
                root(RelativeId("hextlet-contest"))
            }

            steps {
                script {
                    name = "test step"
                    scriptContent = """
                    echo "Hello from script"
                    """
                }
            }
        }))
    }
}
