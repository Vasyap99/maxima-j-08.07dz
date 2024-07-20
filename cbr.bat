@set jp=c:\OpenJDK\bin\

@del *.class 2>nul

@%jp%javac.exe *.java

@:%jp%java.exe  HumanDAOInFileMemory

@%jp%java.exe  Main
@pause