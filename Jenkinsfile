node {
   def zceeHome
   stage('Checkout Git Code') { // for display purposes
      // Get some code from a GitHub repository
      //println "project name is catalogTest"
      //dir ("catalogTest") {
        //git credentialsId: 'git', url: 'https://github.com/Jack-Billings-IBM/catalogTest.git'

         // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.           
        //mvnHome = tool 'M3'
        
        //}
   }
    stage('Compile zOS Connect Source Project') {
        println "Calling zconbt"
        def output = sh (returnStdout: true, script: 'pwd')
        println output
        sh "/var/lib/jenkins/jobs/catalogTest/workspace/zconbt/bin/zconbt -pd=/var/lib/jenkins/jobs/catalogTest/workspace/properties/inquireCatalog.properties -f=/var/lib/jenkins/jobs/catalogTest/workspace/archives/inquireSingle.sar "
        println "Called zconbt"
        sh "ls"
        println "Exiting Stage 2, entering Stage 3!"
   }
}
