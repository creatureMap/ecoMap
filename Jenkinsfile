pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    sh './gradlew build'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    def dockerRepository = 'm1nddoong/ecology_map'
                    def dockerImage = "${dockerRepository}:${env.BUILD_ID}"

                    // Docker login using credentials
                    withCredentials([[
                        $class: 'UsernamePasswordMultiBinding',
                        credentialsId: 'dockerhub_credentials',
                        usernameVariable: 'DOCKERHUB_USERNAME',
                        passwordVariable: 'DOCKERHUB_PASSWORD'
                    ]]) {
                        sh "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD"
                        sh "docker build -t ${dockerImage} ."
                        sh "docker push ${dockerImage}"
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Build and push to Docker Hub succeeded!'
            }
        }
        failure {
            script {
            echo 'Build or push to Docker Hub failed!'
            }
        }
    }
}
