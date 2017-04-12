# MVVM Example App

Plain Java Model-View-ViewModel example application

## RxJava

ReactiveX allows for Reactive Asyncrhonous Data Streams.  This is a good fit in an MVVM application,
as the databinding interaction that sits between the View and ViewModel is reactive by nature.
Thus, designing the entire app this way helps with consistency, and gives an elegant way to think
about data and how it drives app behaviour.

## Databinding

Android Databinding is a nail in the coffin of Butterknife, allowing very simple view binding
through generated code.  RxBinding is also utilized, to get away from some of the otherwise
necessary boilerplate code for more custom interactions, as well as event handling.

## Clean Modules

* Domain -- Business Logic (Models)
* Presentation -- Presentation Logic (ViewModels)
* Services -- Repository Implementations
* Data -- Data/Query Mapper Implementations
* App -- UI (View)

## Dependencies

The Domain module depends on no other modules, and limits it's dependencies.  What you bring into
this module is going to be "baked in" to your app, and won't be easily removable, as it'll then
effect all of your business logic, so be careful.  I have made the decision to bring in RxJava
and AutoValue, as I find all of these to be fairly essential to any Java applicaiton that I would
write today, as well as configuring Retrolambda.

The Presentation and Services Module both then depend on the Domain module, and bring in extra
dependencies as required.  For example, the Presentation module is an Android library so that it may
access databinding classes, and also brings in RxAndroid for thread scheduling.

The Data layer depends solely on Services, and is also an Android libray, as this is where any
concrete database or networking implementation would live.

The App layer then depends on data and presentation.  It then brings in all the necessary compat
libraries, widgets, etc. as well as Dagger, and builds a dependency tree.

## Activity based Application

This applicaiton is based off activities, as a way to try to keep the focus on architecture, not
implementation details.  The data layer is also faked, as I did not want to get too deep into SQL
code for this app.  Personally, I'd normally utilize a tool like Conductor, as well as Kotlin if I'm
being honest.

## Testing

* Domain -- Unit Tests for Business Logic
* Presentation -- Unit Tests for Presentation Logic
* Services -- Unit Tests for Service Logic
* Data -- Currently no tests, as the only logic there is fake.
* App -- Espresso Tests for UI

