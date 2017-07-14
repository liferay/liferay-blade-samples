# Greedy Service Reference

This module's `GenericPortlet` `GreedyServiceReferencePortlet` consumes an OSGi
service `com.liferay.blade.greedy.svc.api.SomeService` and prints a message in
the portlet indicating the bound `SomeService` implementation class it calls.

![The portlet calls on `SomeService` implementation `SomeServiceImpl`, by default.](images/using-default-service-impl.png)

This module depends on module `blade.greedy.svc.api` for its `SomeService`
interface.

`GreedyServiceReferencePortlet`'s field `_someService` uses an `@Reference`
annotation, whose policy is static and greedy.

	@Reference (policyOption = ReferencePolicyOption.GREEDY, unbind = "-")
	private SomeService _someService;

The annotation's attribute setting `policyOption = ReferencePolicyOption.GREEDY`
allows it to bind immediately to the highest ranked service implementation. The
setting `unbind = "-"` indicates that the registrator class doesn't use any
method to unbind the service.

If no higher ranked `SomeService` implementation is available at deployment, the
portlet is bound to its own default service implementation `SomeServiceImpl`.

Sample module `blade.greedy.svc.override` provides
a higher ranked `SomeService` implementation `CustomServiceImpl`.

    @Component(
    	immediate = true, property = {"service.ranking:Integer=100"},
    	service = SomeService.class

    )
    public class CustomServiceImpl implements SomeService {

    	@Override
    	public String doSomething() {
    		StringBuilder sb = new StringBuilder();

    		Class<?> clazz = getClass();

    		sb.append(clazz.getName());

    		sb.append(", which delegates to ");
    		sb.append(_defaultService.doSomething());

    		return sb.toString();
    	}

    	@Reference (
    		target = "(component.name=com.liferay.blade.greedy.svc.impl.SomeServiceImpl)",
    		unbind = "-"
    	)
    	private SomeService _defaultService;

    }

Note `CustomServiceImpl`'s `@Component` annotation:

    @Component(
       immediate = true, property = {"service.ranking:Integer=100"},
       service = SomeService.class
       )

Its service ranking value `100` is higher than the default service ranking value
`0`.  After deploying the `blade.greedy.svc.override` module, if
`CustomServiceImpl` provides the highest ranked `SomeService` implementation,
then its bound to all greedy `SomeService` references it satisfies.

`CustomServiceImpl` references the default service too.

    @Reference (
        target = "(component.name=com.liferay.blade.greedy.svc.impl.SomeServiceImpl)",
        unbind = "-"
    )
    private SomeService _defaultService;

Note, rather than inheriting from `SomeServiceImpl`, `CustomServiceImpl` refers
to the `blade.greedy.svc.reference` module's `SomeServiceImpl` object (via
an` @Reference` annotated field) and delegates to it.

![Here's the result of this module's `CustomServiceImpl` out-ranking all other `SomeService` implementations for component `GreedyStaticServiceReferencePortlet`.](images/providing-a-some-service-impl-that-delegates.png)