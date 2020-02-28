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

package com.liferay.blade.workflow.asset.service.impl;

import com.liferay.blade.workflow.asset.model.Qux;
import com.liferay.blade.workflow.asset.service.base.QuxLocalServiceBaseImpl;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the qux local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.blade.workflow.asset.service.QuxLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Inácio Nery
 * @see QuxLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.blade.workflow.asset.model.Qux",
	service = AopService.class
)
public class QuxLocalServiceImpl extends QuxLocalServiceBaseImpl {

	@Override
	public Qux addQux(long userId, long groupId, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Qux qux = quxPersistence.create(counterLocalService.increment());

		qux.setGroupId(groupId);
		qux.setCompanyId(user.getCompanyId());
		qux.setUserId(user.getUserId());
		qux.setUserName(user.getFullName());
		qux.setCreateDate(serviceContext.getCreateDate(null));
		qux.setModifiedDate(serviceContext.getModifiedDate(null));

		qux = quxPersistence.update(qux);

		assetEntryLocalService.updateEntry(
			userId, qux.getGroupId(), qux.getCreateDate(),
			qux.getModifiedDate(), Qux.class.getName(), qux.getQuxId(),
			qux.getUuid(), 0, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(), false, false, null, null, null,
			null, ContentTypes.TEXT, String.valueOf(qux.getPrimaryKey()), null,
			StringPool.BLANK, null, null, 0, 0,
			serviceContext.getAssetPriority());

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			qux.getCompanyId(), qux.getGroupId(), qux.getUserId(),
			Qux.class.getName(), qux.getPrimaryKey(), qux, serviceContext);

		return qux;
	}

	@Override
	public Qux deleteQux(long quxId) throws PortalException {
		Qux qux = quxPersistence.remove(quxId);

		assetEntryLocalService.deleteEntry(Qux.class.getName(), quxId);

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			qux.getCompanyId(), qux.getGroupId(), Qux.class.getName(),
			qux.getPrimaryKey());

		return qux;
	}

	@Override
	public Qux updateQux(long quxId, ServiceContext serviceContext)
		throws PortalException {

		Qux qux = quxPersistence.findByPrimaryKey(quxId);

		qux.setModifiedDate(serviceContext.getModifiedDate(null));

		qux = quxPersistence.update(qux);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(
			qux.getCompanyId(), qux.getGroupId(), qux.getUserId(),
			Qux.class.getName(), qux.getPrimaryKey(), qux, serviceContext);

		return qux;
	}

	@Override
	public Qux updateStatus(long userId, long quxId, int status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Qux qux = getQux(quxId);

		qux.setStatus(status);
		qux.setStatusByUserId(user.getUserId());
		qux.setStatusByUserName(user.getFullName());
		qux.setStatusDate(new Date());

		return updateQux(qux);
	}

}