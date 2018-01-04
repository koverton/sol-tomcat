# sol-tomcat

Provides a JMS Resource Factory for the Tomcat JNDI store to call out to a Solace JNDI store
as needed. This can be used to configure Servlets that are deployed under Tomcat to access
Solace JMS resources such as Connection Factories, Topics and Queues without any vendor-specific
servlet code.

For example, the following sample HttpServlet code uses @Resource injection to retrieve a JMS ConnectionFactory, Queue and
Topic via the sample ObjectFactory. 

```
public class mywebsample extends HttpServlet {
    private Log log = LogFactory.getLog(solwebsample.class);
    @Resource(name = "jms/ConnectionFactory")
    ConnectionFactory connectionFactory;
    @Resource(name = "jms/Queue")
    Queue queue;
    MessageConsumer queueConsumer;
    @Resource(name = "jms/Topic")
    Topic topic;
    ...
}
```

## Build

This is a Maven2 project with dependencies on public libraries that should build via mvn directly.

```
sol-tomcat% mvn install
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building sol-tomcat 0.1.0
[INFO] ------------------------------------------------------------------------
[INFO]
...
```

A ZIP package is also produced that contains a `lib/` directory with all the JARs that should be 
deployed to the `[TOMCAT]/lib/` directory.

## Deploy

Install the Solace libraries and sol-tomcat-X.Y.Z.jar into the `[TOMCAT]/lib/` directory. You can use 
the additional `SolTomcat.zip` artifact to retrieve all the libraries for deployment.

Create a webapp/servlet project with whatever your preferred toolset; HttpServlet is recommended for 
ease of use. Package your build artifacts as a .WAR file, and deploy the app to Tomcat. An example can 
be found at https://github.com/koverton/solwebsample

## Documentation

There is an integration guide available on the Solace Developers Portal:

http://dev.solace.com/wp-content/uploads/2015/10/Solace-JMS-Integration-with-Apache-Tomcat.pdf
