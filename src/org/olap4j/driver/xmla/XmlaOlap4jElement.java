/*
// This software is subject to the terms of the Common Public License
// Agreement, available at the following URL:
// http://www.opensource.org/licenses/cpl.html.
// Copyright (C) 2007-2007 Julian Hyde
// All Rights Reserved.
// You must accept the terms of that agreement to use this software.
*/
package org.olap4j.driver.xmla;

import org.olap4j.impl.Named;
import org.olap4j.metadata.MetadataElement;

import java.util.Locale;

/**
 * Abstract implementation of {@link MetadataElement}
 * for XML/A providers.
 *
 * @author jhyde
 * @version $Id: $
 * @since Dec 5, 2007
 */
abstract class XmlaOlap4jElement implements MetadataElement, Named {
    protected final String uniqueName;
    protected final String name;
    protected final String caption;
    protected final String description;

    XmlaOlap4jElement(
        String uniqueName,
        String name,
        String caption,
        String description)
    {
        assert uniqueName != null;
        assert description != null;
        assert name != null;
        assert caption != null;
        this.description = description;
        this.uniqueName = uniqueName;
        this.caption = caption;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getCaption(Locale locale) {
        return caption;
    }

    public String getDescription(Locale locale) {
        return description;
    }
}

// End XmlaOlap4jElement.java