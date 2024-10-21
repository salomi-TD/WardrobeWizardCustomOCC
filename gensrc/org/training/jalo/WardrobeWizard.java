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
 * Generated class for type WardrobeWizard.
 */
@SLDSafe
@SuppressWarnings({"unused","cast"})
public class WardrobeWizard extends GenericItem
{
	/** Qualifier of the <code>WardrobeWizard.brandName</code> attribute **/
	public static final String BRANDNAME = "brandName";
	/** Qualifier of the <code>WardrobeWizard.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>WardrobeWizard.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>WardrobeWizard.category</code> attribute **/
	public static final String CATEGORY = "category";
	/** Qualifier of the <code>WardrobeWizard.currency</code> attribute **/
	public static final String CURRENCY = "currency";
	/** Qualifier of the <code>WardrobeWizard.gender</code> attribute **/
	public static final String GENDER = "gender";
	/** Qualifier of the <code>WardrobeWizard.matchingImage</code> attribute **/
	public static final String MATCHINGIMAGE = "matchingImage";
	/** Qualifier of the <code>WardrobeWizard.price</code> attribute **/
	public static final String PRICE = "price";
	/** Qualifier of the <code>WardrobeWizard.reducedPrice</code> attribute **/
	public static final String REDUCEDPRICE = "reducedPrice";
	/** Qualifier of the <code>WardrobeWizard.siteUrl</code> attribute **/
	public static final String SITEURL = "siteUrl";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(BRANDNAME, AttributeMode.INITIAL);
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		tmp.put(CURRENCY, AttributeMode.INITIAL);
		tmp.put(GENDER, AttributeMode.INITIAL);
		tmp.put(MATCHINGIMAGE, AttributeMode.INITIAL);
		tmp.put(PRICE, AttributeMode.INITIAL);
		tmp.put(REDUCEDPRICE, AttributeMode.INITIAL);
		tmp.put(SITEURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.brandName</code> attribute.
	 * @return the brandName
	 */
	public String getBrandName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "brandName".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.brandName</code> attribute.
	 * @return the brandName
	 */
	public String getBrandName()
	{
		return getBrandName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.brandName</code> attribute. 
	 * @param value the brandName
	 */
	public void setBrandName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "brandName".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.brandName</code> attribute. 
	 * @param value the brandName
	 */
	public void setBrandName(final String value)
	{
		setBrandName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.category</code> attribute.
	 * @return the category
	 */
	public String getCategory(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "category".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.category</code> attribute.
	 * @return the category
	 */
	public String getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.category</code> attribute. 
	 * @param value the category
	 */
	public void setCategory(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "category".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.category</code> attribute. 
	 * @param value the category
	 */
	public void setCategory(final String value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.currency</code> attribute.
	 * @return the currency
	 */
	public String getCurrency(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "currency".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.currency</code> attribute.
	 * @return the currency
	 */
	public String getCurrency()
	{
		return getCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "currency".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final String value)
	{
		setCurrency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "description".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "description".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.gender</code> attribute.
	 * @return the gender
	 */
	public String getGender(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "gender".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.gender</code> attribute.
	 * @return the gender
	 */
	public String getGender()
	{
		return getGender( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.gender</code> attribute. 
	 * @param value the gender
	 */
	public void setGender(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "gender".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.gender</code> attribute. 
	 * @param value the gender
	 */
	public void setGender(final String value)
	{
		setGender( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.id</code> attribute.
	 * @return the id
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "id".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.id</code> attribute.
	 * @return the id
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "id".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.matchingImage</code> attribute.
	 * @return the matchingImage
	 */
	public String getMatchingImage(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "matchingImage".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.matchingImage</code> attribute.
	 * @return the matchingImage
	 */
	public String getMatchingImage()
	{
		return getMatchingImage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.matchingImage</code> attribute. 
	 * @param value the matchingImage
	 */
	public void setMatchingImage(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "matchingImage".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.matchingImage</code> attribute. 
	 * @param value the matchingImage
	 */
	public void setMatchingImage(final String value)
	{
		setMatchingImage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.price</code> attribute.
	 * @return the price
	 */
	public String getPrice(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "price".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.price</code> attribute.
	 * @return the price
	 */
	public String getPrice()
	{
		return getPrice( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.price</code> attribute. 
	 * @param value the price
	 */
	public void setPrice(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "price".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.price</code> attribute. 
	 * @param value the price
	 */
	public void setPrice(final String value)
	{
		setPrice( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.reducedPrice</code> attribute.
	 * @return the reducedPrice
	 */
	public String getReducedPrice(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "reducedPrice".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.reducedPrice</code> attribute.
	 * @return the reducedPrice
	 */
	public String getReducedPrice()
	{
		return getReducedPrice( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.reducedPrice</code> attribute. 
	 * @param value the reducedPrice
	 */
	public void setReducedPrice(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "reducedPrice".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.reducedPrice</code> attribute. 
	 * @param value the reducedPrice
	 */
	public void setReducedPrice(final String value)
	{
		setReducedPrice( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.siteUrl</code> attribute.
	 * @return the siteUrl
	 */
	public String getSiteUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "siteUrl".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>WardrobeWizard.siteUrl</code> attribute.
	 * @return the siteUrl
	 */
	public String getSiteUrl()
	{
		return getSiteUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.siteUrl</code> attribute. 
	 * @param value the siteUrl
	 */
	public void setSiteUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "siteUrl".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>WardrobeWizard.siteUrl</code> attribute. 
	 * @param value the siteUrl
	 */
	public void setSiteUrl(final String value)
	{
		setSiteUrl( getSession().getSessionContext(), value );
	}
	
}
