/*
// $Id: //open/mondrian/src/main/org.olap4j.type/SetType.java#4 $
// This software is subject to the terms of the Common Public License
// Agreement, available at the following URL:
// http://www.opensource.org/licenses/cpl.html.
// Copyright (C) 2005-2005 Julian Hyde
// All Rights Reserved.
// You must accept the terms of that agreement to use this software.
*/
package org.olap4j.type;

import org.olap4j.metadata.Dimension;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;

/**
 * Set type.
 *
 * @author jhyde
 * @since Feb 17, 2005
 * @version $Id: //open/mondrian/src/main/org.olap4j.type/SetType.java#4 $
 */
public class SetType implements Type {

    private final Type elementType;

    /**
     * Creates a type representing a set of elements of a given type.
     *
     * @param elementType The type of the elements in the set, or null if not
     *   known
     */
    public SetType(Type elementType) {
        assert elementType instanceof MemberType ||
                elementType instanceof TupleType;
        this.elementType = elementType;
    }

    /**
     * Returns the type of the elements of this set.
     */
    public Type getElementType() {
        return elementType;
    }

    public boolean usesDimension(Dimension dimension, boolean maybe) {
        if (elementType == null) {
            return maybe;
        }
        return elementType.usesDimension(dimension, maybe);
    }

    public Dimension getDimension() {
        return elementType == null ? null :
                elementType.getDimension();
    }

    public Hierarchy getHierarchy() {
        return elementType == null ? null :
                elementType.getHierarchy();
    }

    public Level getLevel() {
        return elementType == null ? null :
                elementType.getLevel();
    }
}

// End SetType.java