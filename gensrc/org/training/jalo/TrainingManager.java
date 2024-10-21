/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 07-Nov-2023, 6:45:32 pm                     ---
 * ----------------------------------------------------------------
 */
package org.training.jalo;

import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.HashMap;
import java.util.Map;
import org.training.constants.TrainingConstants;
import org.training.jalo.WardrobeWizard;
import org.training.jalo.WardrobeWizardReg;

/**
 * Generated class for type <code>TrainingManager</code>.
 */
@SuppressWarnings({"unused","cast"})
@SLDSafe
public class TrainingManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public WardrobeWizard createWardrobeWizard(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType("WardrobeWizard");
			return (WardrobeWizard)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating WardrobeWizard : "+e.getMessage(), 0 );
		}
	}
	
	public WardrobeWizard createWardrobeWizard(final Map attributeValues)
	{
		return createWardrobeWizard( getSession().getSessionContext(), attributeValues );
	}
	
	public WardrobeWizardReg createWardrobeWizardReg(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType("WardrobeWizardReg");
			return (WardrobeWizardReg)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating WardrobeWizardReg : "+e.getMessage(), 0 );
		}
	}
	
	public WardrobeWizardReg createWardrobeWizardReg(final Map attributeValues)
	{
		return createWardrobeWizardReg( getSession().getSessionContext(), attributeValues );
	}
	
	public static final TrainingManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (TrainingManager) em.getExtension(TrainingConstants.EXTENSIONNAME);
	}
	
	@Override
	public String getName()
	{
		return TrainingConstants.EXTENSIONNAME;
	}
	
}
