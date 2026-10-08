pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

    }

    post {

        always {
            echo 'Automation execution completed.'
        }

        success {
            echo 'Automation tests passed successfully.'
        }

        failure {
            echo 'Automation tests failed.'
        }
    }
}