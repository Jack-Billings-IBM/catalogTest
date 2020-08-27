node('master') {
   //jdk = tool name: 'JDK8'
   //env.JAVA_HOME = "${jdk}"
   //env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
   sh "echo $JAVA_HOME"
   sh "echo $PATH"
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
        sh "${WORKSPACE}/zconbt/bin/zconbt --properties=${WORKSPACE}/properties/inquireCatalog.properties --file=${WORKSPACE}/archives/inquireCatalog.sar "
        println "Called zconbt for inquireCatalog"
        sh "${WORKSPACE}/zconbt/bin/zconbt --properties=${WORKSPACE}/properties/inquireSingle.properties --file=${WORKSPACE}/archives/inquireSingle.sar "
        println "Called zconbt for inquireSingle"
        sh "${WORKSPACE}/zconbt/bin/zconbt -pd=${WORKSPACE}/catalog -f=${WORKSPACE}/archives/catalog.aar "
        println "Exiting Stage 2, entering Stage 3!"
   }
}
