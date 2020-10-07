node('master') {
   
   stage('Checkout Git Code to Jenkins on OpenShift') { // for display purposes
      // Get some code from a GitHub repository
      println "project name is catalogTest"
      git credentialsId: 'git', url: 'https://github.com/Jack-Billings-IBM/catalog.git'
   }

   stage('Rebuild zOS Connect Services') {
      sh "curl --location --request GET 'http://cap-sg-prd-2.integration.ibmcloud.com:16221/catalog/items/10' --header 'Authorization: Basic YmtlbGxlcjpwYXNzdzByZA==' --header 'Content-Type: application/json'"
   }
}
