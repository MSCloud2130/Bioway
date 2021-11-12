pipeline {
    agent any
    stages {
        stage('preparation') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
            }
        }
        stage('ProductSearch') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/ProductSearch') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/ProductSearch') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/ProductSearch') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-ProductSearch'
                        }
                    }
                }
            }
        }
        stage('eurekaserver') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/eurekaserver') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/eurekaserver') {
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
                    }
                }
            }
        }
        stage('apigateway') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/apigateway') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/apigateway') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/apigateway') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-apigateway'
                        }
                    }
                }
            }    
        }
        stage('cart') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/cart') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/cart') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/cart') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-cart'
                        }
                    }
                }
            }
        }
        stage('customers') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/customers') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/customers') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/customers') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-customers'
                        }
                    }
                }
            }
        }
        stage('identity') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/identity') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/identity') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/identity') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-identity'
                        }
                    }
                }
            }
        }
        stage('payments') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/payments') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/payments') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/payments') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-payments'
                        }
                    }
                }
            }
        }
        stage('products') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/products') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/products') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/products') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-products'
                        }
                    }
                }
            }
        }
        stage('purchases') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/purchases') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/purchases') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/purchases') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-purchases'
                        }
                    }
                }
            }
        }
        stage('suppliers') {
            stages {
                stage('build') {
                    steps {
                        dir('Source/suppliers') {
                            sh './mvnw clean compile'
                        }
                    }
                }
                stage('test') {
                    steps {
                        dir('Source/suppliers') {
                            sh './mvnw test'
                        }
                    }
                }
                stage('deploy') {
                    steps {
                        dir('Source/suppliers') {
                            sh './mvnw package -Dmaven.test.skip'
                            sh 'cp ./target/app.war /deploy-suppliers'
                        }
                    }
                }
            }
        }
    }
}
