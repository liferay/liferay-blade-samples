package com.liferay.blade.samples.trackmen;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.felix.service.command.CommandProcessor;
import org.osgi.framework.Filter;
import org.osgi.framework.hooks.service.ListenerHook;
import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	property = { 
		CommandProcessor.COMMAND_SCOPE + "=blade",
		CommandProcessor.COMMAND_FUNCTION + "=trackers" 
	}, 
	service = ListenerHook.class
)
public class WhoTracksTheTrackmen implements ListenerHook {

	@Override
	public void added(Collection<ListenerInfo> listeners) {
		_listenerInfos.addAll(listeners);
	}

	private void checkFilter(Filter f, Map<String, Map<String, List<String>>> interesting, Stack<String> objectNames)
			throws Exception {
		String attr = (String) getField(f, "attr");
		Object value = getField(f, "value");

		if ("objectClass".equals(attr)) {
			if (value instanceof String) {
				String className = (String) value;

				Map<String, List<String>> props = interesting.get(className);

				if (props == null) {
					props = new HashMap<>();
				}

				interesting.put(className, props);
				objectNames.push(className);
			}
		} else if (value instanceof Filter[]) {
			Filter[] filters = (Filter[]) value;
			for (Filter filter : filters) {
				checkFilter(filter, interesting, objectNames);
			}

			objectNames.pop();
		} else {
			String className = objectNames.peek();

			Map<String, List<String>> props = interesting.get(className);

			List<String> possibleValues = props.get(attr);

			if (possibleValues == null) {
				possibleValues = new ArrayList<>();
			}

			if (value != null && !possibleValues.contains(value)) {
				possibleValues.add(value.toString());
			}

			props.put(attr, possibleValues);
		}
	}

	private Object getField(Object obj, String name) throws Exception {
		Field field = obj.getClass().getDeclaredField(name);
		field.setAccessible(true);
		return field.get(obj);
	}

	@Override
	public void removed(Collection<ListenerInfo> listeners) {
		_listenerInfos.removeAll(listeners);
	}

	public String trackers() {
		StringBuilder output = new StringBuilder();

		Map<String, Map<String, List<String>>> objectClassInfo = new HashMap<>();
		Stack<String> objectNames = new Stack<String>();

		for (ListenerInfo listenerInfo : _listenerInfos) {
			String filter = listenerInfo.getFilter();

			try {
				if (filter != null) {
					Filter f = listenerInfo.getBundleContext().createFilter(filter);

					try {
						objectNames.clear();
						checkFilter(f, objectClassInfo, objectNames);
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}

		Set<String> objectClasses = objectClassInfo.keySet();

		for (String objectClass : objectClasses) {
			output.append("objectClass=" + objectClass + "\n");

			Map<String, List<String>> props = objectClassInfo.get(objectClass);

			if (props != null) {
				for (String prop : props.keySet()) {
					output.append("\t" + prop + "\n");

					List<String> pvs = props.get(prop);

					if (pvs != null) {
						for (String pv : pvs) {
							output.append("\t\t\t" + pv + "\n");
						}
					}
				}
			}
		}

		return output.toString();
	}

	private final Collection<ListenerInfo> _listenerInfos = new HashSet<>();
}
