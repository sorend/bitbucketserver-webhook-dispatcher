library identifier: 'builders@master', retriever: modernSCM([$class: 'GitSCMSource', remote: 'https://bitbucket.bdpnet.dk/scm/uds-dpi/builders.git']) _

def buildVersion = "0.0.0-SNAPSHOT"
pipeline {
    agent {
        kubernetes {
            yaml libraryResource("jdk11.yml")
            defaultContainer "builder"
        }
    }
    options {
        disableConcurrentBuilds()
    }
    environment {
        ARTIFACTORY_PUBLISHING = credentials('artifactory-publishing')
    }
    triggers {
        cron("H H * * H")
    }
    stages {
        stage('Build') {
            steps {
                script {
                    buildVersion = tagVersion()
                }
                sh 'gradle -Pversion=${buildVersion} clean build artifactoryPublish'
            }
        }
    }
}
