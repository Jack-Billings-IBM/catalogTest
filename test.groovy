node('master') {
   jdk = tool name: 'JDK8'
   env.JAVA_HOME = "${jdk}"
   env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
   
   stage('Checkout Git Code to Jenkins on OpenShift') { // for display purposes
      // Get some code from a GitHub repository
      println "project name is catalogTest"
      git credentialsId: 'git', url: 'https://github.com/Jack-Billings-IBM/catalog.git'
   }

   stage('Rebuild zOS Connect Services') {
      sh "curl --location --request GET 'http://esysmvs1.ztec.dmz:39555/zosConnect/services/inquireSingle' --header 'Content-Type: application/json'"
   }
}
