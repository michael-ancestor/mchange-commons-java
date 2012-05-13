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


package com.mchange.util.impl;

import com.mchange.util.IntChecklist;
import com.mchange.util.IntEnumeration;

public class HashIntChecklist implements IntChecklist
{
  private final static Object DUMMY = new Object();

  IntObjectHash ioh = new IntObjectHash();

  public void check(int num)
    {ioh.put(num, DUMMY);}

  public void uncheck(int num)
    {ioh.remove(num);}

  public boolean isChecked(int num)
    {return ioh.containsInt(num);}

  public void clear()
    {ioh.clear();}

  public int countChecked()
    {return ioh.getSize();}

  public int[] getChecked()
    {
      synchronized (ioh)
	{
	  int[] out = new int[ioh.getSize()];
	  IntEnumeration ints = ioh.ints();
	  for (int i = 0; ints.hasMoreInts(); ++i) out[i] = ints.nextInt();
	  return out;
	}
    }

  public IntEnumeration checked()
    {return ioh.ints();}

}