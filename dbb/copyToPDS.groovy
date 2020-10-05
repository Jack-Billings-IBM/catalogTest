import com.ibm.dbb.build.*

// Copy a entire directory to PDS
  new CopyToPDS().file(new File("dbb/catalog/copybook")).dataset("JBILL.CICS.SDFHSAMP").execute()
