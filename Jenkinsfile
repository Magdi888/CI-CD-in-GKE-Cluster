def gv
pipeline {
    agent any

    parameters {
        string(name: 'namespace', defaultValue: 'default', description: 'Namespace to deploy on')
    }


    stages {
        stage('start') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }
        stage('CI') {
            steps {
                script {
                    gv.BuildImage()
                }
            }
        }
        stage('CD') {
            steps {
                script {
                    gv.Deploy(${namespace})
                }
            }
        }
    }
    post {
          success {
              slackSend (message:"Deploy successfully - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)")
          }
          failure {
              slackSend (message:"Deploy failed  - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)")
          }
      }
}