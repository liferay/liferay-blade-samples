# Greedy Service Override

This module provides a `com.liferay.blade.greedy.svc.api.SomeService`
implementation called `CustomServiceImpl` whose service ranking is `100`. On
deploying it, sample module `blade.greedy.svc.reference`'s portlet binds to a `CustomServiceImpl` instance.

This module depends on sample module `blade.greedy.svc.api` to register the
`com.liferay.blade.greedy.svc.api.SomeService` interface.

See `blade.greedy.svc.reference` module README file for details.