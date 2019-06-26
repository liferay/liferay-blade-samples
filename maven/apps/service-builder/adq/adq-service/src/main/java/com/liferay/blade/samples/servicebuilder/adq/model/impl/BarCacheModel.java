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

package com.liferay.blade.samples.servicebuilder.adq.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.blade.samples.servicebuilder.adq.model.Bar;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Bar in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Bar
 * @generated
 */
@ProviderType
public class BarCacheModel implements CacheModel<Bar>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BarCacheModel)) {
			return false;
		}

		BarCacheModel barCacheModel = (BarCacheModel)obj;

		if (barId == barCacheModel.barId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, barId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", barId=");
		sb.append(barId);
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
		sb.append(", field1=");
		sb.append(field1);
		sb.append(", field2=");
		sb.append(field2);
		sb.append(", field3=");
		sb.append(field3);
		sb.append(", field4=");
		sb.append(field4);
		sb.append(", field5=");
		sb.append(field5);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Bar toEntityModel() {
		BarImpl barImpl = new BarImpl();

		if (uuid == null) {
			barImpl.setUuid("");
		}
		else {
			barImpl.setUuid(uuid);
		}

		barImpl.setBarId(barId);
		barImpl.setGroupId(groupId);
		barImpl.setCompanyId(companyId);
		barImpl.setUserId(userId);

		if (userName == null) {
			barImpl.setUserName("");
		}
		else {
			barImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			barImpl.setCreateDate(null);
		}
		else {
			barImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			barImpl.setModifiedDate(null);
		}
		else {
			barImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (field1 == null) {
			barImpl.setField1("");
		}
		else {
			barImpl.setField1(field1);
		}

		barImpl.setField2(field2);
		barImpl.setField3(field3);

		if (field4 == Long.MIN_VALUE) {
			barImpl.setField4(null);
		}
		else {
			barImpl.setField4(new Date(field4));
		}

		if (field5 == null) {
			barImpl.setField5("");
		}
		else {
			barImpl.setField5(field5);
		}

		barImpl.resetOriginalValues();

		return barImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		barId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		field1 = objectInput.readUTF();

		field2 = objectInput.readBoolean();

		field3 = objectInput.readInt();
		field4 = objectInput.readLong();
		field5 = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(barId);

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

		if (field1 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(field1);
		}

		objectOutput.writeBoolean(field2);

		objectOutput.writeInt(field3);
		objectOutput.writeLong(field4);

		if (field5 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(field5);
		}
	}

	public String uuid;
	public long barId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String field1;
	public boolean field2;
	public int field3;
	public long field4;
	public String field5;
}