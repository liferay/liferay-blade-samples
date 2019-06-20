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

package com.liferay.blade.samples.servicebuilder.adq.service.persistence.impl;

import com.liferay.blade.samples.servicebuilder.adq.exception.NoSuchBarException;
import com.liferay.blade.samples.servicebuilder.adq.model.Bar;
import com.liferay.blade.samples.servicebuilder.adq.model.impl.BarImpl;
import com.liferay.blade.samples.servicebuilder.adq.model.impl.BarModelImpl;
import com.liferay.blade.samples.servicebuilder.adq.service.persistence.BarPersistence;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence implementation for the bar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class BarPersistenceImpl
	extends BasePersistenceImpl<Bar> implements BarPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BarUtil</code> to access the bar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BarImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the bars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching bars
	 */
	@Override
	public List<Bar> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of matching bars
	 */
	@Override
	public List<Bar> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bars
	 */
	@Override
	public List<Bar> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Bar> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bars where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bars
	 */
	@Override
	public List<Bar> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Bar> orderByComparator, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Bar> list = null;

		if (retrieveFromCache) {
			list = (List<Bar>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Bar bar : list) {
					if (!uuid.equals(bar.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_BAR_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(BarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first bar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByUuid_First(
			String uuid, OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		Bar bar = fetchByUuid_First(uuid, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the first bar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByUuid_First(
		String uuid, OrderByComparator<Bar> orderByComparator) {

		List<Bar> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByUuid_Last(
			String uuid, OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		Bar bar = fetchByUuid_Last(uuid, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the last bar in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByUuid_Last(
		String uuid, OrderByComparator<Bar> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Bar> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bars before and after the current bar in the ordered set where uuid = &#63;.
	 *
	 * @param barId the primary key of the current bar
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bar
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar[] findByUuid_PrevAndNext(
			long barId, String uuid, OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		uuid = Objects.toString(uuid, "");

		Bar bar = findByPrimaryKey(barId);

		Session session = null;

		try {
			session = openSession();

			Bar[] array = new BarImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, bar, uuid, orderByComparator, true);

			array[1] = bar;

			array[2] = getByUuid_PrevAndNext(
				session, bar, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Bar getByUuid_PrevAndNext(
		Session session, Bar bar, String uuid,
		OrderByComparator<Bar> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BAR_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(BarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(bar)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Bar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bars where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Bar bar :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(bar);
		}
	}

	/**
	 * Returns the number of bars where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching bars
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BAR_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "bar.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(bar.uuid IS NULL OR bar.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the bar where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchBarException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByUUID_G(String uuid, long groupId)
		throws NoSuchBarException {

		Bar bar = fetchByUUID_G(uuid, groupId);

		if (bar == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchBarException(msg.toString());
		}

		return bar;
	}

	/**
	 * Returns the bar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the bar where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof Bar) {
			Bar bar = (Bar)result;

			if (!Objects.equals(uuid, bar.getUuid()) ||
				(groupId != bar.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_BAR_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Bar> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					Bar bar = list.get(0);

					result = bar;

					cacheResult(bar);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByUUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Bar)result;
		}
	}

	/**
	 * Removes the bar where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the bar that was removed
	 */
	@Override
	public Bar removeByUUID_G(String uuid, long groupId)
		throws NoSuchBarException {

		Bar bar = findByUUID_G(uuid, groupId);

		return remove(bar);
	}

	/**
	 * Returns the number of bars where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching bars
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BAR_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"bar.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(bar.uuid IS NULL OR bar.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"bar.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the bars where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching bars
	 */
	@Override
	public List<Bar> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of matching bars
	 */
	@Override
	public List<Bar> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bars
	 */
	@Override
	public List<Bar> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Bar> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bars where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Override
	public List<Bar> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Bar> orderByComparator, boolean retrieveFromCache) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] {uuid, companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<Bar> list = null;

		if (retrieveFromCache) {
			list = (List<Bar>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Bar bar : list) {
					if (!uuid.equals(bar.getUuid()) ||
						(companyId != bar.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_BAR_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(BarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first bar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		Bar bar = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the first bar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByUuid_C_First(
		String uuid, long companyId, OrderByComparator<Bar> orderByComparator) {

		List<Bar> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		Bar bar = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the last bar in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByUuid_C_Last(
		String uuid, long companyId, OrderByComparator<Bar> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Bar> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public Bar[] findByUuid_C_PrevAndNext(
			long barId, String uuid, long companyId,
			OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		uuid = Objects.toString(uuid, "");

		Bar bar = findByPrimaryKey(barId);

		Session session = null;

		try {
			session = openSession();

			Bar[] array = new BarImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, bar, uuid, companyId, orderByComparator, true);

			array[1] = bar;

			array[2] = getByUuid_C_PrevAndNext(
				session, bar, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Bar getByUuid_C_PrevAndNext(
		Session session, Bar bar, String uuid, long companyId,
		OrderByComparator<Bar> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_BAR_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(BarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(bar)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Bar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bars where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Bar bar :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(bar);
		}
	}

	/**
	 * Returns the number of bars where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching bars
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_BAR_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"bar.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(bar.uuid IS NULL OR bar.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"bar.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByField2;
	private FinderPath _finderPathWithoutPaginationFindByField2;
	private FinderPath _finderPathCountByField2;

	/**
	 * Returns all the bars where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the matching bars
	 */
	@Override
	public List<Bar> findByField2(boolean field2) {
		return findByField2(field2, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of matching bars
	 */
	@Override
	public List<Bar> findByField2(boolean field2, int start, int end) {
		return findByField2(field2, start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bars
	 */
	@Override
	public List<Bar> findByField2(
		boolean field2, int start, int end,
		OrderByComparator<Bar> orderByComparator) {

		return findByField2(field2, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bars where field2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param field2 the field2
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching bars
	 */
	@Override
	public List<Bar> findByField2(
		boolean field2, int start, int end,
		OrderByComparator<Bar> orderByComparator, boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByField2;
			finderArgs = new Object[] {field2};
		}
		else {
			finderPath = _finderPathWithPaginationFindByField2;
			finderArgs = new Object[] {field2, start, end, orderByComparator};
		}

		List<Bar> list = null;

		if (retrieveFromCache) {
			list = (List<Bar>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Bar bar : list) {
					if ((field2 != bar.isField2())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_BAR_WHERE);

			query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(BarModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(field2);

				if (!pagination) {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first bar in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByField2_First(
			boolean field2, OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		Bar bar = fetchByField2_First(field2, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("field2=");
		msg.append(field2);

		msg.append("}");

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the first bar in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByField2_First(
		boolean field2, OrderByComparator<Bar> orderByComparator) {

		List<Bar> list = findByField2(field2, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last bar in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar
	 * @throws NoSuchBarException if a matching bar could not be found
	 */
	@Override
	public Bar findByField2_Last(
			boolean field2, OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		Bar bar = fetchByField2_Last(field2, orderByComparator);

		if (bar != null) {
			return bar;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("field2=");
		msg.append(field2);

		msg.append("}");

		throw new NoSuchBarException(msg.toString());
	}

	/**
	 * Returns the last bar in the ordered set where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bar, or <code>null</code> if a matching bar could not be found
	 */
	@Override
	public Bar fetchByField2_Last(
		boolean field2, OrderByComparator<Bar> orderByComparator) {

		int count = countByField2(field2);

		if (count == 0) {
			return null;
		}

		List<Bar> list = findByField2(
			field2, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the bars before and after the current bar in the ordered set where field2 = &#63;.
	 *
	 * @param barId the primary key of the current bar
	 * @param field2 the field2
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bar
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar[] findByField2_PrevAndNext(
			long barId, boolean field2,
			OrderByComparator<Bar> orderByComparator)
		throws NoSuchBarException {

		Bar bar = findByPrimaryKey(barId);

		Session session = null;

		try {
			session = openSession();

			Bar[] array = new BarImpl[3];

			array[0] = getByField2_PrevAndNext(
				session, bar, field2, orderByComparator, true);

			array[1] = bar;

			array[2] = getByField2_PrevAndNext(
				session, bar, field2, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Bar getByField2_PrevAndNext(
		Session session, Bar bar, boolean field2,
		OrderByComparator<Bar> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_BAR_WHERE);

		query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(BarModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(field2);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(bar)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Bar> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the bars where field2 = &#63; from the database.
	 *
	 * @param field2 the field2
	 */
	@Override
	public void removeByField2(boolean field2) {
		for (Bar bar :
				findByField2(
					field2, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(bar);
		}
	}

	/**
	 * Returns the number of bars where field2 = &#63;.
	 *
	 * @param field2 the field2
	 * @return the number of matching bars
	 */
	@Override
	public int countByField2(boolean field2) {
		FinderPath finderPath = _finderPathCountByField2;

		Object[] finderArgs = new Object[] {field2};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_BAR_WHERE);

			query.append(_FINDER_COLUMN_FIELD2_FIELD2_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(field2);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FIELD2_FIELD2_2 =
		"bar.field2 = ?";

	public BarPersistenceImpl() {
		setModelClass(Bar.class);

		setModelImplClass(BarImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(BarModelImpl.ENTITY_CACHE_ENABLED);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the bar in the entity cache if it is enabled.
	 *
	 * @param bar the bar
	 */
	@Override
	public void cacheResult(Bar bar) {
		entityCache.putResult(
			BarModelImpl.ENTITY_CACHE_ENABLED, BarImpl.class,
			bar.getPrimaryKey(), bar);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {bar.getUuid(), bar.getGroupId()}, bar);

		bar.resetOriginalValues();
	}

	/**
	 * Caches the bars in the entity cache if it is enabled.
	 *
	 * @param bars the bars
	 */
	@Override
	public void cacheResult(List<Bar> bars) {
		for (Bar bar : bars) {
			if (entityCache.getResult(
					BarModelImpl.ENTITY_CACHE_ENABLED, BarImpl.class,
					bar.getPrimaryKey()) == null) {

				cacheResult(bar);
			}
			else {
				bar.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all bars.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BarImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the bar.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Bar bar) {
		entityCache.removeResult(
			BarModelImpl.ENTITY_CACHE_ENABLED, BarImpl.class,
			bar.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((BarModelImpl)bar, true);
	}

	@Override
	public void clearCache(List<Bar> bars) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Bar bar : bars) {
			entityCache.removeResult(
				BarModelImpl.ENTITY_CACHE_ENABLED, BarImpl.class,
				bar.getPrimaryKey());

			clearUniqueFindersCache((BarModelImpl)bar, true);
		}
	}

	protected void cacheUniqueFindersCache(BarModelImpl barModelImpl) {
		Object[] args = new Object[] {
			barModelImpl.getUuid(), barModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, barModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		BarModelImpl barModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				barModelImpl.getUuid(), barModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((barModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				barModelImpl.getOriginalUuid(),
				barModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new bar with the primary key. Does not add the bar to the database.
	 *
	 * @param barId the primary key for the new bar
	 * @return the new bar
	 */
	@Override
	public Bar create(long barId) {
		Bar bar = new BarImpl();

		bar.setNew(true);
		bar.setPrimaryKey(barId);

		String uuid = PortalUUIDUtil.generate();

		bar.setUuid(uuid);

		bar.setCompanyId(companyProvider.getCompanyId());

		return bar;
	}

	/**
	 * Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar that was removed
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar remove(long barId) throws NoSuchBarException {
		return remove((Serializable)barId);
	}

	/**
	 * Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar that was removed
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar remove(Serializable primaryKey) throws NoSuchBarException {
		Session session = null;

		try {
			session = openSession();

			Bar bar = (Bar)session.get(BarImpl.class, primaryKey);

			if (bar == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBarException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(bar);
		}
		catch (NoSuchBarException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Bar removeImpl(Bar bar) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(bar)) {
				bar = (Bar)session.get(BarImpl.class, bar.getPrimaryKeyObj());
			}

			if (bar != null) {
				session.delete(bar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (bar != null) {
			clearCache(bar);
		}

		return bar;
	}

	@Override
	public Bar updateImpl(Bar bar) {
		boolean isNew = bar.isNew();

		if (!(bar instanceof BarModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(bar.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(bar);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in bar proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Bar implementation " +
					bar.getClass());
		}

		BarModelImpl barModelImpl = (BarModelImpl)bar;

		if (Validator.isNull(bar.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			bar.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (bar.getCreateDate() == null)) {
			if (serviceContext == null) {
				bar.setCreateDate(now);
			}
			else {
				bar.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!barModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				bar.setModifiedDate(now);
			}
			else {
				bar.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (bar.isNew()) {
				session.save(bar);

				bar.setNew(false);
			}
			else {
				bar = (Bar)session.merge(bar);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!BarModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {barModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				barModelImpl.getUuid(), barModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {barModelImpl.isField2()};

			finderCache.removeResult(_finderPathCountByField2, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByField2, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((barModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {barModelImpl.getOriginalUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {barModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((barModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					barModelImpl.getOriginalUuid(),
					barModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					barModelImpl.getUuid(), barModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((barModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByField2.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {barModelImpl.getOriginalField2()};

				finderCache.removeResult(_finderPathCountByField2, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByField2, args);

				args = new Object[] {barModelImpl.isField2()};

				finderCache.removeResult(_finderPathCountByField2, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByField2, args);
			}
		}

		entityCache.putResult(
			BarModelImpl.ENTITY_CACHE_ENABLED, BarImpl.class,
			bar.getPrimaryKey(), bar, false);

		clearUniqueFindersCache(barModelImpl, false);
		cacheUniqueFindersCache(barModelImpl);

		bar.resetOriginalValues();

		return bar;
	}

	/**
	 * Returns the bar with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the bar
	 * @return the bar
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBarException {

		Bar bar = fetchByPrimaryKey(primaryKey);

		if (bar == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBarException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return bar;
	}

	/**
	 * Returns the bar with the primary key or throws a <code>NoSuchBarException</code> if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar
	 * @throws NoSuchBarException if a bar with the primary key could not be found
	 */
	@Override
	public Bar findByPrimaryKey(long barId) throws NoSuchBarException {
		return findByPrimaryKey((Serializable)barId);
	}

	/**
	 * Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param barId the primary key of the bar
	 * @return the bar, or <code>null</code> if a bar with the primary key could not be found
	 */
	@Override
	public Bar fetchByPrimaryKey(long barId) {
		return fetchByPrimaryKey((Serializable)barId);
	}

	/**
	 * Returns all the bars.
	 *
	 * @return the bars
	 */
	@Override
	public List<Bar> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @return the range of bars
	 */
	@Override
	public List<Bar> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bars
	 */
	@Override
	public List<Bar> findAll(
		int start, int end, OrderByComparator<Bar> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the bars.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BarModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of bars
	 * @param end the upper bound of the range of bars (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of bars
	 */
	@Override
	public List<Bar> findAll(
		int start, int end, OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Bar> list = null;

		if (retrieveFromCache) {
			list = (List<Bar>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_BAR);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_BAR;

				if (pagination) {
					sql = sql.concat(BarModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Bar>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the bars from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Bar bar : findAll()) {
			remove(bar);
		}
	}

	/**
	 * Returns the number of bars.
	 *
	 * @return the number of bars
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_BAR);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "barId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_BAR;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BarModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the bar persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			BarModelImpl.UUID_COLUMN_BITMASK |
			BarModelImpl.FIELD1_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			BarModelImpl.UUID_COLUMN_BITMASK |
			BarModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			BarModelImpl.UUID_COLUMN_BITMASK |
			BarModelImpl.COMPANYID_COLUMN_BITMASK |
			BarModelImpl.FIELD1_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByField2 = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByField2",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByField2 = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, BarImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByField2",
			new String[] {Boolean.class.getName()},
			BarModelImpl.FIELD2_COLUMN_BITMASK |
			BarModelImpl.FIELD1_COLUMN_BITMASK);

		_finderPathCountByField2 = new FinderPath(
			BarModelImpl.ENTITY_CACHE_ENABLED,
			BarModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByField2",
			new String[] {Boolean.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(BarImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_BAR = "SELECT bar FROM Bar bar";

	private static final String _SQL_SELECT_BAR_WHERE =
		"SELECT bar FROM Bar bar WHERE ";

	private static final String _SQL_COUNT_BAR =
		"SELECT COUNT(bar) FROM Bar bar";

	private static final String _SQL_COUNT_BAR_WHERE =
		"SELECT COUNT(bar) FROM Bar bar WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "bar.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Bar exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Bar exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BarPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}