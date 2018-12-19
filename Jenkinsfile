pipeline {
	agent {
		node { label 'mvn' }
	}

	options {
		buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
	}

	stages {
		stage('Build') {
			steps {
				sh 'mvn clean package'
			}
		}

		stage('Archive Artifacts') {
			steps {
				archiveArtifacts 'target/*.jar'
			}
		}
	}
}