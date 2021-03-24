"# SpringBootAssignment" 

To access the h2 db you can check  : http://localhost:8080/api/h2-console

swagger url : http://localhost:8080/api/swagger-ui.html

To login using POST call :  "http://localhost:8080/api/user/authenticate" endpoint

Post Body : 
{
    "username" : "admin",
    "password" : "password"
}

After that copy the value of "jwtToken" from response and authorize in swagger using below format and start using apis

Bearer "jwtToken"



To Deploy the app to cloud ::
1) In Project configuration add one more config for "Cloud Code: Kubernetes"
2) After selecting it, check if Deployment Context is properly selected with the required cluster where deployment should happen.
3) Make sure that "Delete Deployment when finish" check box is unchecked 
4) And under "Build/Deploye" tab the "skaffold Configuration" field has proper skaffold file name.
5) Finaly to deploy the service package the project and then run the configuration.

