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

package com.liferay.blade.workflow.basic.model.impl;

import com.liferay.blade.workflow.basic.model.Baz;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Baz in entity cache.
 *
 * @author Inácio Nery
 * @generated
 */
@ProviderType
public class BazCacheModel implements CacheModel<Baz>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BazCacheModel)) {
			return false;
		}

		BazCacheModel bazCacheModel = (BazCacheModel)obj;

		if (bazId == bazCacheModel.bazId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, bazId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", bazId=");
		sb.append(bazId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Baz toEntityModel() {
		BazImpl bazImpl = new BazImpl();

		if (uuid == null) {
			bazImpl.setUuid("");
		}
		else {
			bazImpl.setUuid(uuid);
		}

		bazImpl.setBazId(bazId);
		bazImpl.setGroupId(groupId);
		bazImpl.setCompanyId(companyId);
		bazImpl.setUserId(userId);

		if (userName == null) {
			bazImpl.setUserName("");
		}
		else {
			bazImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			bazImpl.setCreateDate(null);
		}
		else {
			bazImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			bazImpl.setModifiedDate(null);
		}
		else {
			bazImpl.setModifiedDate(new Date(modifiedDate));
		}

		bazImpl.setStatus(status);
		bazImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			bazImpl.setStatusByUserName("");
		}
		else {
			bazImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			bazImpl.setStatusDate(null);
		}
		else {
			bazImpl.setStatusDate(new Date(statusDate));
		}

		bazImpl.resetOriginalValues();

		return bazImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		bazId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(bazId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public String uuid;
	public long bazId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}