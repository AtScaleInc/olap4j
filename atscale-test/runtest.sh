#!/bin/bash

export CLASSPATH="olap4j-TRUNK-SNAPSHOT/lib/*:.:backport-util-concurrent-3.1/backport-util-concurrent.jar:~/source/olap4j-patch/lib*:~/source/olap4j-patch/testlib/*:retroweaver-rt-1.2.4.jar"
javac MDXTest.java

jwt=`curl -X GET -u "user:pass" "http://atscale:10500/default/auth"`

echo "==== JWT ===="
java MDXTest "jdbc:xmla:Server=http://atscale:10502/xmla/default/dev;USEJWT=true" "TOKEN" "$jwt"

echo "==== USER ===="
java MDXTest "jdbc:xmla:Server=http://atscale:10502/xmla/default/dev" "admin" "admin"

