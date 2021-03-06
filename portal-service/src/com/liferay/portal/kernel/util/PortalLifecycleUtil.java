/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalLifecycleUtil {

	public static void flushDestroys() {
		_inFlushDestroys = true;

		for (int i = _portalLifecyclesDestroy.size() - 1; i >= 0; i--) {
			PortalLifecycle portalLifecycle = _portalLifecyclesDestroy.get(i);

			portalLifecycle.portalDestroy();
		}

		_portalLifecyclesDestroy.clear();

		_inFlushDestroys = false;
	}

	@SuppressWarnings("deprecation")
	public static void flushInits() {
		if (_portalLifecyclesInit != null) {
			List<PortalLifecycle> portalLifecyclesInit = _portalLifecyclesInit;

			_portalLifecyclesInit = null;

			for (PortalLifecycle portalLifecycle : portalLifecyclesInit) {
				portalLifecycle.portalInit();
			}
		}

		PortalInitableUtil.flushInitables();
	}

	public static void register(PortalLifecycle portalLifecycle) {
		register(portalLifecycle, PortalLifecycle.METHOD_ALL);
	}

	public static void register(PortalLifecycle portalLifecycle, int method) {
		if ((method == PortalLifecycle.METHOD_ALL) ||
			(method == PortalLifecycle.METHOD_INIT)) {

			if (_portalLifecyclesInit == null) {
				portalLifecycle.portalInit();
			}
			else {
				_portalLifecyclesInit.add(portalLifecycle);
			}
		}

		if ((method == PortalLifecycle.METHOD_ALL) ||
			(method == PortalLifecycle.METHOD_DESTROY)) {

			_portalLifecyclesDestroy.add(portalLifecycle);
		}
	}

	public static void removeDestroy(PortalLifecycle portalLifecycle) {
		if (!_inFlushDestroys) {
			_portalLifecyclesDestroy.remove(portalLifecycle);
		}
	}

	private static boolean _inFlushDestroys;
	private static List<PortalLifecycle> _portalLifecyclesDestroy =
		new ArrayList<PortalLifecycle>();
	private static List<PortalLifecycle> _portalLifecyclesInit =
		new ArrayList<PortalLifecycle>();

}