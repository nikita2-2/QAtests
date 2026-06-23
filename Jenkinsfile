pipeline {
    agent any

    parameters {
        string(name: 'BROWSERS', defaultValue: 'chrome:120.0,chrome:110.0,MicrosoftEdge:114.0', description: 'Список браузеров для UI тестов через запятую')
        string(name: 'SELENOID_HOST', defaultValue: '192.168.0.170', description: 'IP адрес вашей Windows машины с Selenoid')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Automated Tests') {
            steps {
                sh 'chmod +x gradlew'
                
                sh "execution_mode=SELENOID SELENOID_HOST=${params.SELENOID_HOST} ./gradlew clean test -Dbrowsers.list=${params.BROWSERS}"
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
        }
    }
}
