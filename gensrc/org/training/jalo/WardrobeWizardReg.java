/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 07-Nov-2023, 6:45:32 pm                     ---
 * ----------------------------------------------------------------
 */
package org.training.jalo;

import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type WardrobeWizardReg.
 */
@SLDSafe
@SuppressWarnings({"unused","cast"})
public class WardrobeWizardReg extends GenericItem
{
	/** Qualifier of the <code>WardrobeWizardReg.mobileNumber</code> attribute **/
	public static final String MOBILENUMBER = "mobileNumber";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(MOBILENUMBER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizardReg.mobileNumber</code> attribute.
	 * @return the mobileNumber
	 */
	public String getMobileNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "mobileNumber".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizardReg.mobileNumber</code> attribute.
	 * @return the mobileNumber
	 */
	public String getMobileNumber()
	{
		return getMobileNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizardReg.mobileNumber</code> attribute. 
	 * @param value the mobileNumber
	 */
	public void setMobileNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "mobileNumber".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizardReg.mobileNumber</code> attribute. 
	 * @param value the mobileNumber
	 */
	public void setMobileNumber(final String value)
	{
		setMobileNumber( getSession().getSessionContext(), value );
	}
	
}
