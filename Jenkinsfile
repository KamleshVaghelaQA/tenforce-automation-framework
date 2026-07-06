pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Verify Reports') {
            steps {
                bat 'dir /s target'
            }
        }
    }

  post {
    always {

        junit '**/surefire-reports/*.xml'

        publishHTML([
            allowMissing: true,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'target',
            reportFiles: 'ExtentReport.html',
            reportName: 'Extent Automation Report'
        ])

        archiveArtifacts artifacts: 'target/screenshots/*.png',
                         fingerprint: true,
                         allowEmptyArchive: true
      }
  }
}