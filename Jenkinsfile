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
                    def propsContent = readFile(file: propertiesFile).trim()

                    // 새로운 값으로 application.properties 파일 내용을 업데이트합니다.
                    propsContent = propsContent.replaceAll(/spring.datasource.url=.*/, "spring.datasource.url=jdbc:mysql://34.145.28.166:3306/ecology_map?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC")
                    propsContent = propsContent.replaceAll(/spring.datasource.username=.*/, "spring.datasource.username=${dbCredential.username}")
                    propsContent = propsContent.replaceAll(/spring.datasource.password=.*/, "spring.datasource.password=${dbCredential.password}")
                    propsContent = propsContent.replaceAll(/spring.datasource.driver-class-name=.*/, "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver")
                    propsContent = propsContent.replaceAll(/spring.jpa.hibernate.ddl-auto=.*/, "spring.jpa.hibernate.ddl-auto=update")

                    propsContent = propsContent.replaceAll(/openai.model=.*/, "openai.model=${openAICredential.username}")
                    propsContent = propsContent.replaceAll(/openai.api.url=.*/, "openai.api.url=https://api.openai.com/v1/chat/completions")
                    propsContent = propsContent.replaceAll(/openai.api.key=.*/, "openai.api.key=${openAICredential.password}")
                    propsContent = propsContent.replaceAll(/chatGpt.key=.*/, "chatGpt.key=${openAICredential.password}")

                    // application.properties 파일을 업데이트합니다.
                    writeFile(file: propertiesFile, text: propsContent)
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
