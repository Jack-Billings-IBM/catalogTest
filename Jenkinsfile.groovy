node('master') {
   jdk = tool name: 'JDK8'
   env.JAVA_HOME = "${jdk}"
   env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
   def zceeHome
   stage('Checkout Git Code') { // for display purposes
      // Get some code from a GitHub repository
      println "project name is catalogTest"
      dir ("catalogTest") {
        git credentialsId: 'git', url: 'https://github.com/Jack-Billings-IBM/catalogTest.git'

         // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.           
        //mvnHome = tool 'M3'
        
        }
   }
    stage('Compile zOS Connect Source Project') {
        println "Calling zconbt"
        def output = sh (returnStdout: true, script: 'pwd')
        println output
        sh "${WORKSPACE}/catalogTest/zconbt/bin/zconbt --properties=${WORKSPACE}/catalogTest/properties/inquireCatalog.properties --file=${WORKSPACE}/catalogTest/archives/inquireCatalog.sar "
        println "Called zconbt for inquireCatalog"
        sh "${WORKSPACE}/catalogTest/zconbt/bin/zconbt --properties=${WORKSPACE}/catalogTest/properties/inquireSingle.properties --file=${WORKSPACE}/catalogTest/archives/inquireSingle.sar "
        println "Called zconbt for inquireSingle"
        println "Exiting Stage 2, entering Stage 3!"
   }
}

node('zOS') {
   stage('Update Copybooks on zOS') {
      git credentialsId: 'git', url: 'https://github.com/Jack-Billings-IBM/catalogTest.git'
      env.JAVA_HOME = "/usr/lpp/java/J8.0_64"
      env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
      echo "java -v"
      sh '/usr/lpp/IBM/dbb/bin/groovyz copyToPDS.groovy'
   }
}
