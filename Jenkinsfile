pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }

    stages {
        stage('Checkout Git repository') {
            steps {
                echo 'Pulling ';
                git branch: 'master', url: 'https://github.com/aymeennefzi/Gestion_Bank.git';
            }
        }

        stage('Maven Clean Compile') {
            steps {
                sh 'mvn clean'
                echo 'Running Maven Compile'
                sh 'mvn compile'
            }
        }

        stage('Maven Install') {
            steps {
                sh 'mvn install -DskipTests=true'  // Ignorer les tests pendant l'installation
            }
        }
    }
}
