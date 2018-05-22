/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.samples.servicebuilder.adq.service.impl;

import com.liferay.blade.samples.servicebuilder.adq.model.Bar;
import com.liferay.blade.samples.servicebuilder.adq.service.base.BarLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

/**
 * The implementation of the bar local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.blade.samples.servicebuilder.adq.service.BarLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarLocalServiceBaseImpl
 * @see com.liferay.blade.samples.servicebuilder.adq.service.BarLocalServiceUtil
 */
public class BarLocalServiceImpl extends BarLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.blade.samples.servicebuilder.adq.service.BarLocalServiceUtil} to access the bar local service.
	 */
	public Bar addBarWithoutId(Bar bar) {
		long resourcePrimKey = counterLocalService.increment();

		bar.setBarId(resourcePrimKey);

		return addBar(bar);
	}

	public String barLocal() {
		return "barLocal";
	}

	public void massUpdate() {
		ActionableDynamicQuery adq = getActionableDynamicQuery();

		adq.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(RestrictionsFactoryUtil.lt("field3", 100));
				}

			});

		adq.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Bar>() {

				@Override
				public void performAction(Bar bar) {
					int field3 = bar.getField3();

					field3++;
					bar.setField3(field3);

					updateBar(bar);
				}

			});

		try {
			adq.performActions();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}