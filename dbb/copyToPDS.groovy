import com.ibm.dbb.build.*

// Copy a entire directory to PDS
  new CopyToPDS().file(new File("copybooks")).dataset("DFH550.CICS.SDFHSAMP").execute()
