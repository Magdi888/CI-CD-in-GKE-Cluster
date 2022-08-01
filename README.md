# Building CI/CD Pipeline in Jenkins Server to deploy application on GKE cluster:

### Prerequisites:

 - Create a Kuberenetes cluster on GCP.
 - Provision Jenkins server as a Deployment in the Kuberenetes cluster.
 - Give credentials to jenkins server to deploy application on  Kuberenetes cluster.
### To find all Prerequisites ==> Press the ICON 
 <p align='center'>
 <a href="https://github.com/Magdi888/Jenkins-in-GKE-Cluster"><img src="https://s18955.pcdn.co/wp-content/uploads/2018/02/github.png" width="50"/></a>
 </p>

## Jenkins Configuration:

### Jenkins Plugins:

#### Configure Slack Notification plugin:
 - Install Slack Notification plugin.
 - Login to slack workspace.
 - Add jenkins CI to Slack from "Browse Slack" => "Apps"
 ![image](https://user-images.githubusercontent.com/91858017/182221619-bf3bb132-5f28-46db-aa9d-92cf2b408669.png)
 
 - Select the channel you want to post in.
 - In Jenkins look for Slack from "Manage jenkins" => "Configure system"
 -Add your workspace and credentials and your default channel to post in.
 - Test the connection.
 ![image](https://user-images.githubusercontent.com/91858017/182223305-86900308-62b7-4e06-929e-17d4e54279cf.png)

### Jenkins Credentials:

- Add DockerHub credentials.
- Add GitHub credentials.

### Create Jenkinsfile and Groovy.script:

- Groovy.script Functions [Groovy Script](https://github.com/Magdi888/CI-CD-in-GKE-cluster/blob/master/script.groovy):
  - BuildImage Function:
    - Build the docker image from docker file in [Dockerfile](https://github.com/Magdi888/CI-CD-in-GKE-cluster/blob/master/App/Dockerfile) and tag the image with my dockerhub repo.
    - Using DockerHub credential to login to my dockerhub account.
    - Push the image to dockerhub.
  - Deploy Function:
    - Deploy the Applications Yaml files on the GKE cluster on dev namespace.


- Jenkinsfile stages [Jenkinsfile](https://github.com/Magdi888/CI-CD-in-GKE-cluster/blob/master/Jenkinsfile):
  - Start: load the groovy.script.
  - CI: run groovy.script BuildImage function.
  - CD: run groovy.script Deploy function.
- Jenkinsfile Post:
  - In case of success send notification to slack channel "Deploy successfully".
  - In case of failure send notification to slack channel "Deploy failed".

### Create Pipeline:

 - In Jenkins add new item with type pipeline.
 - In Pipeline definition select Pipeline script from SCM.
 - set my repo link.
 - Apply and save.
 ![image](https://user-images.githubusercontent.com/91858017/182248231-4a0267f6-7c20-4465-ac7a-a7c78333519a.png)
 
### Run the pipeline:
![image](https://user-images.githubusercontent.com/91858017/182248397-6af13120-5c51-4f1d-9913-2d635462a3ce.png)

### Final Results:
 - The Application Deployed on the GKE in namespace dev
  ```
  # Get all resource in dev namespace
   kubectl get all -n dev
  ```
 ![image](https://user-images.githubusercontent.com/91858017/182253095-54bcbe86-1656-4728-82ba-a7fa6b53be0d.png)
 
 - Visit the IP of the loadbalancer
  ```
   # Get the IP 
    kubectl get ingress -n dev
  ```
  ![image](https://user-images.githubusercontent.com/91858017/182254049-423c8045-569e-4072-928d-10818aa0e60a.png)
  
 #### The UI:
 
  ![image](https://user-images.githubusercontent.com/91858017/182253716-d63b997a-d145-43c9-ac3c-1f121dfe5061.png)


 


