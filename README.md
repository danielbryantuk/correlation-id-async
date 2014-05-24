correlation-id-async
====================

This is a simple Spring boot-based application that demonstrates how request correlationIds could be generated and managed in order to correlate and track an initial request's handling (and journey) through a distributed SOA/microservices application.

This version extends on the work in my other correclation-id-sync repo (which works correctly only with synchronous request handling) by providing a series of wrapper Classes to transparently propogate the correlation Id across differing Threads. Note that the main NewsController.externalNews method returns a ```DeferredResult<String>``` instead of a ```String``` directly.


Notes
-----
The adapter code between the DeferredResult and Future is currently dependent on Google Guava, but at some point I may re-write this to utilise the new Spring 4.0 ListenableFuture or Java 8 CompletableFuture.

This code also utilses Java 8 syntax, namely a method reference in the NewsController, but this could easily be converted for Java 7 compliance.

This code could do with some tidying-up (e.g. extraction of ```private ListeningExecutorService service``` in NewsController) ;-)


Disclaimer
----------
This is just an experiment and toy-example! I'm not suggesting this code is production-ready! :-)
