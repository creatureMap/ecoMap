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
                    def project = 'ecoMap'
                    def dockerImage = "gcr.io/hazel-math-398908/$project:${env.BUILD_ID}"

                    sh "docker build -t $dockerImage -f Dockerfile ."

                    withCredentials([[
                        $class: 'UsernamePasswordMultiBinding',
                        credentialsId: 'dockerhub_credentials',
                        usernameVariable: 'alstjsdlr990321@gmail.com',
                        passwordVariable: 'ecomap2023@'
                    ]]) {
                        sh "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD"
                        sh "docker push m1nddoong/ecology_map"
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Build and push to Docker Hub succeeded!'

                def images = sh(script: 'docker images -q m1nddoong/ecology_map', returnStdout: true).trim().split('\n')
                if (images.size() > env.CLEANUP_THRESHOLD.toInteger()) {
                    def oldImages = images[0..(images.size() - env.CLEANUP_THRESHOLD)].join(' ')
                    sh "docker rmi $oldImages"
                }
            }
        }
        failure {
            script {
                echo 'Build or push to Docker Hub failed!'
            }
        }
    }

    environment {
        CLEANUP_THRESHOLD = 5
    }
}
