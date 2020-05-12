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

package com.liferay.blade.workflow.basic.service.impl;

import com.liferay.blade.workflow.basic.model.Baz;
import com.liferay.blade.workflow.basic.service.base.BazLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the baz local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.blade.workflow.basic.service.BazLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Inácio Nery
 * @see BazLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.blade.workflow.basic.model.Baz",
	service = AopService.class
)
public class BazLocalServiceImpl extends BazLocalServiceBaseImpl {

	@Override
	public Baz addBaz(long userId, long groupId, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Baz baz = bazPersistence.create(counterLocalService.increment());

		baz.setGroupId(groupId);
		baz.setCompanyId(user.getCompanyId());
		baz.setUserId(user.getUserId());
		baz.setUserName(user.getFullName());
		baz.setCreateDate(serviceContext.getCreateDate(null));
		baz.setModifiedDate(serviceContext.getModifiedDate(null));

		baz = bazPersistence.update(baz);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			baz.getCompanyId(), baz.getGroupId(), baz.getUserId(),
			Baz.class.getName(), baz.getPrimaryKey(), baz, serviceContext);

		return baz;
	}

	@Override
	public Baz deleteBaz(long bazId) throws PortalException {
		Baz baz = bazPersistence.remove(bazId);

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			baz.getCompanyId(), baz.getGroupId(), Baz.class.getName(),
			baz.getPrimaryKey());

		return baz;
	}

	@Override
	public Baz updateBaz(long bazId, ServiceContext serviceContext)
		throws PortalException {

		Baz baz = bazPersistence.findByPrimaryKey(bazId);

		baz.setModifiedDate(serviceContext.getModifiedDate(null));

		baz = bazPersistence.update(baz);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			baz.getCompanyId(), baz.getGroupId(), baz.getUserId(),
			Baz.class.getName(), baz.getPrimaryKey(), baz, serviceContext);

		return baz;
	}

	@Override
	public Baz updateStatus(long userId, long bazId, int status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Baz baz = getBaz(bazId);

		baz.setStatus(status);
		baz.setStatusByUserId(user.getUserId());
		baz.setStatusByUserName(user.getFullName());
		baz.setStatusDate(new Date());

		return updateBaz(baz);
	}

}