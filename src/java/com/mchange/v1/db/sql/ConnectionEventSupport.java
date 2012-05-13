/*
 * Distributed as part of mchange-commonslib v.0.2
 *
 * Copyright (C) 2010 Machinery For Change, Inc.
 *
 * Author: Steve Waldman <swaldman@mchange.com>
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 2.1, as 
 * published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; see the file LICENSE.  If not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 */


package com.mchange.v1.db.sql;

import java.util.*;
import java.sql.*;
import javax.sql.*;

/**
 * @deprecated use com.mchange.v2.c3p0.util.ConectionEventSupport
 */
public class ConnectionEventSupport
{
    PooledConnection source;
    Set              mlisteners = new HashSet();

    public ConnectionEventSupport(PooledConnection source)
    { this.source = source; }

    public synchronized void addConnectionEventListener(ConnectionEventListener mlistener)
    {mlisteners.add(mlistener);}

    public synchronized void removeConnectionEventListener(ConnectionEventListener mlistener)
    {mlisteners.remove(mlistener);}

    public synchronized void fireConnectionClosed()
    {
	ConnectionEvent evt = new ConnectionEvent(source);
	for (Iterator i = mlisteners.iterator(); i.hasNext();)
	    {
		ConnectionEventListener cl = (ConnectionEventListener) i.next();
		cl.connectionClosed(evt);
	    }
    }

    public synchronized void fireConnectionErrorOccurred(SQLException error)
    {
	ConnectionEvent evt = new ConnectionEvent(source, error);
	for (Iterator i = mlisteners.iterator(); i.hasNext();)
	    {
		ConnectionEventListener cl = (ConnectionEventListener) i.next();
		cl.connectionErrorOccurred(evt);
	    }
    }
}


