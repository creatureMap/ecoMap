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
                    def project = 'ecomap'
                    def dockerImage = "m1nddoong/ecology_map:${env.BUILD_ID}"

                    // Check if the Docker image already exists, then build it
                    def dockerImageExists = sh(script: "docker images -q $dockerImage", returnStatus: true) == 0
                    if (!dockerImageExists) {
                        sh "docker build -t $dockerImage -f Dockerfile ."
                    } else {
                        echo "Docker image $dockerImage already exists. Skipping build."
                    }

                    withCredentials([[
                        $class: 'UsernamePasswordMultiBinding',
                        credentialsId: 'dockerhub_credentials',
                        usernameVariable: 'DOCKERHUB_USERNAME',  // Set Docker Hub username variable
                        passwordVariable: 'DOCKERHUB_PASSWORD'   // Set Docker Hub password variable
                    ]]) {
                        sh "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD"
                        sh "docker push $dockerImage"
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Build and push to Docker Hub succeeded!'

                // Removing old Docker images after successful build and push
                def images = sh(script: "docker images -q gcr.io/hazel-math-398908/ecomap", returnStdout: true).trim().split('\n')
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
