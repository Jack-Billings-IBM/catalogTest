import com.ibm.dbb.build.*

def compile = new MVSExec()
compile.setPgm("IGYCRCTL")
compile.setParm("LIB")

def sysin = new DDStatement()
sysin.setName("SYSIN")
sysin.setDsn("DFH550.CICS.SDFHSAMP(DFH0XCMN)")
sysin.setOptions("shr")
compile.addDDStatement(sysin)
compile.dd(new DDStatement().name("TASKLIB").dsn("IGY620.SIGYCOMP").options("shr"))
