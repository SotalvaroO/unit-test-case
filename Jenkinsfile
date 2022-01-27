pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/SotalvaroO/unit-test-case.git'
                //sh './mvnw clean compile'
                bat '.\\mvnw clean compile'
            }
        }
        stage('Test') {
            steps {
                //sh './mvnw test'
                bat '.\\mvnw test'
            }

            post {
                always {
                    cleanWs()
                }
            }
        }
    }
}