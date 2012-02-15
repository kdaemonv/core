/*
 * Copyright 2012 PrimeFaces Extensions.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Id$
 */

package org.primefaces.extensions.component.tristatecheckbox;

import java.util.ArrayList;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UINamingContainer;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import org.primefaces.component.api.Widget;

/**
 * TriStateCheckbox 
 *
 * @author  Mauricio Fenoglio / last modified by $Author$
 * @version $Revision$
 * @since   0.3
 */
@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="primefaces.css"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="primefaces.js"),
        @ResourceDependency(library = "primefaces-extensions", name = "primefaces-extensions.js")
})

public class TriStateCheckbox extends HtmlInputText implements Widget {

    public static final String COMPONENT_FAMILY = "org.primefaces.extensions.component";
    private static final String DEFAULT_RENDERER = "org.primefaces.extensions.component.";
    private static final String OPTIMIZED_PACKAGE = "org.primefaces.extensions.component.";

    protected enum PropertyKeys {

        widgetVar, itemLabel;
        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

        @Override
        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }

    public TriStateCheckbox() {
        setRendererType(DEFAULT_RENDERER);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getWidgetVar() {
        return (java.lang.String) getStateHelper().eval(TriStateCheckbox.PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(java.lang.String _widgetVar) {
        getStateHelper().put(TriStateCheckbox.PropertyKeys.widgetVar, _widgetVar);
        handleAttribute("widgetVar", _widgetVar);
    }

    public java.lang.String getItemLabel() {
        return (java.lang.String) getStateHelper().eval(TriStateCheckbox.PropertyKeys.itemLabel, null);
    }

    public void setItemLabel(java.lang.String _itemLabel) {
        getStateHelper().put(TriStateCheckbox.PropertyKeys.itemLabel, _itemLabel);
        handleAttribute("itemLabel", _itemLabel);
    }

    @Override
    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Override
    public String resolveWidgetVar() {
        FacesContext context = FacesContext.getCurrentInstance();
        String userWidgetVar = (String) getAttributes().get("widgetVar");

        if (userWidgetVar != null) {
            return userWidgetVar;
        } else {
            return "widget_" + getClientId(context).replaceAll("-|" + UINamingContainer.getSeparatorChar(context), "_");
        }
    }

    public void handleAttribute(String name, Object value) {
        List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
        if (setAttributes == null) {
            String cname = this.getClass().getName();
            if (cname != null && cname.startsWith(OPTIMIZED_PACKAGE)) {
                setAttributes = new ArrayList<String>(6);
                this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
            }
        }
        if (setAttributes != null) {
            if (value == null) {
                ValueExpression ve = getValueExpression(name);
                if (ve == null) {
                    setAttributes.remove(name);
                } else if (!setAttributes.contains(name)) {
                    setAttributes.add(name);
                }
            }
        }
    }
}
