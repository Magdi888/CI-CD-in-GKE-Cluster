#!/usr/bin/env groovy

def BuildImage() {
    withCredentials([usernamePassword(credentialsId :'DockerHub',usernameVariable :'USER',passwordVariable :'PASSWORD')]){
       sh 'docker build -t amagdi888/my-repo:Python-App -f ./App/Dockerfile ./App'
       sh 'echo $PASSWORD | docker login -u $USER --password-stdin'
       sh 'docker push amagdi888/my-repo:Python-App'
    }

}

def Deploy() {
    sh 'kubectl apply -Rf ./K8s_resources -n dev'
}

return this