Simple test application for Java grails app.

To build runnable war file:
```grails package```

then deploy to cluster and
```java -cp $CLASSPATH -Djava.library.path=/opt/mapr/lib  -Dgrails.env=prod -jar fakeyelp-0.1.war```

App home page:
http://hostname:8080/mockYelp/index