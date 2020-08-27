node('master') {
   jdk = tool name: 'JDK8'
   env.JAVA_HOME = "${jdk}"
   env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
   sh "java -version"
   def zceeHome
   stage('Checkout Git Code') { // for display purposes
      // Get some code from a GitHub repository
      println "project name is catalogTest"
      git credentialsId: 'git', url: 'https://github.com/Jack-Billings-IBM/catalogTest.git'

         // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.           
        //mvnHome = tool 'M3'
   }

   stage('Compile zOS Connect Source Project') {
        println "Calling zconbt"
        def output = sh (returnStdout: true, script: 'pwd')
        println output
        sh "ls"
        sh "${WORKSPACE}/zconbt/bin/zconbt --properties=${WORKSPACE}/properties/inquireCatalog.properties --file=${WORKSPACE}/archives/inquireCatalog.sar "
        sh "${WORKSPACE}/zconbt/bin/zconbt -pd=${WORKSPACE}/catalog -f=${WORKSPACE}/archives/catalog.aar "
        println "Called zconbt for catalog"
   }
}
