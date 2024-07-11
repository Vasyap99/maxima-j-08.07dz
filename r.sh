#!/bin/bash

rm -rf ./*.class

/usr/lib/jvm/java-11-openjdk-amd64/bin/javac Human.java IHumanDAO.java HumanDAOInMemory.java HumanDAOInFileMemory.java IHumanService.java HumanService.java Main.java

/usr/lib/jvm/java-11-openjdk-amd64/bin/java  HumanDAOInFileMemory