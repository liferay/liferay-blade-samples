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

package com.liferay.blade.samples.jndiservicebuilder.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.blade.samples.jndiservicebuilder.service.base.RegionLocalServiceBaseImpl;

/**
 * The implementation of the region local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RegionLocalServiceBaseImpl
 * @see com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalServiceUtil
 */
public class RegionLocalServiceImpl extends RegionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalServiceUtil} to access the region local service.
	 */

	public void useJNDI() {
		try {
			DataSource datasource = regionPersistence.getDataSource();
			Connection connection = datasource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select id, name from region");

			while (resultSet.next()) {
				_logger.info("Record from external database:");
				String id = resultSet.getString("id");
				_logger.info("ID: " + id);
				String name = resultSet.getString("name");
				_logger.info("Name: " + name + System.lineSeparator());
			}
			
			connection.close();
		}
		catch (SQLException sqle) {
			_logger.error("Failed to retrieve data from external database!");

			sqle.printStackTrace();
		}
	}

	private Logger _logger = LoggerFactory.getLogger(this.getClass().getName());
}