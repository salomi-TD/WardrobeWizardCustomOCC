package org.training.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.dao.UserLoginDao;
import org.training.model.WardrobeWizardRegModel;


public class UserLoginDaoImpl implements UserLoginDao
{

	private static final Logger LOG = LoggerFactory.getLogger(UserLoginDaoImpl.class);

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	private static final String GET_EXISTING_USER = "SELECT {" + WardrobeWizardRegModel.PK + "} FROM {"
			+ WardrobeWizardRegModel._TYPECODE + "} WHERE {" + WardrobeWizardRegModel.MOBILENUMBER + "} = ?mobileNumber";

	@Override
	public Boolean checkUser(final String mobileNumber)
	{
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(GET_EXISTING_USER);
		searchQuery.addQueryParameter("mobileNumber", mobileNumber);

		final SearchResult<WardrobeWizardRegModel> results = getFlexibleSearchService().search(searchQuery);
		return results.getCount() > 0;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
