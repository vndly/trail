# Trail

Trail is a simple logging system for **Java** and **Android**.

### Features

* Easy and direct
* Same API for Java/Android logging (the library will automatically detect the platform)
* 5 log levels: 
   * _VERBOSE_
   * _DEBUG_
   * _INFO_
   * _WARNING_
   * _ERROR_
* 5 different ways to create logs for each level
* Register/unregister listeners for log events

### Log parameters

* **Tag**: The tag of the log. It can be any object from which the method `toString()` will be called. If missing a default tag with the following format will be used:

`[THREAD_NAME]CLASS_NAME.METHOD_NAME:LINE_NUMBER`

For example:

`[main]Sample.run:78`

* **Message**: The message of the log. It can be any object from which the method `toString()` will be called.


* **Exception**: The exception to log (if any).

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
 catch (Exception e)
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
 catch (Exception e)
 {
     // providing a tag, a message and an exception
     Trail.error("DATABASE_PROBLEM", "Invalid query format", e);
 }
```

### Listeners

### Settings

## Download

### Latest JAR:

[trail-1.0.0.jar][1]

### Maven:

```xml
<dependency>
    <groupId>com.mauriciotogneri.trail</groupId>
    <artifactId>trail</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle:

```groovy
compile 'com.mauriciotogneri.trail:trail:1.0.0'
```

## Compatibility

Trail works in any version of **Java** and **Android**.

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

[1]: https://www.google.com