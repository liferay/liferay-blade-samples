/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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