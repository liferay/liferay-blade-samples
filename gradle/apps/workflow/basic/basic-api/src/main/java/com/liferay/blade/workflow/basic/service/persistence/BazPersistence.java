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

package com.liferay.blade.workflow.basic.service.persistence;

import com.liferay.blade.workflow.basic.exception.NoSuchBazException;
import com.liferay.blade.workflow.basic.model.Baz;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the baz service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Inácio Nery
 * @see BazUtil
 * @generated
 */
@ProviderType
public interface BazPersistence extends BasePersistence<Baz> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BazUtil} to access the baz persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the bazs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching bazs
	 */
	public java.util.List<Baz> findByUuid(String uuid);

	/**
	 * Returns a range of all the bazs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @return the range of matching bazs
	 */
	public java.util.List<Baz> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the bazs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bazs
	 */
	public java.util.List<Baz> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Baz>
			orderByComparator);

	/**
	 * Returns an ordered range of all the bazs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bazs
	 */
	public java.util.List<Baz> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Baz> orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first baz in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching baz
	 * @throws NoSuchBazException if a matching baz could not be found
	 */
	public Baz findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Baz>
				orderByComparator)
		throws NoSuchBazException;

	/**
	 * Returns the first baz in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching baz, or <code>null</code> if a matching baz could not be found
	 */
	public Baz fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Baz>
			orderByComparator);

	/**
	 * Returns the last baz in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching baz
	 * @throws NoSuchBazException if a matching baz could not be found
	 */
	public Baz findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Baz>
				orderByComparator)
		throws NoSuchBazException;

	/**
	 * Returns the last baz in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching baz, or <code>null</code> if a matching baz could not be found
	 */
	public Baz fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Baz>
			orderByComparator);

	/**
	 * Returns the bazs before and after the current baz in the ordered set where uuid = &#63;.
	 *
	 * @param bazId the primary key of the current baz
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next baz
	 * @throws NoSuchBazException if a baz with the primary key could not be found
	 */
	public Baz[] findByUuid_PrevAndNext(
			long bazId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Baz>
				orderByComparator)
		throws NoSuchBazException;

	/**
	 * Removes all the bazs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of bazs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching bazs
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the baz where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchBazException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching baz
	 * @throws NoSuchBazException if a matching baz could not be found
	 */
	public Baz findByUUID_G(String uuid, long groupId)
		throws NoSuchBazException;

	/**
	 * Returns the baz where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching baz, or <code>null</code> if a matching baz could not be found
	 */
	public Baz fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the baz where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching baz, or <code>null</code> if a matching baz could not be found
	 */
	public Baz fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the baz where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the baz that was removed
	 */
	public Baz removeByUUID_G(String uuid, long groupId)
		throws NoSuchBazException;

	/**
	 * Returns the number of bazs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching bazs
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the bazs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching bazs
	 */
	public java.util.List<Baz> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the bazs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @return the range of matching bazs
	 */
	public java.util.List<Baz> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the bazs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bazs
	 */
	public java.util.List<Baz> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Baz>
			orderByComparator);

	/**
	 * Returns an ordered range of all the bazs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bazs
	 */
	public java.util.List<Baz> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Baz> orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first baz in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching baz
	 * @throws NoSuchBazException if a matching baz could not be found
	 */
	public Baz findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Baz>
				orderByComparator)
		throws NoSuchBazException;

	/**
	 * Returns the first baz in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching baz, or <code>null</code> if a matching baz could not be found
	 */
	public Baz fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Baz>
			orderByComparator);

	/**
	 * Returns the last baz in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching baz
	 * @throws NoSuchBazException if a matching baz could not be found
	 */
	public Baz findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Baz>
				orderByComparator)
		throws NoSuchBazException;

	/**
	 * Returns the last baz in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching baz, or <code>null</code> if a matching baz could not be found
	 */
	public Baz fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Baz>
			orderByComparator);

	/**
	 * Returns the bazs before and after the current baz in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param bazId the primary key of the current baz
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next baz
	 * @throws NoSuchBazException if a baz with the primary key could not be found
	 */
	public Baz[] findByUuid_C_PrevAndNext(
			long bazId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Baz>
				orderByComparator)
		throws NoSuchBazException;

	/**
	 * Removes all the bazs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of bazs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching bazs
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Caches the baz in the entity cache if it is enabled.
	 *
	 * @param baz the baz
	 */
	public void cacheResult(Baz baz);

	/**
	 * Caches the bazs in the entity cache if it is enabled.
	 *
	 * @param bazs the bazs
	 */
	public void cacheResult(java.util.List<Baz> bazs);

	/**
	 * Creates a new baz with the primary key. Does not add the baz to the database.
	 *
	 * @param bazId the primary key for the new baz
	 * @return the new baz
	 */
	public Baz create(long bazId);

	/**
	 * Removes the baz with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bazId the primary key of the baz
	 * @return the baz that was removed
	 * @throws NoSuchBazException if a baz with the primary key could not be found
	 */
	public Baz remove(long bazId) throws NoSuchBazException;

	public Baz updateImpl(Baz baz);

	/**
	 * Returns the baz with the primary key or throws a <code>NoSuchBazException</code> if it could not be found.
	 *
	 * @param bazId the primary key of the baz
	 * @return the baz
	 * @throws NoSuchBazException if a baz with the primary key could not be found
	 */
	public Baz findByPrimaryKey(long bazId) throws NoSuchBazException;

	/**
	 * Returns the baz with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param bazId the primary key of the baz
	 * @return the baz, or <code>null</code> if a baz with the primary key could not be found
	 */
	public Baz fetchByPrimaryKey(long bazId);

	/**
	 * Returns all the bazs.
	 *
	 * @return the bazs
	 */
	public java.util.List<Baz> findAll();

	/**
	 * Returns a range of all the bazs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @return the range of bazs
	 */
	public java.util.List<Baz> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the bazs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bazs
	 */
	public java.util.List<Baz> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Baz>
			orderByComparator);

	/**
	 * Returns an ordered range of all the bazs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BazModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bazs
	 * @param end the upper bound of the range of bazs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of bazs
	 */
	public java.util.List<Baz> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Baz> orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the bazs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of bazs.
	 *
	 * @return the number of bazs
	 */
	public int countAll();

}