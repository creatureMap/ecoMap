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
        stage('Update Application Properties') {
            steps {
                script {
                    // Jenkins Credential을 가져옵니다.
                    def dbCredential = credentials('database_credentials')
                    def openAICredential = credentials('openai-credentials')

                    // application.properties 파일을 읽습니다.
                    def propertiesFile = 'src/main/resources/application.properties'
                    def props = readProperties(file: propertiesFile)

                    // Credential에서 가져온 데이터를 application.properties 파일에 적용합니다.
                    props.'spring.datasource.url' = "jdbc:mysql://34.145.28.166:3306/ecology_map?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC"
                    props.'spring.datasource.username' = dbCredential.username
                    props.'spring.datasource.password' = dbCredential.password
                    props.'spring.datasource.driver-class-name' = "com.mysql.cj.jdbc.Driver"
                    props.'spring.jpa.hibernate.ddl-auto' = "update"

                    // OpenAI Credential에서 가져온 데이터를 application.properties 파일에 적용합니다.
                    props.'openai.model' = openAICredential.username
                    props.'openai.api.url' = "https://api.openai.com/v1/chat/completions"
                    props.'openai.api.key' = openAICredential.password
                    props.'chatGpt.key' = openAICredential.password

                    // application.properties 파일을 업데이트합니다.
                    writeProperties file: propertiesFile, properties: props
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
