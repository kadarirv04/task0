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
                sh "/usr/local/bin/docker build --platform linux/amd64 -t ${IMAGE_NAME}:${IMAGE_TAG} ."
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
                script {
                    // Option 1: If Render is configured for auto-deploy from Docker Hub
                    echo "Render will auto-deploy the new image from Docker Hub."
                    
                    // Option 2: Manual API call to trigger Render deployment
                    // Uncomment the lines below if you want to use Render API
                    // withCredentials([string(credentialsId: 'render-api-token', variable: 'RENDER_API_TOKEN')]) {
                    //     sh '''
                    //         curl -X POST "https://api.render.com/v1/services/YOUR_SERVICE_ID/deploys" \
                    //         -H "Authorization: Bearer $RENDER_API_TOKEN" \
                    //         -H "Content-Type: application/json"
                    //     '''
                    // }
                }
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