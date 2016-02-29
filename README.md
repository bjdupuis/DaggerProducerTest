# DaggerProducerTest

An Android project testing a proof of concept for dealing with multiple asynchronous dependencies in a `@Producer` that then produces a single composite dependency that the UI layer can be gated on.

For the asynchronous dependencies, there's `SomeAsyncDependency` and `AnotherAsyncDependency`. 

* `SomeAsyncDependency` mimics a dependency that itself does some extended initializaton before making itself available for use. An example of this kind of dependency is a provider that communicates with a bound `Service`. Ideally the `Service` connection is bound before making the provider available for use by other parts of the application. Once the `Service` connection is complete, the provider makes itself available to fulfil the dependency.
* `AnotherAsyncDependency` mimics a dependency that performs some extended initialization before making an instance of the dependency available. An example of this kind of dependency is something that pulls information from a remote server before being available for use. In our application, this models a localization provider that provides localized strings for back-end data that can't be baked into the app statically. The localized strings for the user's selected language are fetched from the remote API and then the provider is instantied with the given data and made available for use.

For our application, these dependencies are valid for a particular user session. So the `@ForSession` scope is applied. In the real app, this module would be cleared in a log out scenario, where a new session module would be created. This is mimiced by the "Reset dependencies" button in the test UI. This clears the session dependencies and reacquires a session mimicing a new login. 

Rotation is handled appropriately by checking for an existing session before creating a new one. The dependencies that were previously loaded are still valid and used.
