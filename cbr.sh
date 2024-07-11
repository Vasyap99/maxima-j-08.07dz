#!/bin/bash

rm -rf ./*.class

/usr/lib/jvm/java-11-openjdk-amd64/bin/javac IIdGenerator.java MemSequenceGenerator.java FileSequenceGenerator.java  Human.java IHumanDAO.java HumanDAOInMemory.java HumanDAOInMemorySimple.java HumanDAOInFileMemory.java IHumanService.java HumanService.java Main.java

#/usr/lib/jvm/java-11-openjdk-amd64/bin/java  HumanDAOInFileMemory

/usr/lib/jvm/java-11-openjdk-amd64/bin/java  Main