package org.primefaces.barcelona.component;

import javax.faces.component.UIComponentBase;

public class Tab extends UIComponentBase {

	public static final String COMPONENT_TYPE = "org.primefaces.component.BarcelonaTab";
	public static final String COMPONENT_FAMILY = "org.primefaces.component";

	public enum PropertyKeys {
		icon,
        title;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
        }
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getIcon() {
		return (String) getStateHelper().eval(PropertyKeys.icon, null);
	}
	public void setIcon(String _icon) {
		getStateHelper().put(PropertyKeys.icon, _icon);
	}
    
    public String getTitle() {
		return (String) getStateHelper().eval(PropertyKeys.title, null);
	}
	public void setTitle(String _title) {
		getStateHelper().put(PropertyKeys.title, _title);
	}
    
}