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
                
                //bat '.\\mvnw compile'
                
                //bat '.\\mvnw package'

                bat 'false'

                // junit 'target/surefire-reports/*.xml'

            }

            post {
                //always {
                //    junit '*target/surefire-reports/TEST-*.xml'
                //    archiveArtifacts 'target/*.jar'
                //}
                changed {
                    emailext subject: "Job \'${JOB_NAME}\' (${BUILD_NUMBER}) ${currentBuild.result}",
                        body: "Please go to ${BUILD_URL} and verify the build.",
                        attachLog: true,  
                        compressLog: true, 
                        to: 'dev@uan.edu.co',
                        recipientProviders: [requestor(), upstreamDevelopers()]
                }
            }
        }
    }
}
