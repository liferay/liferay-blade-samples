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

package com.liferay.blade.samples.jndiservicebuilder.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.blade.samples.jndiservicebuilder.model.Region;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Region in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Region
 * @generated
 */
@ProviderType
public class RegionCacheModel implements CacheModel<Region>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegionCacheModel)) {
			return false;
		}

		RegionCacheModel regionCacheModel = (RegionCacheModel)obj;

		if (regionId == regionCacheModel.regionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, regionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{regionId=");
		sb.append(regionId);
		sb.append(", regionName=");
		sb.append(regionName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Region toEntityModel() {
		RegionImpl regionImpl = new RegionImpl();

		regionImpl.setRegionId(regionId);

		if (regionName == null) {
			regionImpl.setRegionName("");
		}
		else {
			regionImpl.setRegionName(regionName);
		}

		regionImpl.resetOriginalValues();

		return regionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		regionId = objectInput.readLong();
		regionName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(regionId);

		if (regionName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(regionName);
		}
	}

	public long regionId;
	public String regionName;
}