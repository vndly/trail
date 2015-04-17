# Trail

Trail is a simple logging system for **Java** and **Android**. Create logs using the same API and the library will detect automatically in which platform the code is running.

## Features

* Easy and direct
* Same API for Java/Android logging
* 5 log levels: 
   * _VERBOSE_
   * _DEBUG_
   * _INFO_
   * _WARNING_
   * _ERROR_
* 5 different ways to create logs for each level
* Register/unregister listeners for log events

## Log parameters

* **Tag**: The tag of the log. It can be any object from which the method `toString()` will be called. If missing, a default tag with the name of the class where the log is created will be used.

* **Message**: The message of the log. It can be any object from which the method `toString()` will be called.

* **Exception**: The exception of the log (if any).

## Examples

```java
 // providing only a message
 Trail.verbose("Applicatio started!");
```

```java
 // providing a tag and a message
 Trail.info("LOGIN", "Successful");
```

```java
 try
 {
     // do something
 }
 catch (Exception e)
 {
     // providing only an exception
     Trail.debug(e);
 }
```

```java
 try
 {
     // do something
 }
 catch (NetworkException e)
 {
     // providing a tag and an exception
     Trail.warning("NETWORK_PROBLEM", e);
 }
```

```java
 try
 {
     // do something
 }
 catch (SQLException e)
 {
     // providing a tag, a message and an exception
     Trail.error("DATABASE_PROBLEM", "Invalid query format", e);
 }
```

## Utils

The library provides a method to retrieve the current location of the executing code:

```java
 Trail.getCodeLocation()
```

This method returns an instance of the class `CodeLocation` that contains the name of the thread, the name of the class, the name of the method, the line number and the stack trace of the line of code being executed at that moment. This can be used to add more information to a log.

For example:

```java
 Trail.verbose("LOCATION", "I am at " + Trail.getCodeLocation());
```

Could generate the following output:

```text
 LOCATION: I am at [main]Sample.run:78
```

## Listeners

The library allows to register/unregister listeners to be informed when a log is created:

```java
 public class SampleListener implements Listener
 {
     public void run()
     {
         // register the object to receive log events
         Trail.register(this);

         // the object will be informed about the following log
         Trail.verbose("Message 1");

         // unregister the object to stop receiving log events
         Trail.unregister(this);
         
         // the object will NOT be informed about the following log
         Trail.info("Tag", "Message 2");
     }

     @Override
     public void onLog(TrailLog log)
     {
         // TODO: process log information (write in a file/database, send by network, etc.)
     }
 }
```

## Global settings

Enable/disable log printing (enabled by default):

```java
 // disables the log printing
 Trail.enableLogPrinting(false);
```

Enable/disable listeners notification (enabled by default):

```java
 // disables the listener notification
 Trail.enableListenerNotification(false);
```

## Download

### Latest JAR:

[ ![Download](https://api.bintray.com/packages/mauriciotogneri/maven/trail/images/download.svg) ](https://github.com/mauriciotogneri/trail/releases/download/v1.0.0/trail-1.0.0.jar)

### Maven (from JCenter):

```xml
<dependency>
    <groupId>com.mauriciotogneri</groupId>
    <artifactId>trail</artifactId>
    <version>1.0.0</version>
</dependency>
```

Adding **JCenter** to _pom.xml_:

```xml
<repositories>
    <repository>
        <id>central</id>
        <name>bintray</name>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>

```

### Gradle (from JCenter):

```groovy
compile 'com.mauriciotogneri:trail:1.0.0'
```

Adding **JCenter** to _build.gradle_:

```groovy
repositories {
    maven {
        url  "http://jcenter.bintray.com" 
    }
}
```

## Compatibility

Trail works with any version of **Java** and **Android**.

## License

	The MIT License (MIT)

	Copyright (c) 2015 Mauricio Togneri

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.