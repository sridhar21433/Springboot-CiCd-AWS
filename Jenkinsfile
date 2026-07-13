pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'sridhar2143/springboot-cicd'
        CONTAINER_NAME = 'springboot-cicd-container'
        APP_PORT = '8081'

        // Prefer the private IP when both EC2 instances are in the same VPC.
        APP_SERVER = '172.31.11.110'
        APP_USER = 'ubuntu'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        stage('Checkout Source Code') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                sh '''
                    echo "Checking installed tools..."
                    java -version
                    mvn -version
                    git --version
                    docker version
                '''
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    docker build \
                      -t ${DOCKER_IMAGE}:${BUILD_NUMBER} \
                      -t ${DOCKER_IMAGE}:latest .
                '''
            }
        }

        stage('Login and Push to Docker Hub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_TOKEN'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_TOKEN" | \
                        docker login \
                          --username "$DOCKER_USERNAME" \
                          --password-stdin

                        docker push ${DOCKER_IMAGE}:${BUILD_NUMBER}
                        docker push ${DOCKER_IMAGE}:latest

                        docker logout
                    '''
                }
            }
        }

        stage('Deploy to AWS EC2') {
        steps {
        sshagent(credentials: ['aws-app-server-key']) {
            sh '''
                ssh -o StrictHostKeyChecking=no \
                  ${APP_USER}@${APP_SERVER} "
                    set -e

                    CURRENT_IMAGE=\$(docker inspect \
                      --format='{{.Config.Image}}' \
                      ${CONTAINER_NAME} 2>/dev/null || true)

                    echo \"Current image: \$CURRENT_IMAGE\"

                    docker pull ${DOCKER_IMAGE}:${BUILD_NUMBER}

                    docker rm -f ${CONTAINER_NAME} || true

                    docker run -d \
                      --name ${CONTAINER_NAME} \
                      --restart unless-stopped \
                      -p ${APP_PORT}:${APP_PORT} \
                      ${DOCKER_IMAGE}:${BUILD_NUMBER}

                    echo 'Waiting for application startup...'
                    sleep 15

                    if curl --fail http://localhost:${APP_PORT}/health; then
                        echo 'New deployment is healthy.'
                    else
                        echo 'Health check failed.'

                        docker rm -f ${CONTAINER_NAME} || true

                        if [ -n \"\$CURRENT_IMAGE\" ]; then
                            echo 'Rolling back to previous image...'

                            docker run -d \
                              --name ${CONTAINER_NAME} \
                              --restart unless-stopped \
                              -p ${APP_PORT}:${APP_PORT} \
                              \$CURRENT_IMAGE
                        fi

                        exit 1
                    fi
                  "
            '''
        }
    }
}
        stage('Verify Deployment') {
            steps {
                script {
                    sleep(time: 15, unit: 'SECONDS')

                    sh '''
                        curl --fail \
                          --retry 5 \
                          --retry-delay 5 \
                          http://${APP_SERVER}:${APP_PORT}/health
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "Deployment successful."
            echo "Application image: ${DOCKER_IMAGE}:${BUILD_NUMBER}"
        }

        failure {
            echo "Pipeline failed. Check the failed stage and console output."
        }

        always {
            sh '''
                docker image rm ${DOCKER_IMAGE}:${BUILD_NUMBER} || true
                docker image prune -f || true
            '''
        }
    }
}