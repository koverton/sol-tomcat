# sol-tomcat

Provides a JMS Resource Factory for the Tomcat JNDI store to call out to a Solace JNDI store
as needed. This can be used to configure Servlets that are deployed under Tomcat to access
Solace JMS resources such as Connection Factories, Topics and Queues without any vendor-specific
servlet code.

There is an integration guide available on the Solace Developers Portal:

http://dev.solace.com/wp-content/uploads/2015/10/Solace-JMS-Integration-with-Apache-Tomcat.pdf
