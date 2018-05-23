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

package com.liferay.blade.samples.servicebuilder.adq.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.blade.samples.servicebuilder.adq.exception.NoSuchBarException;
import com.liferay.blade.samples.servicebuilder.adq.model.Bar;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the bar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.blade.samples.servicebuilder.adq.service.persistence.impl.BarPersistenceImpl
 * @see BarUtil
 * @generated
 */
@ProviderType
public interface BarPersistence extends BasePersistence<Bar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BarUtil} to access the bar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the bars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching bars
	*/
	public java.util.List<Bar> findByUuid(String uuid);

	/**
	* Returns a range of all the bars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	*/
	public java.util.List<Bar> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the bars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns an ordered range of all the bars where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first bar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the first bar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the last bar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the last bar in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the bars before and after the current bar in the ordered set where uuid = &#63;.
	*
	* @param barId the primary key of the current bar
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar[] findByUuid_PrevAndNext(long barId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Removes all the bars where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of bars where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching bars
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the bar where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBarException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByUUID_G(String uuid, long groupId)
		throws NoSuchBarException;

	/**
	* Returns the bar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the bar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the bar where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the bar that was removed
	*/
	public Bar removeByUUID_G(String uuid, long groupId)
		throws NoSuchBarException;

	/**
	* Returns the number of bars where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching bars
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the bars where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching bars
	*/
	public java.util.List<Bar> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the bars where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	*/
	public java.util.List<Bar> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the bars where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns an ordered range of all the bars where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first bar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the first bar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the last bar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the last bar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the bars before and after the current bar in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param barId the primary key of the current bar
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar[] findByUuid_C_PrevAndNext(long barId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Removes all the bars where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of bars where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching bars
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the bars where field1 = &#63;.
	*
	* @param field1 the field1
	* @return the matching bars
	*/
	public java.util.List<Bar> findByField1(String field1);

	/**
	* Returns a range of all the bars where field1 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param field1 the field1
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	*/
	public java.util.List<Bar> findByField1(String field1, int start, int end);

	/**
	* Returns an ordered range of all the bars where field1 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param field1 the field1
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByField1(String field1, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns an ordered range of all the bars where field1 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param field1 the field1
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByField1(String field1, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first bar in the ordered set where field1 = &#63;.
	*
	* @param field1 the field1
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByField1_First(String field1,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the first bar in the ordered set where field1 = &#63;.
	*
	* @param field1 the field1
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByField1_First(String field1,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the last bar in the ordered set where field1 = &#63;.
	*
	* @param field1 the field1
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByField1_Last(String field1,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the last bar in the ordered set where field1 = &#63;.
	*
	* @param field1 the field1
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByField1_Last(String field1,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the bars before and after the current bar in the ordered set where field1 = &#63;.
	*
	* @param barId the primary key of the current bar
	* @param field1 the field1
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar[] findByField1_PrevAndNext(long barId, String field1,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Removes all the bars where field1 = &#63; from the database.
	*
	* @param field1 the field1
	*/
	public void removeByField1(String field1);

	/**
	* Returns the number of bars where field1 = &#63;.
	*
	* @param field1 the field1
	* @return the number of matching bars
	*/
	public int countByField1(String field1);

	/**
	* Returns all the bars where field2 = &#63;.
	*
	* @param field2 the field2
	* @return the matching bars
	*/
	public java.util.List<Bar> findByField2(boolean field2);

	/**
	* Returns a range of all the bars where field2 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param field2 the field2
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	*/
	public java.util.List<Bar> findByField2(boolean field2, int start, int end);

	/**
	* Returns an ordered range of all the bars where field2 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param field2 the field2
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByField2(boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns an ordered range of all the bars where field2 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param field2 the field2
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByField2(boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first bar in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByField2_First(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the first bar in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByField2_First(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the last bar in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByField2_Last(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Returns the last bar in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByField2_Last(boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the bars before and after the current bar in the ordered set where field2 = &#63;.
	*
	* @param barId the primary key of the current bar
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar[] findByField2_PrevAndNext(long barId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException;

	/**
	* Removes all the bars where field2 = &#63; from the database.
	*
	* @param field2 the field2
	*/
	public void removeByField2(boolean field2);

	/**
	* Returns the number of bars where field2 = &#63;.
	*
	* @param field2 the field2
	* @return the number of matching bars
	*/
	public int countByField2(boolean field2);

	/**
	* Caches the bar in the entity cache if it is enabled.
	*
	* @param bar the bar
	*/
	public void cacheResult(Bar bar);

	/**
	* Caches the bars in the entity cache if it is enabled.
	*
	* @param bars the bars
	*/
	public void cacheResult(java.util.List<Bar> bars);

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public Bar create(long barId);

	/**
	* Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar remove(long barId) throws NoSuchBarException;

	public Bar updateImpl(Bar bar);

	/**
	* Returns the bar with the primary key or throws a {@link NoSuchBarException} if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar findByPrimaryKey(long barId) throws NoSuchBarException;

	/**
	* Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar, or <code>null</code> if a bar with the primary key could not be found
	*/
	public Bar fetchByPrimaryKey(long barId);

	@Override
	public java.util.Map<java.io.Serializable, Bar> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the bars.
	*
	* @return the bars
	*/
	public java.util.List<Bar> findAll();

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	*/
	public java.util.List<Bar> findAll(int start, int end);

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of bars
	*/
	public java.util.List<Bar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of bars
	*/
	public java.util.List<Bar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the bars from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}