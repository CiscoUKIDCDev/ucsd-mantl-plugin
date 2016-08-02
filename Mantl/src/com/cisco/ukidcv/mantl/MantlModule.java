package com.cisco.ukidcv.mantl;

import java.util.List;

import org.apache.log4j.Logger;

import com.cisco.ukidcv.mantl.account.MantlAccount;
import com.cisco.ukidcv.mantl.account.MantlAccountDB;
import com.cisco.ukidcv.mantl.account.MantlConvergedStackBuilder;
import com.cisco.ukidcv.mantl.account.handler.MantlConnectionHandler;
import com.cisco.ukidcv.mantl.account.handler.MantlInventoryItemHandler;
import com.cisco.ukidcv.mantl.account.handler.MantlInventoryListener;
import com.cisco.ukidcv.mantl.account.inventory.MantlInventory;
import com.cisco.ukidcv.mantl.constants.MantlConstants;
import com.cisco.ukidcv.mantl.exceptions.MantlAccountException;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.connector.ConfigItemDef;
import com.cloupia.lib.connector.account.AccountTypeEntry;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.lib.connector.account.PhysicalAccountTypeManager;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;
import com.cloupia.model.cIM.InfraAccount;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.AbstractCloupiaModule;
import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.CustomFeatureRegistry;
import com.cloupia.service.cIM.inframgr.collector.controller.CollectorFactory;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReport;

/**
 * This is the main entry point for the plugin.
 * <p>
 * Everything it can do is initialised here including tasks, reports, list of
 * values and so on.
 *
 * @author Matt Day
 *
 */
public class MantlModule extends AbstractCloupiaModule {

	private static Logger logger = Logger.getLogger(MantlModule.class);

	/**
	 * Provide a list of top-level reports to show in the summary/converged
	 * view. The order here is the order they'll show up in the GUI
	 */
	@Override
	public CloupiaReport[] getReports() {
		return new CloupiaReport[] {

		};
	}

	/**
	 * Provide a list of tasks to add to the orchestration engine
	 */
	@Override
	public AbstractTask[] getTasks() {
		return new AbstractTask[] {

		};
	}

	@Override
	public void onStart(CustomFeatureRegistry cfr) {

		try {

			// Register LOV inputs
			// cfr.registerTabularField(MantlConstants.ACCOUNT_LIST_FORM_PROVIDER,
			// MantlAccountSelector.class, "0", "0");

			// Register the account type drilldown
			ReportContextRegistry.getInstance().register(MantlConstants.INFRA_ACCOUNT_TYPE,
					MantlConstants.INFRA_ACCOUNT_LABEL);

			// You have to register each kind of report you want to use here or
			// they won't be able to drilldown
			/*
			 * ReportContextRegistry.getInstance().register(MantlConstants.
			 * ROOM_LIST_DRILLDOWN, MantlConstants.ROOM_LIST_DRILLDOWN_LABEL);
			 */

			/*
			 * ReportContextRegistry.getInstance().register(MantlConstants.
			 * TEAM_LIST_DRILLDOWN, MantlConstants.TEAM_LIST_DRILLDOWN_LABEL);
			 */
			/*
			 * ReportContextRegistry.getInstance().register(MantlConstants.
			 * INVENTORY_LIST_DRILLDOWN,
			 * MantlConstants.INVENTORY_LIST_DRILLDOWN_LABEL);
			 */

			// Register workflow inputs - this is done in a separate file
			// WorkflowInputTypeDeclaration.registerWFInputs();

			// Create the Mantl account type below
			this.createAccountType();

			/*
			 * Initialise account inventory. This is a bit convoluted.
			 *
			 * Step one is to loop through all accounts in UCS Director and find
			 * any that are Mantl accounts.
			 *
			 * Step two is to initialise the MantlInventory which will do an
			 * initial pull from the API server and cache it for reports
			 */
			try {
				final ObjStore<InfraAccount> store = ObjStoreHelper.getStore(InfraAccount.class);
				final List<InfraAccount> objs = store.queryAll();
				for (final InfraAccount a : objs) {
					final PhysicalInfraAccount acc = AccountUtil.getAccountByName(a.getAccountName());
					// Important to check if the account type is null first
					if ((acc != null) && (acc.getAccountType() != null)
							&& (acc.getAccountType().equals(MantlConstants.INFRA_ACCOUNT_TYPE))) {

						// Collect inventory
						final MantlAccount account = new MantlAccount(acc.getAccountName());
						MantlInventory.update(account, MantlConstants.INVENTORY_REASON_INITIAL, true);
					}

				}
			}
			catch (final Exception e) {
				logger.warn("Could not start initial inventory collection");
				logger.warn("Inventory collection failed: " + e.getMessage());
				throw new MantlAccountException(e.getMessage());
			}

		}
		catch (final Exception e) {
			logger.error("Error loading Mantl module.", e);
		}

	}

	// Create the plugin as an account in UCSD
	private void createAccountType() throws Exception {
		final AccountTypeEntry entry = new AccountTypeEntry();

		// Tell UCSD where and how to store account details
		entry.setCredentialClass(MantlAccountDB.class);

		// Internal account type
		entry.setAccountType(MantlConstants.INFRA_ACCOUNT_TYPE);

		// Account type label for the GUI
		entry.setAccountLabel(MantlConstants.INFRA_ACCOUNT_LABEL);

		// Type of account (can be storage, networking or compute) - this is
		// storage for now
		entry.setCategory(InfraAccountTypes.CAT_STORAGE);

		// Each account has its own context ID - set it here
		entry.setContextType(
				ReportContextRegistry.getInstance().getContextByName(MantlConstants.INFRA_ACCOUNT_TYPE).getType());

		// Make this a physical account - not sure if other types even work!
		entry.setAccountClass(AccountTypeEntry.PHYSICAL_ACCOUNT);

		// Task prefix, only used for authorised plugins
		entry.setInventoryTaskPrefix(MantlConstants.TASK_PREFIX);

		// Workflow category
		entry.setWorkflowTaskCategory(MantlConstants.WORKFLOW_CATEGORY);

		// Collection frequency - 60 minutes is the lowest you can do
		entry.setInventoryFrequencyInMins(60);

		// Pod types for this plugin - just allow Generic Pods for now
		entry.setPodTypes(new String[] {
				"GenericPod",
		});

		// This tests if the account credentials are OK or not when you try and
		// add it
		entry.setTestConnectionHandler(new MantlConnectionHandler());

		// Register an inventory listener to periodically poll
		entry.setInventoryListener(new MantlInventoryListener());

		// Add converged stack icon and detail
		entry.setConvergedStackComponentBuilder(new MantlConvergedStackBuilder());

		// You can edit this to allow Stack Designer support (not implemented
		// here!)
		// entry.setStackViewItemProvider(new FooStackViewProvider());

		// Add the plugin to the UCSD system:
		try {
			// Adding inventory root
			this.registerInventoryObjects(entry);
			PhysicalAccountTypeManager.getInstance().addNewAccountType(entry);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("static-method")
	private void registerInventoryObjects(AccountTypeEntry mantlRecoverPointAccountEntry) {
		@SuppressWarnings("unused")
		final ConfigItemDef mantlRecoverPointStateInfo = mantlRecoverPointAccountEntry
				.createInventoryRoot("Mantl.inventory.root", MantlInventoryItemHandler.class);
	}

	// This method is deprecated, so return null
	@Override
	@Deprecated
	public CollectorFactory[] getCollectors() {
		return null;
	}

}
