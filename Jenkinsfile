pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                dir('Source/eurekaserver') {
                    sh './mvnw clean compile'
                }
                dir('Source/ProductSearch') {
                    sh './mvnw clean compile'
                }
                dir('Source/apigateway') {
                    sh './mvnw clean compile'
                }
                dir('Source/cart') {
                    sh './mvnw clean compile'
                }
                dir('Source/customers') {
                    sh './mvnw clean compile'
                }
                dir('Source/identity') {
                    sh './mvnw clean compile'
                }
                dir('Source/payments') {
                    sh './mvnw clean compile'
                }
                dir('Source/products') {
                    sh './mvnw clean compile'
                }
                dir('Source/purchases') {
                    sh './mvnw clean compile'
                }
                dir('Source/suppliers') {
                    sh './mvnw clean compile'
                }
            }
        }
        stage('test') {
            steps {
                dir('Source/eurekaserver') {
                    sh './mvnw test'
                }
                dir('Source/ProductSearch') {
                    sh './mvnw test'
                }
                dir('Source/apigateway') {
                    sh './mvnw test'
                }
                dir('Source/cart') {
                    sh './mvnw test'
                }
                dir('Source/customers') {
                    sh './mvnw test'
                }
                dir('Source/identity') {
                    sh './mvnw test'
                }
                dir('Source/payments') {
                    sh './mvnw test'
                }
                dir('Source/products') {
                    sh './mvnw test'
                }
                dir('Source/purchases') {
                    sh './mvnw test'
                }
                dir('Source/suppliers') {
                    sh './mvnw test'
                }
            }
        }
        stage('deploy') {
            steps {
                dir('Source/eurekaserver') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-eurekaserver'
                }
                dir('Source/ProductSearch') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-ProductSearch'
                }
                dir('Source/apigateway') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-apigateway'
                }
                dir('Source/cart') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-cart'
                }
                dir('Source/customers') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-customers'
                }
                dir('Source/identity') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-identity'
                }
                dir('Source/payments') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-payments'
                }
                dir('Source/products') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-products'
                }
                dir('Source/purchases') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-purchases'
                }
                dir('Source/suppliers') {
                    sh './mvnw package -Dmaven.test.skip'
                    sh 'cp ./target/app.war /deploy-suppliers'
                }
            }
        }
    }
}
