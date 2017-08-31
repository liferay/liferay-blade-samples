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

package com.liferay.blade.samples.jdbcservicebuilder.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.blade.samples.jdbcservicebuilder.service.base.CountryLocalServiceBaseImpl;

/**
 * The implementation of the country local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.blade.samples.jdbcservicebuilder.service.CountryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CountryLocalServiceBaseImpl
 * @see com.liferay.blade.samples.jdbcservicebuilder.service.CountryLocalServiceUtil
 */
public class CountryLocalServiceImpl extends CountryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.blade.samples.jdbcservicebuilder.service.CountryLocalServiceUtil} to access the country local service.
	 */
	
	public void useJDBC() {
		try {
			DataSource datasource = countryPersistence.getDataSource();
			Connection connection = datasource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select id, name from country");

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