/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.editor.preferences;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

import com.archimatetool.editor.ArchiPlugin;


/**
 * System Preferences Page
 * 
 * Used as a top level parent replacement for Eclipse's "General" page (org.eclipse.ui.preferencePages.Workbench)
 * 
 * @author Phillip Beauvoir
 */
public class SystemPreferencesPage
extends PreferencePage
implements IWorkbenchPreferencePage {
    
    private static String HELP_ID = "com.archimatetool.help.prefsSystem"; //$NON-NLS-1$
    
	public SystemPreferencesPage() {
		setPreferenceStore(ArchiPlugin.PREFERENCES);
		setDescription(Messages.SystemPreferencesPage_0);
	}
	
    @Override
    protected Control createContents(Composite parent) {
        // Help
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, HELP_ID);
        
        Composite pageArea = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        pageArea.setLayout(layout);
        Dialog.applyDialogFont(pageArea);

        // Help
        addLinkArea(pageArea, "org.eclipse.help.ui.browsersPreferencePage"); //$NON-NLS-1$
        
        // Keys
        addLinkArea(pageArea, "com.archimatetool.editor.keys"); //$NON-NLS-1$
        
        // Metwork
        addLinkArea(pageArea, "com.archimatetool.editor.prefsNetwork"); //$NON-NLS-1$

        // Security
        addLinkArea(pageArea, "com.archimatetool.editor.security"); //$NON-NLS-1$
        
        // Browser
        addLinkArea(pageArea, "org.eclipse.ui.browser.preferencePage"); //$NON-NLS-1$
        
        return pageArea;
    }

    private PreferenceLinkArea addLinkArea(Composite parent, String pageId) {
        PreferenceLinkArea linkArea = new PreferenceLinkArea(parent, SWT.NONE, pageId,
                Messages.SystemPreferencesPage_1,
                (IWorkbenchPreferenceContainer) getContainer(), null);
        
        linkArea.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
        
        return linkArea;
    }
    
    @Override
    public void init(IWorkbench workbench) {
        noDefaultAndApplyButton();
    }
}