pipeline {
    agent any

    triggers { pollSCM('* * * * *') }

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/g0t4/jgsu-spring-petclinic.git'

            }
        }
        
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/g0t4/jgsu-spring-petclinic.git'
                
                bat '.\\mvnw compile'
                
                bat '.\\mvnw package'

                // junit 'target/surefire-reports/*.xml'

            }

            post {
                always {
                    junit '*target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
