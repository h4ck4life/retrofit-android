RetrofitAndroid is a repackaging of the Square's Retrofit Maven project into a reusable Android library project.

For future reference please refer to the original Github repository

https://github.com/square/retrofit

Usage
-----

Create an interface for your API.  You can create as many of these interfaces as you like.  For
each interface you create, calling `RestAdapter.create(MyInterface.class)` will create an
instance of that API handler, which you can then store and use throughout your application.  An
example interface:

```java
public interface DummyServiceAsync {
  // Produces a url like "foo/bar?id=idValue".
  @GET("foo/bar")
  void normalGet(@Named("id") String id, Callback<SimpleResponse> callback);

  // Produces a url like "foo/idValue/bar?category=categoryValue".
  @GET("foo/{id}/bar")
  void getWithPathParam(@Named("id") String id, @Named("category") String category, Callback<SimpleResponse> callback);

  // Produces a url like "foo/bar/idValue" and body like "id=idValue&body=bodyValue".
  @POST("foo/bar/{id}")
  void normalPost(@Named("id") String id, @Named("body") String body, Callback<SimpleResponse> callback);

  // Produces a url like "foo/bar/idValue" and body generated by MyJsonObj.
  @POST("foo/bar/{id}")
  void singleEntityPost(@SingleEntity MyJsonObj card, @Named("id") String id, Callback<SimpleResponse> callback);
}
```

Each method has a `Callback` type specified at the end of the parameter list.  This is how your
application will handle the results of your network calls asynchronously: errors and successful
responses are both handled by the `Callback` interface.

If you want to use the `@SingleEntity` method of specifying request body (see `singleEntityPost` above),
your `MyJsonObject` will need to implement `TypedBytes`.

For synchronous execution, omit the `Callback` parameter and specify the response as the return type.

```java
public interface DummyServiceSync {
  @GET("foo/bar")
  SimpleResponse normalGet(@Named("id") String id);
}
```

Also worth noting: for POST/PUT requests using default form encoding for the request entity (see
normalPost), any path parameters are also included in the request body.  This is different from the
behavior of GET/DELETE, where path parameters are excluded from the query string.


Personal Note
-------------
If there are notable differences from the original project, I'll do some remarks accordingly or branch out from this project.

I've done a test Android project based on the library project itself.


License
=======

    Copyright 2012 Kevin Tan Hong Ann

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.









