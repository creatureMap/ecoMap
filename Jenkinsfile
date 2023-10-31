pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Git checkout 등 빌드 단계 설정
                    sh './gradlew build' // 또는 해당 프로젝트의 빌드 명령어 실행
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    def project = 'ecoMap' // 프로젝트 이름
                    def dockerImage = "gcr.io/hazel-math-398908/$project:${env.BUILD_ID}" // GCP Container Registry 경로 설정

                    // Docker 이미지 빌드
                    sh "docker build -t $dockerImage -f Dockerfile ."

                    withCredentials([[
                        $class: 'UsernamePasswordMultiBinding',
                        credentialsId: 'dockerhub_credentials',
                        usernameVariable: 'm1nddoong',   // Docker Hub 계정 ID
                        passwordVariable: 'dckr_pat_LIULgF9TmYBeQb-ojqco2bs0dSI'  // Access Token
                    ]]) {
                        sh "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD"
                        sh "docker push m1nddoong/ecology_map" // Docker Hub Repository 경로 설정
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Build and push to Docker Hub succeeded!'

                // 빌드 성공 시 수행할 추가 작업 추가 가능
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

                // 빌드 실패 시 수행할 추가 작업 추가 가능
            }
        }
    }

    environment {
        CLEANUP_THRESHOLD = 5
    }
}
