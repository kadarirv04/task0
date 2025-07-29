pipeline {
    agent any

    environment {
        IMAGE_NAME = "kadarirv04/task0"
        IMAGE_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests=false'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "/usr/local/bin/docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }
        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh "echo $DOCKER_PASS | /usr/local/bin/docker login -u $DOCKER_USER --password-stdin"
                    sh "/usr/local/bin/docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }
        stage('Trigger Render Deploy') {
            steps {
                echo "Render will auto-deploy the new image if configured."
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            cleanWs()
        }
    }
} 